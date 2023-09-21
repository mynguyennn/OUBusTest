/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.VeXe;
import javafx.scene.control.Alert;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thanh
 */
public class ChuyenDiService {
    TicketService ticketService = new TicketService();

    public List<ChuyenDi> getChuyenDi(String diemKH, String diemKT) throws SQLException {
        List<ChuyenDi> chuyendi = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM chuyendi";
//            if (diemKH != null && !diemKH.isEmpty())
//                sql += " WHERE diemKhoiHanh like concat('%', ?, '%')";
//            if (diemKT != null && !diemKT.isEmpty())
//                sql += " AND diemKetThuc like concat('%', ?, '%')";
//            PreparedStatement stm = conn.prepareCall(sql);
//            if (diemKH != null && !diemKH.isEmpty())
//                stm.setString(1, diemKH);
//            if (diemKT != null && !diemKT.isEmpty())
//                stm.setString(2, diemKT);
            PreparedStatement stm = conn.prepareCall(sql);
            if (diemKH != null  && diemKT != null) {
                sql += " WHERE diemKhoiHanh like concat('%', ?, '%') AND diemKetThuc like concat('%', ?, '%')";
                stm = conn.prepareCall(sql);
                stm.setString(1, diemKH);
                stm.setString(2, diemKT);
            }
            else if (diemKH == null  && diemKT != null) {
                sql += " WHERE diemKetThuc like concat('%', ?, '%')";
                stm = conn.prepareCall(sql);
                stm.setString(1, diemKT);
            }
            else if (diemKH != null && diemKT == null ) {
                sql += " WHERE diemKhoiHanh like concat('%', ?, '%')";
                stm = conn.prepareCall(sql);
                stm.setString(1, diemKH);
            }
            else if (diemKH == null && diemKT == null) {
                stm = conn.prepareCall(sql);
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"),
                        rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"),
                        rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
                chuyendi.add(c);
            }
        }
        return chuyendi;
    }

    public ChuyenDi getChuyenDiByMaChuyenDi(int MaChuyenDi) throws SQLException {
        ChuyenDi result = null;
        try (Connection conn = jdbcUtils.getConn()) {

            List<ChuyenDi> dscd = new ArrayList<>();
            ChuyenDi chuyen = null;
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE  id = ?");
            stm.setInt(1, MaChuyenDi);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                result = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"), rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"), rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
            }
        }
        return result;
//            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi");
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                chuyen = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"), rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
//                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"), rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
//                dscd.add(chuyen);
//            }
//            for (ChuyenDi c : dscd) {
//                if (c.getMaChuyenDi() == MaChuyenDi) {
//                    PreparedStatement stmMa = conn.prepareCall("SELECT * FROM chuyendi WHERE  id = ?");
//                    stmMa.setInt(1, MaChuyenDi);
//
//                    ResultSet rsMa = stmMa.executeQuery();
//                    while (rsMa.next()) {
//                        result = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"), rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
//                                rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"), rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
//                    }
//                } else
//                    return null;
//            }
//            return result;

    }

    public boolean addTour(ChuyenDi c) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chuyendi(giaVe, ngayKhoiHanh, gioKhoiHanh, diemKhoiHanh, diemKetThuc, maXe) VALUES(?, ?, ?, ?, ?, ?)"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, c.getGiaVe());
            stm.setDate(2, c.getNgayKhoiHanh());
            stm.setTime(3, c.getGioKhoiHanh());
            stm.setString(4, c.getDiemKhoiHanh());
            stm.setString(5, c.getDiemKetThuc());
            stm.setInt(6, c.getMaXe());
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

    public boolean deleteTour(int id) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            ticketService.deleteListTicket(id);

            String sql = "DELETE FROM chuyendi WHERE id= ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            stm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateTour(int maxe, int maChuyenDi, int giaVe, String noiDi, String noiDen, Date ngayKH, String tg) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE chuyendi SET maXe=?, giaVe=?, diemKhoiHanh=?, diemKetThuc=?, ngayKhoiHanh=?,gioKhoiHanh=? WHERE id=?"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, maxe);
            stm.setInt(2, giaVe);
            stm.setString(3, noiDi);
            stm.setString(4, noiDen);
            stm.setDate(5, ngayKH);
            stm.setString(6, tg);
            stm.setInt(7, maChuyenDi);
            int r = stm.executeUpdate();
//            return r;
            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean updateTrangThaiTour(int maChuyenDi) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE chuyendi SET trangThai='Đã khởi hành' WHERE id=?"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, maChuyenDi);
            stm.executeUpdate();
            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public String checkDinhDangTime(String tgKH) {
        String gio, phut;
        //Kiem tra gio theo dinh danh hh:mm va kiem tra chuoi
        if (tgKH.length() == 5) {
            int viTri = tgKH.indexOf(":");
            if (viTri != -1) {
                gio = tgKH.substring(0,viTri);
                phut = tgKH.substring(viTri + 1, 5);
                return gio + ":" + phut ;
            }
            else {
                return " ";
            }
        }
        else if (tgKH.length() == 4) {
            int viTri = tgKH.indexOf(":");
            if (viTri != -1) {
                if (tgKH.substring(0,viTri).length() < 2) {
                    gio = "0" + tgKH.substring(0,viTri);
                    phut = tgKH.substring(viTri + 1, 4);
                    return gio + ":" + phut;
                }
                else if (tgKH.substring(0,viTri).length() == 2) {
                    gio = tgKH.substring(0,viTri);
                    //phut = tgKH.substring(viTri, 4);
                    phut = "0" + tgKH.substring(viTri + 1, 4);
                    return gio + ":" + phut;
                }
            }
            else {
                return " ";
            }
        }
        else if (tgKH.length() == 3) {
            int viTri = tgKH.indexOf(":");
            if (viTri != -1) {
                if (tgKH.substring(0,viTri).length() == 1 && tgKH.substring(viTri + 1, 3).length() == 1) {
                    gio = "0" + tgKH.substring(0,viTri);
                    phut = "0" + tgKH.substring(viTri + 1, 3);
                    return gio + ":" + phut;
                }
            }
            else {
                return " ";
            }
        }
        else if (tgKH.length() == 2) {
            return tgKH + ":00" ;
        }
        else if (tgKH.length() == 1) {
            return "0" + tgKH + ":00";
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add time failed");
            alert.show();
            return "";
        }
        return " ";
    }

    public boolean checkTimeKieuSo(String time) {
        try {
            Integer b = Integer.parseInt(time.substring(0, 1));
            if (time.length() < 6 && b % 1 == 0)
                return true;
            return false;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean checkGia(String gia) {
        try {
//            String g = gia.substring(0,1);
            Integer b = Integer.parseInt(gia.substring(0));
            if (gia.length() > 0 && gia.length() < 10 && b % 1 == 0 && b > 0)
                return true;
            return false;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

}
