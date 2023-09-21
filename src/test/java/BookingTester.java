
import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.VeXe;
import com.myproject.services.BookingService;
import com.myproject.services.TicketService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vbmho
 */
public class BookingTester {
    private static final BookingService bk = new BookingService();
    private static final TicketService tk = new TicketService();
    private static Connection conn;
    
    @BeforeAll
    public static void openConn() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    @AfterAll
    public static void closeConn() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @Test
    public void testTextField() throws SQLException{
        boolean actual = bk.checkTextField("","","");
        Assertions.assertFalse(actual);
    }
    @Test
    public void testTextField2() throws SQLException{
        boolean actual = bk.checkTextField("A","","");
        Assertions.assertFalse(actual);
    }
    @Test
    public void testTextField3() throws SQLException{
        boolean actual = bk.checkTextField("A","A","");
        Assertions.assertFalse(actual);
    }
    @Test
    public void testTextField4() throws SQLException{
        boolean actual = bk.checkTextField("A","A","A");
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testCheckSDT() throws SQLException{
        boolean actual = bk.checkSDT("0399987202");
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testCheckSDT2() throws SQLException{
        boolean actual = bk.checkSDT("5399987202");
        Assertions.assertFalse(actual);
    }
    
    @Test
    public void testCheckSDT3() throws SQLException{
        boolean actual = bk.checkSDT("987202");
        Assertions.assertFalse(actual);
    }
    
    @Test
    public void testAddTicket() throws SQLException{
        VeXe ve = new VeXe("5","Minh Hoang", Date.valueOf(LocalDate.now()) , "0399987202",
            7, "B02", "Đã đặt",
            2, 1, "Nha Trang");

        try {
            bk.addVeXe(ve);
            String MaChuyen = ve.getMaVe();
//            Assertions.assertTrue(actual);

            PreparedStatement stm = conn.prepareCall("SELECT * FROM vexe WHERE id=?");
            stm.setString(1, ve.getMaVe());

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(MaChuyen, rs.getString("id"));
            Assertions.assertEquals("Minh Hoang", rs.getString("tenKhachHang"));
        } catch (SQLException ex) {
            Logger.getLogger(BookingTester.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Assertions.assertFalse(actual);
    }

    @Test
    public void testUpdateTicket() throws SQLException{
        VeXe ve = new VeXe("2","Minh Hoang", Date.valueOf(LocalDate.now()) , "0399987202",
                7, "B02", "Đã đặt",
                2, 1, "Nha Trang");

        try {
            bk.update(ve);
            String MaChuyen = ve.getMaVe();
//            Assertions.assertTrue(actual);

            PreparedStatement stm = conn.prepareCall("SELECT * FROM vexe WHERE id=?");
            stm.setString(1, ve.getMaVe());

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(MaChuyen, rs.getString("id"));
            Assertions.assertEquals("Minh Hoang", rs.getString("tenKhachHang"));
        } catch (SQLException ex) {
            Logger.getLogger(BookingTester.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Assertions.assertFalse(actual);
    }

}
