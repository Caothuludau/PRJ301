/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author CaoThuLuDau
 */
public class Bill {
    private String billID;
    private String dateCreate;
    private String recAddress;
    private String recPhone;
    private String note;
    private double totalMoney;
    private int status;
    private String cusID;

    //For insert
    public Bill(String billID, String recAddress, String recPhone, String note, double totalMoney, int status, String cusID) {
        this.billID = billID;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = status;
        this.cusID = cusID;
    }
    
    //For update
    public Bill(String billID, String dateCreate, String recAddress, String recPhone, String note, double totalMoney, int status, String cusID) {
        this.billID = billID;
        this.dateCreate = dateCreate;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = status;
        this.cusID = cusID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }
    
}
