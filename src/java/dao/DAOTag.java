/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Tag;
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
public class DAOTag extends DBConnect {
    
    public int addTag(Tag tag){ //ByPre
        int n = 0;
        String sql = "INSERT INTO [Tag]\n"
                + "           ([tagName]\n"
                + "           ,[status])\n"
                + "     VALUES(?,?)"; 
                //The '?' symbol represents a placeholder for a parameter in a prepared statement.
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //Set parameter
            pre.setString(1, tag.getTagName());
            pre.setInt(2, tag.getStatus());
            //Run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOTag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int updateTag(Tag tag){
        int n = 0;
        String sql = "UPDATE [dbo].[Tag]\n"
                + "   SET [tagName] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [tagID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, tag.getTagName());
            pre.setInt(2, tag.getStatus());
            pre.setInt(3, tag.getTagID());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Tag> getAllTag(String sql){
        Vector<Tag> vector = new Vector<Tag>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Tag");
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int sta = rs.getInt(3);
                Tag tag = new Tag(id, name, sta);
                vector.add(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }
    
    public void displayAll(){
                String sql = "select * from Tag";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int sta = rs.getInt(3);
                Tag tag = new Tag(id, name, sta);
                System.out.println(tag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int removeTag(String id){
        int n = 0;
        String sql = "delete from Tag where tagID='" + id + "'";
        try {
            //note: Tag --1---n--> Book --> khong xoa duoc
            // neu cid ton tai tren Book (foreign key contrain)
            ResultSet rs = this.getData("Select * from Book where tagID='" + id + "'");
            if (rs.next()) {
                // co ton tai
                // thong bao
                n=-1; // khong xoa duoc vi contrain
                // servlet call update status of Tag =0
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTag.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
    }
}
