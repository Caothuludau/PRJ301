<%-- 
    Document   : PannerAD
    Created on : Mar 20, 2023, 10:11:43 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Admin"%>
<%

            Admin ad = (Admin) session.getAttribute("admin");
            String view = "unLoggedIn";
            if (ad != null){
                view = "loggedIn";
            }
            String search = request.getParameter("bookName");
            if (search == null) {
                search = "";
            }
            String viewMode = "";
            if ( request.getAttribute("viewMode") != null) {
                viewMode = (String) request.getAttribute("viewMode");
            }          
    
%>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-6 px-lg-8">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <%if (view.equals("loggedIn")) {%>
                    <li class="nav-item nav-link" > Welcome <%=ad.getAdmin()%></li>
                    <li class="nav-item"><a class="nav-link" href="AdminControllerURL?go=logout">Logout</a></li>
                <%}%>
                <%if (view.equals("unLoggedIn")) {%>
                    <li class="nav-item"><a class="nav-link" href="AdminControllerURL?go=login">Login</a></li>
                <%}%>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="AdminControllerURL?go=manual">Home</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="AdminControllerURL?go=customerManage">Customer Manager</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="AdminControllerURL?go=bookManage">Book Manager</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page" href="AdminControllerURL?go=billManage">Bill Manager</a></li>
                <form action="AdminControllerURL" class="d-flex">
                    <%if (viewMode.equals("customerManage")) {%>
                    <li class="nav-item"><input class="nav-link" style="margin-right: 10px; margin-left: 20px" type="search" name="cusInfo" value="<%= search%>" placeholder="Search by customer name or cusID"/></li>
                    <button class="btn btn-outline-dark" type="submit" name="go" value="searchCustomer">
                        Search
                    </button>
                    <%}%>
                    <%if (viewMode.equals("bookManage")) {%>
                    <li class="nav-item"><input class="nav-link" style="margin-right: 10px; margin-left: 20px" type="search" name="bookInfo" value="<%= search%>" placeholder="Search by book name or bookID"/></li>
                    <button class="btn btn-outline-dark" type="submit" name="go" value="searchBook">
                        Search
                    </button>
                    <%}%>
                    <%if (viewMode.equals("billManage")) {%>
                    <li class="nav-item"><input class="nav-link" style="margin-right: 10px; margin-left: 20px" type="search" name="billInfo" value="<%= search%>" placeholder="Search by billID or cusID"/></li>
                    <button class="btn btn-outline-dark" type="submit" name="go" value="searchBill">
                        Search
                    </button>
                    <li class="nav-item">
                        <select class="form-select" name="billStatus">
                            <option value="Deleted" <%= search=="Deleted" ? "selected" : "" %>>Deleted</option>
                            <option value="Wait" <%= search=="Wait" ? "selected" : "" %>>Wait</option>
                            <option value="Process" <%= search=="Process" ? "selected" : "" %>>Process</option>
                            <option value="Done" <%= search=="Done" ? "selected" : "" %>>Done</option>
                        </select>
                    </li>
                    <button class="btn btn-outline-dark" type="submit" name="go" value="searchBillByStatus">
                        Search
                    </button>
                    <%}%>
                </form>
            </ul>
        </div>
    </div>
</nav>
