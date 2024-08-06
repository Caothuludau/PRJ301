<%-- 
    Document   : UpdateAdmin
    Created on : Mar 4, 2023, 9:44:02 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.BillDetail"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<BillDetail> vec = (Vector<BillDetail>) request.getAttribute("data");
            BillDetail billDe = vec.get(0);
        %>

        <form action="BillDetailControllerURL"  method = "POST">
            <input type="hidden" name="go" value="update">  
            <table>
                <tr>
                    <td><label for="billID">Bill ID</label></td>
                    <td><input type="text" name="billID" id = "billID" value = "<%=billDe.getBillID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="bookID">Product ID</label></td>
                    <td><input type="text" name="bookID" id = "bookID" value = "<%=billDe.getBookID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="buyQuantity">Quantity</label></td>
                    <td><input type="text" name="buyQuantity" id = "buyQuantity" value = "<%=billDe.getBuyQuantity()%>"></td>
                </tr>
                <tr>
                    <td><label for="buyPrice">Price</label></td>
                    <td><input type="text" name="buyPrice" id = "buyPrice" value = "<%=billDe.getBuyPrice()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Bill Detail" name="submit" ></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>

            </table>
        </form>
    </body>
</html>
