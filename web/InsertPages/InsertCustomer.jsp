<%-- 
    Document   : InsertCustomer
    Created on : Mar 7, 2023, 11:55:59 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Customer</title>
    </head>
    <body>
        <form action = "../CustomerControllerURL" method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="cusID">Customer ID</label></td>
                    <td><input type="text" name="cusID" id = "cusID"></td>
                </tr>
                <tr>
                    <td><label for="cusName">Customer name</label></td>
                    <td><input type="text" name="cusName" id = "cusName"></td>
                </tr>
                <tr>
                    <td><label for="username">username</label></td>
                    <td><input type="text" name="username" id = "username"></td>
                </tr>
                <tr>
                    <td><label for="password">password</label></td>
                    <td><input type="text" name="password" id = "password"></td>
                </tr>
                <tr>
                    <td><label for="address">address</label></td>
                    <td><input type="text" name="address" id = "address"></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="text" name="phone" id = "phone"></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" checked > Enable
                        <input type="radio" name="status" id = "status" value="0" checked > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Customer" name="submit"></td>
                    <td><input type="reset" value="reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
