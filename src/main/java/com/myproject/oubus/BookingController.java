/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.conf.Utils;
import static com.myproject.oubus.LoginController.mnv;
import static com.myproject.oubus.MainStaffScreenController.ticket;
import com.myproject.pojo.Account;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.NhanVien;
import com.myproject.pojo.VeXe;
import com.myproject.pojo.XeKhach;
import com.myproject.services.AccountService;
import com.myproject.services.BookingService;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.NhanVienService;
import com.myproject.services.TicketService;
import com.myproject.services.XeKhachService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import static java.time.LocalDate.now;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class BookingController implements Initializable {

    private int id;
    @FXML private TextField txtMaVe;
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
    @FXML private Label lbMaVe;
    @FXML private Label time1;
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
    String pattern = "dd/MM/yyyy HH:mm:ss";
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    String maVe;
    private VeXe veXe;
    int maChuyen;
    @FXML private Label labelMaVe;

    private static final ChuyenDiService cd = new ChuyenDiService();
    private static final XeKhachService xk = new XeKhachService();
    private static final NhanVienService nv = new NhanVienService();
    private static final BookingService bk = new BookingService();
    private static final TicketService tk = new TicketService();

    public void setId(String id) {
        this.maVe = id;
//        lbMaVe.setText(maVe);
    }

    public void setMaChuyen(int maChuyen) {
        this.maChuyen = maChuyen;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

         
    public void loadBookingForm(int id) throws SQLException {
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
            for(int i = 0; i < listConTrong.size(); i++) {
                if (v.getViTriGhe().equals(listConTrong.get(i).getId()))
                    this.listConTrong.get(i).setDisable(true);
            }
        }); 
        // Tính thời gian khởi hành và thời gian hiện tại
        long timeKhoiHanh = (a.getNgayKhoiHanh().getTime() + a.getGioKhoiHanh().getTime());
        long timeHienTai = System.currentTimeMillis();
        long s =  timeKhoiHanh - timeHienTai;
        sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;

        //Hien thong tin ve neu dat lai chuyen
        if (maVe != null)
        {
            veXe = tk.getVeTheoMaVe(maVe);
//            labelMaVe.setDisable(false);
//            txtMaVe.setDisable(false);
//            this.txtMaVe.setText(veXe.getMaVe());
            this.txtHoVaTen.setText(veXe.getTenKhachHang());
            this.txtSDT.setText(veXe.getSdt());
            this.txtDiemDon.setText(veXe.getDiemDon());
            tk.deleteTicket(veXe);
        }
    }

    public void bookingHandler(ActionEvent event) {
        if (sec > 60) {
            RadioButton selectedRadioButton = (RadioButton) Ghe.getSelectedToggle();
            if (this.txtHoVaTen.getText().length() != 0 && this.txtSDT.getText().length() != 0 && this.txtDiemDon.getText().length() != 0
                    && Ghe.getSelectedToggle() != null) {
                if (bk.checkSDT(this.txtSDT.getText())) {
                    String viTriGhe = selectedRadioButton.getText();
                    VeXe v = new VeXe(this.txtHoVaTen.getText(), Date.valueOf(LocalDate.now()), this.txtSDT.getText(), Integer.parseInt(this.maChuyenDi.getText()), viTriGhe, "Đã đặt", mnv, 1, this.txtDiemDon.getText());
                    try {

//                        if (this.txtMaVe.getText() == "") {
                            bk.addVeXe(v);
                            Utils.getBox("Đặt vé mới thành công", Alert.AlertType.INFORMATION).show();
                            this.resetForm();
                            this.loadBookingForm(id);
//                        }
//                        else {
//                            VeXe ve = new VeXe(this.txtMaVe.getText(),this.txtHoVaTen.getText(), Date.valueOf(LocalDate.now()), this.txtSDT.getText(), Integer.parseInt(this.maChuyenDi.getText()), viTriGhe, "Đã đặt", mnv, 1, this.txtDiemDon.getText());
//                            bk.update(ve);
//                            Utils.getBox("Đặt vé thành công", Alert.AlertType.INFORMATION).show();
//                            labelMaVe.setDisable(true);
//                            txtMaVe.setDisable(true);
//                            this.resetForm();
//                            this.loadBookingForm(id);
//                        }
                    } catch (SQLException ex) {
                        Utils.getBox("Đặt vé thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                } else 
                    Utils.getBox("Số điện thoại không hợp lệ !", Alert.AlertType.WARNING).show();             
            } else 
                Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();       
        } else
            Utils.getBox("Đặt vé thất bại: Chỉ được Đặt vé trước 60 phút khi chuyến xe khởi hành.", Alert.AlertType.WARNING).show(); 
    }

//    public void bookingHandler(ActionEvent event) {
//        if (sec > 60) {
//            RadioButton selectedRadioButton = (RadioButton) Ghe.getSelectedToggle();
//            if (this.txtHoVaTen.getText().length() != 0 && this.txtSDT.getText().length() != 0 && this.txtDiemDon.getText().length() != 0
//                    && Ghe.getSelectedToggle() != null) {
//                if (bk.checkSDT(this.txtSDT.getText())) {
//                    String viTriGhe = selectedRadioButton.getText();
//                    VeXe v = new VeXe( this.txtHoVaTen.getText(), Date.valueOf(LocalDate.now()), this.txtSDT.getText(), Integer.parseInt(this.maChuyenDi.getText()), viTriGhe, "Đã đặt", mnv, 1, this.txtDiemDon.getText());
//                    try {
//                        bk.addVeXe(v);
//                        Utils.getBox("Đặt vé thành công", Alert.AlertType.INFORMATION).show();
//                        this.resetForm();
//                        this.loadBookingForm(id);
//                    } catch (SQLException ex) {
//                        Utils.getBox("Đặt vé thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
//                    }
//                } else
//                    Utils.getBox("Số điện thoại không hợp lệ !", Alert.AlertType.WARNING).show();
//            } else
//                Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();
//        } else
//            Utils.getBox("Đặt vé thất bại: Chỉ được Đặt vé trước 60 phút khi chuyến xe khởi hành.", Alert.AlertType.WARNING).show();
//    }
    
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
