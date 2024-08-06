<%-- 
    Document   : BillDetailView
    Created on : Mar 22, 2023, 9:37:27 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.BillDetail, entity.Book, dao.DAOBook , java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<BillDetail> vec = (Vector<BillDetail>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
            DAOBook dao = new DAOBook();
        %>
        <div class="table-responsive">
            <table class="table">
                <tcaption style="display: block; text-align: center;"><h3>BILL DETAIL</h3></tcaption>
                <tr>
                    <th>Bill ID</th>
                    <th>Book ID</th>
                    <th>Image</th>
                    <th>Buy Quantity</th>
                    <th>Buy Price</th>
                    <th>Subtotal</th>
                </tr>
                <% 
                    for (BillDetail temp : vec) {
                        Vector<Book> vecb = dao.getAllBook("select * from Book where bookID = '"+ temp.getBookID() +"'");
                        Book book = vecb.get(0);
                %>
                <tr>
                    <td><%=temp.getBillID()%> </td>
                    <td><%=temp.getBookID()%> </td>
                    <td><img src="./images/<%=book.getImage()%>"  width="100" height="150"> </td>
                    <td><%=temp.getBuyQuantity()%> </td>
                    <td><%=temp.getBuyPrice()%> </td>
                    <td><%=temp.getSubtotal()%> </td>
                </tr>
                <%}%>
                <tr>
                    <td><a href="AdminControllerURL?go=billManage">Back to Bill Manage</a></td>
                </tr>
            </table>
        </div>
    </body>
</html>
