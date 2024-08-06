<%-- 
    Document   : ViewAdmin
    Created on : Mar 3, 2023, 10:19:15 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet, entity.Admin"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
        %>
        <table border = "1">
            <caption>ADMIN LIST</caption>
            <tr>
                <th>ADMIN</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%while(rs.next()){%>
            <tr>
                <td> <%=rs.getString(1)%> </td>
                <td><a href="AdminControllerURL?go=update&admin=<%=rs.getString(1)%>">Update</a></td>
                <td><a href="AdminControllerURL?go=delete&admin=<%=rs.getString(1)%>">Delete</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="ManagePages/AdminManage.html">Admin Manage</a></td>
                <td><a href="./InsertPages/InsertAdmin.html">Insert Admin</a></td>
            </tr>
        </table>
    </body>
</html>
