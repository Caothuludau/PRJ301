<%-- 
    Document   : UpdateTag
    Created on : Mar 4, 2023, 9:44:02 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Tag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Tag> vec = (Vector<Tag>) request.getAttribute("data");
            Tag tag = vec.get(0);
        %>
        
        <form action = "TagControllerURL" method = "POST">
            <input type="hidden" name="go" value="update">
            <input type="hidden" name="tagID" value="<%=tag.getTagID()%>" readonly>
            <table>
                <tr>
                    <td><label for="tagName">Tag Name</label></td>
                    <td><input type="text" name="tagName" id = "tagName"  value = "<%=tag.getTagName()%> "></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" <%=(tag.getStatus() == 1 ? "checked" : "")%> > Enable
                        <input type="radio" name="status" id = "status" value="0" <%=(tag.getStatus() == 0 ? "checked" : "")%> > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Tag" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
