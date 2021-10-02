<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
ChangeForm changeForm = null;
String date = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	changeForm = (ChangeForm) request.getAttribute("cf");
} catch (Exception e) {
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

<div class="container"  style="margin-top: 35px;">
	<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
		
		
				<h3>แบบฟอร์มการขอเปลี่ยนแปลงการใช้เครื่องมือวิทยาศาสตร์</h3>
				
				<hr class="colorgraph">
					

					<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อผู้ขอใช้บริการ
									</label>
									<div class="col-sm-4">
										<input type="text" name="name" id="name"
											class="form-control data"
											<% if(changeForm.getSericeRequestForm().getMember().getMember_ID() != null) {%>
											value="<%=changeForm.getSericeRequestForm().getMember().getMember_Prefix() + " " + changeForm.getSericeRequestForm().getMember().getMember_FirstName() + "  " + changeForm.getSericeRequestForm().getMember().getMember_LastName()%>"
											<% }else if(changeForm.getSericeRequestForm().getStudent().getStudentID() != null){ %>
											value="<%= changeForm.getSericeRequestForm().getStudent().getNameTh()+" "+changeForm.getSericeRequestForm().getStudent().getSurnameTh() %>"
											<% } %>
											readonly="readonly"
											>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									ตำแหน่ง
									</label>
									<div class="col-sm-4">
										<input type="text" name="position" id="position"
											class="form-control data"
											<% if(changeForm.getSericeRequestForm().getMember().getMember_ID() != null) {%>
											 value="<%=changeForm.getSericeRequestForm().getMember().getPosition()%>"
											<% }else if(changeForm.getSericeRequestForm().getStudent().getStudentID() != null){ %>
											value="นักศึกษา"
											<% } %>
											readonly="readonly"
											 >
									</div>
								</div>



								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									โทรศัพท์
									</label>
									<div class="col-sm-4">
										<input type="text" name="phoneno" id="phoneno"
											class="form-control data"
											<% if(changeForm.getSericeRequestForm().getMember().getMember_ID() != null) {%>
											value="<%=changeForm.getSericeRequestForm().getMember().getPhonenumber()%>"
											<% }else if(changeForm.getSericeRequestForm().getStudent().getStudentID() != null){ %>
											value="<%=changeForm.getSericeRequestForm().getStudent().getPhonenumber()%>"
											<% } %>
											readonly="readonly"
											>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									Email
									</label>
									<div class="col-sm-4">
										<input type="text" name="email" id="email"
											class="form-control  data" 
												<% if(changeForm.getSericeRequestForm().getMember().getMember_ID() != null) {%>
											value="<%=changeForm.getSericeRequestForm().getMember().getEmail()%>"
											<% }else if(changeForm.getSericeRequestForm().getStudent().getStudentID() != null){ %>
											value="<%=changeForm.getSericeRequestForm().getStudent().getEmail()%>"
											<% } %>
											readonly="readonly"
											>
									</div>
								</div>


								<%
									if(changeForm.getSericeRequestForm().getMember().getMember_ID() != null){
										if (changeForm.getSericeRequestForm().getMember().getPosition().equals("อาจารย์")) {
								%>

								<div class="form-group row">
									
								</div>
								<div class="form-group row">
									<label class="col-sm-4 col-form-label text-right">
									หลักสูตร/คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="tn1" id="tn1"
											class="form-control  data" value="<%=changeForm.getSericeRequestForm().getMember().getFaculty()%>"
											readonly="readonly">
									</div>

								</div>

								<%
									} else if (changeForm.getSericeRequestForm().getMember().getPosition().equals("หน่วยงานภายนอก")) {
								%>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
										หน่วยงานภายนอก 
									</label>
								</div>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อหน่วยงาน
									</label>
									<div class="col-sm-4">
										<input type="text" name="tn2" id="tn2"
											class="form-control  data"
											value="<%=changeForm.getSericeRequestForm().getMember().getCompany_name()%>"
											readonly="readonly">
									</div>

								</div>
								<%
									} else if (changeForm.getSericeRequestForm().getMember().getPosition().equals("นักศึกษา")) {
								%>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
										นักศึกษาระดับปริญญา 
									</label>

									<div class="col-sm-4">
										<%
											if (changeForm.getSericeRequestForm().getMember().getDegree().equals("ปริญญาตรี")) {
										%>
										<input type="text" name="degree" id="degree" value="ตรี"
											class="form-control  data" readonly="readonly">
										<%
											} else if (changeForm.getSericeRequestForm().getMember().getDegree().equals("ปริญญาโท")) {
										%>
										<input type="text" name="degree" id="degree" value="โท"
											class="form-control  data" readonly="readonly">
										<%
											} else if (changeForm.getSericeRequestForm().getMember().getDegree().equals("ปริญญาเอก")) {
										%>
										<input type="text" name="degree" id="degree" value="เอก"
											class="form-control  data" readonly="readonly">
										<%
											}
										%>

									</div>

									<label class="col-sm-2 col-form-label text-right">
									ชื่ออาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisorName " id="advisorName"
											value="<%=changeForm.getSericeRequestForm().getMember().getAdvisor_name()%>" readonly="readonly">
									</div>

								</div>

								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
										<input type="text" name="branch" id="branch"
											class="form-control  data" value="<%=changeForm.getSericeRequestForm().getMember().getBranch()%>" readonly="readonly">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="faculty" id="faculty"
											class="form-control  data" value="<%=changeForm.getSericeRequestForm().getMember().getFaculty()%>" readonly="readonly">
									</div>
								</div>
								
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									มหาวิทยาลัย
									</label>
									<div class="col-sm-4">
										<input type="text"  name="university" id="university"
											class="form-control  data" id="university"
											value="<%=changeForm.getSericeRequestForm().getMember().getUniversity()%>" readonly="readonly">
									</div>

								</div>

								<%
										}
									}else if (changeForm.getSericeRequestForm().getStudent().getStudentID() != null){
								%>
								<div class="form-group row">
									

									<label class="col-sm-2 col-form-label text-right">
									ชื่ออาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisorName" id="advisorName"
											required="required" value="<%=changeForm.getSericeRequestForm().getStudent().getAdvisor_name() %>"
											readonly="readonly">
									</div>
									
									<label class="col-sm-2 col-form-label text-right">
									อีเมลอาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisoremail" id="advisoremail"
											required="required" value="<%=changeForm.getSericeRequestForm().getStudent().getEmailadvisor() %>"
											readonly="readonly"
											>
									</div>
									

								</div>

								<div class="form-group row">
								
								<label class="col-sm-2 col-form-label text-right">
										ระดับการศึกษา
									</label>

									<div class="col-sm-4">
									
										<input type="text" name="degree" id="degree" value="<%= changeForm.getSericeRequestForm().getStudent().getLevelName() %>"
											class="form-control  data" readonly="readonly">
										

									</div>
								
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
										<input type="text" name="branch" id="branch"
											class="form-control  data" value="<%=changeForm.getSericeRequestForm().getStudent().getProgramNameTh()%>" readonly="readonly">
									</div>
									
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="faculty" id="faculty"
											class="form-control  data" value="<%=changeForm.getSericeRequestForm().getStudent().getFacultyNameTh()%>" readonly="readonly">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									มหาวิทยาลัย
									</label>
									<div class="col-sm-4">
										<input type="text" name="university" id="university"
											class="form-control  data" id="university"
											value="แม่โจ้" readonly="readonly">
									</div>

								</div>
								
								
								<% } %>
				
							<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ตามใบคำขอเลขที่
									</label>
									<div class="col-sm-4">
									 <input type="text" class="form-control  data" name="formnumber" id="formnumber" readonly value="<%= changeForm.getSericeRequestForm().getSericeRequestFormNumber()%>">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									เมื่อวันที่ 
									</label>
									<div class="col-sm-4">
										<%date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(changeForm.getSericeRequestForm().getCreatedate());%>
										 <input type="text"  class="form-control  data" name="formnumber" id="formnumber" readonly value="<%= date %>">
									</div>
								</div>
						
							<div class="form-group row">
										<label class="col-sm-2 col-form-label text-right">ได้รับอนุมัติให้ใช้บริการ</label>
										<div class="col-sm-10">
											<div class="form-check form-check-inline">
												<input type="radio"  class="form-check-input" name="typeservice" id="typeservice1" onclick="return typeserviceOtherClick()"  <%if(changeForm.getSericeRequestForm()!=null){ %> checked <%} %>value="เครื่องมือวิทยาศาสตร์" > 
												<label class="form-check-label" for="typeservice1">
												 เครื่องมือวิทยาศาสตร์
												</label>	
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" class="form-check-input" name="typeservice" id="typeservice2" onclick="return typeserviceOtherClick()"  value="วิเคราะห์ทดสอบ">
												<label class="form-check-label" for="typeservice2">
												วิเคราะห์ทดสอบ
												</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" name="typeservice" id="typeservice" onclick="return typeserviceOtherClick()"   > 
												<label class="form-check-label" for="typeservice3">
												อื่นๆ (โปรดระบุ) 
												</label>	
											</div>	
										<div class="form-check form-check-inline">
										<input type="text" class="form-control " name="othertypeservice" id="othertypeservice" disabled>
										</div>
									</div>
								</div>	
						
			
				<div>
				
					มีความประสงค์จะขอเปลี่ยนแปลงการขอใช้บริการ ดังนี้ <br>
					<table id="myTable" class="table table-bordered">
					<thead align="center">
						<tr >
							<th rowspan="2">    ลำดับ<br>ที่ </th>
							<th colspan="3"> รายละเอียดการใช้บริการ(เดิม)</th>
							<th rowspan="2"> รายละเอียดการใช้บริการ <br> ที่ขอเปลี่ยนแปลง (ใหม่) </th>
							<th colspan="2">  การเปลี่ยนแปลง </th>
						</tr>
						<tr>
						<th style="width: 400px;"> ชื่อเครื่องมือ</th>
						<th>เวลาเริ่มใช้</th>
						<th>เวลาสิ้นสุด</th>
						<th>เวลาเริ่มใช้</th>
						<th>เวลาสิ้นสุด</th>
						</tr>
						
					</thead>	
					<tbody>
					<% if(changeForm.getSericeRequestForm().getListSchedule()!=null){ %>
						<% for (int i = 0 ; i <changeForm.getSericeRequestForm().getListSchedule().size() ; i++) { %>
							<tr>
								<td><%= i+1 %></td>
								<td><%= changeForm.getSericeRequestForm().getListSchedule().get(i).getSciInstruments().getSciInstruments_Name() %></td>
								<td><%= date = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH")).format(changeForm.getSericeRequestForm().getListSchedule().get(i).getStartDateTime()) %></td>
								<td><%= date = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH")).format(changeForm.getSericeRequestForm().getListSchedule().get(i).getEndDateTime()) %></td>
								<td><%= changeForm.getChangelists().get(i).getDetail() %></td>
								<% if(changeForm.getChangelists().get(i).getNewschedule().getSchedule_ID() != null) {%>
								<td><%=  date = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH")).format(changeForm.getChangelists().get(i).getNewschedule().getStartDateTime())%></td>
								<td><%=  date = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH")).format(changeForm.getChangelists().get(i).getNewschedule().getEndDateTime())%></td>
								<% }else{ %>
								<td></td>
								<td></td>
								<% } %>
								
							</tr> 
						<% } %>	
					<% } %>	
					</tbody>	
					</table>
					
				</div>

				<%
								if (changeForm.getChange_status().equals("รอการอนุมัติ")) {
							%>
						
							<br>
							<h5>ข้อมูลการอนุมัติ</h5>
							<hr>

							<%
							int no = 0;
							int id = -1;
							boolean next = false ;
							for (int i = 0; i < changeForm.getListApprovalChangeForm().size(); i++) {
							if(changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 1){
								next = true ;
							}
							}
							if(!next){
								no = 2;	
							}
							for (int i = 0; i < changeForm.getListApprovalChangeForm().size(); i++) {
								
								if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 1
								&& changeForm.getListApprovalChangeForm().get(i).getStatus().equals("อนุมัติ")) {
									no = 2;
								} else {
									id = changeForm.getListApprovalChangeForm().get(i).getApproval_ID();

								}
								
								
								if (no == 2) {
									for (int k = 0; k < changeForm.getListApprovalChangeForm().size(); k++) {
								if (changeForm.getListApprovalChangeForm().get(k).getApproveNo() == 2) {
									if (changeForm.getListApprovalChangeForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 3;
									} else {
										id = changeForm.getListApprovalChangeForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 3) {
									for (int k = 0; k <changeForm.getListApprovalChangeForm().size(); k++) {
								if (changeForm.getListApprovalChangeForm().get(k).getApproveNo() == 3) {
									if (changeForm.getListApprovalChangeForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 4;
									} else {
										id =changeForm.getListApprovalChangeForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 4) {
									for (int k = 0; k < changeForm.getListApprovalChangeForm().size(); k++) {
								if (changeForm.getListApprovalChangeForm().get(k).getApproveNo() == 4) {
									if (changeForm.getListApprovalChangeForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 5;
									} else {
										id = changeForm.getListApprovalChangeForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 5) {
									for (int k = 0; k < changeForm.getListApprovalChangeForm().size(); k++) {
								if (changeForm.getListApprovalChangeForm().get(k).getApproveNo() == 5) {
									if (changeForm.getListApprovalChangeForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 6;
									} else {
										id = changeForm.getListApprovalChangeForm().get(k).getApproval_ID();

									}
								}
									}
								}

							}
							%>
							
							<%
								for (int i = 0; i < changeForm.getListApprovalChangeForm().size(); i++) {
							%>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label text-left"> 
								<% if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 1) { %>
 							 อาจารย์ที่ปรึกษา/หัวหน้าโครงการ 
 							<%} else if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 3) {%> 
 								ประธานหลักสูตร  
  							<% } else if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 2) { %>
 							 เจ้าหน้าที่ห้องปฏิบัติการ
  							<% } else if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 4) { %>
 							 หัวหน้างานบริการวิชาการและวิจัย 
 							<% } else {	%>
 							 รองคณบดีฯ 
 							<%	} %>
								</label> 
								<label class="col-sm-4 col-form-label text-left"> 
									<% if (changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID() != null) { %> 
											<%=changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_Prefix() + " "
											+ changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_FirstName() + " "
											+ changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_LastName()%>
									<% } else { %>
										<% if(changeForm.getSericeRequestForm().getMember().getMember_ID() != null){ %>
											<%=changeForm.getSericeRequestForm().getMember().getAdvisor_name()%>
										<% }else if (changeForm.getSericeRequestForm().getStudent().getStudentID() != null){ %>
											 <%=changeForm.getSericeRequestForm().getStudent().getAdvisor_name()%>
										<% } %>
									<% } %>


								</label>
								
									<label  class="col-sm-2  "
									<% if(changeForm.getListApprovalChangeForm().get(i).getStatus().equals("ไม่อนุมัติ")){ %>
									style="color: red;"
									<% }else if(changeForm.getListApprovalChangeForm().get(i).getStatus().equals("อนุมัติ")) {%>
										style="color: green;"
									<%}else{ %>
										style="color: yellow;"
									<% } %>
									>
									<%=changeForm.getListApprovalChangeForm().get(i).getStatus()%>
									</label> 
								
								




								<%
									boolean check = false;
								if (changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID() != null
										&& personnel.getPersonnel_ID().equals(changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID())
										&& !changeForm.getListApprovalChangeForm().get(i).getStatus().equals("อนุมัติ")
										&& changeForm.getListApprovalChangeForm().get(i).getApproval_ID() == id) {
									check = true;
								} else if (personnel.getPersonnel_ID().equals(changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID())
										&& !changeForm.getListApprovalChangeForm().get(i).getStatus().equals("อนุมัติ")
										&& changeForm.getListApprovalChangeForm().get(i).getApproval_ID() == id) {
									check = true;
								}
								%>
							
								<div class="col-sm-3">
									<%if (check) { 	%>
									<button type="button" name="approve" class="btn btn-success"
									onclick="location.href='doApproveChangeForm?approval_ID=<%= changeForm.getListApprovalChangeForm().get(i).getApproval_ID() %>&formnumber=<%=changeForm.getChangeFormNumber() %>&approval_no=<%= changeForm.getListApprovalChangeForm().get(i).getApproveNo() %>&status=อนุมัติ';"
									 >
									 
									อนุมัติ 
									</button>
									<button type="button" name="noapprove" class="btn btn-danger"
									onclick="location.href='doApproveChangeForm?approval_ID=<%= changeForm.getListApprovalChangeForm().get(i).getApproval_ID() %>&formnumber=<%=changeForm.getChangeFormNumber() %>&approval_no=<%= changeForm.getListApprovalChangeForm().get(i).getApproveNo() %>&status=ไม่อนุมัติ';"
									> 
									ไม่อนุมัติ
									 </button>
									 <% } %>
								</div>
								
							</div>




							<%
								}
							} else {
							%>
							<br>
							<h5>ข้อมูลการอนุมัติ</h5>
							<hr>
							<%
								for (int i = 0; i < changeForm.getListApprovalChangeForm().size(); i++) {
							%>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label text-left"> 
								<% if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 1) {%> 
								อาจารย์ที่ปรึกษา/หัวหน้าโครงการ 
								<%} else if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 3) {%> 
								ประธานหลักสูตร
								 <%	} else if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 2) {	%> 
								 เจ้าหน้าที่ห้องปฏิบัติการ
								<%	} else if (changeForm.getListApprovalChangeForm().get(i).getApproveNo() == 4) {%>
								 หัวหน้างานบริการวิชาการและวิจัย 
								 <%} else {	%> 
								 รองคณบดีฯ 
								 <% } %>
								</label> <label class="col-sm-4 col-form-label text-left"> 
									<% if (changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID() != null) { %>
										 <%=changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_Prefix() + " "
											+ changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_FirstName() + " "
											+ changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_LastName()%>
									<% } else {%>
									<% if(changeForm.getSericeRequestForm().getMember() != null){ %>
									 <%=changeForm.getSericeRequestForm().getMember().getAdvisor_name()%>
									<% }else if (changeForm.getSericeRequestForm().getStudent() != null){ %>
									 <%=changeForm.getSericeRequestForm().getStudent().getAdvisor_name()%>
									<% } %>
									 <% } %>


								</label>
								<div class="col-sm-3">
									<span class="form-control bg-txt data"> <%=changeForm.getListApprovalChangeForm().get(i).getStatus()%>
									</span>
								</div>
							</div>




							<%
								}
							%>
							<%
								}
							%>


							


							<div class="form-group row">

								<div class="col-sm-12">

									<a href="loadServicerequest"><button type="button"
											class="btn btn-success">ย้อนกลับ</button></a>

								</div>
							</div>
				
				
							
			
		

					</div>
				</div>
			</div>
		</section>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>