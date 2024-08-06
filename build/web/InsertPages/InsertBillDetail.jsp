<%-- 
    Document   : InsertBillDetail
    Created on : Feb 28, 2023, 3:18:04 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.DAOBill, dao.DAOBook"%>
<%@page import="entity.Bill, entity.Book, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            DAOBill daoB = new DAOBill();
            Vector<Bill> vecB 
                    = daoB.getAllBill("select * from Bill");
            DAOBook daoBK = new DAOBook();
            Vector<Book> vecBK 
                    = daoBK.getAllBook("select * from Book");
        %>
        <form action="../BillDetailControllerURL"  method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="billID">Bill ID</label></td>
                    <td>
                        <select name="billID" id="billID">
                            <%for(Bill temp : vecB){%>
                            <option value="<%=temp.getBillID()%>"><%=temp.getBillID()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="bookID">Book ID</label></td>
                    <td>
                        <select name="bookID" id="bookID">
                            <%for(Book temp : vecBK){%>
                            <option value="<%=temp.getBookID()%>"><%=temp.getBookID()%> : <%=temp.getBookName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="buyQuantity">Quantity</label></td>
                    <td><input type="text" name="buyQuantity" id = "buyQuantity"></td>
                </tr>
                <tr>
                    <td><label for="buyPrice">Price</label></td>
                    <td><input type="text" name="buyPrice" id = "buyPrice"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Bill Detail" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr> 
            </table>
        </form>
    </body>
</html>
