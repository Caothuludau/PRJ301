/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOTag;
import entity.Tag;
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
        (name = "TagController",
            urlPatterns = {"/TagControllerURL"})
public class TagController extends HttpServlet {

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
        DAOTag dao = new DAOTag();
        try ( PrintWriter out = response.getWriter()) {
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }

            if (go.equals("insert")) {
                String tagName = request.getParameter("tagName");
                int status = Integer.parseInt(request.getParameter("status"));
                Tag tag = new Tag(tagName, status);
                dao.addTag(tag);
                response.sendRedirect("TagControllerURL");
            }
            if (go.equals("listAll")){
                String sql = "select * from Tag";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                request.getRequestDispatcher("ViewPages/ViewTag.jsp").forward(request, response); 
            }
            if (go.equals("delete")) {
                String tagID = request.getParameter("tagID");
                dao.removeTag(tagID);
                response.sendRedirect("TagControllerURL");
            }

            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String tagID = request.getParameter("tagID");
                    Vector<Tag> vec = dao.getAllTag("select * from Tag where tagID ='" + tagID + "'");
                    request.setAttribute("data", vec);
                    request.getRequestDispatcher("UpdatePages/UpdateTag.jsp").forward(request, response);
                    
                } else {
                    int tagID = Integer.parseInt(request.getParameter("tagID"));
                    String tagName = request.getParameter("tagName");
                    int status = Integer.parseInt(request.getParameter("status"));

                    Tag tag = new Tag(tagID, tagName, status);
                    dao.updateTag(tag);
                    response.sendRedirect("TagControllerURL");
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
