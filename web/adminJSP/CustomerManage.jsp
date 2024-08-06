<%-- 
    Document   : CustomerManage
    Created on : Mar 20, 2023, 10:41:10 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Customer, java.util.Vector"%>
<!DOCTYPE html>

<%
    Vector<Customer> vec = (Vector<Customer>) request.getAttribute("data");
    String title = (String) request.getAttribute("title");
    String errCode = (String) request.getAttribute("err");
%>
    <div class="table-responsive">
        <table class="table">
                <tcaption style="display: block; text-align: center;"><h3>CUSTOMER MANAGER</h3></tcaption>

            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Username</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (Customer temp : vec) {%>
            <tr>
                <td><%=temp.getCusID()%></td>
                <td><%=temp.getCusName()%></td>
                <td><%=temp.getUsername()%></td>
                <td><%=temp.getAddress()%></td>
                <td><%=temp.getPhone()%></td>
                <td><%=(temp.getStatus() == 1?"Enable" : "Disable")%></td>
                <td><a href="CustomerControllerURL?go=update&cusID=<%=temp.getCusID()%>">Update</a></td>
                <td><a href="CustomerControllerURL?go=delete&cusID=<%=temp.getCusID()%>">Delete</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="./InsertPages/InsertCustomer.jsp">Insert Customer</a></td>
            </tr>
        </table>
        <% if (errCode != null && errCode.equals("delete")) {%>
        <p style="color: red"> This customer's bill(s) is still available. Can not delete.</p>
        <%}%>
    </div>

        <style type="text/css">
            .img-cart {
                display: block;
                max-width: 50px;
                height: auto;
                margin-left: auto;
                margin-right: auto;
            }
            table tr td{
                border:1px solid #FFFFFF;
            }

            table tr th {
                background:#eee;
            }

            .panel-shadow {
                box-shadow: rgba(0, 0, 0, 0.3) 7px 7px 7px;
            }
        </style>


