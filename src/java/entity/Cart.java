/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CaoThuLuDau
 */
public class Cart {
    private Map<String, BillDetail> cart;

    public Cart() {
    }

    public Cart(Map<String, BillDetail> cart) {
        this.cart = cart;
    }

    public Map<String, BillDetail> getCart() {
        return cart;
    }

    public void setCart(Map<String, BillDetail> cart) {
        this.cart = cart;
    }
    
    public void addBillDetail(BillDetail billDe) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(billDe.getBookID())) {
            int currentQuantity = this.cart.get(billDe.getBookID()).getBuyQuantity();
            billDe.setBuyQuantity(currentQuantity + billDe.getBuyQuantity());
        }
        cart.put(billDe.getBookID(), billDe);
    }
    
    public void updateBillDetail(BillDetail billDe) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(billDe.getBookID())) {
            this.cart.replace(billDe.getBookID(), billDe);
        }
    }
    
    public void deleteBillDetail(String id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }
}
