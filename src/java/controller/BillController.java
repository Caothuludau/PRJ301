/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOBook;
import entity.Bill;
import entity.BillDetail;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author CaoThuLuDau
 */
@WebServlet
        (name = "BillController",
            urlPatterns = {"/BillControllerURL"})
public class BillController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOBill dao = new DAOBill();
        
        try ( PrintWriter out = response.getWriter()) {
              
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll"; //Default value
            }

            if (go.equals("insert")) {
                String billID = request.getParameter("billID");
                String recAddress = request.getParameter("recAddress");
                String recPhone = request.getParameter("recPhone");
                String note = request.getParameter("note");
                double totalMoney = 0;
                if (!request.getParameter("totalMoney").isEmpty()){
                    totalMoney = Double.parseDouble(request.getParameter("totalMoney"));
                }   
                int status = Integer.parseInt(request.getParameter("status"));
                String cusID = request.getParameter("cusID");
                Bill bill = new Bill(billID, recAddress, recPhone, note, totalMoney, status, cusID);
                dao.addBill(bill);
                response.sendRedirect("BillControllerURL");
            }
            
            if (go.equals("listAll")) {
                if (request.getParameter("deleted") != null && !request.getParameter("deleted").isEmpty()) {
                    if (request.getParameter("deleted").equals("-1")) {
                        request.setAttribute("err", "delete");
                    }
                }
                request.getRequestDispatcher("AdminControllerURL?go=billManage").forward(request, response);  
            }

            if (go.equals("delete")) {
                String billID = request.getParameter("billID");
                int n = dao.removeBill(billID);
                response.sendRedirect("BillControllerURL?deleted=" + n);
            }
            
            if (go.equals("deleteByUser")) {
                String billID = request.getParameter("billID");
                Vector<Bill> vec = dao.getAllBill("select * from Bill where billID = '"+ billID +"'");
                if (vec.size() > 0) {
                    Bill bill = vec.get(0);
                    int status = 0;
                    bill.setStatus(status);
                    dao.updateBill(bill);
                }
                response.sendRedirect("HomeControllerURL?go=viewBill");
            }

            if (go.equals("update")) {
                    String billID = request.getParameter("billID");
                    Vector<Bill> vec = dao.getAllBill("select * from Bill where billID = '"+ billID +"'");
                    if (vec.size() > 0) {
                    Bill bill = vec.get(0);
                    int status = Integer.parseInt(request.getParameter("status"));
                    int preStatus = bill.getStatus();
                    bill.setStatus(status);
                    DAOBillDetail daobd = new DAOBillDetail();
                    DAOBook daob = new DAOBook();
                    if (status == 3) {

                        Vector<BillDetail> vecbd = daobd.getAllBillDetail("select * from BillDetail where billID = '" + billID + "'");
           
                        for (BillDetail billDe: vecbd) {
                            Vector<Book> vecb = daob.getAllBook("select * from Book where bookID = '" + billDe.getBookID() + "'");
                            Book book = vecb.get(0);
                            int currentQuantity = book.getQuantity();
                            book.setQuantity(currentQuantity - billDe.getBuyQuantity());
                            daob.updateBook(book);
                        }
                    }
                    if (status == 2 && preStatus == 3) {
                 
                        Vector<BillDetail> vecbd = daobd.getAllBillDetail("select * from BillDetail where billID = '" + billID + "'");
        
                        for (BillDetail billDe: vecbd) {
                            Vector<Book> vecb = daob.getAllBook("select * from Book where bookID = '" + billDe.getBookID() + "'");
                            Book book = vecb.get(0);
                            int currentQuantity = book.getQuantity();
                            book.setQuantity(currentQuantity + billDe.getBuyQuantity());
                            daob.updateBook(book);
                        }
                    }
                    dao.updateBill(bill);
                    }
                    response.sendRedirect("BillControllerURL");
                }
            }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
