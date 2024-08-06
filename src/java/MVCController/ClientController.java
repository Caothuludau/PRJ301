/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVCController;

import dao.DAOCustomer;
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
@WebServlet(name = "ClientController", urlPatterns = {"/ClientControllerURL"})
public class ClientController extends HttpServlet {

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
         DAOCustomer dao = new DAOCustomer();
        if (go == null) {
            go = "login";
        }
        
        if (go.equals("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean result = dao.login(username, password);
            if (result == true) {
                Vector<Customer> vec = dao.getAllCustomer("select * from Customer where username = '"+ username +"'");
                HttpSession session = request.getSession();
                session.setAttribute("user", vec.get(0));
                session.setMaxInactiveInterval(10*60); // max 10 minute inactive
                response.sendRedirect("HomeControllerURL");
            } else {
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Login.jsp");
                dispatch.forward(request, response);
            }
        }
        
         if (go.equals("reLogin")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean result = dao.login(username, password);
            if (result == true) {
                Vector<Customer> vec = dao.getAllCustomer("select * from Customer where username = '"+ username +"'");
                HttpSession session = request.getSession();
                session.setAttribute("user", vec.get(0));
                session.setMaxInactiveInterval(10*60); // max 10 minute inactive
                response.sendRedirect("HomeControllerURL");
            } else {
                request.setAttribute("reLogin", true);
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Login.jsp");
                dispatch.forward(request, response);
            }
        }
        
        if (go.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("HomeControllerURL");
        }
        
        if (go.equals("signup")) {
            request.setAttribute("username", "");
            request.setAttribute("password", "");
            request.setAttribute("address", "");
            request.setAttribute("phone", "");
            request.setAttribute("cusName", "");
            //request.setAttribute("errCode", "");
            RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("reSignup")) {
            request.setAttribute("errCode", "");
            Vector<Customer> vec = dao.getAllCustomer("select * from Customer");
            
            //GET PARAMETER
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String cusName = request.getParameter("cusName");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            
            //CHECK
            //Check username
            if (username.length() > 30 || username.length() < 6) {
                request.setAttribute("errCode", "Username must be [6-30] characters");
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                dispatch.forward(request, response);
            }
            for (Customer temp: vec) {
                if (temp.getUsername().equals(username)) {
                    request.setAttribute("errCode", "Username is taken");
                    RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                    dispatch.forward(request, response);
                }
            }
            //Check password
            if (password.length() > 32 || password.length() < 8) {
                request.setAttribute("errCode", "Password must be [8-30] characters");
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                dispatch.forward(request, response);
            }
            //Check confirm password
            if (!password.equals(confirm)){
                request.setAttribute("errCode", "Reconfirm password does not match with password");
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                dispatch.forward(request, response);
            }
            //Check customer name
            if (cusName.length() > 50 || cusName.length() < 6) {
                request.setAttribute("errCode", "Name length must be [6-50] characters");
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                dispatch.forward(request, response);
            }
            //Check phone
            if (phone.length() > 20 || phone.length() < 6) {
                request.setAttribute("errCode", "Phone number length must be [6-20] characters");
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                dispatch.forward(request, response);
            }
            try{
                Integer.parseInt(request.getParameter("phone"));
            }
            catch(NumberFormatException e) {
                request.setAttribute("errCode", "Phone must be a number");
                RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/Signup.jsp");
                dispatch.forward(request, response);
            }
            //Create new cusID
            //B1: Get the index value of the last Customer and increase it by 1. If no customer availabled, made it 001
            int index = 1;
            if (!vec.isEmpty()){
                index = Integer.parseInt(vec.lastElement().getCusID().substring(1)) + 1;
            }
            //B2: Concat the index with 'C' to make a proper cusID
            String cusID = "";
            if (index < 10) {
                cusID = "C00" + Integer.toString(index);
            }
            else if (index < 100) {
                cusID = "C0" + Integer.toString(index);
            }
            else {
                cusID = "C" + Integer.toString(index);
            }
            //Create status = 1
            int status = 1;

            Customer cus = new Customer(cusID, cusName, username, password, address, phone, status);
            dao.addCustomer(cus);
            
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            request.setAttribute("cusName", cusName);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("./clientJSP/SignupSuccess.jsp");
            dispatch.forward(request, response);
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
