<%-- 
    Document   : UpdateCustomer
    Created on : Mar 4, 2023, 9:44:02 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Customer> vec = (Vector<Customer>) request.getAttribute("data");
            Customer cus = vec.get(0);
        %>
        
        <form action="CustomerControllerURL"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="cusID">Customer ID</label></td>
                    <td><input type="text" name="cusID" id = "cusID" value = "<%=cus.getCusID()%> " readonly></td>
                </tr>
                <tr>
                    <td><label for="cusName">Customer Name</label></td>
                    <td><input type="text" name="cusName" id = "cusName" value = "<%=cus.getCusName()%> "></td>
                </tr>
                <tr>
                    <td><label for="username">Username</label></td>
                    <td><input type="text" name="username" id = "username" value = "<%=cus.getUsername()%> "></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="text" name="password" id = "password" value = "<%=cus.getPassword()%> "></td>
                </tr>
                <tr>
                    <td><label for="address">address</label></td>
                    <td><input type="text" name="address" id = "address" value = "<%=cus.getAddress()%> "></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="text" name="phone" id = "phone" value = "<%=cus.getPhone()%> "></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" <%=(cus.getStatus() == 1 ? "checked" : "")%> > Enable
                        <input type="radio" name="status" id = "status" value="0" <%=(cus.getStatus() == 0 ? "checked" : "")%> > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Customer" name="submit"></td>
                    <td><input type="reset" value="reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
