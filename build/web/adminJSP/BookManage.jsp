<%-- 
    Document   : BookManage
    Created on : Mar 20, 2023, 10:43:53 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet, entity.Book"%>
<%@page import="dao.DAOTag"%>
<%@page import="entity.Tag, java.util.Vector"%>
<%@page import="jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>
<body>
    <%
        ResultSet rs = (ResultSet) request.getAttribute("data");
        String title = (String) request.getAttribute("title");
        DAOTag dao = new DAOTag();
        Vector<Tag> vec = dao.getAllTag("select * from Tag");
    %>
    <div class="table-responsive">
        <table class="table">
            <tcaption style="display: block; text-align: center;"><h3>BOOK MANAGER</h3></tcaption>

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
                <td> <%=rs.getString(15)%> </td>
                <td> <%=rs.getString(2)%> </td>                                
                <td><img src="./images/<%= rs.getString(3)%>"  width="100" height="150"></td>
                <td>
                    <div class="description"><%=rs.getString(4)%></div>
                    <button class="toggle-button">Expand</button>
                </td>
                <td> <%=rs.getString(5)%> </td>
                <td> <%=rs.getInt(6)%> </td>
                <td> <%=rs.getString(7)%> </td>
                <td> <%=rs.getString(8)%> </td>
                <td> <%=rs.getInt(9)%> </td>
                <td> <%=rs.getDouble(10)%> </td>   
                <td> <%=(rs.getInt(11) == 1 ? "Enable" : "Disable")%> </td>
                <td><a href="BookControllerURL?go=update&bookID=<%=rs.getString(1)%>&cateID=<%=rs.getInt(12)%>" >Update</a></td>
                <td><a href="BookControllerURL?go=delete&bookID=<%=rs.getString(1)%>">Delete</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="./InsertPages/InsertBook.jsp">Insert Book</a></td>
            </tr>
        </table>
    </div>
            <style>
.description {
    max-height: 100px;
    overflow: hidden;
}
.toggle-button {
    display: block;
    margin-top: 10px;
}
.description.expanded {
    max-height: none;
}
</style>
<script>
$(document).ready(function() {
    $('.toggle-button').click(function() {
        var $description = $(this).prev('.description');
        $description.toggleClass('expanded');
        $(this).text(function(i, text){
            return text === "Expand" ? "Collapse" : "Expand";
        });
    });
});
</script>