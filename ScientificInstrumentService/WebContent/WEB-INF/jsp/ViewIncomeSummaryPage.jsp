<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
double servicerequestformincome =Double.parseDouble( request.getAttribute("servicerequestformincome").toString() );
double testanalysisformincome =  Double.parseDouble( request.getAttribute("testanalysisformincome").toString());
double total = servicerequestformincome+testanalysisformincome;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
	e.printStackTrace();
}
%>    
<!DOCTYPE html>
<html>
<head>
<title>เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./img/logosci.png" rel="icon">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit' rel='stylesheet' type='text/css'>
<link rel="stylesheet"	href="./css/web_css.css">
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container"  style="margin-top: 5%;">
<div>
		สรุปการให้บริการ 	<input id="bday-month" type="month" name="bday-month" > 
		</div>
		
		<hr>	
		<div>
		ยอดรวมทั้งหมด <span><%= total %>    บาท</span>
		</div>
		<hr>	
		
		<div>
		การให้บริการเครื่องมือวิทยาศาสตร์    <span><%= servicerequestformincome %>    บาท</span>
		</div>
		

		<div> 
		การให้บริการวิเคราะห์ทดสอบ  <span><%= testanalysisformincome %>    บาท</span>
		</div>
	
		

	</div>
	
	
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>