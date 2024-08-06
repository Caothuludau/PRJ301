/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVCController;

import dao.DAOBill;
import dao.DAOBillDetail;
import entity.Bill;
import entity.BillDetail;
import entity.Cart;
import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;

/**
 *
 * @author CaoThuLuDau
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartControllerURL"})
public class CartController extends HttpServlet {

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
        String go = request.getParameter("go");
        DAOBill daoBi = new DAOBill();
        DAOBillDetail daoBiDe = new DAOBillDetail();
        if (go.equals("viewCart")) {
            RequestDispatcher dispatch = request.getRequestDispatcher("cart.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("addToCart")) {
            //Get Parameter
            String bookID = request.getParameter("bookID");
            double price = Double.parseDouble(request.getParameter("price"));
            int buyQuantity = Integer.parseInt(request.getParameter("quantity"));
            double subtotal = (double)buyQuantity*price;
            //Add Parameter into a new BillDetail
            BillDetail billDe = new BillDetail(bookID, buyQuantity, price, subtotal);
            //Add BillDetail into Cart 
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            cart.addBillDetail(billDe);
            session.setAttribute("cart", cart);
            response.sendRedirect("HomeControllerURL");
        }
        
        if (go.equals("updateCart")) {
            //Get Parameter
            String bookID = request.getParameter("bookID");
            double price = Double.parseDouble(request.getParameter("price"));
            int buyQuantity = Integer.parseInt(request.getParameter("quantity"));
            double subtotal = (double)buyQuantity*price;
            //Add Parameter into a new BillDetail
            BillDetail billDe = new BillDetail(bookID, buyQuantity, price, subtotal);
            //Add BillDetail into Cart 
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            cart.updateBillDetail(billDe);
            response.sendRedirect("CartControllerURL?go=viewCart");
        }
        
        if (go.equals("removeFromCart")) {
            //Get Parameter
            String bookID = request.getParameter("bookID");
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            cart.deleteBillDetail(bookID);
            response.sendRedirect("CartControllerURL?go=viewCart");
        }
        
        if (go.equals("checkOut")) {
            RequestDispatcher dispatch = request.getRequestDispatcher("CheckOut.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("confirmAndExit")) {
        //Add Bill
            //Create new billID
            //B1: Get the index value of the last Bill and increase it by 1. If no bill availabled, made it 001
            int index = 1;
            Vector<Bill> vec = daoBi.getAllBill("select * from Bill");
            if (!vec.isEmpty()){
                index = Integer.parseInt(vec.lastElement().getBillID().substring(2)) + 1;
            }
            //B2: Concat the index with 'BI' to make a proper billID
            String billID = "";
            if (index < 10) {
                billID = "BI00" + Integer.toString(index);
            }
            else if (index < 100) {
                billID = "BI0" + Integer.toString(index);
            }
            else {
                billID = "BI" + Integer.toString(index);
            }
            
            String recAddress = request.getParameter("recAddress");
            String recPhone = request.getParameter("recPhone");
            String note = request.getParameter("note");
            double totalMoney = 0;
            if (request.getParameter("totalMoney") != null) {
                totalMoney = Double.parseDouble(request.getParameter("totalMoney"));
            }
            int status = 1; // 1 = "wait";
            String cusID = request.getParameter("cusID");
            Bill bill = new Bill(billID, recAddress, recPhone, note, totalMoney, status, cusID);
            daoBi.addBill(bill);
            
        //Add BillDetail
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            for (BillDetail billDe: cart.getCart().values()) {
                billDe.setBillID(billID);
                daoBiDe.addBillDetail(billDe);
            }
            
        //Renew cart attribute in session
            session.removeAttribute("cart");
            response.sendRedirect("CartControllerURL?go=buySuccess");
        }
        
        if (go.equals("buySuccess")) {
            response.sendRedirect("BuySuccess.jsp");
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
