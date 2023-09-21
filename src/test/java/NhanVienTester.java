/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.NhanVien;
import com.myproject.services.NhanVienService;
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

/**
 *
 * @author vbmho
 */
public class NhanVienTester {
    private static final NhanVienService nv = new NhanVienService();
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
    public void testAddNhanVien() throws SQLException {
        NhanVien nvien = new NhanVien(7, "Võ Bùi Minh Hoàng", "Tổng giám đốc", Date.valueOf(LocalDate.now()), "0399987202", "056202010094", "Nha Trang", 5);
        boolean actual = nv.addNhanVien(nvien);
        try {

            int maNhanVien = nvien.getMaNhanVien();

            Assertions.assertTrue(actual);


            PreparedStatement stm = conn.prepareCall("SELECT * FROM nhanvien WHERE id=?");
            stm.setInt(1, nvien.getMaNhanVien());

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(maNhanVien, rs.getInt("id"));
            Assertions.assertEquals("Võ Bùi Minh Hoàng", rs.getString("tenNhanVien"));
        } catch (SQLException ex) {
            Logger.getLogger(BookingTester.class.getName()).log(Level.SEVERE, null, ex);
            Assertions.assertFalse(actual);
        }

    }

    @Test
    public void testGetNhanVienByMaNV() throws SQLException{
        NhanVien nvien = nv.getNhanVienByMaNV(1);
        Assertions.assertEquals(nvien.getTenNhanVien(),"Lê Văn Lâm");
    }

}
