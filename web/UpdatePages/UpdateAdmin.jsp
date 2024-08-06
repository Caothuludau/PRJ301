<%-- 
    Document   : UpdateAdmin
    Created on : Mar 4, 2023, 9:44:02 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Admin"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Admin> vec = (Vector<Admin>) request.getAttribute("data");
            Admin ad = vec.get(0);
        %>
        
        <form action="AdminControllerURL"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="admin">Admin account</label></td>
                    <td><input type="text" name="admin" id = "admin" value = "<%=ad.getAdmin()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="text" name="password" id = "password" value = "<%=ad.getPassword()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Admin" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
