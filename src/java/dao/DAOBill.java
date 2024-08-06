/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bill;
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
public class DAOBill extends DBConnect {
    
    public int addBill(Bill bil){ //ByPre
        int n = 0;
        String sql = "INSERT INTO [Bill]\n"
                + "           ([billID]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[totalMoney]\n"
                + "           ,[note]\n"
                + "           ,[cusID]\n"
                + "           ,[status])\n"
                + "     VALUES(?,?,?,?,?,?,?)"; 
                //The '?' symbol represents a placeholder for a parameter in a prepared statement.
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //Set parameter
            pre.setString(1, bil.getBillID());
            pre.setString(2, bil.getRecAddress());
            pre.setString(3, bil.getRecPhone());
            pre.setDouble(4, bil.getTotalMoney());
            pre.setString(5, bil.getNote());
            pre.setString(6, bil.getCusID());
            pre.setInt(7, bil.getStatus());
            //Run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int updateBill(Bill bil){
        int n = 0;
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [dateCreate] = ?\n"
                + "      ,[recAddress] = ?\n"
                + "      ,[recPhone] = ?\n"
                + "      ,[totalMoney] = ?\n"
                + "      ,[note] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cusID] = ?\n"
                + " WHERE [billID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, bil.getDateCreate());
            pre.setString(2, bil.getRecAddress());
            pre.setString(3, bil.getRecPhone());
            pre.setDouble(4, bil.getTotalMoney());
            pre.setString(5, bil.getNote());
            pre.setInt(6, bil.getStatus());
            pre.setString(7, bil.getCusID());
            pre.setString(8, bil.getBillID());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
    public Vector<Bill> getAllBill(String sql){
        Vector<Bill> vector = new Vector<Bill>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Bill");
        try {
            while (rs.next()) {
                String billID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String recAddress = rs.getString(3);
                String recPhone = rs.getString(4);
                String note = rs.getString(5);
                double totalMoney = rs.getDouble(6);
                int sta = rs.getInt(7);
                String cusID = rs.getString(8);
                
                Bill bil = new Bill(billID, dateCreate, recAddress, recPhone,
                         note, totalMoney, sta, cusID);
                vector.add(bil);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }
    
    public void displayAll(){
                String sql = "select * from Bill";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String billID = rs.getString(1);
                String dateCreate = rs.getString(2);
                String recAddress = rs.getString(3);
                String recPhone = rs.getString(4);
                double totalMoney = rs.getDouble(5);
                String note = rs.getString(6);
                String cusID = rs.getString(7);
                int sta = rs.getInt(8);
                
                Bill bil = new Bill(billID, dateCreate, recAddress, recPhone,
                         note, totalMoney, sta, cusID);
                System.out.println(bil);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int removeBill(String id){
        int n = 0;
        String sql = "delete from Bill where billID='" + id + "'";
        try {
            //note: Bill --1---n--> BillDetail --> khong xoa duoc
            // neu billID ton tai tren BillDetail (foreign key contrain)
            ResultSet rs = this.getData("Select * from BillDetail where billID='" + id + "'");
            if (rs.next()) {
                // co ton tai
                // thong bao
                n=-1; // khong xoa duoc vi contrain
                // servlet call update status of Bill =0
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
        //DAOBill dao = new DAOBill();
        //System.out.println(dao.getAllBill("select * from Bill where billID = 'BI002'"));
    }
}
