/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class jdbcUtils {
    private static Connection conn;
     static {
        try {
            // B1 Nap driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(jdbcUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConn() throws SQLException {
        // B2 Thiet lap ket noi
        return DriverManager.getConnection("jdbc:mysql://localhost/oubus", "root", "Thanh@123");
    }
    
}
