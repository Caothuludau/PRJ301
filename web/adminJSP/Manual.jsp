<%-- 
    Document   : Manual
    Created on : Mar 22, 2023, 4:32:17 PM
    Author     : CaoThuLuDau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Bookstore Admin Panel</title>
	<!-- Bootstrap 5 CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css">
	<!-- Custom CSS -->
	<style>
		.container {
			margin-top: 50px;
		}
		h1 {
			margin-bottom: 30px;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>Bookstore Admin Panel</h1>
		<h2><a class="nav-link" aria-current="page" href="AdminControllerURL?go=customerManage">Customer Manager Page</a></h2>
		<p>Use this page to manage customers in the bookstore.</p>
		<ul>
			<li>View all the customers signed up for the bookstore.</li>
			<li>Manually insert a new customer.</li>
			<li>Change the status and information of a user account, except for the customer ID.</li>
			<li>Delete a customer who does not have any bills.</li>
			<li>Search for a customer by name or ID.</li>
		</ul>
		<h2><a class="nav-link" aria-current="page" href="AdminControllerURL?go=bookManage">Book Manager Page</a></h2>
		<p>Use this page to manage books in the bookstore.</p>
		<ul>
			<li>View all the books' information.</li>
			<li>Insert, delete or update information of books. Directly update quantity for books.</li>
			<li>Search for books by name or ID.</li>
		</ul>
		<h2><a class="nav-link" aria-current="page" href="AdminControllerURL?go=billManage">Bill Manager Page</a></h2>
		<p>Use this page to manage bills in the bookstore.</p>
		<ul>
			<li>Check for bills in different status: Wait (Customer order) -> Process (Books on shipping) -> Done (Book shipped).</li>
			<li>View detailed information of a specific bill.</li>
			<li>Delete the bill which is deleted or on wait status.</li>
			<li>Search for bills by bill ID or customer ID.</li>
		</ul>
	</div>
	<!-- Bootstrap 5 JS Bundle -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
