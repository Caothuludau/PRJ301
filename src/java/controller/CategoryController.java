/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOCategory;
import entity.Category;
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
        (name = "CategoryController",
            urlPatterns = {"/CategoryControllerURL"})
public class CategoryController extends HttpServlet {

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
        DAOCategory dao = new DAOCategory();
        try ( PrintWriter out = response.getWriter()) {
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }

            if (go.equals("insert")) {
                String cateName = request.getParameter("cateName");
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                Category cate = new Category(cateName, description, status);
                dao.addCategory(cate);
                response.sendRedirect("CategoryControllerURL");
            }
            if (go.equals("listAll")){
                String sql = "select * from Category";
                Vector<Category> vec = dao.getAllCategory(sql);
                request.setAttribute("data", vec);
                if (request.getParameter("deleted") != null && !request.getParameter("deleted").isEmpty()) {
                    if (request.getParameter("deleted").equals("-1")) {
                        request.setAttribute("err", "delete");
                    }
                }
                request.getRequestDispatcher("ViewPages/ViewCategory.jsp").forward(request, response); 
            }
            if (go.equals("delete")) {
                String cateID = request.getParameter("cateID");
                int n = dao.removeCategory(cateID);
                response.sendRedirect("CategoryControllerURL?deleted=" + n);
            }

            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String cateID = request.getParameter("cateID");
                    Vector<Category> vec = dao.getAllCategory("select * from Category where cateID ='" + cateID + "'");
                    request.setAttribute("data", vec);
                    request.getRequestDispatcher("UpdatePages/UpdateCategory.jsp").forward(request, response);
                    
                } else {
                    int cateID = Integer.parseInt(request.getParameter("cateID"));
                    String cateName = request.getParameter("cateName");
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));

                    Category cat = new Category(cateID, cateName, description, status);
                    dao.updateCategory(cat);
                    response.sendRedirect("CategoryControllerURL");
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
