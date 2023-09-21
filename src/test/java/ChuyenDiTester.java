import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.VeXe;
import com.myproject.services.BookingService;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.TicketService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
//import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;


public class ChuyenDiTester {
    private static Connection conn;
    private static ChuyenDiService cd;
    private static TicketService ticket;
    private static final BookingService bk = new BookingService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        cd = new ChuyenDiService();
        ticket = new TicketService();
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

    //Truyen null
    @Test
    public void checkTimKiem() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi(null, null);
        List<ChuyenDi> dscd = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM chuyendi";
            PreparedStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"),
                        rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"),
                        rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
                dscd.add(c);
            }
            Assertions.assertEquals(dscd.size(), ds.size());
        }
    }

    //Truyen du lieu Diem khoi hanh
    @Test
    public void checkTimKiem2() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("HCM", null);
        Assertions.assertEquals(1, ds.size());
    }

    //Truyen du lieu Diem ket thuc
    @Test
    public void checkTimKiem3() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi(null, "Sai Gon");
        Assertions.assertEquals(1, ds.size());
    }

    //Truyen du lieu Diem khoi hanh va Diem ket thuc
    @Test
    public void checkTimKiem6() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("TPHCM", "Hue");
        Assertions.assertEquals(2, ds.size());
    }

    //Truyen ki tu dac biet
    @Test
    public void checkTimKiem4() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("   @TPHCM", null);
        Assertions.assertEquals(0, ds.size());
    }

    //Truyen du lieu khooang trang
    @Test
    public void checkTimKiem5() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("TP   HCM", null);
        Assertions.assertEquals(0, ds.size());
    }

    //chuyen theo ma am
    @Test
    public void checkMaChuyenDi() throws Exception {
        Assertions.assertNull(cd.getChuyenDiByMaChuyenDi(-1));
    }

    //chuyen theo ma duong
    @Test
    public void checkMaChuyenDi2() throws Exception {
        ChuyenDi c = cd.getChuyenDiByMaChuyenDi(27);
        Assertions.assertEquals(27, c.getMaChuyenDi());
    }

    //them chuyen
    @Test
    public void checkAddChuyenDi() throws ParseException {
        ChuyenDi c = new ChuyenDi(47, 1, 123000,
                Date.valueOf(LocalDate.now()), Time.valueOf("17:00" + ":00"),
                "An Giang", "Tien Giang", 20,
                0, "Chua khoi hanh");
        try {
            cd.addTour(c);
            int MaChuyen = c.getMaChuyenDi();
            String diemKH = c.getDiemKhoiHanh();
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id=?");
            stm.setInt(1, MaChuyen);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(MaChuyen, rs.getInt("id"));
            Assertions.assertEquals("An Giang", rs.getString("diemKhoiHanh"));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //truyen du lieu xoa chuyen di khong co ve chua khoi hanh
    @Test
    public void checkDeleteChuyenDi() {
        int id = 23;
        try {
            ChuyenDi c = new ChuyenDi(23, 2, 130000,
                    Date.valueOf(LocalDate.now()), Time.valueOf("17:00" + ":00"),
                    "Ben Tre", "Tien Giang", 20,
                    0, "Chua khoi hanh");
            cd.addTour(c);
            boolean actual = cd.deleteTour(id);
            Assertions.assertTrue(actual);

            String sql = "SELECT * FROM chuyendi WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());

            Assertions.assertTrue(ticket.deleteListTicket(id));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //truyen du lieu xoa chuyen di co ve chua khoi hanh
    @Test
    public void checkDeleteChuyenDi3() {
        int id = 23;
        try {
            ChuyenDi c = new ChuyenDi(23, 2, 130000,
                    Date.valueOf(LocalDate.now()), Time.valueOf("17:00" + ":00"),
                    "Ben Tre", "Tien Giang", 20,
                    0, "Chua khoi hanh");
            cd.addTour(c);
            VeXe ve = new VeXe("5","Minh Hoang", Date.valueOf(LocalDate.now()) , "0399987202",
                    23, "B02", "Đã đặt",
                    2, 1, "Nha Trang");
            bk.addVeXe(ve);
            boolean actual = cd.deleteTour(id);
            Assertions.assertFalse(actual);

            String sql = "SELECT * FROM chuyendi WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            Assertions.assertTrue(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Xoa chuyen di da khoi hanh va co ve
    @Test
    public void checkDeleteChuyenDi2() {
        int id = 47;
        try {
            VeXe ve = new VeXe("5","Minh Hoang", Date.valueOf(LocalDate.now()) , "0399987202",
                    47, "B02", "Đã đặt",
                    2, 1, "Nha Trang");
            bk.addVeXe(ve);
            boolean actual = cd.deleteTour(id);
            Assertions.assertTrue(actual);

            String sql = "SELECT * FROM chuyendi WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
            Assertions.assertTrue(ticket.deleteListTicket(47));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Xoa chuyen di da khoi hanh va khong co ve
    @Test
    public void checkDeleteChuyenDi4() {
        int id = 49;
        try {
            ChuyenDi c = new ChuyenDi(49, 2, 130000,
                    Date.valueOf(LocalDate.now()), Time.valueOf("17:00" + ":00"),
                    "Ben Tre", "Tien Giang", 20,
                    0, "Đã khởi hành");
            cd.addTour(c);
            boolean actual = cd.deleteTour(id);
            Assertions.assertTrue(actual);

            String sql = "SELECT * FROM chuyendi WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //update chuyen
    @Test
    public void checkUpdateChuyenDi() {
        boolean actual = false;
        try {
            actual = cd.updateTour(1, 61, 120000, "Hue", "Sai Gon", Date.valueOf(LocalDate.now()), String.valueOf(Time.valueOf("17:00" + ":00")));
            Assertions.assertTrue(actual);

            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id= ?");
            stm.setInt(1, 61);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(61, rs.getInt("id"));
            Assertions.assertEquals(Time.valueOf("17:00" + ":00"), rs.getTime("gioKhoiHanh"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update trang thai chuyen
    @Test
    public void testUpdateTrangThai() {
        boolean actual = false;
        try {
            actual = cd.updateTrangThaiTour(20);
            Assertions.assertTrue(actual);

            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id= ?");
            stm.setInt(1, 20);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(20, rs.getInt("id"));
            Assertions.assertEquals("Đã khởi hành", rs.getString("trangThai"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDinhDangTime() {
        boolean actual = true;
        String time = "1:2";
        Assertions.assertEquals("01:02",cd.checkDinhDangTime(time));
        String time1 = "12";
        Assertions.assertEquals("12:00",cd.checkDinhDangTime(time1));
        String time2 = "1";
        Assertions.assertEquals("01:00",cd.checkDinhDangTime(time2));
        try{
            cd.checkDinhDangTime("12:345");
            cd.checkDinhDangTime("12345");
        }
        catch (ExceptionInInitializerError IllegalStateException) {
            actual = false;
            Assertions.assertFalse(actual);
        }

    }

    @Test
    public void testTimeKieuSo() {
        boolean actual = true;
        Assertions.assertTrue(cd.checkTimeKieuSo("12:34"));
        Assertions.assertFalse(cd.checkTimeKieuSo("12:345"));
        Assertions.assertFalse(cd.checkTimeKieuSo("123:12"));
        Assertions.assertTrue(cd.checkTimeKieuSo("1:3"));
        try{
            cd.checkTimeKieuSo("ab:cx");
            cd.checkTimeKieuSo("abcdef");
        }
        catch (NumberFormatException ex){
            actual = false;
            Assertions.assertFalse(actual);
        }
    }

    @Test
    public void testCheckGia() {
        Assertions.assertTrue(cd.checkGia("125000"));
        Assertions.assertFalse(cd.checkGia("1250000000"));
        Assertions.assertFalse(cd.checkGia("nam tram nghin"));
        Assertions.assertFalse(cd.checkGia("-125000"));
        Assertions.assertFalse(cd.checkGia("5 nghin"));
    }

}

