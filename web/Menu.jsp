<%-- 
    Document   : Menu
    Created on : Mar 9, 2023, 11:17:19 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.DAOBook, entity.Book"%>
<%@page import="dao.DAOCategory, entity.Category"%>
<%@page import="java.util.Vector"%>

<%
            DAOCategory cate = new DAOCategory();
            String sql = "Select * from Category";
            Vector<Category> vec = cate.getAllCategory(sql);
%>

<aside class="menu">
    <div class="menu-header">
        <h5>Categories</h5>
    </div>
    <div class="menu-body">
        <ul>
            <% for (Category i : vec) { %>
            <li>
                <a href="HomeControllerURL?go=listBook&id=<%= i.getCateID() %>"> <%= i.getCateName() %>
                </a>
            </li>
            <% } %>
        </ul>
    </div>
</aside>
        <style>
.menu {
    background-color: #f8f9fa;
    width: 100%;
    height: 100%;
/*    position: fixed;*/
    top: 0;
    left: 0;
    padding: 1rem;
}

.menu-header {
    margin-bottom: 1rem;
}

.menu-header h5 {
    font-size: 1.2rem;
    font-weight: bold;
    margin: 0;
}

.menu-body ul {
    list-style-type: none;
    padding: 0;
}

.menu-body li {
    margin-bottom: 0.5rem;
}

.menu-body a {
    color: #343a40;
    text-decoration: none;
    font-size: 1.1rem;
}

.menu-body a:hover {
    color: #007bff;
}

    </style>
