/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author CaoThuLuDau
 */
public class Customer {
    private String cusID, cusName ,username, password, address, phone;
    private int status;

    @Override
    public String toString() {
        return "Customer{" + "cusID=" + cusID + ", cusName=" + cusName + ", address=" + address + ", phone=" + phone + ", status=" + status + '}';
    }

    public Customer() {
    }    

    public Customer(String cusID, String cusName, String username, String password, String address, String phone, int status) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
}
