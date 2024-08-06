<%-- 
    Document   : ViewBook
    Created on : Feb 16, 2023, 8:04:32 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet, entity.Book"%>
<%@page import="dao.DAOTag"%>
<%@page import="entity.Tag, java.util.Vector"%>
<%@page import="jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rs = (ResultSet) request.getAttribute("data");
            String title = (String) request.getAttribute("title");
            DAOTag dao = new DAOTag();
            Vector<Tag> vec = dao.getAllTag("select * from Tag");
        %>
        <select name="tagID">
            <%for (Tag temp: vec){%>
            <option value="<%=temp.getTagID()%>"><%=temp.getTagName()%></option>
            <%}%>
        </select>
        <table border="1">
            <caption>Book list</caption>
            <tr>
                <th>Book ID</th>
                <th>Category</th>
                <th>Book Name</th>
                <th>Image</th>
                <th>Description</th>
                <th>Author</th>
                <th>Number of pages</th>
                <th>Size</th>
                <th>Release Date</th>
                <th>Quantity</th>
                <th>Price </th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%while(rs.next()){%>
            <%if (rs.getString(13) == request.getParameter("tagID"))%>
            <tr>
                <td> <%=rs.getString(1)%> </td>
                <td> <%=rs.getString(12)%> </td>
                <td> <%=rs.getString(2)%> </td>                                
                <td><img src="./images/<%= rs.getString(3)%>"  width="100" height="150"></td>
                <td> <%=rs.getString(4)%> </td>
                <td> <%=rs.getString(5)%> </td>
                <td> <%=rs.getInt(6)%> </td>
                <td> <%=rs.getString(7)%> </td>
                <td> <%=rs.getString(8)%> </td>
                <td> <%=rs.getInt(9)%> </td>
                <td> <%=rs.getDouble(10)%> </td>   
                <td> <%=(rs.getInt(11) == 1 ? "Enable" : "Disable")%> </td>
                <td><a href="BookControllerURL?go=update&bookID=<%=rs.getString(1)%>&cateID=<%=rs.getInt(12)%>" >update</a></td>
                <td><a href="BookControllerURL?go=delete&bookID=<%=rs.getString(1)%>">Delete</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="ManagePages/BookManage.html">Book Manage</a></td>
                <td><a href="./InsertPages/InsertBook.jsp">Insert Book</a></td>
            </tr>
        </table>
    </body>
</html>
