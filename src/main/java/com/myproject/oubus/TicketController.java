/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.conf.Utils;
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
import java.util.Arrays;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class TicketController implements Initializable {
    private int maChuyenDi;
    private String maVe;
    private String hoTen;
    private String SoDienThoai;
    @FXML private Label diemKhoiHanh;
    @FXML private Label diemKetThuc;
    @FXML private Label gioKhoiHanh;
    @FXML private Label bienSoXe;
    @FXML private Label nhanVien;
    @FXML private Label giaVe;
    @FXML private Label viTriGhe;
    @FXML private Label ten;
    @FXML private Label sdt;
    @FXML private Label diemDon;
    @FXML private Label ngayDat;
    long sec= 0 ;
    
    
    private static final ChuyenDiService cd = new ChuyenDiService();
    private static final XeKhachService xk = new XeKhachService();
    private static final TicketService tk = new TicketService(); 
    private static final AccountService ac = new AccountService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void loadTicket(String maVe) throws SQLException {
        this.maVe = maVe;
        VeXe veXe = tk.getVeTheoMaVe(maVe);
        this.hoTen = veXe.getTenKhachHang();
        this.SoDienThoai = veXe.getSdt();
        ChuyenDi chuyenDi = cd.getChuyenDiByMaChuyenDi(veXe.getMaChuyenDi());
        XeKhach xeKhach = xk.getXeKhachByMaXe(chuyenDi.getMaXe());
        Account account = ac.getAccountById(veXe.getMaNhanVien());
        this.diemKhoiHanh.setText(chuyenDi.getDiemKhoiHanh());
        this.diemKetThuc.setText(chuyenDi.getDiemKetThuc());
        this.gioKhoiHanh.setText(String.valueOf(chuyenDi.getGioKhoiHanh()));
        this.bienSoXe.setText(xeKhach.getBienSoXe());
        this.nhanVien.setText(account.getTaiKhoan());
        this.giaVe.setText(String.valueOf(chuyenDi.getGiaVe()));
        this.sdt.setText(veXe.getSdt());
        this.ten.setText(veXe.getTenKhachHang());
        this.diemDon.setText(veXe.getDiemDon());
        this.viTriGhe.setText(veXe.getViTriGhe());
        this.ngayDat.setText(String.valueOf(veXe.getNgayDat()));
        // Tính thời gian khởi hành và thời gian hiện tại
        long timeKhoiHanh = (chuyenDi.getNgayKhoiHanh().getTime() + chuyenDi.getGioKhoiHanh().getTime());
        long timeHienTai = System.currentTimeMillis();
        long s =  timeKhoiHanh - timeHienTai;
        this.sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;
    }

    public void actionExportTicket(ActionEvent event) throws SQLException, IOException {

        if (this.sec > 30) {
            VeXe veXe = tk.getVeTheoMaVe(this.maVe);
            if(veXe.getTrangThai().equals("Đã đặt")){
                tk.exportTicket(veXe);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirm Export");
                alert.setHeaderText("Xuất vé thành công");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    this.actionQuayVe(event);
                }
            }
            else{
                Utils.getBox("Vé đã xuất!", Alert.AlertType.WARNING).show(); 
            }
            

        }
        else {
            Utils.getBox("Vé đã được thu hồi!", Alert.AlertType.WARNING).show(); 
        }
    }

    public void actionDeleteTicket(ActionEvent event) throws SQLException, IOException {
        
        if (this.sec > 30) {
            VeXe veXe = tk.getVeTheoMaVe(this.maVe);
            if(veXe.getTrangThai().equals("Đã đặt")) {
                tk.deleteTicket(veXe);
           
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirm Delete");
                alert.setHeaderText("Hủy vé thành công");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {
                    this.actionQuayVe(event);
                }   
                
            }
            else {
                Utils.getBox("Vé đã xuất không thể hủy!", Alert.AlertType.WARNING).show();
            }
            

        }
        else {
            Utils.getBox("Hủy vé thất bại: Chỉ được hủy vé trước khi chuyến xe khởi hành 30 phút.", Alert.AlertType.WARNING).show(); 
        }
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
    
    public void actionChangeTicket(ActionEvent event) throws IOException, SQLException{
        
        if (this.sec > 30) {
            VeXe veXe = tk.getVeTheoMaVe(this.maVe);
//            if(veXe.getTrangThai().equals("Đã đặt")) {
//                tk.deleteTicket(veXe);
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Confirm change");
//                alert.setHeaderText("Hủy vé thành công");
//
//                Optional<ButtonType> option = alert.showAndWait();
//
//                if (option.get() == ButtonType.OK) {
//                    this.actionQuayVe(event);
//                }
//            }
//            else {
//                Utils.getBox("Vé đã xuất không thể đổi!", Alert.AlertType.WARNING).show();
//            }
            try {
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ChangeTicket.fxml"));
                Parent bookingView = loader.load();
                Scene scene = new Scene(bookingView);
                ChangeTicketController controller = loader.getController();
                int id = veXe.getMaChuyenDi();
                controller.loadChangeTicket(id, maVe);
                stage.setScene(scene);
            } catch (SQLException ex) {
                Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            Utils.getBox("Đổi vé thất bại: Chỉ được hủy vé trước khi chuyến xe khởi hành 60 phút.", Alert.AlertType.WARNING).show(); 
        }                      
    }

    public void actionChangeTour(ActionEvent event) throws SQLException {
        if (this.sec > 60) {
            VeXe veXe = tk.getVeTheoMaVe(this.maVe);
            try {
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainStaffScreen.fxml"));
                Parent bookingView = loader.load();
                Scene scene = new Scene(bookingView);
                MainStaffScreenController controller = loader.getController();
//                controller.maChuyen = veXe.getMaChuyenDi();
                controller.setId(veXe.getMaVe());
                controller.setMaChuyen(veXe.getMaChuyenDi());
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            Utils.getBox("Đổi vé thất bại: Chỉ được hủy vé trước khi chuyến xe khởi hành 60 phút.", Alert.AlertType.WARNING).show();
        }
    }
}
