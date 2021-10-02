<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
	e.printStackTrace();
}
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Record Instruments Usage Page</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
		<ul class="navbar-nav">
			<li class="nav-item"><a href="index" class="nav-link ">Home</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> รายการบริการ </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="loadServicerequest ">การบริการทั้งหมด</a>
					<a class="dropdown-item" href="loadChangRequestForm">การเปลี่ยนแปลงบริการเครื่องมือวิทยาศาสตร์</a>
				</div></li>
			<li class="nav-item  active"><a	href="do_getListTestAnalysisForm" class="nav-link">รายการวิเคราห์ทดสอบ</a></li>

		</ul>
		<div class="navbar-nav ml-auto">
			<a href="varifylogout" class="nav-link">Logout</a>
		</div>
	</nav>


	<div style="margin-left: 5%; margin-right: 5%; margin-top: 5%;">


	</div>
</body>
</html>