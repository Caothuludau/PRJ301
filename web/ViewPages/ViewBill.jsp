<%-- 
    Document   : ViewBill
    Created on : Feb 14, 2023, 1:50:23 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Bill, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Bill> vec = (Vector<Bill>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
            String errCode = (String) request.getAttribute("err");
        %>
        <table border = "1">
            <caption>BILL LIST</caption>
            <tr>
                <th>Bill ID</th>
                <th>Date Create</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Note</th>
                <th>Total Money</th>
                <th>Status</th>
                <th>Customer ID</th>
            </tr>
            <% for (Bill temp : vec) {%>
            <tr>
                <td><%=temp.getBillID()%> </td>
                <td><%=temp.getDateCreate()%> </td>
                <td><%=temp.getRecAddress()%> </td>
                <td><%=temp.getRecPhone()%> </td>
                <td><%=temp.getNote()%> </td>
                <td><%=temp.getTotalMoney()%> </td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%> </td>
                <td><%=temp.getCusID()%> </td>
                <td><a href="BillControllerURL?go=update&billID=<%=temp.getBillID()%> ">Update</a></td>
                <td><a href="BillControllerURL?go=delete&billID=<%=temp.getBillID()%> ">Delete</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="ManagePages/BillManage.html">Bill Manage</a></td>
                <td><a href="./InsertPages/InsertBill.jsp">Insert Bill</a></td>
            </tr>
        </table>
        <% if (errCode != null && errCode.equals("delete")) {%>
            <p style="color: red"> This bill's detail data is still available. Can not delete.</p>
        <%}%>
    </body>
</html>
