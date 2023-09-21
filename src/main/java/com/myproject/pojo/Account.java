/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.pojo;

/**
 *
 * @author dell
 */
public class Account {
    private String taiKhoan;
    private String matKhau;
    private int maQuyen;
    private int id;

    public Account(){
    }

    public Account(int id, String taiKhoan, String matKhau, int maQuyen) {
        this.id=id;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.maQuyen = maQuyen;
    }
    
    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the maQuyen
     */
    public int getMaQuyen() {
        return maQuyen;
    }

    /**
     * @param maQuyen the maQuyen to set
     */
    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
