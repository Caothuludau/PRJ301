<%-- 
    Document   : adminindex
    Created on : Mar 20, 2023, 9:57:04 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Admin"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/index.css" rel="stylesheet" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper-base.min.js" integrity="sha384-8oN5+tGj5gBcNau5d30ruW+1ldqJ/2yPBQ/5t4nDvJ2QZ9YdrnK1JhW8EaGJMWy" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
    </head>
    <%            
    Admin admin = (Admin) session.getAttribute("admin");
            if (admin == null) {
                response.sendRedirect("AdminControllerURL?go=login");
                return;
            }
    %>
    <%
    String viewMode = "manual";
    if ( request.getAttribute("viewMode") != null) {
        viewMode = (String) request.getAttribute("viewMode");
    }          
    %>
    <body>
        <jsp:include page="adminJSP/PannerAD.jsp"></jsp:include>
        <%if (viewMode.equals("manual")) {%>
            <jsp:include page="adminJSP/Manual.jsp"></jsp:include>
        <%}%>
        <%if (viewMode.equals("customerManage")) {%>
            <jsp:include page="adminJSP/CustomerManage.jsp"></jsp:include>
        <%}%>
        <%if (viewMode.equals("bookManage")) {%>
            <jsp:include page="adminJSP/BookManage.jsp"></jsp:include>
        <%}%>
        <%if (viewMode.equals("billManage")) {%>
            <jsp:include page="adminJSP/BillManage.jsp"></jsp:include>
        <%}%>
        <%if (viewMode.equals("billDetailView")) {%>
            <jsp:include page="adminJSP/BillDetailView.jsp"></jsp:include>
        <%}%>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
