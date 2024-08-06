/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BillDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;

/**
 *
 * @author CaoThuLuDau
 */
public class DAOBillDetail extends DBConnect {

    public int addBillDetail(BillDetail billDe) {
        int n = 0;
        String sql = "USE [BookStore]\n"
                + "INSERT INTO [BillDetail]\n"
                + "           ([billID]\n"
                + "           ,[bookID]\n"
                + "           ,[buyQuantity]\n"
                + "           ,[buyPrice]\n"
                + "           ,[subtotal])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, billDe.getBillID());
            pre.setString(2, billDe.getBookID());
            pre.setDouble(3, billDe.getBuyQuantity());
            pre.setDouble(4, billDe.getBuyPrice());
            pre.setDouble(5, (double)billDe.getBuyQuantity()*billDe.getBuyPrice());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateBillDetail(BillDetail billDe) {
        int n = 0;
        String sql = "USE [BookStore]\n"
                + "UPDATE [dbo].[BillDetail]\n"
                + "   SET [buyQuantity] = ?\n"
                + "      ,[buyPrice] = ?\n"
                + "      ,[subtotal] = ?\n"
                + " WHERE [billID] = ? and [bookID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, billDe.getBuyQuantity());
            pre.setDouble(2, billDe.getBuyPrice());
            pre.setDouble(3, (double)billDe.getBuyQuantity()*billDe.getBuyPrice());
            pre.setString(4, billDe.getBillID());
            pre.setString(5, billDe.getBookID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public Vector<BillDetail> getAllBillDetail(String sql) {
        Vector<BillDetail> vector = new Vector<>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                String billID = rs.getString(1);
                String bookID = rs.getString(2);
                int buyQuantity = rs.getInt(3);
                double buyPrice = rs.getDouble(4);
                double subTotal = rs.getDouble(5);
                BillDetail billDetail = new BillDetail(billID, bookID, buyQuantity, buyPrice, subTotal);
                vector.add(billDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int removeBillDetail(String billID, String bookID) {
        int n = 0;
        String sql = "Delete from BillDetail where billID ='" + billID + "' and bookID ='" + bookID + "'";
        try {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
        DAOBillDetail dao = new DAOBillDetail();
        BillDetail billDe = new BillDetail("BI004", "B001", 12, 2000, 24000);
        dao.addBillDetail(billDe);
    }

}
