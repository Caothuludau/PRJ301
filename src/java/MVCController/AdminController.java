/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVCController;

import dao.DAOAdmin;
import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOBook;
import dao.DAOCustomer;
import entity.Admin;
import entity.Bill;
import entity.BillDetail;
import entity.Book;
import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author CaoThuLuDau
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminControllerURL"})
public class AdminController extends HttpServlet {

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
         DAOAdmin dao = new DAOAdmin();
         DAOCustomer daoc = new DAOCustomer();
         DAOBook daob = new DAOBook();
         DAOBill daobi = new DAOBill();
         DAOBillDetail daobide = new DAOBillDetail();
        if (go == null) {
            go = "login";
        }
        
        if (go.equals("login")) {
            String admin = request.getParameter("admin");
            String password = request.getParameter("password");
            boolean result = dao.login(admin, password);
            if (result == true) {
                Vector<Admin> vec = dao.getAllAdmin("select * from Admin where admin = '"+ admin +"'");
                HttpSession session = request.getSession();
                session.setAttribute("admin", vec.get(0));
                session.setMaxInactiveInterval(10*60); // max 10 minute inactive
                response.sendRedirect("adminindex.jsp");
            } else {
                RequestDispatcher dispatch = request.getRequestDispatcher("./adminJSP/AdminLogin.jsp");
                dispatch.forward(request, response);
            }
        }
        
         if (go.equals("reLogin")) {
            String admin = request.getParameter("admin");
            String password = request.getParameter("password");
            boolean result = dao.login(admin, password);
            if (result == true) {
                Vector<Admin> vec = dao.getAllAdmin("select * from Admin where admin = '"+ admin +"'");
                HttpSession session = request.getSession();
                session.setAttribute("admin", vec.get(0));
                session.setMaxInactiveInterval(10*60); // max 10 minute inactive
                response.sendRedirect("adminindex.jsp");
            } else {
                request.setAttribute("reLogin", true);
                RequestDispatcher dispatch = request.getRequestDispatcher("./adminJSP/AdminLogin.jsp");
                dispatch.forward(request, response);
            }
        }
        
        if (go.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("AdminControllerURL");
        }
        
        if (go.equals("manual")) {
            RequestDispatcher dispatch = request.getRequestDispatcher("adminindex.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("customerManage")) {
            Vector<Customer> vec = daoc.getAllCustomer("select * from Customer");
            request.setAttribute("data", vec);
            request.setAttribute("viewMode", "customerManage");
            RequestDispatcher dispatch = request.getRequestDispatcher("adminindex.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("bookManage")) {
            
            String sql = "select * from Book, Category "
                            + "where Book.cateId = Category.cateId";
            ResultSet rs = daob.getData(sql);
            request.setAttribute("data", rs);  
            request.setAttribute("viewMode", "bookManage");
            RequestDispatcher dispatch = request.getRequestDispatcher("adminindex.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("billManage")) {
            String sql = "select * from Bill";
            Vector<Bill> vec = daobi.getAllBill(sql);
            request.setAttribute("data", vec);
            request.setAttribute("viewMode", "billManage");
            RequestDispatcher dispatch = request.getRequestDispatcher("adminindex.jsp");
            dispatch.forward(request, response);
        }
        
        if (go.equals("searchCustomer")) {
            String cusInfo = request.getParameter("cusInfo");
            String sql =  "select * from Customer"
                    + " where cusName like N'%" + cusInfo + "%' or cusID like '%" + cusInfo +"%'";
            Vector<Customer> vec = daoc.getAllCustomer(sql);
            String titleTable = "List of Customer";
            //chuan bi du lieu cho jsp
            request.setAttribute("data", vec);
            request.setAttribute("title", titleTable);
            request.setAttribute("viewMode", "customerManage");
            //call jsp
            RequestDispatcher dispatch
                    = request.getRequestDispatcher("adminindex.jsp");
            //run
            dispatch.forward(request, response);
        }
        
        if (go.equals("searchBook")) {
            String bookInfo = request.getParameter("bookInfo");
            String sql =  "select * from Book as b join "
                    + "Category as c on b.cateID = c.cateID"
                    + " where b.bookName like N'%" + bookInfo + "%' or b.bookID like '%" + bookInfo +"%'";
            ResultSet rs = daob.getData(sql);
            String titleTable = "List of Book";
            //chuan bi du lieu cho jsp
            request.setAttribute("data", rs);
            request.setAttribute("title", titleTable);
            request.setAttribute("viewMode", "bookManage");
            //call jsp
            RequestDispatcher dispatch
                    = request.getRequestDispatcher("adminindex.jsp");
            //run
            dispatch.forward(request, response);
        }
        
        if (go.equals("searchBill")) {
            String billInfo = request.getParameter("billInfo");
            String sql =  "select * from Bill"
                    + " where billID like '%" + billInfo + "%' or cusID like '%" + billInfo +"%'";
            Vector<Bill> vec = daobi.getAllBill(sql);
            String titleTable = "List of Bill";
            //chuan bi du lieu cho jsp
            request.setAttribute("data", vec);
            request.setAttribute("title", titleTable);
            request.setAttribute("viewMode", "billManage");
            //call jsp
            RequestDispatcher dispatch
                    = request.getRequestDispatcher("adminindex.jsp");
            //run
            dispatch.forward(request, response);
        }
        
        if (go.equals("searchBillByStatus")) {
            String billStatus = request.getParameter("billStatus");
            int status = -1;
            if (billStatus.equalsIgnoreCase("deleted")) {
                status = 0;
            }
            if (billStatus.equalsIgnoreCase("wait")) {
                status = 1;
            }
            if (billStatus.equalsIgnoreCase("process")) {
                status = 2;
            }
            if (billStatus.equalsIgnoreCase("done")) {
                status = 3;
            }
            String sql =  "select * from Bill"
                    + " where status =" + status;
            Vector<Bill> vec = daobi.getAllBill(sql);
            String titleTable = "List of Bill";
            //chuan bi du lieu cho jsp
            request.setAttribute("data", vec);
            request.setAttribute("title", titleTable);
            request.setAttribute("viewMode", "billManage");
            //call jsp
            RequestDispatcher dispatch
                    = request.getRequestDispatcher("adminindex.jsp");
            //run
            dispatch.forward(request, response);
        }
        
        if (go.equals("billDetailView")) {
            String billID = request.getParameter("id");
            String sql = "select * from BillDetail where billID = '" + billID +"'";
            Vector<BillDetail> vec = daobide.getAllBillDetail(sql);
            request.setAttribute("data", vec);
            request.setAttribute("viewMode", "billDetailView");
            RequestDispatcher dispatch = request.getRequestDispatcher("adminindex.jsp");
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
