<%-- 
    Document   : index
    Created on : Mar 9, 2023, 11:19:30 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            String viewMode = "list";
            if ( request.getAttribute("viewMode") != null) {
                viewMode = (String) request.getAttribute("viewMode");
            }          
        %>
    <body>
        <jsp:include page="Panner.jsp"></jsp:include>
        <div class="container-fluid">
        <div class="row">
            <div class="col-lg-2 col-md-4 col-sm-12">
                <jsp:include page="Menu.jsp"></jsp:include>
            </div>
            <div class="col-lg-10 col-md-8 col-sm-12">
                <%if (viewMode.equals("list")) {%>
                    <jsp:include page="Content.jsp"></jsp:include>
                <%}%>
                <%if (viewMode.equals("detail")) {%>
                    <jsp:include page="BookDetail.jsp"></jsp:include>
                <%}%>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        </div>
    </body>
</html>
