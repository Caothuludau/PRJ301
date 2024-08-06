/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author CaoThuLuDau
 */
public class BillDetail {
    private String billID;
    private String bookID;
    private int buyQuantity;
    private double buyPrice;
    private double subtotal;
    
    //For Cart
    public BillDetail(String bookID, int buyQuantity, double buyPrice, double subtotal) {
        this.bookID = bookID;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
        this.subtotal = subtotal;
    }
    

    public BillDetail(String billID, String bookID, int buyQuantity, double buyPrice) {
        this.billID = billID;
        this.bookID = bookID;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
    }

    public BillDetail(String billID, String bookID, int buyQuantity, double buyPrice, double subtotal) {
        this.billID = billID;
        this.bookID = bookID;
        this.buyQuantity = buyQuantity;
        this.buyPrice = buyPrice;
        this.subtotal = subtotal;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
