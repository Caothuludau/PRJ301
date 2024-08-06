<%-- 
    Document   : ViewTag
    Created on : Feb 14, 2023, 1:50:23 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Tag, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Tag> vec = (Vector<Tag>) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
        %>
        <table border = "1">
            <caption>Tag LIST</caption>
            <tr>
                <th>Tag ID</th>
                <th>Tag Name</th>
                <th>Status</th>
            </tr>");
            <% for (Tag temp : vec) {%>
            <tr>
                <td><%=temp.getTagID()%> </td>
                <td><%=temp.getTagName()%> </td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%> </td>
                <td><a href="TagControllerURL?go=update&tagID=" + <%=temp.getTagID()%> ">Update</a></td>
                <td><a href="TagControllerURL?go=delete&tagID=" + <%=temp.getTagID()%> ">Delete</a></td>
            </tr>");
            <%}%>
            <tr>
                <td><a href="ManagePages/TagManage.html">Tag Manage</a></td>
                <td><a href="./InsertPages/InsertTag.html">Insert Tag</a></td>
            </tr>
        </table>
    </body>
</html>
