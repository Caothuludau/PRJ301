<%-- 
    Document   : ViewCustomer
    Created on : Feb 14, 2023, 1:50:23 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Customer, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Customer> vec = (Vector<Customer>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
            String errCode = (String) request.getAttribute("err");
        %>
        <table border = "1">
            <caption>CUSTOMER LIST</caption>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
        <% for (Customer temp : vec) {%>
        <tr>
            <td><%=temp.getCusID()%></td>
            <td><%=temp.getCusName()%></td>
            <td><%=temp.getUsername()%></td>
            <td><%=temp.getAddress()%></td>
            <td><%=temp.getPhone()%></td>
            <td><%=(temp.getStatus() == 1?"Enable" : "Disable")%></td>
            <td><a href="CustomerControllerURL?go=update&cusID=<%=temp.getCusID()%>">Update</a></td>
            <td><a href="CustomerControllerURL?go=delete&cusID=<%=temp.getCusID()%>">Delete</a></td>
        </tr>
        <%}%>
        <tr>
            <td><a href="ManagePages/CustomerManage.html">Customer Manage</a></td>
            <td><a href="./InsertPages/InsertCustomer.jsp">Insert Customer</a></td>
        </tr>
        </table>
        <% if (errCode != null && errCode.equals("delete")) {%>
            <p style="color: red"> This customer's bill(s) is still available. Can not delete.</p>
        <%}%>
    </body>
</html>
