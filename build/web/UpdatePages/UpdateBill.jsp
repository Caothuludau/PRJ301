<%-- 
    Document   : UpdateBill
    Created on : Mar 4, 2023, 9:44:02 AM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Bill"%>
<%@page import="dao.DAOCustomer, entity.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Bill> vec = (Vector<Bill>) request.getAttribute("data");
            Bill bill = vec.get(0);
            DAOCustomer daoc = new DAOCustomer();
            Vector<Customer> vecC = daoc.getAllCustomer("select * from Customer");
        %>
        
        <form action="BillControllerURL"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="billID">Bill ID</label></td>
                    <td><input type="text" name="billID" id = "billID"  value = "<%=bill.getBillID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="dateCreate">Created on</label></td>
                    <td><input type="text" name="dateCreate" id = "dateCreate"   value = "<%=bill.getDateCreate()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="recAddress">Address</label></td>
                    <td><input type="text" name="recAddress" id = "recAddress"   value = "<%=bill.getRecAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="recPhone">recPhone</label></td>
                    <td><input type="text" name="recPhone" id = "recPhone"   value = "<%=bill.getRecPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="note">note</label></td>
                    <td><input type="text" name="note" id = "note"   value = "<%=bill.getNote()%>"></td>
                </tr>
                <tr>
                    <td><label for="totalMoney">Total Money</label></td>
                    <td><input type="text" name="totalMoney" id = "totalMoney"   value = "<%=bill.getTotalMoney()%>"></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" <%=(bill.getStatus() == 1 ? "checked" : "")%> > Enable
                        <input type="radio" name="status" id = "status" value="0" <%=(bill.getStatus() == 0 ? "checked" : "")%> > Disable
                    </td>
<!--                </tr>
                                
                    <td><label for="cusID">Customer</label></td>
                        <td><input type="text" name="cusID" id = "cusID" value = "<%=bill.getCusID()%>"></td>
                    
                </tr>-->
                <tr>
                    <td><label for="cusID">Customer</label></td>
                    <td>
                        <select name="cusID" id="cusID">
                            <%for (Customer temp: vecC){%>
                                <%if (temp.getCusID() == bill.getCusID()) {%>
                                    <option value="<%=temp.getCusID()%>"><%=temp.getCusID()%> : <%=temp.getCusName()%></option>
                                <%}%>
                            <%}%>
                            <%for (Customer temp: vecC){%>
                                <%if (temp.getCusID() != bill.getCusID()) {%>
                                    <option value="<%=temp.getCusID()%>"><%=temp.getCusID()%> : <%=temp.getCusName()%></option>
                                <%}%>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Bill" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>

            </table>
        </form>
    </body>
</html>
