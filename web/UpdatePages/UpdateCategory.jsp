<%-- 
    Document   : UpdateCategory
    Created on : Mar 4, 2023, 9:44:02 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Category"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Category> vec = (Vector<Category>) request.getAttribute("data");
            Category cat = vec.get(0);
        %>
        
        <form action = "CategoryControllerURL" method = "POST">
            <input type="hidden" name="go" value="update">
            <input type="hidden" name="cateID" value="<%=cat.getCateID()%>" readonly>
            <table>
                <tr>
                    <td><label for="cateName">Category Name</label></td>
                    <td><input type="text" name="cateName" id = "cateName"  value = "<%=cat.getCateName()%> "></td>
                </tr>
                <tr>
                    <td><label for="cateName">Description</label></td>
                    <td><input type="text" name="description" id = "description"  value = "<%=cat.getDescription()%> "></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" <%=(cat.getStatus() == 1 ? "checked" : "")%> > Enable
                        <input type="radio" name="status" id = "status" value="0" <%=(cat.getStatus() == 0 ? "checked" : "")%> > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Category" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
