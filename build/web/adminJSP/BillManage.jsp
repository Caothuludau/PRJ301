<%-- 
    Document   : BillManage
    Created on : Mar 20, 2023, 10:44:41 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Bill, java.util.Vector"%>
<!DOCTYPE html>

<%
    Vector<Bill> vec = (Vector<Bill>) request.getAttribute("data");
    String title = (String) request.getAttribute("title");
    String errCode = (String) request.getAttribute("err");
%>
<div class="table-responsive">
    <table class="table">
        <tcaption style="display: block; text-align: center;"><h3>BILL MANAGER</h3></tcaption>
        <tr>
            <th>Bill ID</th>
            <th>Date Create</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Note</th>
            <th>Total Money</th>
            <th>Status</th>
            <th>Customer ID</th>
        </tr>
        <% for (Bill temp : vec) {%>
        <tr>
            <td>
                <a href="AdminControllerURL?id=<%=temp.getBillID()%>&go=billDetailView">
                   <%=temp.getBillID()%>
                </a>
            </td>
            <td><%=temp.getDateCreate()%> </td>
            <td><%=temp.getRecAddress()%> </td>
            <td><%=temp.getRecPhone()%> </td>
            <td><%=temp.getNote()%> </td>
            <td><%=temp.getTotalMoney()%> </td>
            <td>
                <form action="BillControllerURL?go=update" method="POST" id="form">
                    <input type="hidden" name="billID" value="<%=temp.getBillID()%>"/>
                    <select name="status" onchange="submit()">
                        <option value="0" <%= temp.getStatus() == 0 ? "selected":""%> disabled="true">Deleted</option>
                        <option value="1" <%= temp.getStatus() == 1 ? "selected":""%> disabled="true">Wait</option>
                        <option value="2" <%= temp.getStatus() == 2 ? "selected":""%>>Process</option>
                        <option value="3" <%= temp.getStatus() == 3 ? "selected":""%>>Done</option>
                    </select>

                </form>
            </td>
            <td><%=temp.getCusID()%> </td>
                <%if (temp.getStatus() == 1 || temp.getStatus() == 0) {%>
                    <td><a href="BillControllerURL?go=delete&billID=<%=temp.getBillID()%> ">Delete</a></td>
                <%}%>
        </tr>
        <%}%>
        <tr>
            <td><a href="./InsertPages/InsertBill.jsp">Insert Bill</a></td>
        </tr>
    </table>
    <% if (errCode != null && errCode.equals("delete")) {%>
        <p style="color: red"> This bill's detail data is still available. Can not delete.</p>
    <%}%>
</div>

<script>
    fucntion submit() {
        document.getElementById("form").submit();
    }
</script>

