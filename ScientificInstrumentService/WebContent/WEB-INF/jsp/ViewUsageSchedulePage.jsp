<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
FlatFeeForm flatFeeForm = null;

try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}

try {
	flatFeeForm = (FlatFeeForm) request.getAttribute("flatFeeForm");
} catch (Exception e) {}

%>
<!DOCTYPE html>
<html>
<head>
<title>เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./img/logosci.png" rel="icon">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="./css/web_css.css">
</head>
<body>

	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container" style="margin-top: 35px;">
		<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">

						<div class="container">
							<h3>การใช้งานเครื่องมือ</h3>
							<hr class="colorgraph">

							<h5>ข้อมูล</h5>
							<hr>


							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ชื่อ-สกุล</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data">
										<% if(flatFeeForm.getMember().getMember_ID() != null){ %>
									<%=flatFeeForm.getMember().getMember_Prefix() + " " 
									+ flatFeeForm.getMember().getMember_FirstName() + " "
									+ flatFeeForm.getMember().getMember_LastName()%>
									<% }else if (flatFeeForm.getStudent().getStudentID() != null){ %>
									<%= flatFeeForm.getStudent().getNameTh() + " "+ flatFeeForm.getStudent().getSurnameTh()  %>
									<% }else if(flatFeeForm.getPersonnel().getPersonnel_ID() != null){ %>
									<%= flatFeeForm.getPersonnel().getPersonnel_Prefix()+" "
									+flatFeeForm.getPersonnel().getPersonnel_FirstName()+" "
									+flatFeeForm.getPersonnel().getPersonnel_LastName() %>
									<% } %></span>
								</div>
								<label class="col-sm-2 col-form-label text-right">ตำแหน่ง</label>
								<div class="col-sm-4">
									<span class="form-control  bg-txt data">
									<% if(flatFeeForm.getMember().getMember_ID() != null){ %>
									<%=flatFeeForm.getMember().getPosition()%>
									<% }else if (flatFeeForm.getStudent().getStudentID() != null){ %>
										นักศึกษา
									<% }else if(flatFeeForm.getPersonnel().getPersonnel_ID() != null){ %>
									<%= flatFeeForm.getPersonnel().getPosition() %>
									<% } %>
								</span>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">เลขที่คำขอ</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"><%=flatFeeForm.getSericeRequestFormNumber()%></span>
								</div>


							</div>


							<br>
							<div class="form-group row">
								<label class="col-sm-9 col-form-label text-left">
									<h5>ข้อมูลการยืมเครื่องมือ</h5>
								</label>
										<% if(flatFeeForm.getSericeRequestFormNumber().contains("E")){ %>
								<div class="col-sm-3 txt-right">
									<button type="button" class="btn btn-success"
										onclick="return btnshow()">เพิ่มเวลาที่ใช้จริง</button>
									
								</div>
								<%} %>
							</div>
							<hr>
							<% if(flatFeeForm.getSericeRequestFormNumber().contains("A")){ %>
							<div class="form-group row">
								<label class="col-sm-6 col-form-label text-center">ชื่อเครื่องมือ</label>
								<label class="col-sm-3 col-form-label text-center">เวลาที่เริ่ม</label>
								<label class="col-sm-3 col-form-label text-center">เวลาที่สิ้นสุด</label>
							</div>

							<% 
									for (int i = 0; i < flatFeeForm.getListrentalschedule().size(); i++) {
									String strat = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"))
									.format(flatFeeForm.getListrentalschedule().get(i).getStartDate());
									String end = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"))
									.format(flatFeeForm.getListrentalschedule().get(i).getEndDate());
							%>
							<div class="form-group row">
								<div class="col-sm-6">
									<span class="form-control  bg-txt data"><%=flatFeeForm.getListrentalschedule().get(i).getSciInstruments().getSciInstruments_Name()%></span>
								</div>
								<div class="col-sm-3">
									<span class="form-control  bg-txt data"><%=strat%></span>
								</div>
								<div class="col-sm-3">
									<span class="form-control  bg-txt data"><%=end%></span>
								</div>

							</div>
							<%
									}
									} %>


							<div class="form-group row">
								<label class="col-sm-6 col-form-label text-center">ชื่อเครื่องมือ</label>
								<label class="col-sm-3 col-form-label text-center">เวลาที่เริ่ม</label>
								<label class="col-sm-3 col-form-label text-center">เวลาที่สิ้นสุด</label>
							</div>



							<%
								if(flatFeeForm.getListSchedule().size()>0){
								for(int i = 0 ;i<flatFeeForm.getListSchedule().size();i++){
								String 	strat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(flatFeeForm.getListSchedule().get(i).getStartDateTime());	
								String 	end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(flatFeeForm.getListSchedule().get(i).getEndDateTime());
								%>
							<div class="form-group row">
								<div class="col-sm-6">
									<span class="form-control  bg-txt data" style="height: 100px;"><%=flatFeeForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_Name() %></span>
								</div>
								<div class="col-sm-3">
									<span class="form-control  bg-txt data"><%= strat%></span>
								</div>
								<div class="col-sm-3">
									<span class="form-control  bg-txt data"><%=end %></span>
								</div>

							</div>
							<% } 
								}else{%>
							<div class="form-group row">
								<label class="col-sm-12 col-form-label text-center">ยังไม่ได้ลงทะเบียนจองเครื่องมือ</label>

							</div>
							<% } %>

							<div class="form-group row">

								<div class="col-sm-12">

									<a href="ListApprovedPage"><button type="button"
											class="btn btn-success">ย้อนกลับ</button></a>

								</div>
							</div>




						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<div id="schedule" class="modal">
									<form name="frm" action="doAddinstrumentsusage" method="post">
									<input type="hidden" name="formnumber" id="formnumber" value="<%=flatFeeForm.getSericeRequestFormNumber()%>">
										<!-- Modal content -->
										<div class="modal-content" >
											<span class="close " onclick="return spanclick()" style="color: red;">&times;</span>
									
												<table id="servicerequestFormTable"
										class="table table-bordered  " >
										<thead>
											<tr align="center">
												<th rowspan="2">ข้อที่</th>
												<th rowspan="2">ชื่อเครื่องมือวิทยาศาสตร์</th>
												<th colspan="2">วันเวลาที่ขอใช้</th>
												<th rowspan="2">ระยะเวลาการใช้งาน<br>(ชั่วโมง)</th>
												<th colspan="2">ค่าบำรุงการใช้เครื่องมือ(บาท)</th>
												<th rowspan="2">ค่าใช้จ่าย<br>เพิ่มเติม(บาท)</th>
												<th rowspan="2">ผู้ดูแล<br>เครื่องมือ</th>
											</tr>
											<tr  align="center">
												<th>วันที่</th>
												<th>เวลา</th>
												<th>ค่าบำรุงต่อชั่วโมง</th>
												<th>รวม</th>
											</tr>
										</thead>
									<tbody>
											<%
										if (flatFeeForm.getListSchedule().size() > 0) {
											for(int i = 0 ;i<flatFeeForm.getListSchedule().size();i++){
												String 	date = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH")).format(flatFeeForm.getListSchedule().get(i).getStartDateTime());	
												String 	time = new SimpleDateFormat("HH:mm:ss", new Locale("th", "TH")).format(flatFeeForm.getListSchedule().get(i).getStartDateTime());
										%>
								
											<tr>
											<td>
											
											<input type="hidden" name="schedule_ID" value="<%=flatFeeForm.getListSchedule().get(i).getSchedule_ID()%>">
											
											<input type="text" name="no" id="no" class="form-control data"  value="<%=i+1%>"></td>
											<td><input type="text" name="sname" id="sname"  class="form-control  data" value="<%=flatFeeForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_Name()%>"  disabled="disabled"></td>
											<td><input type="text" name="date" id="date" class="form-control  data" value="<%=date%>" disabled="disabled"></td>
											<td><input type="text" name="time" id="time" class="form-control  data" value="<%=time%>" disabled="disabled"></td>
											<td><input type="text" name="totaltime" id="" class="form-control  data" value="<%= flatFeeForm.getListSchedule().get(i).getTime() %>"  disabled="disabled"></td>
											<td><input type="text" name="maintenancefee" id="maintenancefee" class="form-control  data" value="<%=flatFeeForm.getListSchedule().get(i).getMaintenancefee()%>" disabled="disabled"></td>
											<% double totalprice = flatFeeForm.getListSchedule().get(i).getMaintenancefee() *  Integer.parseInt( flatFeeForm.getListSchedule().get(i).getTime())  ;  %>
											<td><input type="text" name="totalprice" id="totalprice" class="form-control  data"  value="<%=totalprice%>" disabled="disabled"></td>
											<td><input type="text" name="additionalcost" id="additionalcost" class="form-control  data" value="<%=flatFeeForm.getListSchedule().get(i).getAdditionalcost()%>"></td>
											<% Staff s =  flatFeeForm.getListSchedule().get(i).getSciInstruments().getStaff();%>
											<td><input type="text" name="staff" id="staff" class="form-control  data" value="<%= s.getPrefix()+" "+s.getFirstname()+" "+s.getLastname() %>"   disabled="disabled"></td>
											
											</tr>
											<% } 
											}%>
											</tbody>
												</table>
												<div class="col-sm-6">
												<button class="btn btn-success" type="submit" onclick="return  confirm('ต้องการเพิ่มค่าใช้จ่ายเพิ่มเติม  ใช่หรือไม่')">เพิ่ม</button>
												</div>
												
										</div>
										
									</form>
									</div>
	<jsp:include page="common/footer.jsp"></jsp:include>

	<script>
		var modal = document.getElementById("schedule");
		function btnshow() {

			modal.style.display = "block";
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		}

		function spanclick() {
			modal.style.display = "none";
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		}
	</script>
</body>
</html>