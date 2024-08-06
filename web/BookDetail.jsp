<%-- 
    Document   : BookDetail
    Created on : Mar 16, 2023, 8:19:34 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Book"%>
<%@page import="java.util.Vector"%>

<%
    Vector<Book> vecB = (Vector<Book>) request.getAttribute("data");
    Book book = new Book();
    if (vecB != null && vecB.size() > 0) {
        book = vecB.get(0);
    }
%>     
<div class="container">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title text-center">Book Detail</h3>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-3">
                    <img src="images/<%=book.getImage()%>" alt="Book Cover" class="img-fluid">
                </div>
                <div class="col-md-9">
                    <h3><%=book.getBookName()%></h3>
                    <p><strong>Author:</strong> <%=book.getAuthor()%></p>    
                    <p><strong>Pages:</strong> <%=book.getPageNum()%></p>
                    <p><strong>Size:</strong> <%=book.getSize()%></p>
                    <p><strong>Release Date:</strong> <%=book.getReleaseDate()%></p>
                    <p><strong>Price: </strong> <%=book.getPrice()%> $</p>
                    <p><strong>Description: </strong> <%=book.getDescription()%></p>
                    <p><strong>Book(s) left: </strong> <%=book.getQuantity()%></p>
                    <!-- Book actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <form action="CartControllerURL" method="POST">
                                <input type="hidden" name="bookID" value="<%=book.getBookID()%>"/>
                                <input type="hidden" name="price" value="<%=book.getPrice()%>"/>
                                <div class="text-center">
                                    <div class="d-flex">
                                        <input type="number" name="quantity" value="1" min="1" max="<%=book.getQuantity()%>"/><br>
                                    </div>
                                </div>
                                <br>
                                <button class="d-flex btn btn-outline-dark" type="submit" name="go" value="addToCart">
                                Add To Cart
                                </button>
                           
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>





