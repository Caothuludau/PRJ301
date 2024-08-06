<%-- 
    Document   : InsertBook
    Created on : Mar 5, 2023, 12:46:49 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="entity.Category, dao.DAOCategory"%>
<%@page import="entity.Tag, dao.DAOTag"%>
<%@page import="jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Insert Book</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            DAOCategory daoc = new DAOCategory();
            Vector<Category> vecC 
                    = daoc.getAllCategory("select * from Category");
            DAOTag daot = new DAOTag();
            Vector<Tag> vecT 
                    = daot.getAllTag("select * from Tag");
        %>
        <form action="../BookControllerURL"  method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="bookID">Book ID</label></td>
                    <td><input type="text" name="bookID" id = "bookID" placeholder="VD: B009 B099 B999"></td>
                </tr>
                <tr>
                    <td><label for="bookName">Book Name</label></td>
                    <td><input type="text" name="bookName" id = "bookName"></td>
                </tr>
                <tr>
                    <td><label for="image">Image</label></td>
                    <td><input type="text" name="image" id = "image" placeholder="Image link"></td>
                </tr>
                <tr>
                    <td><label for="description"> Description</label></td>
                    <td><input type="text" name="description" id = "description"></td>
                </tr>
                <tr>
                    <td><label for="author"> Author</label></td>
                    <td><input type="text" name="author" id = "author"></td>
                </tr>
                <tr>
                    <td><label for="pageNum">Page Number</label></td>
                    <td><input type="pageNum" name="pageNum" id = "pageNum"></td>
                </tr>
                <tr>
                    <td><label for="size">size</label></td>
                    <td><input type="text" name="size" id = "size" placeholder="VD: 32cm x 12cm"></td>
                </tr>
                <tr>
                    <td><label for="releaseDate">Release Date</label></td>
                    <td><input type="date" name="releaseDate" id = "releaseDate"></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id = "quantity"></td>
                </tr>
                <tr>
                    <td><label for="price">Price</label></td>
                    <td><input type="text" name="price" id = "price" placeholder="Price by dollar"></td>
                </tr>
                <tr>
                    <td><label for="status">Status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" checked > Enable
                        <input type="radio" name="status" id = "status" value="0" checked > Disable
                    </td>
                </tr>
                <tr>
                    <td><label for="cateID">Category</label></td>
                    <td>
                        <select name="cateID" id="cateID">
                            <%for(Category temp : vecC){%>
                            <option value="<%=temp.getCateID()%>"><%=temp.getCateID()%> : <%=temp.getCateName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="tagID">Tag</label></td>
                    <td>
                        <select name="tagID" id="tagID">
                            <%for(Tag temp : vecT){%>
                            <option value="<%=temp.getTagID()%>"><%=temp.getTagID()%> : <%=temp.getTagName()%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Book" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>   
            </table>
        </form>
    </body>
</html>
