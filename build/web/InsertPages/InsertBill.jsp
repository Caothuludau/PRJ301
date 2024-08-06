<%-- 
    Document   : InsertBill
    Created on : Feb 26, 2023, 10:29:48 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Bill</title>
    </head>
    <body>
        <%@page import="dao.DAOCustomer"%>
        <%@page import="entity.Customer, java.util.Vector"%>
        <%
            DAOCustomer dao = new DAOCustomer();
            Vector<Customer> vector 
                    = dao.getAllCustomer("select * from Customer");
        %>
        <form action="../BillControllerURL"  method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="billID">Bill ID</label></td>
                    <td><input type="text" name="billID" id = "billID" placeholder="BI001"></td>
                </tr>
                <tr>
                    <td><label for="recAddress">Address</label></td>
                    <td><input type="text" name="recAddress" id = "recAddress"></td>
                </tr>
                <tr>
                    <td><label for="recPhone">recPhone</label></td>
                    <td><input type="text" name="recPhone" id = "recPhone"></td>
                </tr>
                <tr>
                    <td><label for="note">note</label></td>
                    <td><input type="text" name="note" id = "note"></td>
                </tr>
                <tr>
                    <td><label for="totalMoney">Total Money</label></td>
                    <td><input type="text" name="totalMoney" id = "totalMoney"></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" checked > Enable
                        <input type="radio" name="status" id = "status" value="0" checked > Disable
                    </td>
                </tr>
                <tr>
                    <td><label for="cusID">Customer ID</label></td>
                    <!--<td><input type="text" name="cusID" id = "cusID"></td>-->
                    <td>
                        <select name="cusID" id="cusID">
                            <%for(Customer temp : vector){%>
                            <option value="<%=temp.getCusID()%>"><%=temp.getCusID()%> : <%=temp.getCusName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Bill" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
