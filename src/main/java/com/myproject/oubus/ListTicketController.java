/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.conf.Utils;
import static com.myproject.oubus.MainStaffScreenController.ticket;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.VeXe;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class ListTicketController implements Initializable {
    private int id;
    static TicketService tk = new TicketService();
    @FXML private TableView<VeXe> tableVeXe;
    @FXML private TextField txtTimKiem;
    private final List<VeXe> listVeXe = new ArrayList();
    String pattern = "dd/MM/yyyy HH:mm:ss";
    long sec;
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    private static final ChuyenDiService cd = new ChuyenDiService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
            loadTableTicketColumns();
        try {
            loadTableDataTicket(this.getId(), null);
        } catch (SQLException ex) {
            Logger.getLogger(ListTicketController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.getBox( ex.getMessage(), Alert.AlertType.WARNING).show();
            
        }

        this.txtTimKiem.textProperty().addListener(e -> {
            try {
                this.loadTableDataTicket(this.getId(), this.txtTimKiem.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ListTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void loadTableTicketColumns() {
        
        TableColumn colTen = new TableColumn("Ten");
        colTen.setCellValueFactory(new PropertyValueFactory("tenKhachHang"));
        colTen.setPrefWidth(200);
        
        TableColumn colSdt = new TableColumn("Số điện thoại");
        colSdt.setCellValueFactory(new PropertyValueFactory("sdt"));
        colSdt.setPrefWidth(200);
        
        
        TableColumn colNgayDat = new TableColumn("Ngày đặt");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
        colNgayDat.setCellFactory(column -> {
            TableCell<ChuyenDi, Date> cell = new TableCell<ChuyenDi, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) 
                        setText(null);
                    else 
                        this.setText(format.format(item));
                }
            };

            return cell;
        });
        colNgayDat.setPrefWidth(180);
        
        TableColumn colViTriGhe = new TableColumn("Số ghế");
        colViTriGhe.setCellValueFactory(new PropertyValueFactory("viTriGhe"));
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
        TableColumn colChiTiet = new TableColumn();
            colChiTiet.setCellFactory(evt -> {
                Button btn = new Button("Chi tiết");
                btn.setOnAction(e -> {
                try {
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    TableCell cell = (TableCell) btn.getParent();
                    VeXe q = (VeXe) cell.getTableRow().getItem();
                    try {   
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("Ticket.fxml"));
                        Parent ticketView = loader.load();
                        Scene scene = new Scene(ticketView);
                        TicketController controller = loader.getController();
                        controller.loadTicket(q.getMaVe());
                        stage.setScene(scene);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            TableCell cellChiTiet = new TableCell();
            cellChiTiet.setGraphic(btn);
            return cellChiTiet;
        });
        
        tableVeXe.getColumns().addAll(colTen, colSdt, colNgayDat, colViTriGhe, colTrangThai, colChiTiet);
    }
    
    public void loadTableDataTicket(int id, String kw) throws SQLException {
        this.setId(id);
        if(autoDeleteVeXe(this.id)){
                Utils.getBox("Hệ thống đã tự hủy các vé chưa được xuất", Alert.AlertType.WARNING).show();
        }
        List<VeXe> ve = ticket.getVeTheoMa(id, kw);
        this.tableVeXe.getItems().clear();
        this.tableVeXe.setItems(FXCollections.observableList(ve));
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public void actionQuayVe(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("MainStaffScreen.fxml"));
                                Scene scene = new Scene(fxmloader.load());
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                Button btn = (Button) event.getSource();
                                Stage stagelogin = (Stage) btn.getScene().getWindow();
                                stagelogin.close();
    }
    
    public boolean autoDeleteVeXe(int id) throws SQLException{
       boolean flag = false;
       this.listVeXe.addAll(tk.getVeTheoMa(this.getId(),null));
       for(VeXe v : this.listVeXe){  
           
           ChuyenDi c = cd.getChuyenDiByMaChuyenDi(v.getMaChuyenDi());
           // Tính thời gian khởi hành và thời gian hiện tại
            long timeKhoiHanh = (c.getNgayKhoiHanh().getTime() + c.getGioKhoiHanh().getTime());
            long timeHienTai = System.currentTimeMillis();
            long s =  timeKhoiHanh - timeHienTai;
            sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;
           if (sec <= 30) {
               if (v.getTrangThai().equals("Đã đặt")) {
                   tk.deleteTicket(v);
                   flag = true;
               }
           }
       }
       return flag;
    }
    
}
