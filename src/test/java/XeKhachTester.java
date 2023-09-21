import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.XeKhach;
import com.myproject.services.XeKhachService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class XeKhachTester {
    private static Connection conn;
    private static final XeKhachService xkService = new XeKhachService();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @Test
    public void testGetXeKhachByInValidId(){
        XeKhach xk;
        try {
            xk = xkService.getXeKhachByMaXe(3);
            Assertions.assertNull(xk);
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetXeKhachById(){
        XeKhach xk;
        try {
            xk = xkService.getXeKhachByMaXe(1);
            Assertions.assertEquals("55-A23", xk.getBienSoXe());
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testUniqueId() throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM xekhach");

            List<String> kq = new ArrayList<>();
            while(rs.next()){
                String id = rs.getString("bienSoXe");
                kq.add(id);
            }

            Set<String> kq2 = new HashSet<>(kq);
            Assertions.assertEquals(kq.size(), kq2.size());
        }
    }
}

