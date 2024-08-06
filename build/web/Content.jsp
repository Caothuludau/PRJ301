<%-- 
    Document   : Content
    Created on : Mar 9, 2023, 11:16:31 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Book"%>
<%@page import="java.util.Vector"%>


<!--Section-->
<%
    Vector<Book> vecB = (Vector<Book>) request.getAttribute("data");
%>    
<%
    if (vecB != null && vecB.size() > 0) {
%>

<section class="py-12">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <%
                for (Book book : vecB) {
            %>
            <div class="col mb-5">
                <div class="card h-100" style="width: 16vw">
                    <form action="CartControllerURL">
                        <a href="HomeControllerURL?go=viewBookDetail&bookID=<%=book.getBookID()%>">
                            <!-- Book image-->      
                            <img class="card-img-top mx-auto d-block" src="images/<%=book.getImage()%>" alt="..." style="width: 16vw; height: 21vw"/>
                            <!-- Book details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Book name-->
                                    <h6 class="fw-bolder"><%=book.getBookName()%></h6>
                                    <!-- Book author-->
                                    <p style="font-style: oblique">- <%=book.getAuthor()%> -</p>
                                    <!-- Book price-->
                                    <p style="font-weight: bold; color: red"><%=book.getPrice()%> $</p>
                                </div>
                            </div>
                        </a>
                        <!-- Book actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <form action="CartController" method="POST">
                                <input type="hidden" name="bookID" value="<%=book.getBookID()%>"/>
                                <input type="hidden" name="price" value="<%=book.getPrice()%>"/>
                                <div class="text-center">
                                    <div class="d-flex justify-content-center">
                                        <input type="number" name="quantity" value="1" min="1" max="<%=book.getQuantity()%>" class="form-control form-control-lg text-center">
                                    </div>
                                </div>
                                <br>
                                <div class="d-flex justify-content-center">
                                    <button class="btn btn-outline-dark" type="submit" name="go" value="addToCart">
                                        Add To Cart
                                    </button>
                                </div>
                            </form>
                        </div>
                    </form>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</section>
<%
    }
%>