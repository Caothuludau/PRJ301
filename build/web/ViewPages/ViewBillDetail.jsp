<%-- 
    Document   : ViewBillDetail
    Created on : Feb 14, 2023, 1:50:23 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html><%@page import="entity.BillDetail, java.util.Vector"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Bill Detail</title>
    </head>
    <body>
        <%
            Vector<BillDetail> vec = (Vector<BillDetail>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
        %>
        <table border = "1">
            <caption>PRODUCT LIST</caption>
            <tr>
                <th>Bill ID</th>
                <th>Product ID</th>
                <th>Buy Quantity</th>
                <th>Buy Price</th>
                <th>Subtotal</th>
            </tr>
            <% for (BillDetail temp : vec) {%>
            <tr>
                <td><%=temp.getBillID()%> </td>
                <td><%=temp.getBookID()%> </td>
                <td><%=temp.getBuyQuantity()%> </td>
                <td><%=temp.getBuyPrice()%> </td>
                <td><%=temp.getSubtotal()%> </td>
                <td><a href="BillDetailControllerURL?go=update&billID=<%=temp.getBillID()%>&bookID=<%=temp.getBookID()%> ">Update</a></td>
                <td><a href="BillDetailControllerURL?go=delete&billID=<%=temp.getBillID()%>&bookID=<%=temp.getBookID()%>">Delete</a></td>
            </tr>
            <%}%>
        <tr>
                <td><a href="ManagePages/BillDetailManage.html">Bill Detail Manage</a></td>
                <td><a href="./InsertPages/InsertBillDetail.jsp">Insert Bill Detail</a></td>
        </tr>
        </table>
    </body>
</html>
