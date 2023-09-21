/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.pojo;

/**
 *
 * @author dell
 */
public class XeKhach {
    private int maXe;
    private String bienSoXe;
    private int soGhe;
    private int maNhanVien;

    public XeKhach() {
    }

    public XeKhach(int maXe, String BienSoXe, int soGhe, int MaNhanVien) {
        this.maXe = maXe;
        this.bienSoXe = BienSoXe;
        this.soGhe = soGhe;
        this.maNhanVien = MaNhanVien;
    }

    public XeKhach(int maXe, String bienSoXe) {
        this.maXe = maXe;
        this.bienSoXe = bienSoXe;
    }

    /**
     * @return the maXe
     */
    public Integer getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the BienSoXe
     */
    public String getBienSoXe() {
        return bienSoXe;
    }

    /**
     * @param BienSoXe the BienSoXe to set
     */
    public void setBienSoXe(String BienSoXe) {
        this.bienSoXe = BienSoXe;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
    
    @Override
    public String toString(){
        return String.valueOf(this.bienSoXe);
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
}
