/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.pojo;

import java.sql.Date;
import java.util.UUID;


/**
 *
 * @author dell
 */
public class VeXe {

    /**
     * @return the maVe
     */
    public String getMaVe() {
        return maVe;
    }

    /**
     * @param maVe the maVe to set
     */
    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    /**
     * @return the tenKhachHang
     */
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    /**
     * @param tenKhachHang the tenKhachHang to set
     */
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the viTriGhe
     */
    public String getViTriGhe() {
        return viTriGhe;
    }

    /**
     * @param viTriGhe the viTriGhe to set
     */
    public void setViTriGhe(String viTriGhe) {
        this.viTriGhe = viTriGhe;
    }

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the maChuyenDi
     */
    public int getMaChuyenDi() {
        return maChuyenDi;
    }

    /**
     * @param maChuyenDi the maChuyenDi to set
     */
    public void setMaChuyenDi(int maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    /**
     * @return the maNhanVien
     */
    public int getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the maDoanhThu
     */
    public int getMaDoanhThu() {
        return maDoanhThu;
    }

    /**
     * @param maDoanhThu the maDoanhThu to set
     */
    public void setMaDoanhThu(int maDoanhThu) {
        this.maDoanhThu = maDoanhThu;
    }
    private String maVe ;
    private String tenKhachHang;
    private Date ngayDat; 
    private String sdt;
    private String viTriGhe;
    private String trangThai;
    private String diemDon;
    private int maChuyenDi;
    private int maNhanVien;
    private int maDoanhThu;
        
    public VeXe() {
    }

    
    public VeXe(String tenKhachHang, Date ngayDat, String sdt, 
            int maChuyenDi, String viTriGhe, String trangThai, 
            int maNhanVien, int maDoanhThu, String diemDon) {
        this.maVe = UUID.randomUUID().toString();
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.sdt = sdt;
        this.maChuyenDi = maChuyenDi;
        this.viTriGhe = viTriGhe;
        this.trangThai = trangThai;
        this.maNhanVien = maNhanVien;
        this.maDoanhThu = maDoanhThu;
        this.diemDon = diemDon;
    }
    
    public VeXe(String id, String tenKhachHang, Date ngayDat, String sdt, 
            int maChuyenDi, String viTriGhe, String trangThai, 
            int maNhanVien, int maDoanhThu, String diemDon) {
        this.maVe = id;
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.sdt = sdt;
        this.maChuyenDi = maChuyenDi;
        this.viTriGhe = viTriGhe;
        this.trangThai = trangThai;
        this.maNhanVien = maNhanVien;
        this.maDoanhThu = maDoanhThu;
        this.diemDon = diemDon;
    }

    /**
     * @return the diemDon
     */
    public String getDiemDon() {
        return diemDon;
    }

    /**
     * @param diemDon the diemDon to set
     */
    public void setDiemDon(String diemDon) {
        this.diemDon = diemDon;
    }
}
    
    
    