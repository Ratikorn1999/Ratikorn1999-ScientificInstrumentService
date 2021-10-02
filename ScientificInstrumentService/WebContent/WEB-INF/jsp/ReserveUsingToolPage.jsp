<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
	Member member = null;

FlatFeeForm flatFeeForm = new FlatFeeForm();
ListAllRequestManager larm = new ListAllRequestManager();
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {
}
try {
	flatFeeForm = (FlatFeeForm) request.getAttribute("flatFeeForm");
} catch (Exception e) {
}

/*------------------- Create Date -------------------*/
Calendar today = Calendar.getInstance();
String day = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
if (day.length() < 2) {
	day = "0" + day;
}
String month = String.valueOf(today.get(Calendar.MONTH) + 1);
if (month.length() < 2) {
	month = "0" + month;
}
int year = (today.get(Calendar.YEAR)) - 543;
String mindate = year + "-" + month + "-" + day;
String maxdate = (year + 10) + "-" + month + "-" + day;
/*------------------- End  Create Date -------------------*/

String date = "";
%>
<!DOCTYPE html>
<html  >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'	rel='stylesheet' type='text/css'>

<style>
body {
	font-family: 'Kanit', sans-serif;
}
header {
	font-family: 'Kanit', sans-serif;
}
.bg-txt {
	background-color: #ffffee;
}
.colorgraph {
	height: 5px;
	border-top: 0;
	background: #c4e17f;
	border-radius: 5px;
	background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca
		25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe
		50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1
		87.5%, #62c2e4 87.5%, #62c2e4);
}
</style>
<title>Reserve Using Tool Page</title>
</head>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container" style="margin-top: 35px;">
	<form name="frm" action="do_ReserveUsingTool" method="post">
	<input type="hidden" name="sericeRequestFormNumber" id="sericeRequestFormNumber"  value="<%=flatFeeForm.getSericeRequestFormNumber()%>">
	<br>
									<h5>เครื่องมือวิทยาศาสตร์ที่ต้องการขอใช้มีดังนี้</h5>
									<hr>

									<table id="servicerequestFormTable"
										class="table table-bordered" >
										<thead>
											<tr align="center">
												<th rowspan="2">ชื่อเครื่องมือวิทยาศาสตร์</th>
												<th colspan="2">วันเวลาที่ขอใช้</th>
												
											</tr>
											<tr>
												<th>วันที่</th>
												<th>เวลา</th>
											</tr>
										</thead>
										<tbody>
										<% for(int i = 0 ;i < flatFeeForm.getListrentalschedule().size();i++){
											String  sdate = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "en-AU")).format(flatFeeForm.getListrentalschedule().get(i).getStartDate());
											String  edate = new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "en-AU")).format(flatFeeForm.getListrentalschedule().get(i).getEndDate());
											int check = -1;
											DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
											try {
												Date date1 = df.parse(mindate);
												Date date2 = df.parse(sdate);
												
												if(date1.compareTo(date2) > 0){
													check = 1;
												}else{
													check = 2;
												}
											} catch (ParseException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											%>
										<tr>
										<td><input type="hidden" name="sciInstruments_ID" id="sciInstruments_ID"  value="<%=flatFeeForm.getListrentalschedule().get(i).getSciInstruments().getSciInstruments_ID()%>">
										<input type="text" name="sciInstruments_Name" id="sciInstruments_Name" value="<%=flatFeeForm.getListrentalschedule().get(i).getSciInstruments().getSciInstruments_Name()%>" readonly  class='form-control'>
										</td>
										<td>
										<input type="date" name="date" id="date"
										<%if(check == 1){ %>
										 min="<%=mindate%>" 
										 <% }else{ %>
										  min="<%=sdate%>" 
										 <% } %>
										max="<%=edate%>" required></td>
										<td><input type="time" name="starttime" id="starttime" min="09:00" max="18:00"  required> ถึง <input type="time"  name="endtime" id="endtime" min="09:00" max="18:00"  required></td>
										</tr>
										<% } %>
										</tbody>



									</table>
									
								<div class="form-group row">
									<div class="col-sm-12 text-center">
										<button type="submit" name="submit" class="btn btn-success"
											onclick="return checkform(frm)">ยืนยัน</button>
										<a href="#"><button type="reset" class="btn btn-warning">ยกเลิก</button></a>
									</div>
								</div>
						</form>
						</div>
	
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>