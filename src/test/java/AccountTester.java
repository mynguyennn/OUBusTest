/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.Account;
import com.myproject.pojo.VeXe;
import com.myproject.services.AccountService;
import com.mysql.cj.jdbc.JdbcConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;



public class AccountTester {
    private static final AccountService acService = new AccountService();
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
    public void testGetAccount() throws SQLException{
        Account ac = acService.getAccount("admin");
        Assertions.assertEquals(ac.getTaiKhoan(),"admin");
    }
    @Test
    public void testGetAccount2() throws SQLException{
        Account ac = acService.getAccount("staff");
        Assertions.assertEquals(ac.getTaiKhoan(),"staff");
    }
    
    @Test
    public void testGetInValidAccount() throws SQLException{
        Account ac = acService.getAccount("1");
        Assertions.assertNull(ac);
    }

    @Test
    public void testGetAccountById() throws SQLException{
        Account ac = acService.getAccountById(1);
        Assertions.assertEquals("admin", ac.getTaiKhoan());
        Account ac2 = acService.getAccountById(2);
        Assertions.assertEquals("staff", ac2.getTaiKhoan());
    }
    
    
}
