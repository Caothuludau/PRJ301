/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
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
public class DAOCategory extends DBConnect {
    
    public int addCategory(Category cat){ //ByPre
        int n = 0;
        String sql = "INSERT INTO [Category]\n"
                + "           ([cateName]\n"
                + "           ,[description]\n"
                + "           ,[status])\n"
                + "     VALUES(?,?,?)"; 
                //The '?' symbol represents a placeholder for a parameter in a prepared statement.
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //Set parameter
            pre.setString(1, cat.getCateName());
            pre.setString(2, cat.getDescription());
            pre.setInt(3, cat.getStatus());
            //Run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int updateCategory(Category cat){
        int n = 0;
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [cateName] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [cateID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cat.getCateName());
            pre.setString(2, cat.getDescription());
            pre.setInt(3, cat.getStatus());
            pre.setInt(4, cat.getCateID());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Category> getAllCategory(String sql){
        Vector<Category> vector = new Vector<Category>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Category");
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String des = rs.getString(3);
                int sta = rs.getInt(4);
                Category cat = new Category(id, name, des, sta);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }
    
    public void displayAll(){
                String sql = "select * from Category";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String des = rs.getString(3);
                int sta = rs.getInt(4);
                Category cat = new Category(id, name, des, sta);
                System.out.println(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int removeCategory(String id){
        int n = 0;
        String sql = "delete from Category where cateID='" + id + "'";
        try {
            //note: Category --1---n--> Book --> khong xoa duoc
            // neu cid ton tai tren Book (foreign key contrain)
            ResultSet rs = this.getData("Select * from Book where cateID='" + id + "'");
            if (rs.next()) {
                // co ton tai
                // thong bao
                n=-1; // khong xoa duoc vi contrain
                // servlet call update status of Category =0
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
    }
}
