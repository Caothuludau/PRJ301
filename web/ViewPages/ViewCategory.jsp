<%-- 
    Document   : ViewCategory
    Created on : Feb 14, 2023, 1:50:23 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Category, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Category> vec = (Vector<Category>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
            String errCode = (String) request.getAttribute("err");
        %>
        <table border = "1">
            <caption>CATEGORY LIST</caption>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Description</th>
                <th>Status</th>
            </tr>
            <% for (Category temp : vec) {%>
            <tr>
                <td><%=temp.getCateID()%> </td>
                <td><%=temp.getCateName()%> </td>
                <td><%=temp.getDescription()%> </td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%> </td>
                <td><a href="CategoryControllerURL?go=update&cateID=<%=temp.getCateID()%>">Update</a></td>
                <td><a href="CategoryControllerURL?go=delete&cateID=<%=temp.getCateID()%>">Delete</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="ManagePages/CategoryManage.html">Category Manage</a></td>
                <td><a href="./InsertPages/InsertCategory.html">Insert Category</a></td>
            </tr>
        </table>
            <% if (errCode != null && errCode.equals("delete")) {%>
                <p style="color: red"> This category's product(s) is still available. Can not delete.</p>
            <%}%>
    </body>
</html>
