/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBillDetail;
import entity.BillDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author CaoThuLuDau
 */
@WebServlet
        (name = "BillDetailController",
            urlPatterns = {"/BillDetailControllerURL"})
public class BillDetailController extends HttpServlet {

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
        DAOBillDetail dao = new DAOBillDetail();
        
        try ( PrintWriter out = response.getWriter()) {
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insert")) {
                String billID = request.getParameter("billID");
                String bookID = request.getParameter("bookID");
                int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
            
                BillDetail billDetail = new BillDetail(billID, bookID, buyQuantity, buyPrice, buyQuantity * buyPrice);
                dao.addBillDetail(billDetail);
                response.sendRedirect("BillDetailControllerURL");
            }
            
            if (go.equals("listAll")) {
                String sql = "select * from BillDetail";
                Vector<BillDetail> vec = dao.getAllBillDetail(sql);
                request.setAttribute("data", vec);
                request.getRequestDispatcher("ViewPages/ViewBillDetail.jsp").forward(request, response);
                
            }
            if (go.equals("delete")) {
                String billID = request.getParameter("billID");
                String bookID = request.getParameter("bookID");
                dao.removeBillDetail(billID, bookID);
                response.sendRedirect("BillDetailControllerURL");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String billID = request.getParameter("billID");
                    String bookID = request.getParameter("bookID");
                    Vector<BillDetail> vec = dao.getAllBillDetail("select * from billDetail where billID ='" + billID + "' and bookID = '" + bookID + "'");
                    request.setAttribute("data", vec);
                    request.getRequestDispatcher("UpdatePages/UpdateBillDetail.jsp").forward(request,response);
                } else {
                    String billID = request.getParameter("billID");
                    String bookID = request.getParameter("bookID");
                    int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                    double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
                    
                    BillDetail billDe = new BillDetail(billID, bookID, buyQuantity, buyPrice);
                    dao.updateBillDetail(billDe);
                    response.sendRedirect("BillDetailControllerURL");
                }
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
