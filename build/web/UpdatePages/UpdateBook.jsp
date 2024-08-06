<%-- 
    Document   : UpdateBook
    Created on : Feb 16, 2023, 8:49:01 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, java.sql.ResultSet, entity.Book"%>
<%@page import="dao.DAOCategory, entity.Category"%>
<%@page import="jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Book> vecP = (Vector<Book>) request.getAttribute("data");
            Book pro = vecP.get(0);
            DAOCategory daoc = new DAOCategory();
            Vector<Category> vecC 
                    = daoc.getAllCategory("select * from Category");
        %>
        <form action="BookControllerURL"  method = "POST">
            <input type="hidden" name="go" value="update">
            <input type="hidden" name ="tagID" value="<%=pro.getTagID()%>">
            <table>
                <tr>
                    <td><label for="bookID">Book ID</label></td>
                    <td><input type="text" name="bookID" id = "bookID" value="<%=pro.getBookID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="bookName">Book Name</label></td>
                    <td><input type="text" name="bookName" id = "bookName" value="<%=pro.getBookName()%>"></td>
                </tr>
                <tr>
                    <td><label for="image">image</label></td>
                    <td><input type="text" name="image" id = "image" value="<%=pro.getImage()%>"></td>
                </tr>
                <tr>
                    <td><label for="description">description</label></td>
                    <td><input type="text" name="description" id = "description" value="<%=pro.getDescription()%>"></td>
                </tr>
                <tr>
                    <td><label for="author">author</label></td>
                    <td><input type="text" name="author" id = "author" value="<%=pro.getAuthor()%>"></td>
                </tr>
                <tr>
                    <td><label for="pageNum">image</label></td>
                    <td><input type="pageNum" name="pageNum" id = "pageNum" value="<%=pro.getPageNum()%>"></td>
                </tr>
                <tr>
                    <td><label for="size">size</label></td>
                    <td><input type="text" name="size" id = "size" value="<%=pro.getSize()%>"></td>
                </tr>
                <tr>
                    <td><label for="releaseDate">Release Date</label></td>
                    <td><input type="text" name="releaseDate" id = "releaseDate" value="<%=pro.getReleaseDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="quantity">quantity</label></td>
                    <td><input type="text" name="quantity" id = "quantity" value="<%=pro.getQuantity()%>"></td>
                </tr>
                <tr>
                    <td><label for="price">price</label></td>
                    <td><input type="text" name="price" id = "price" value="<%=pro.getPrice()%>"></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id="status-enable" value="1" <%=(pro.getStatus() == 1 ? "checked" : "")%> >Enable
                        <input type="radio" name="status" id="status-disable" value="0" <%=(pro.getStatus() == 0 ? "checked" : "")%> >Disable
                    </td>
                </tr>
                <tr>
                    <td><label for="cateID">Category</label></td>
                    <td>
                        <select name="cateID">
                            <%for (Category temp: vecC){%>
                                <%if (temp.getCateID() == pro.getCateID()) {%>
                                    <option value="<%=temp.getCateID()%>"><%=temp.getCateID()%> : <%=temp.getCateName()%></option>
                                <%}%>
                            <%}%>
                            <%for (Category temp: vecC){%>
                                <%if (temp.getCateID() != pro.getCateID()) {%>
                                    <option value="<%=temp.getCateID()%>"><%=temp.getCateID()%> : <%=temp.getCateName()%></option>
                                <%}%>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Book" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
