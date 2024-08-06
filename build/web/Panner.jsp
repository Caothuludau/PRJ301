<%-- 
    Document   : Panner
    Created on : Mar 9, 2023, 5:24:29 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Customer"%>
<%

            Customer cus = (Customer) session.getAttribute("user");
            String view = "unLoggedIn";
            if (cus != null){
                view = "loggedIn";
            }
            String search = request.getParameter("bookName");
            if (search == null) {
                search = "";
            }
%>


<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-6 px-lg-8">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <%if (view.equals("loggedIn")) {%>
                    <li class="nav-item nav-link" > Welcome <%=cus.getCusName()%></li>
                    <li class="nav-item"><a class="nav-link" href="ClientControllerURL?go=logout">Logout</a></li>
                <%}%>
                <%if (view.equals("unLoggedIn")) {%>
                    <li class="nav-item"><a class="nav-link" href="ClientControllerURL?go=login">Login</a></li>
                <%}%>
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="HomeControllerURL">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="HomeControllerURL?go=viewBill">Bills</a></li>
                <form action="HomeControllerURL" class="d-flex">
                    <li class="nav-item"><input class="nav-link" style="margin-right: 10px; margin-left: 20px" type="search" name="bookName" value="<%= search%>" placeholder="Search by book name"/></li>
                    <button class="btn btn-outline-dark" type="submit" name="go" value="searchBook">
                        Search
                    </button>
                </form>
            </ul>
            <form class="d-flex" action="CartControllerURL">
                <button class="btn btn-outline-dark" type="submit" value="viewCart" name="go">
                    <i class="bi-cart-fill me-1"></i>
                    Cart
                    <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                </button>
            </form>
        </div>
    </div>
</nav>
<!-- Header-->
<header class="py-5">
  <div class="container px-4 px-lg-5 my-5">
    <div class="text-center text-white">
      <h1 class="display-4 fw-bolder" style="text-shadow: 3px 3px #000">BookoStoru</h1>
      <p class="lead fw-normal text-white-50 mb-0" style="text-shadow: 1px 1px #000">- Giữ tinh túy, gửi tương lai -</p>
    </div>
  </div>
</header>

<style>
  header {
    background-image: url("images/header.jpg");
    opacity: 0.8;
    background-size: cover;
  }
</style>
