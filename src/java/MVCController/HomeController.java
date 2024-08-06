/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MVCController;

import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOBook;
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
import java.util.Vector;

/**
 *
 * @author CaoThuLuDau
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeControllerURL"})
public class HomeController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String go = request.getParameter("go");
        DAOBook dao = new DAOBook();
        
        if (go == null) {
            go = "listAll";
        }
        
        if (go.equals("listAll")){
            String sql = "select * from Book as b, Category as c "
                    + "where b.cateID = c.cateID";
            Vector<Book> vec = dao.getAllBook(sql);
            request.setAttribute("data", vec);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        if (go.equals("listBook")) {
            String cateID = request.getParameter("id");
            String sql = "select * from Book as b join "
                    + "Category as c on b.cateID = c.cateID"
                    + " where b.cateID = " + cateID;
            Vector<Book> vec = dao.getAllBook(sql);
            String titleTable = "List of Book";
            //chuan bi du lieu cho jsp
            request.setAttribute("data", vec);
            request.setAttribute("title", titleTable);
            //call jsp
            RequestDispatcher dispath
                    = request.getRequestDispatcher("index.jsp");
            //run
            dispath.forward(request, response);
        }

        if (go.equals("searchBook")) {
            String bookName = request.getParameter("bookName");
            String sql =  "select * from Book as b join "
                    + "Category as c on b.cateID = c.cateID"
                    + " where b.bookName like N'%" + bookName + "%'";
            Vector<Book> vec = dao.getAllBook(sql);
            String titleTable = "List of Book";
            //chuan bi du lieu cho jsp
            request.setAttribute("data", vec);
            request.setAttribute("title", titleTable);
            //call jsp
            RequestDispatcher dispatch
                    = request.getRequestDispatcher("index.jsp");
            //run
            dispatch.forward(request, response);
        }
        
        if (go.equals("menu")){
            // send user to menu page
            response.sendRedirect("HomeControllerURL?go=listAll");
        }
        
        if (go.equals("viewBookDetail")) {
            String bookID = request.getParameter("bookID");
            String sql =  "select * from Book "
                    + "where bookID = '" + bookID + "'";   
            Vector<Book> vec = dao.getAllBook(sql);
            request.setAttribute("data", vec);
            request.setAttribute("viewMode", "detail"); 
            //call jsp
            RequestDispatcher dispath
                    = request.getRequestDispatcher("index.jsp");
            //run
            dispath.forward(request, response);
        }
        
        if (go.equals("viewBill")) {
            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("user");
            DAOBill daobi = new DAOBill();
            Vector<Bill> vec = daobi.getAllBill("select * from Bill where cusID = '" + cus.getCusID() + "'");
            request.setAttribute("data", vec);
            RequestDispatcher dispath
                    = request.getRequestDispatcher("Bill.jsp");
            dispath.forward(request, response);
        
        }
        
        if (go.equals("billDetail")){
            DAOBillDetail daobd = new DAOBillDetail();
            String billID = request.getParameter("id");
            String sql = "select * from BillDetail where billID = '" + billID +"'";
            Vector<BillDetail> vec = daobd.getAllBillDetail(sql);
            request.setAttribute("data", vec);
            RequestDispatcher dispatch = request.getRequestDispatcher("BillDetail.jsp");
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
