<%-- 
    Document   : cart
    Created on : Mar 16, 2023, 10:43:07 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Book, entity.Customer, entity.BillDetail, entity.Bill"%>
<%@page import=" dao.DAOBillDetail"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </head>
    <body>
        <%
            Customer cus = (Customer) session.getAttribute("user");
            if (cus == null) {
                response.sendRedirect("ClientControllerURL");
                return;
            }
            Vector<Bill> vecb = (Vector<Bill>) request.getAttribute("data");
            if (vecb == null || vecb.get(0) == null) {
        %>
        <div class="container-fluid  mt-100">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body cart">
                            <div class="col-sm-12 empty-cart-cls text-center">
                                <img src="https://i.imgur.com/dCdflKN.png" width="130" height="130" class="img-fluid mb-4 mr-3">
                                <h3><strong>You haven't have bill yet</strong></h3>
                                <h4>More book, more happiness. Add one more. :)</h4>
                                <a href="HomeControllerURL" class="btn btn-primary cart-btn-transform m-3" data-abc="true">continue shopping</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <style>
            .mt-100{
                margin-top:100px;
            }
            .card {
                margin-bottom: 30px;
                border: 0;
                -webkit-transition: all .3s ease;
                transition: all .3s ease;
                letter-spacing: .5px;
                border-radius: 8px;
                -webkit-box-shadow: 1px 5px 24px 0 rgba(68,102,242,.05);
                box-shadow: 1px 5px 24px 0 rgba(68,102,242,.05);
            }
            .card .card-header {
                background-color: #fff;
                border-bottom: none;
                padding: 24px;
                border-bottom: 1px solid #f6f7fb;
                border-top-left-radius: 8px;
                border-top-right-radius: 8px;
            }
            .card-header:first-child {
                border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0;
            }
            .card .card-body {
                padding: 30px;
                background-color: transparent;
            }
            .btn-primary, .btn-primary.disabled, .btn-primary:disabled {
                background-color: #4466f2!important;
                border-color: #4466f2!important;
            }
        </style>
        <%         } else {
            DAOBillDetail daobd = new DAOBillDetail();
        %>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
        <div class="container bootstrap snippets bootdey">
            <div class="col-md-12 col-sm-8 content">
                <div class="row">
                    <div class="col-md-12">
                        <ol class="breadcrumb">
                            <li><a href="HomeControllerURL">Home</a></li>
                            <li class="active">Cart</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-info panel-shadow">
                            <div class="panel-heading">
                                <h3>
                                    <img class="img-circle img-thumbnail" src="https://bootdey.com/img/Content/user_3.jpg">
                                    <%= cus.getCusName()%>
                                </h3>
                            </div>
                            <div class="panel-body"> 
                                <div class="table-responsive">

                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Bill ID</th>
                                                <th>Purchase Date</th>
                                                <th>Number of items</th>
                                                <th>Bill Total</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            for (Bill bill: vecb) {
                                            Vector<BillDetail> vecbd = daobd.getAllBillDetail("select * from BillDetail where billID ='"+ bill.getBillID() +"'");
                                                if (bill.getStatus() != 0)  {  
                                            %>
                                            <tr>
                                                <td>
                                                    <a href="HomeControllerURL?id=<%=bill.getBillID()%>&go=billDetail">
                                                        <%=bill.getBillID()%>
                                                    </a>
                                                </td>
                                                <td><%= bill.getDateCreate()%></td>
                                                <td><%= vecbd.size()%> items</td>
                                                <td><%= bill.getTotalMoney()%> $</td>
                                                <% if (bill.getStatus() == 1) {%>
                                                <td>Wait</td>
                                                <%}%>
                                                <% if (bill.getStatus() == 2) {%>
                                                <td>Process</td>
                                                <%}%>
                                                <% if (bill.getStatus() == 3) {%>
                                                <td>Done</td>
                                                <%}%>
                                                <%if (bill.getStatus() == 1) {%>
                                                <td><a href="BillControllerURL?go=deleteByUser&billID=<%=bill.getBillID()%> ">Delete</a></td>
                                                <%}%>
                                            </tr>
                                            <%            
                                                    }
                                                }
                                            %>    
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <a href="HomeControllerURL" class="btn btn-success"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continue Shopping</a>

                    </div>
                </div>
            </div>
        </div>
        <%            }
        %>
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
        <script type="text/javascript">
        </script>
    </div>
</body>
</html>
