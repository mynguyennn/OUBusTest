import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.VeXe;
import com.myproject.services.BookingService;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.TicketService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketTester {
    private static Connection conn;
    private static TicketService ticket;
    private static BookingService bk;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        ticket = new TicketService();
        bk = new BookingService();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //ma co ds ve
    @Test
    public void checkGetVeTheoMa() {
        List<VeXe> dsve = new ArrayList<>();
        try {
            dsve = ticket.getVeTheoMa(16, null);
            Assertions.assertEquals(2,dsve.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ma k co ds ve
    @Test
    public void checkGetVeTheoMa2() {
        List<VeXe> dsve = new ArrayList<>();
        try {
            dsve = ticket.getVeTheoMa(22, null);
            Assertions.assertEquals(0, dsve.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ma k co ds chuyen
    @Test
    public void checkGetVeTheoMa3() {
        List<VeXe> dsve = new ArrayList<>();
        try {
            dsve = ticket.getVeTheoMa(700, null);
            Assertions.assertEquals(0,dsve.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Co ve
    @Test
    public void checkGetVeTheoMaVeXuatDS() {
        String id = "12f0c751-ac21-4f90-bc29-31ebf6373248";
        try {
            VeXe vx = ticket.getVeTheoMaVe(id);
            Assertions.assertNotNull(vx);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //K co ve
    @Test
    public void checkGetVeTheoMaVeXuatDS2() {
        String id = "12f0c751-ac21-4f9031ebf6373248";
        try {
            VeXe vx = ticket.getVeTheoMaVe(id);
            Assertions.assertNull(vx);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Co ma ve de xuat
    @Test
    public void checkExportTicket() {
        String id = "dasdjadjahlkafjlkajfkweu";
        try {
            VeXe veXe = ticket.getVeTheoMaVe(id);
            ticket.exportTicket(veXe);

            VeXe veXe1 = new VeXe();
            PreparedStatement stm = conn.prepareCall("SELECT * FROM vexe WHERE id=? ");
            stm.setString(1, "dasdjadjahlkafjlkajfkweu");
            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                veXe1 = new VeXe( rs.getString("id"),rs.getString("tenKhachHang"),
//                        rs.getDate("ngayDat"), rs.getString("sdt"),
//                        rs.getInt("maChuyenDi"), rs.getString("viTriGhe"),
//                        rs.getString("trangThai"),
//                        rs.getInt("maNhanVien"), rs.getInt("maDoanhThu"), rs.getString("diemDon"));
//            }
            Assertions.assertEquals("Đã xuất", rs.getString("trangThai"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Ma ve k co de xuat
    @Test
    public void checkExportTicket2() {
        String id = "dasdjadjafjlkajfkweu";
        Assertions.assertThrows(NullPointerException.class, () -> ticket.exportTicket(ticket.getVeTheoMaVe(id)));
    }

    //Xoa ma ve da co trong ds ve
    @Test
    public void checkDeleteTicket() {
        VeXe ve = new VeXe("6","Minh Hoang", Date.valueOf(LocalDate.now()) , "0399987202",
                100, "B02", "Đã đặt",
                2, 1, "Nha Trang");
        try {
            bk.addVeXe(ve);
            String id = "6";
            try {
                ticket.deleteTicket(ticket.getVeTheoMaVe(id));
                VeXe veXe = ticket.getVeTheoMaVe(id);
                Assertions.assertNull(veXe);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Xoa ma ve khong co trong ds ve
    @Test
    public void checkDeleteTicket2() {
        String id = "6";
        Assertions.assertThrows(NullPointerException.class, ()->ticket.deleteTicket(ticket.getVeTheoMaVe(id)));
    }

    //Xoa danh sach ve cua 1 chuyen xe
    @Test
    public void checkDeleteListTicket() {
        boolean actual = false;
        int id = 53;
        try {
            actual = ticket.deleteListTicket(53);
            Assertions.assertTrue(actual);

            List<VeXe> dsve = new ArrayList<>();
            VeXe vexe = new VeXe();
            PreparedStatement stm = conn.prepareCall("SELECT * FROM vexe");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                vexe = new VeXe( rs.getString("id"),rs.getString("tenKhachHang"),
                        rs.getDate("ngayDat"), rs.getString("sdt"),
                        rs.getInt("maChuyenDi"), rs.getString("viTriGhe"),
                        rs.getString("trangThai"),
                        rs.getInt("maNhanVien"), rs.getInt("maDoanhThu"), rs.getString("diemDon"));
                dsve.add(vexe);
            }
            for (VeXe ve : dsve){
                if (ve.getMaChuyenDi() == id) {
                    actual = false;
                }
            }
            Assertions.assertTrue(actual);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
