package com.myproject.oubus;

import com.myproject.conf.Utils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.VeXe;
import com.myproject.pojo.XeKhach;
import com.myproject.services.ChuyenDiService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.services.TicketService;
import com.myproject.services.XeKhachService;
import com.mysql.cj.protocol.Message;
import javafx.collections.FXCollections;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.*;
import java.time.format.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.collections.FXCollections.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Thanh
 */
public class ListTourAdminController implements Initializable {
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    static ChuyenDiService c = new ChuyenDiService();
    static XeKhachService x = new XeKhachService();
    static TicketService tk = new TicketService();
    @FXML private TableView<ChuyenDi> tableChuyenDi;
    
//    @FXML private TableColumn<ChuyenDi, String> maColumn;
//    
//    @FXML private TableColumn<ChuyenDi, String> noiDiColumn;
//    
//    @FXML private TableColumn<ChuyenDi, String> noiDenColumn;
//    
//    @FXML private TableColumn<ChuyenDi, String> ngayKhoiHanhColumn;
//    
//    @FXML private TableColumn<ChuyenDi, Double> giaColumn;
    
//    private ObservableList<ChuyenDi> tourList;
//    
    @FXML private TextField timKiem;
    @FXML private ComboBox<XeKhach> cbbmaXe;
    @FXML private TextField noiDiField;
    @FXML private TextField noiDenField;
    @FXML private DatePicker ngayKhoiHanhField;
    @FXML private TextField tgKhoiHanhField;
    @FXML private TextField giaVeField;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    @FXML private Text maChuyenDi;
    long sec = 0;
    
    StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ngayKhoiHanhField.setConverter(converter);
            ngayKhoiHanhField.setPromptText("dd-MM-yyyy");

            List<ChuyenDi> chuyendi = c.getChuyenDi(null, null);
            for(ChuyenDi cdi : chuyendi) {
                this.autoChangeTrangThai(cdi.getMaChuyenDi());
            }
            List<XeKhach> xeKhaches = x.getTenXeKhach();
            this.cbbmaXe.setItems(FXCollections.observableList(xeKhaches));

            this.loadTableColumns();
            this.loadTableData(null);

        } catch (SQLException ex) {
            Logger.getLogger(ListTourAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.timKiem.textProperty().addListener(e -> {
            try {
                this.loadTableData(this.timKiem.getText());
            } catch (SQLException ex) {
                Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    private void loadTableColumns() {
        TableColumn colMaChuyen = new TableColumn("Mã chuyến");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        
        TableColumn colMaXe = new TableColumn("Mã xe");
        colMaXe.setCellValueFactory(new PropertyValueFactory("maXe"));
        
        TableColumn colGiaVe = new TableColumn("Giá vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        
        TableColumn colNgayKhoiHanh = new TableColumn("Ngày Khởi Hành");
        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        colNgayKhoiHanh.setCellFactory(column -> {
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
        
        TableColumn colGioKhoiHanh = new TableColumn("Giờ khởi hành");
        colGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        
        TableColumn colDiemKhoiHanh = new TableColumn("Điểm khởi hành");
        colDiemKhoiHanh.setCellValueFactory(new PropertyValueFactory("diemKhoiHanh"));
        
        TableColumn colDiemKetThuc = new TableColumn("Điểm kết thúc");
        colDiemKetThuc.setCellValueFactory(new PropertyValueFactory("diemKetThuc"));
        
        TableColumn colSoGheTrong = new TableColumn("Ghế trống");
        colSoGheTrong.setCellValueFactory(new PropertyValueFactory("soGheTrong"));
        
        TableColumn colSoGheDat = new TableColumn("Ghế đặt");
        colSoGheDat.setCellValueFactory(new PropertyValueFactory("soGheDat"));
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
        this.tableChuyenDi.getColumns().addAll(colMaChuyen, colMaXe ,colGiaVe, 
                colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh, colDiemKetThuc,
                colSoGheTrong, colSoGheDat, colTrangThai);
    }
    
    private void loadTableData(String kw) throws SQLException {
        List<ChuyenDi> chuyendi = c.getChuyenDi(kw, null);
        
        this.tableChuyenDi.getItems().clear();
        this.tableChuyenDi.setItems(FXCollections.observableList(chuyendi));
    }
    
    
//    quay ve trang GD Nguoiquanly
    public void actionQuayVe(ActionEvent event) throws IOException {
         FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("MainAdminScreen.fxml"));
                                Scene scene = new Scene(fxmloader.load());
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                Button btn = (Button) event.getSource();
                                Stage stagelogin = (Stage) btn.getScene().getWindow();
                                stagelogin.close();
    }
    
    //them chuyen di
    public void add(ActionEvent e) throws SQLException {


        if (cbbmaXe.getSelectionModel().getSelectedItem() != null && this.noiDiField.getText().length() != 0 && this.noiDenField.getText().length() != 0
                && this.ngayKhoiHanhField.getValue() != null && this.tgKhoiHanhField.getText().length() != 0 && this.giaVeField.getText().length() != 0){
            String tgKH = tgKhoiHanhField.getText();
            // Tính thời gian khởi hành và thời gian hiện tại
            Date datekh = Date.valueOf(this.ngayKhoiHanhField.getValue());
            Time timekh = Time.valueOf(tgKH + ":00");
            long timeKhoiHanh = (datekh.getTime() + timekh.getTime());
            long timeHienTai = System.currentTimeMillis();
            long s =  timeKhoiHanh - timeHienTai;
            sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;

            if (sec > 0) {
                if (c.checkTimeKieuSo(tgKH)) {
                    tgKH = c.checkDinhDangTime(tgKH);
                    if (tgKH != " ") {
                        Time time = Time.valueOf(tgKH + ":00");
                        String gia = this.giaVeField.getText();
                        if (c.checkGia(gia)) {
                            ChuyenDi tour = new ChuyenDi(Integer.parseInt(giaVeField.getText()),
                                    noiDiField.getText(), noiDenField.getText(), Date.valueOf(ngayKhoiHanhField.getValue()),
                                    time, cbbmaXe.getSelectionModel().getSelectedItem().getMaXe());

                            try {
                                c.addTour(tour);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("Add successful");
                                alert.show();
                                loadTableData(null);
                                //            tableChuyenDi.ad;
                                cbbmaXe.setValue(null);
                                noiDiField.setText("");
                                noiDenField.setText("");
                                ngayKhoiHanhField.setValue(null);
                                tgKhoiHanhField.setText("");
                                giaVeField.setText("");
                                maChuyenDi.setText("");
                            } catch (SQLException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Add failed" + ex.getMessage());
                                alert.show();
                            }
                        } else {
                            Utils.getBox("Gía tiền không hợp lý!", Alert.AlertType.WARNING).show();
                        }
                    } else {
                        Utils.getBox("Giờ không hợp lệ!", Alert.AlertType.WARNING).show();
                    }
                } else {
                    Utils.getBox("Giờ không hợp lệ!", Alert.AlertType.WARNING).show();
                }
            }
            else {
                Utils.getBox("Giờ trong quá khứ!", Alert.AlertType.WARNING).show();
            }
        }
        else {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();
        }
    }
    
    //xoa chuyen di
    public void delete(ActionEvent e) throws SQLException {
        ChuyenDi t = tableChuyenDi.getSelectionModel().getSelectedItem();
        if (maChuyenDi.getText() != null && cbbmaXe.getSelectionModel().getSelectedItem() != null && this.noiDiField.getText().length() != 0 && this.noiDenField.getText().length() != 0
                && this.ngayKhoiHanhField.getValue() != null && this.tgKhoiHanhField.getText().length() != 0 && this.giaVeField.getText().length() != 0) {
            String gia = this.giaVeField.getText();
            if (c.checkGia(gia)) {
                List<VeXe> ves = tk.getVeTheoMa(Integer.parseInt(maChuyenDi.getText()), null);
                ChuyenDi cdi = c.getChuyenDiByMaChuyenDi(Integer.parseInt(maChuyenDi.getText()));
                if (ves.isEmpty() && cdi.getTrangThai().equals("Chua khoi hanh") || cdi.getTrangThai().equals("Đã khởi hành")) {
                    if (c.deleteTour(Integer.parseInt(maChuyenDi.getText())) == true) {
                        Utils.getBox("Delete successful", Alert.AlertType.INFORMATION).show();
                        this.loadTableData(null);
                        cbbmaXe.setValue(null);
                        noiDiField.setText("");
                        noiDenField.setText("");
                        ngayKhoiHanhField.setValue(null);
                        tgKhoiHanhField.setText("");
                        giaVeField.setText("");
                        maChuyenDi.setText("");
                    } else
                        Utils.getBox("Delete failed", Alert.AlertType.ERROR).show();
                }
                else {
                    Utils.getBox("Có vé xe tồn tại !", Alert.AlertType.ERROR).show();
                }
            }
            else {
                Utils.getBox("Gía tiền không hợp lý!", Alert.AlertType.WARNING).show();
            }
        }
        else {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();
        }
    }

    //lay du lieu tu table truyen xuong tung textbox
    public void getTour(MouseEvent event) {
        ChuyenDi t = tableChuyenDi.getSelectionModel().getSelectedItem();
        try {
            XeKhach xeKhach = x.getXeKhachByMaXe(t.getMaXe());
            cbbmaXe.setPromptText(xeKhach.getBienSoXe());
            noiDiField.setText(t.getDiemKhoiHanh());
            noiDenField.setText(t.getDiemKetThuc());
            ngayKhoiHanhField.setValue(LocalDate.parse(String.valueOf(t.getNgayKhoiHanh())));
            tgKhoiHanhField.setText(String.valueOf(t.getGioKhoiHanh()));
            giaVeField.setText(String.valueOf(t.getGiaVe()));
            maChuyenDi.setText(String.valueOf(t.getMaChuyenDi()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //cap nhat chuyen di
    public void update(ActionEvent e) throws SQLException {
                int maXe = cbbmaXe.getSelectionModel().getSelectedItem().getMaXe();
                String giaVe = giaVeField.getText();
                String noiDi = noiDiField.getText();
                String noiDen = noiDenField.getText();
                Date ngayKH = Date.valueOf(ngayKhoiHanhField.getValue());
                String tgKH = tgKhoiHanhField.getText();

//        int maXe = Integer.parseInt(maXeField.getText());
                int maChuyen = Integer.parseInt(this.maChuyenDi.getText());
        if (maChuyenDi.getText() != null  && this.noiDiField.getText().length() != 0 && this.noiDenField.getText().length() != 0
                && this.ngayKhoiHanhField.getValue() != null && this.tgKhoiHanhField.getText().length() != 0 && this.giaVeField.getText().length() != 0) {
            if (cbbmaXe.getSelectionModel().getSelectedItem() != null) {
                if (c.checkTimeKieuSo(tgKH)) {
                    tgKH = c.checkDinhDangTime(tgKH);
                    if (tgKH != " ") {
//                    Time time = Time.valueOf(tgKH + ":00");
                        tgKH = tgKH + ":00";
                        String tgKH1 = tgKhoiHanhField.getText();
                        // Tính thời gian khởi hành và thời gian hiện tại
                        Date datekh = Date.valueOf(this.ngayKhoiHanhField.getValue());
                        Time timekh = Time.valueOf(tgKH1 + ":00");
                        long timeKhoiHanh = (datekh.getTime() + timekh.getTime());
                        long timeHienTai = System.currentTimeMillis();
                        long s =  timeKhoiHanh - timeHienTai;
                        sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;
                        if (sec > 0) {
                            if (c.checkGia(giaVe)) {
                                if (c.updateTour(maXe, maChuyen, Integer.parseInt(giaVe), noiDi, noiDen, ngayKH, tgKH) == true) {
                                    Utils.getBox("Update successful", Alert.AlertType.INFORMATION).show();
                                    this.loadTableData(null);
                                    cbbmaXe.setValue(null);
                                    noiDiField.setText("");
                                    noiDenField.setText("");
                                    ngayKhoiHanhField.setValue(null);
                                    tgKhoiHanhField.setText("");
                                    giaVeField.setText("");
                                    maChuyenDi.setText("");
                                }
                                else
                                    Utils.getBox("Update failed", Alert.AlertType.ERROR).show();
                            }
                            else {
                                Utils.getBox("Gía tiền không hợp lý!", Alert.AlertType.WARNING).show();
                            }if (c.checkGia(giaVe)) {
                                if (c.updateTour(maXe, maChuyen, Integer.parseInt(giaVe), noiDi, noiDen, ngayKH, tgKH) == true) {
                                    Utils.getBox("Update successful", Alert.AlertType.INFORMATION).show();
                                    this.loadTableData(null);
                                    cbbmaXe.setValue(null);
                                    noiDiField.setText("");
                                    noiDenField.setText("");
                                    ngayKhoiHanhField.setValue(null);
                                    tgKhoiHanhField.setText("");
                                    giaVeField.setText("");
                                    maChuyenDi.setText("");
                                }
                                else
                                    Utils.getBox("Update failed", Alert.AlertType.ERROR).show();
                            }
                            else {
                                Utils.getBox("Gía tiền không hợp lý!", Alert.AlertType.WARNING).show();
                            }
                        }
                        else {
                            Utils.getBox("Giờ trong quá khứ!", Alert.AlertType.WARNING).show();
                        }
                    } else {
                        Utils.getBox("Giờ không hợp lệ!", Alert.AlertType.WARNING).show();
                    }
                } else {
                    Utils.getBox("Giờ không hợp lệ!", Alert.AlertType.WARNING).show();
                }
            }
            else
                Utils.getBox("Vui lòng chọn lại mã xe!", Alert.AlertType.WARNING).show();
        }
        else {
            Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();
        }
    }


    public boolean autoChangeTrangThai(int id) throws SQLException{
        boolean flag = false;
        ChuyenDi cd = c.getChuyenDiByMaChuyenDi(id);

        // Tính thời gian khởi hành và thời gian hiện tại
        long timeKhoiHanh = (cd.getNgayKhoiHanh().getTime() + cd.getGioKhoiHanh().getTime());
        long timeHienTai = System.currentTimeMillis();
        long s =  timeKhoiHanh - timeHienTai;
        long sec;
        sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;
        if (sec <= 0) {
            c.updateTrangThaiTour(id);
            flag = true;
        }
        return flag;
    }

    //Exit
    public void ActionLockOut(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("OuBus Login");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    
}