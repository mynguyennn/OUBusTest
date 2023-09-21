package com.myproject.oubus;

import com.myproject.conf.Utils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.NhanVien;
import com.myproject.pojo.VeXe;
import com.myproject.pojo.XeKhach;
import com.myproject.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

import static com.myproject.oubus.LoginController.mnv;

public class ChangeTicketController {
    private int maChuyen;
    private String maVe;
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

    private static final ChuyenDiService cd = new ChuyenDiService();
    private static final XeKhachService xk = new XeKhachService();
    private static final TicketService tk = new TicketService();
    private static final AccountService ac = new AccountService();
    private static final NhanVienService nv = new NhanVienService();
    private static final BookingService bk = new BookingService();
    VeXe veXe;

    public void loadChangeTicket(int id, String ma) throws SQLException {
        this.maChuyen = id;
        this.maVe = ma;
        veXe = tk.getVeTheoMaVe(maVe);
        ChuyenDi a = cd.getChuyenDiByMaChuyenDi(maChuyen);
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
            this.listDaDat = tk.getVeTheoMa(this.maChuyen, null);
        } catch (SQLException ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setDisable các vé đã đặt
        this.listDaDat.forEach(v -> {
            for(int i = 0; i < listConTrong.size(); i++) {
                if (v.getViTriGhe().equals(listConTrong.get(i).getId()))
                    this.listConTrong.get(i).setDisable(true);
                if (veXe.getViTriGhe().equals(listConTrong.get(i).getId()))
                    this.listConTrong.get(i).setDisable(false);
            }
        });
        // Tính thời gian khởi hành và thời gian hiện tại
        long timeKhoiHanh = (a.getNgayKhoiHanh().getTime() + a.getGioKhoiHanh().getTime());
        long timeHienTai = System.currentTimeMillis();
        long s =  timeKhoiHanh - timeHienTai;
        sec = TimeUnit.MILLISECONDS.toMinutes(s)+480;

        //Thong tin ve
        txtMaVe.setText(veXe.getMaVe());
        txtHoVaTen.setText(veXe.getTenKhachHang());
        txtSDT.setText(veXe.getSdt());
        txtDiemDon.setText(veXe.getDiemDon());
        this.listDaDat.remove(veXe.getViTriGhe());
//        this.listDaDat.forEach(v -> {
//            for(int i = 0; i < listConTrong.size(); i++) {
//                if (veXe.getViTriGhe().equals(listConTrong.get(i).getId()))
//                    this.listConTrong.get(i).setId(veXe.getViTriGhe());
//            }
//        });
    }

    public void updateTicket(ActionEvent event) {
        if (sec > 60) {
            RadioButton selectedRadioButton = (RadioButton) Ghe.getSelectedToggle();
            if (this.txtHoVaTen.getText().length() != 0 && this.txtSDT.getText().length() != 0 && this.txtDiemDon.getText().length() != 0
                    && Ghe.getSelectedToggle() != null) {
                if (bk.checkSDT(this.txtSDT.getText())) {
                    String viTriGhe = selectedRadioButton.getText();
                    VeXe v = new VeXe(this.txtMaVe.getText(), this.txtHoVaTen.getText(), Date.valueOf(LocalDate.now()), this.txtSDT.getText(), Integer.parseInt(this.maChuyenDi.getText()), viTriGhe, "Đã đặt", mnv, 1, this.txtDiemDon.getText());
                    try {
                        if (bk.update(v)) {
                            Utils.getBox("Cập nhật thành công", Alert.AlertType.INFORMATION).show();
                            this.resetForm();
                            FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("MainStaffScreen.fxml"));
                            Scene scene = new Scene(fxmloader.load());
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
//                        this.loadBookingForm(id);
                        }
                    } catch (SQLException | IOException ex) {
                        Utils.getBox("Cập nhật thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                } else
                    Utils.getBox("Số điện thoại không hợp lệ !", Alert.AlertType.WARNING).show();
            } else
                Utils.getBox("Vui lòng nhập đầy đủ thông tin !", Alert.AlertType.WARNING).show();
        } else
            Utils.getBox("Đặt vé thất bại: Chỉ được Đặt vé trước 60 phút khi chuyến xe khởi hành.", Alert.AlertType.WARNING).show();
    }

    public void resetForm(){
        this.txtHoVaTen.setText("");
        this.txtSDT.setText("");
        this.txtDiemDon.setText("");
        this.txtMaVe.setText("");
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
}
