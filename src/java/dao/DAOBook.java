/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Book;
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

public class DAOBook extends DBConnect {
    
    public int addBook(Book book){
        int n = 0;
        String sql = "INSERT INTO [Book]\n"
                + "           ([bookID]\n"
                + "           ,[bookName]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[author]\n"
                + "           ,[pageNum]\n"
                + "           ,[size]\n"
                + "           ,[releaseDate]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[status]\n"
                + "           ,[cateID]\n"
                + "           ,[tagID])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //The '?' symbol represents a placeholder for a parameter in a prepared statement.
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //Set parameter
            pre.setString(1, book.getBookID());
            pre.setString(2, book.getBookName());
            pre.setString(3, book.getImage());
            pre.setString(4, book.getDescription());
            pre.setString(5, book.getAuthor());
            pre.setInt(6, book.getPageNum());
            pre.setString(7, book.getSize());
            pre.setString(8, book.getReleaseDate());
            pre.setInt(9, book.getQuantity());
            pre.setDouble(10, book.getPrice());
            pre.setInt(11, book.getStatus());
            pre.setInt(12, book.getCateID());
            pre.setInt(13, book.getTagID());
            //Run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public int updateBook(Book book){
         int n = 0;
                String sql = "UPDATE [Book]\n"
                + "    SET     [bookName] = ?\n"
                + "           ,[image] = ?\n"
                + "           ,[description] = ?\n"
                + "           ,[author] = ?\n"
                + "           ,[pageNum] = ?\n"
                + "           ,[size] = ?\n"
                + "           ,[releaseDate] = ?\n"
                + "           ,[quantity] = ?\n"
                + "           ,[price] = ?\n"
                + "           ,[status] = ?\n"
                + "           ,[cateID] = ?\n"
                + "           ,[tagID] = ?\n"
                + "     WHERE  [bookID] = ?";
                //The '?' symbol represents a placeholder for a parameter in a prepared statement.
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //Set parameter
            pre.setString(1, book.getBookName());
            pre.setString(2, book.getImage());
            pre.setString(3, book.getDescription());
            pre.setString(4, book.getAuthor());
            pre.setInt(5, book.getPageNum());
            pre.setString(6, book.getSize());
            pre.setString(7, book.getReleaseDate());
            pre.setInt(8, book.getQuantity());
            pre.setDouble(9, book.getPrice());
            pre.setInt(10, book.getStatus());
            pre.setInt(11, book.getCateID());
            pre.setInt(12, book.getTagID());
            pre.setString(13, book.getBookID());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Book> getAllBook(String sql){
        Vector<Book> vector = new Vector<Book>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Book");
        try {
            while(rs.next()){
                String bookID = rs.getString("bookID");
                String bookName = rs.getString("bookName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String author = rs.getString("author");
                int pageNum = rs.getInt("pageNum");
                String size = rs.getString("size");
                String releaseDate = rs.getString("releaseDate");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int status = rs.getInt("status");
                int cateID = rs.getInt("cateID");
                int tagID = rs.getInt("tagID");
                Book book = new Book(bookID, bookName, image, description, author,
                        pageNum, size, releaseDate, quantity, price, status, cateID, tagID);
                vector.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }
    
    public void displayAll(){
                String sql = "select * from Book";
        try {

            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String bookName = rs.getString("bookName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String author = rs.getString("author");
                int pageNum = rs.getInt("pageNum");
                String size = rs.getString("size");
                String releaseDate = rs.getString("releaseDate");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int status = rs.getInt("status");
                int cateID = rs.getInt("cateID");
                int tagID = rs.getInt("tagID");
                Book book = new Book(bookID, bookName, image, description, author,
                        pageNum, size, releaseDate, quantity, price, status, cateID, tagID);
                System.out.println(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int removeBook(String id){
        int n = 0;
        String sql = "delete from Book where bookID='" + id + "'";
        try {
            //note: Book --1--n--> BillDetail -> ko xoa dc
            //neu bookID ton tai tren Billdetail
            ResultSet rs = this.getData("Select * from BillDetail where bookID='" + id + "'");
            if (rs.next()) {
                n=-1;
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
    }
}
