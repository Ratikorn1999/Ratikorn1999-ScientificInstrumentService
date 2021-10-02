<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,Manager.*,LoginMJU.*,Class_End_Project.*"%>
<%
	Member member = null;
Student student = null;
AuthLogin auths = null;
Personnel personnel = null;
boolean checkauth = false;

try {
	student = (Student) session.getAttribute("student");
} catch (Exception e) {
}
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {
}

try {
	auths = (AuthLogin) session.getAttribute("checkauth");
	checkauth = auths.getResult();
	if (!checkauth) {
		response.sendRedirect("Login");
	}
} catch (Exception e) {
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<div>

		<header>
			<div class="navbar navbar-default navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="index"> <img
							src="./img/header.png" class="hidden-xs" alt="" width=""
							height="90" style="margin-left: -20px;">
						</a>

					</div>
					<div class="widget hidden-xs hidden-sm"
						style="margin-right: -40px;">
						<div class="pull-right top1">
							<%
								if (member == null && student == null && personnel == null) {
							%>
							<span style="color: forestgreen;"> <a href="Login"
								class=" btn btn-success"> <i class="fa fa-sign-in">&nbsp;เข้าสู่ระบบ
								</i>
							</a>
							</span>

							<%
								} else if (member != null) {
							%>
							<span style="color: green;"> <i class="fa fa-user"></i>
								ผู้ใช้งานระบบ : <%=member.getMember_FirstName() + "  " + member.getMember_LastName()%>
							</span> &nbsp;&nbsp; <a href="varifylogout"
								class="btn btn-danger btn-xs"> <i class="fa fa-sign-out">ออกจากระบบ</i>
							</a> <br> <span style="font-size: small; color: #000">สถานะ
								: <%=member.getPosition()%></span>
							<%
								} else if (personnel != null) {
							%>
							<span style="color: green;"> <i class="fa fa-user"></i>
								ผู้ใช้งานระบบ : <%=personnel.getPersonnel_FirstName() + "  " + personnel.getPersonnel_LastName()%>
							</span> &nbsp;&nbsp; <a href="varifylogout"
								class="btn btn-danger btn-xs"> <i class="fa fa-sign-out">ออกจากระบบ</i>
							</a> <br> <span style="font-size: small; color: #000">ตำแหน่ง
								: <%=personnel.getPosition()%></span>
							<%
								} else {
							%>
							<span style="color: green;"> <i class="fa fa-user"></i>
								ผู้ใช้งานระบบ : <%=student.getNameTh() + "  " + student.getSurnameTh()%>
							</span> &nbsp;&nbsp; <a href="varifylogout"
								class="btn btn-danger btn-xs"> <i class="fa fa-sign-out">ออกจากระบบ</i>
							</a> <br> <span style="font-size: small; color: #000">สถานะ
								<%=student.getStatus()%> : นักศึกษา

							</span>
							<%
								}
							%>
						</div>
					</div>
				</div>
			</div>
		</header>

		<%
			if (member == null && student == null && personnel == null) {
		%>
		<div style="width: 100%; background-color: #E67E22;">
			<nav class="navbar navbar-expand-sm navbar-dark container">
				<ul class="nav navbar-nav pull-center">
					<li class="nav-item active "><a href="index" class="nav-link">หน้าแรก</a></li>
					<li><a href="loadListLaboratoryInstrumentsPage"
						class="nav-link   active">รายการเครื่องมือวิทยาศาสตร์</a></li>
				</ul>
			</nav>
		</div>
		<%
			} else {
		%>

		<%
			if (personnel != null) {
			if (!personnel.getPersonnel_type().equals("User")) {
		%>
		<div style="width: 100%; background-color: #E67E22;">
			<nav class="navbar navbar-expand-sm navbar-dark container">

				<ul class="navbar-nav">
					<li class="nav-item active"><a href="index" class="nav-link">หน้าแรก</a></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> รายการบริการ </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item  " href="loadServicerequest ">การบริการเครื่องมือวิทยาศาสตร์</a>
							<a class="dropdown-item " href="loadChangRequestForm">การเปลี่ยนแปลงบริการเครื่องมือวิทยาศาสตร์</a>
							<a class="dropdown-item" href="do_getListTestAnalysisForm  ">บริการวิเคราห์ทดสอบ</a>
						</div></li>
					<%
						if (personnel.getPersonnel_type().equals("DeputyDean")) {
					%>
					<li class="nav-item active"><a href="do_getincomesummary"
						class="nav-link">รายได้ทั้งหมด</a></li>
					<%
						} else if (personnel.getPersonnel_type().equals("FinanceOfficer")) {
					%>
					<li class="nav-item active"><a href="do_getpayment"
						class="nav-link">การจ่ายเงิน</a></li>
					<%
						} else if (personnel.getPersonnel_type().equals("Admin") || personnel.getPersonnel_type().equals("LaboratoryStaff")) {
					%>
					<li class="nav-item active"><a href="ListApprovedPage"
						class="nav-link">รายการที่ผ่านการอนุมัติ</a></li>
					<li class="nav-item active"><a href="ListSciInstrumentsPage"
						class="nav-link">เครื่องมือวิทยาศาสตร์</a></li>
					<%
						if (personnel.getPersonnel_type().equals("Admin")) {
					%>
					<li class="nav-item active"><a href="do_ViewTestItem"
						class="nav-link">รายการวิเคราห์ทดสอบ</a></li>
					<li class="nav-item active"><a href="loadUsageSummary"
						class="nav-link">รายได้</a></li>

					<%
						}
					%>
					<%
						}
					%>
				</ul>

			</nav>
		</div>
		<%
			} else {
		%>
		<div style="width: 100%; background-color: #E67E22;">
			<nav class="navbar navbar-expand-sm navbar-dark container">

				<ul class="nav navbar-nav pull-center">
					<li class="nav-item  active "><a href="index"
						class="nav-link ">หน้าแรก</a></li>
					<li class="nav-item  active"><a
						href="loadListLaboratoryInstrumentsPage" class="nav-link">รายการเครื่องมือวิทยาศาสตร์</a></li>
					<li class="nav-item  active "><a href="Service"
						class="nav-link"> บริการยืมอุปกรณ์วิทยาศาสตร์ </a></li>
					<li class="nav-item  active"><a href="TestingAnalysis"
						class="nav-link">บริการวิเคราะห์ทดสอบ</a></li>
					<li class="nav-item  active"><a href="ListAllRequestPage"
						class="nav-link">รายการร้องขอ</a></li>
				</ul>
				<div class="navbar-nav ml-auto">
					<div class="hidden-lg hidden-md hidden-sm">
						<div style="height: 40px; line-height: 40px; text-align: left">
							<!-- <span style="color: #000;">
					<i class="fa fa-bell"></i>
					</span>	 -->
						</div>
					</div>
				</div>
			</nav>
		</div>
		<%
			}
		%>
		<%
			} else {
		%>
		<div style="width: 100%; background-color: #E67E22;">
			<nav class="navbar navbar-expand-sm navbar-dark container">

				<ul class="nav navbar-nav pull-center">
					<li class="nav-item  active "><a href="index"
						class="nav-link ">หน้าแรก</a></li>
					<li class="nav-item  active"><a
						href="loadListLaboratoryInstrumentsPage" class="nav-link">รายการเครื่องมือวิทยาศาสตร์</a></li>
					<li class="nav-item  active "><a href="Service"
						class="nav-link"> บริการยืมอุปกรณ์วิทยาศาสตร์ </a></li>
					<li class="nav-item  active"><a href="TestingAnalysis"
						class="nav-link">บริการวิเคราะห์ทดสอบ</a></li>
					<li class="nav-item  active"><a href="ListAllRequestPage"
						class="nav-link">รายการร้องขอ</a></li>
				</ul>
				<div class="navbar-nav ml-auto">
					<div class="hidden-lg hidden-md hidden-sm">
						<div style="height: 40px; line-height: 40px; text-align: left">
							<!-- <span style="color: #000;">
					<i class="fa fa-bell"></i>
					</span>	 -->
						</div>
					</div>
				</div>
			</nav>
		</div>
		<%
			}
		%>
		<%
			}
		%>



	</div>

</body>
</html>