/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.conf.Utils;
import static com.myproject.oubus.LoginController.mnv;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.NhanVien;
import com.myproject.pojo.VeXe;
import com.myproject.pojo.XeKhach;
import com.myproject.services.BookingService;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.NhanVienService;
import com.myproject.services.TicketService;
import com.myproject.services.XeKhachService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class SellTicketController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    private int id;
    @FXML private TextField txtHoVaTen;
    @FXML private TextField txtSDT;
    @FXML private TextField txtDiemDon;
    @FXML private Label maChuyenDi;
    @FXML private Label diemKhoiHanh;
    @FXML private Label diemKetThuc;
    @FXML private Label gioKhoiHanh;
    @FXML private Label bienSoXe;
    @FXML private Label nhanVien;
    @FXML private Label giaVe;
    @FXML private Label time;
    @FXML private Label time1;
    String pattern = "dd/MM/yyyy HH:mm:ss";
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    @FXML private RadioButton A01;
    @FXML private RadioButton A02;
    @FXML private RadioButton A03;
    @FXML private RadioButton A04;
    @FXML private RadioButton A05;
    @FXML private RadioButton A06;
    @FXML private RadioButton A07;
    @FXML private RadioButton A08;
    @FXML private RadioButton A09;
    @FXML private RadioButton A10;
    @FXML private RadioButton B01;
    @FXML private RadioButton B02;
    @FXML private RadioButton B03;
    @FXML private RadioButton B04;
    @FXML private RadioButton B05;
    @FXML private RadioButton B06;
    @FXML private RadioButton B07;
    @FXML private RadioButton B08;
    @FXML private RadioButton B09;
    @FXML private RadioButton B10;
    @FXML ToggleGroup Ghe;
    private final List<RadioButton> listConTrong = new ArrayList<>();
    private List<VeXe> listDaDat = new ArrayList<>();
    long sec= 0 ;
    String maVe;

    public void setId(String id) {
        this.maVe = id;
    }
    
    private static final ChuyenDiService cd = new ChuyenDiService();
    private static final XeKhachService xk = new XeKhachService();
    private static final NhanVienService nv = new NhanVienService();
    private static final BookingService bk = new BookingService();
    private static final TicketService tk = new TicketService();  
    
         
    public void loadSellTicket(int id) throws SQLException {
        this.id = id;
        ChuyenDi a = cd.getChuyenDiByMaChuyenDi(id);
        this.maChuyenDi.setText(String.valueOf(a.getMaChuyenDi()));
        this.diemKhoiHanh.setText(a.getDiemKhoiHanh());
        this.diemKetThuc.setText(a.getDiemKetThuc());
        this.gioKhoiHanh.setText(String.valueOf(a.getGioKhoiHanh()));
        this.giaVe.setText(String.valueOf(a.getGiaVe()));
        XeKhach b = xk.getXeKhachByMaXe(a.getMaXe());
        this.bienSoXe.setText(b.getBienSoXe());
        NhanVien c = nv.getNhanVienByMaNV(b.getMaNhanVien());
        this.nhanVien.setText(c.getTenNhanVien());
        
        this.listConTrong.addAll(Arrays.asList(A01, A02, A03, A04, A05, A06, A07, A08, A09, A10, B01, B02, B03, B04, B05, B06, B07, B08, B09, B10));
        
        //Lấy danh sách vé đã đặt
        try {
            this.listDaDat = tk.getVeTheoMa(Integer.parseInt(this.maChuyenDi.getText()), null);
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setDisable các vé đã đặt
        this.listDaDat.forEach(v -> {
            for(int i = 0; i < listConTrong.size(); i++)
                if(v.getViTriGhe().equals(listConTrong.get(i).getId()))
                    this.listConTrong.get(i).setDisable(true);
        }); 
        // Tính thời gian khởi hành và thời gian hiện tại
        long timeKhoiHanh = (a.getNgayKhoiHanh().getTime() + a.getGioKhoiHanh().getTime());
        long timeHienTai = System.currentTimeMillis();
        long s =  timeKhoiHanh - timeHienTai;
        sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;
        
    }
    
    public void sellingHandler(ActionEvent event) throws IOException {
        if (sec > 5) {
            if (this.txtHoVaTen.getText().length() != 0 && this.txtSDT.getText().length() != 0 && this.txtDiemDon.getText().length() != 0 && Ghe.getSelectedToggle() != null) {
                if (bk.checkSDT(this.txtSDT.getText())) {
                    RadioButton selectedRadioButton = (RadioButton) Ghe.getSelectedToggle();
                    String viTriGhe = selectedRadioButton.getText();
                    VeXe v = new VeXe(this.txtHoVaTen.getText(), Date.valueOf(LocalDate.now()), this.txtSDT.getText(), Integer.parseInt(this.maChuyenDi.getText()), viTriGhe, "Đã xuất",mnv, 1, this.txtDiemDon.getText());
                    try {
                        bk.addVeXe(v);
                        Utils.getBox("Bán vé thành công", Alert.AlertType.INFORMATION).show();
                        this.resetForm();
                        this.loadSellTicket(id);
                        
                        Stage stage = new Stage();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("Ticket.fxml"));
                        Parent ticketView = loader.load();
                        Scene scene = new Scene(ticketView);
                        TicketController controller = loader.getController();
                        controller.loadTicket(v.getMaVe());
                        stage.setScene(scene);
                        
                        
                    } catch (SQLException ex) {
                        Utils.getBox("Bán vé thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                } else 
                    Utils.getBox("Số điện thoại không hợp lệ !", Alert.AlertType.WARNING).show();             
            } else 
                Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();       
        } else
            Utils.getBox("Bán thất bại: Chỉ được bán vé trước khi chuyến xe khởi hành 5 phút.", Alert.AlertType.WARNING).show(); 
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
    
    public void resetForm(){
        this.txtHoVaTen.setText("");
        this.txtSDT.setText("");
        this.txtDiemDon.setText("");
    }
    
}
