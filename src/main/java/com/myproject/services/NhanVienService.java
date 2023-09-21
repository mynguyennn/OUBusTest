/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vbmho
 */
public class NhanVienService {
    public NhanVien getNhanVienByMaNV(int maNV) throws SQLException {
        NhanVien results = null;
        try (Connection conn = jdbcUtils.getConn()) {
                    PreparedStatement stm = conn.prepareCall("SELECT * FROM nhanvien WHERE id =?");
                    stm.setInt(1, maNV);

                    ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                results = new NhanVien(rs.getInt("id"),
                        rs.getString("TenNhanVien"), rs.getString("MaLoaiNhanVien"),
                        rs.getDate("NgaySinh"), rs.getString("SoDienThoai"),
                        rs.getString("CMND"), rs.getString("QueQuan"), rs.getInt("MaAccount"));
            }
        }
        return results;
    }
    
    public boolean addNhanVien(NhanVien nvien) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO nhanvien(id, tenNhanVien, maLoaiNhanVien, ngaySinh, soDienThoai, cMND, queQuan, maAccount) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, nvien.getMaNhanVien());
            stm.setString(2, nvien.getTenNhanVien());
            stm.setString(3,nvien.getMaLoaiNhanVien());
            stm.setDate(4, nvien.getNgaySinh());
            stm.setString(5, nvien.getSoDienThoai());
            stm.setString(6, nvien.getCMND());
            stm.setString(7, nvien.getCMND());
            stm.setInt(8, nvien.getMaAccount());
            int r = stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }
}
