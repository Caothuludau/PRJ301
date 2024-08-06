/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Customer;
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
public class DAOCustomer extends DBConnect {
    
    public int addCustomer(Customer cus){ //ByPre
        int n = 0;
        String sql = "INSERT INTO [Customer]\n"
                + "           ([cusID]\n"
                + "           ,[cusName]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[status])\n"
                + "     VALUES(?,?,?,?,?,?,?)"; 
                //The '?' symbol represents a placeholder for a parameter in a prepared statement.
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //Set parameter
            pre.setString(1, cus.getCusID());
            pre.setString(2, cus.getCusName());
            pre.setString(3, cus.getUsername());
            pre.setString(4, cus.getPassword());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getPhone());
            pre.setInt(7, cus.getStatus());
            //Run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int updateCustomer(Customer cus){
        int n = 0;
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [cusName] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [cusID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cus.getCusName());
            pre.setString(2, cus.getUsername());
            pre.setString(3, cus.getPassword());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getPhone());
            pre.setInt(6, cus.getStatus());
            pre.setString(7, cus.getCusID());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public boolean login(String user, String pass){
        String sql = "select * from Customer where username=? and password=? and status=1";
//        sql="select * from Customer where username='"+user+
//                "' and password='"+pass+"'";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public Vector<Customer> getAllCustomer(String sql){
        Vector<Customer> vector = new Vector<Customer>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Customer");
        try {
            while (rs.next()) {
                Customer cus = getCus(rs);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }
    
    private Customer getCus(ResultSet rs) throws SQLException {
        String cid = rs.getString("cusID");
        String name = rs.getString("cusName");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        int status = rs.getInt("status");
        return new Customer(cid, name, username, password, address, phone, status);
    }
    
    public void displayAll(){
                String sql = "select * from Customer";
        try {
//        Statement state=conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
//                ResultSet.CONCUR_READ_ONLY);
//default: TYPE_FORWARD_ONLY con tro chi di xuong
//CONCUR_READ_ONLY: ket qua la chi doc
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//TYPE_SCROLL_SENSITIVE: Scroll: con tro 2 chieu
// sensitive: threadSafe
//UPDATABLE: cho phep sua
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
//                 dataType varName=rs.getDataType("fieldName|index);
//                 index start 1
                String cusID =rs.getString(1);
                String cusName = rs.getString(2);
                String userName = rs.getString(3);
                String pass = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                int sta = rs.getInt("status");
                Customer cus = new Customer(cusID, cusName, userName, pass,
                        address, phone, sta);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int removeCustomer(String id){
        int n = 0;
        String sql = "delete from customer where cusID='" + id + "'";
        try {
            //note: Customer --1---n--> Bill --> khong xoa duoc
            // neu cid ton tai tren Bill (foreign key contrain)
            ResultSet rs = this.getData("Select * from Bill where cusID='" + id + "'");
            if (rs.next()) {
                // co ton tai
                // thong bao
                n=-1; // khong xoa duoc vi contrain
                // servlet call update status of customer =0
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        System.out.println(dao.login("jsmith", "password1"));
    }
}
