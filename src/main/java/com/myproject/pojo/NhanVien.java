/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.pojo;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private String maLoaiNhanVien;
    private Date ngaySinh;
    private String soDienThoai;
    private String cMND;
    private String queQuan;
    private int maAccount;
    
    //
    public NhanVien(){
        
    }
    
    public NhanVien(int maNV, String tenNV, String maLoaiNV, Date nS, String sDT, 
            String cMND, String qQ, int maAccount){
        this.maNhanVien = maNV;
        this.tenNhanVien = tenNV;
        this.maLoaiNhanVien = maLoaiNV;
        this.ngaySinh = nS;
        this.soDienThoai = sDT;
        this.cMND = cMND;
        this.queQuan = qQ;
        this.maAccount = maAccount;
    }
    
    //
    
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
     * @return the tenNhanVien
     */
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    /**
     * @param tenNhanVien the tenNhanVien to set
     */
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    /**
     * @return the maLoaiNhanVien
     */
    public String getMaLoaiNhanVien() {
        return maLoaiNhanVien;
    }

    /**
     * @param maLoaiNhanVien the maLoaiNhanVien to set
     */
    public void setMaLoaiNhanVien(String maLoaiNhanVien) {
        this.maLoaiNhanVien = maLoaiNhanVien;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the soDienThoai
     */
    public String getSoDienThoai() {
        return soDienThoai;
    }

    /**
     * @param soDienThoai the soDienThoai to set
     */
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    /**
     * @return the cMND
     */
    public String getCMND() {
        return cMND;
    }

    /**
     * @param cMND the cMND to set
     */
    public void setCMND(String cMND) {
        this.cMND = cMND;
    }

    /**
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    /**
     * @return the maAccount
     */
    public int getMaAccount() {
        return maAccount;
    }

    /**
     * @param maAccount the maAccount to set
     */
    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }
}
