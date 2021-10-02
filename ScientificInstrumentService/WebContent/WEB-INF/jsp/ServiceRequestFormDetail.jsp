<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*,LoginMJU.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
Personnel personnel = null;
FlatFeeForm flatFeeForm = null;

String date = null;
Student student = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	flatFeeForm = (FlatFeeForm) request.getAttribute("flatFeeForm");
	student = flatFeeForm.getStudent();
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


	<div class="container" style="margin-top: 35px;">

		<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">

						<div class="container">
							<h3>รายละเอียดแบบฟอร์ม</h3>
							<hr class="colorgraph">

							<h5>ข้อมูลผู้ร้องขอ</h5>
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
									<% } %>
						</span>
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
								<label class="col-sm-2 col-form-label text-right">โทรศัพท์</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data">
									<% if(flatFeeForm.getMember().getMember_ID() != null){ %>
									 <%=flatFeeForm.getMember().getPhonenumber()%>
									<% }else if(student.getStudentID() != null){ %>
									<%= student.getPhonenumber()%>
									<% }else if(flatFeeForm.getPersonnel().getPersonnel_ID() != null){ %>
									<%=flatFeeForm.getPersonnel().getPhone()  %>
									<% }  %>
									</span>
								</div>
								<label class="col-sm-2 col-form-label text-right">Email</label>
								<div class="col-sm-4">
									<span class="form-control  bg-txt data"> 
										<% if(flatFeeForm.getMember().getMember_ID() != null){ %>
									<%=flatFeeForm.getMember().getEmail()%>
									<% }else if(student.getStudentID() != null){ %>
									<%= student.getEmail()%>
									<% }else if(flatFeeForm.getPersonnel().getPersonnel_ID() != null){ %>
									<%=flatFeeForm.getPersonnel().getEmail()  %>
									<% }  %>
									
									</span>
								</div>
							</div>


							<%	if(flatFeeForm.getMember().getMember_ID() != null){
								if (flatFeeForm.getMember().getPosition().equals("หน่วยงานภายนอก")) {
							%>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right"></label>
								<div class="col-sm-8">
									<span class="form-control bg-txt data"> </span>
								</div>

							</div>

							<%
								} else if (flatFeeForm.getMember().getPosition().equals("นักศึกษา")) {
							%>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รหัสนักศึกษา
								</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getMember().getStudentID()%>
									</span>
								</div>
								<label class="col-sm-2 col-form-label text-right">ระดับปริญญา</label>
								<div class="col-sm-4">
									<span class="form-control  bg-txt data"> <%=flatFeeForm.getMember().getDegree()%>
									</span>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ภาค/สาขาวิชา
								</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getMember().getBranch()%>
									</span>
								</div>
								<label class="col-sm-2 col-form-label text-right">คณะ</label>
								<div class="col-sm-4">
									<span class="form-control  bg-txt data"> <%=flatFeeForm.getMember().getFaculty()%>
									</span>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">มหาวิทยาลัย</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getMember().getUniversity()%>
									</span>
								</div>
								<label class="col-sm-2 col-form-label text-right">ชื่ออาจารย์ที่ปรึกษา</label>
								<div class="col-sm-4">
									<span class="form-control  bg-txt data"> <%=flatFeeForm.getMember().getAdvisor_name()%>
									</span>
								</div>
							</div>

							<%
								} else if (flatFeeForm.getMember().getPosition().equals("อาจารย์")) {
							%>

								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									หลักสูตร/คณะ
									</label>
									<div class="col-sm-4">
									<%= flatFeeForm.getMember().getFaculty() %>
									</div>

								</div>


							<%
								}
							}else if (flatFeeForm.getStudent().getStudentID() != null){
								
							%>
							<div class="form-group row">
									

									<label class="col-sm-2 col-form-label text-right">
									ชื่ออาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
											<span class="form-control bg-txt data" > 
											<%= student.getAdvisor_name() %>
											</span>
									</div>
									
									<label class="col-sm-2 col-form-label text-right">
									อีเมลอาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<span class="form-control bg-txt data"> 
										<%= student.getEmailadvisor() %>
											</span>
									</div>
									

								</div>

								<div class="form-group row">
								
								<label class="col-sm-2 col-form-label text-right">
										ระดับการศึกษา
									</label>

									<div class="col-sm-4">
									<span class="form-control bg-txt data"> 
									<%= student.getLevelName() %>
									</span>
										

									</div>
								
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
									<span class="form-control bg-txt data"> 
									<%= student.getProgramNameTh()%>
									</span>
									</div>
									
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<span class="form-control bg-txt data"> 
										<%=student.getFacultyNameTh()%>
										</span>

									</div>
									<label class="col-sm-2 col-form-label text-right">
									มหาวิทยาลัย
									</label>
									<div class="col-sm-4">
										<span class="form-control bg-txt data">
										 แม่โจ้
										 </span>
									</div>

								</div>
							
							<% }else if(flatFeeForm.getPersonnel().getPersonnel_ID() != null) {%>
							
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									หลักสูตร/คณะ
									</label>
									<div class="col-sm-4">
									<%= flatFeeForm.getPersonnel().getFaculty()%>
									</div>

								</div>
							
							<% } %>
							<br>
							<h5>ข้อมูลแบบฟอร์ม</h5>
							<hr>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">เลขที่คำขอ</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"><%=flatFeeForm.getSericeRequestFormNumber()%></span>
								</div>

								<label class="col-sm-2 col-form-label text-right">ประเภท</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"><%=flatFeeForm.getType()%></span>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ประเภทของงาน</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getType_Work_Name()%>
									</span>
								</div>
								<label class="col-sm-2 col-form-label text-right">ชื่อเรื่อง/ชื่อวิชา
								</label>
								<div class="col-sm-4">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getSubject()%>
									</span>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ลักษณะการใช้งาน</label>
								<div class="col-sm-6">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getUsage_characteristics_Name()%>
									</span>
								</div>

							</div>


							<br>
							<h5>ข้อมูลการยืมเครื่องมือ</h5>
							<hr>

							<div class="form-group row">
								<label class="col-sm-6 col-form-label text-center">ชื่อเครื่องมือ</label>
								<label class="col-sm-3 col-form-label text-center">เวลาที่เริ่ม</label>
								<label class="col-sm-3 col-form-label text-center">เวลาที่สิ้นสุด</label>
							</div>

							<%
								if (flatFeeForm.getSericeRequestFormNumber().contains("E")) {
								for (int i = 0; i < flatFeeForm.getListSchedule().size(); i++) {
									String strat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
									.format(flatFeeForm.getListSchedule().get(i).getStartDateTime());
									String end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
									.format(flatFeeForm.getListSchedule().get(i).getEndDateTime());
							%>
							<div class="form-group row">
								<div class="col-sm-6">
									<span class="form-control  bg-txt data"><%=flatFeeForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_Name()%></span>
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
							} else if (flatFeeForm.getSericeRequestFormNumber().contains("A")) {

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
							}
							%>


							<%
								if (flatFeeForm.getBooking_status().equals("รอการอนุมัติ")) {
							%>
							
							<br>
							<h5>ข้อมูลการอนุมัติ</h5>
							<hr>

							<%
							int no = 0;
							int id = -1;
							boolean next = false ;
							for (int i = 0; i < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); i++) {
							if(flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 1){
								next = true ;
							}
							}
							if(!next){
								no = 2;	
							}
							for (int i = 0; i < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); i++) {
								
								if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 1
								&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus().equals("อนุมัติ")) {
									no = 2;
								} else {
									id = flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID();

								}
								
								
								if (no == 2) {
									for (int k = 0; k < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); k++) {
								if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 2) {
									if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 3;
									} else {
										id = flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 3) {
									for (int k = 0; k < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); k++) {
								if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 3) {
									if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 4;
									} else {
										id = flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 4) {
									for (int k = 0; k < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); k++) {
								if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 4) {
									if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 5;
									} else {
										id = flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 5) {
									for (int k = 0; k < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); k++) {
								if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 5) {
									if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 6;
									} else {
										id = flatFeeForm.getLsitApprovalOfServiceRequestForm().get(k).getApproval_ID();

									}
								}
									}
								}

							}
							%>
							
							<%
								for (int i = 0; i < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); i++) {
							%>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label text-left"> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 1) {
 %> อาจารย์ที่ปรึกษา/หัวหน้าโครงการ <%
 	} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 3) {
 %> ประธานหลักสูตร <%
 	List<Schedule> listSchedule = flatFeeForm.getListSchedule();
 for (int k = 0; k < listSchedule.size(); k++) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getCoursepresidentid() != null) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getCoursepresidentid()
 		.equals(listSchedule.get(k).getSciInstruments().getCoursepresident().getCoursepresidentid())) {
 %> <br>(<%=listSchedule.get(k).getSciInstruments().getSciInstruments_Name()%>)
									<%
 	//listSchedule.remove(k);
 }
 %> <%
 	}
 %> <%
 	}
 %> <%
 	} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 2) {
 %> เจ้าหน้าที่ห้องปฏิบัติการ <%
 	List<Schedule> listSchedule = flatFeeForm.getListSchedule();
 for (int k = 0; k < flatFeeForm.getListSchedule().size(); k++) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid()
 		.equals(listSchedule.get(k).getSciInstruments().getStaff().getStaffid())) {
 %> <br>(<%=listSchedule.get(k).getSciInstruments().getSciInstruments_Name()%>)
									<%
 	//listSchedule.remove(k);
 }
 %> <%
 	}
 %> <%
 	}
 %> <%
 	} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 4) {
 %> หัวหน้างานบริการวิชาการและวิจัย <%
 	} else {
 %> รองคณบดีฯ <%
 	}
 %>
								</label> <label class="col-sm-4 col-form-label text-left"> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null) {
 %> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getPrefix() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getFirstname() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getLastname()%>
									<%
										} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
											.getCoursepresidentid() != null) {
									%> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getPrefix() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getFirstname() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getLastname()%>
									<%
										} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_ID() != null) {
									%> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_Prefix() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_FirstName() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_LastName()%>
									<%
										} else if(flatFeeForm.getMember().getMember_ID() != null) {
									%> <%=flatFeeForm.getMember().getAdvisor_name()%> <%
 										}else if(flatFeeForm.getStudent().getStudentID() != null) {%>
										<%= flatFeeForm.getStudent().getAdvisor_name() %>
										<%} %>
								</label>
								<div class="col-sm-3">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus()%>
									</span>
								</div>




								<%
									boolean check = false;
								if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null
										&& personnel.getPersonnel_ID()
										.equals(flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid())
										&& !flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus().equals("อนุมัติ")
										&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID() == id) {
									check = true;
								} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getCoursepresidentid() != null
										&& personnel.getPersonnel_ID().equals(
										flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getCoursepresidentid())
										&& !flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus().equals("อนุมัติ")
										&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID() == id) {
									check = true;
								} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_ID() != null
										&& personnel.getPersonnel_ID()
										.equals(flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_ID())
										&& !flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus().equals("อนุมัติ")
										&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID() == id) {
									check = true;
								} else if (personnel.getPersonnel_ID().equals(flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_ID())
										&& !flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus().equals("อนุมัติ")
										&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID() == id) {
									check = true;
								}
								%>
								<%
									if (check) {
								%>
								<div class="col-sm-2">
									<button type="button" name="approve" class="btn btn-success"
									onclick="location.href='doApproverequet?approval_ID=<%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID()%>&formnumber=<%= flatFeeForm.getSericeRequestFormNumber()%>&approval_no=<%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() %>&status=อนุมัติ';"
									 >
									 
									อนุมัติ 
									</button>
									<button type="button" name="noapprove" class="btn btn-danger"
									onclick="location.href='doApproverequet?approval_ID=<%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID()%>&formnumber=<%= flatFeeForm.getSericeRequestFormNumber()%>&approval_no=<%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() %>&status=ไม่อนุมัติ';"
									> 
									ไม่อนุมัติ
									 </button>
								</div>
								<%
									}
								%>
							</div>




							<%
								}
							} else {
							%>
							<br>
							<h5>ข้อมูลการอนุมัติ</h5>
							<hr>
							<%
								for (int i = 0; i < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); i++) {
							%>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label text-left"> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 1) {
 %> อาจารย์ที่ปรึกษา/หัวหน้าโครงการ <%
 	} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 3) {
 %> ประธานหลักสูตร <%
 	List<Schedule> listSchedule = flatFeeForm.getListSchedule();
 for (int k = 0; k < listSchedule.size(); k++) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getCoursepresidentid() != null) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getCoursepresidentid()
 		.equals(listSchedule.get(k).getSciInstruments().getCoursepresident().getCoursepresidentid())) {
 %> <br>(<%=listSchedule.get(k).getSciInstruments().getSciInstruments_Name()%>)
									<%
 	//listSchedule.remove(k);
 }
 %> <%
 	}
 %> <%
 	}
 %> <%
 	} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 2) {
 %> เจ้าหน้าที่ห้องปฏิบัติการ <%
 	List<Schedule> listSchedule = flatFeeForm.getListSchedule();
 for (int k = 0; k < flatFeeForm.getListSchedule().size(); k++) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null) {
 %> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid()
 		.equals(listSchedule.get(k).getSciInstruments().getStaff().getStaffid())) {
 %> <br>(<%=listSchedule.get(k).getSciInstruments().getSciInstruments_Name()%>)
									<%
 	//listSchedule.remove(k);
 }
 %> <%
 	}
 %> <%
 	}
 %> <%
 	} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproveNo() == 4) {
 %> หัวหน้างานบริการวิชาการและวิจัย <%
 	} else {
 %> รองคณบดีฯ <%
 	}
 %>
								</label> <label class="col-sm-4 col-form-label text-left"> <%
 	if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null) {
 %> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getPrefix() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getFirstname() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getLastname()%>
									<%
										} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
											.getCoursepresidentid() != null) {
									%> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getPrefix() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getFirstname() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident().getLastname()%>
									<%
										} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_ID() != null) {
									%> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_Prefix() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_FirstName() + " "
		+ flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getPersonnel().getPersonnel_LastName()%>
									<%
										} else if(flatFeeForm.getMember().getMember_ID() != null){
									%> <%=flatFeeForm.getMember().getAdvisor_name()%> <%
 	}else if (flatFeeForm.getStudent().getStudentID() != null){
 %>
 <%=  flatFeeForm.getStudent().getAdvisor_name()%>
<%}  %>

								</label>
								<div class="col-sm-3">
									<span class="form-control bg-txt data"> <%=flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStatus()%>
									</span>
								</div>
							</div>




							<%
								}
							%>
							<%
								}
							%>


							<% if((personnel.getPersonnel_type().equals("Admin") || personnel.getPersonnel_type().equals("LaboratoryStaff"))
									){ %>
							<div class="form-group row">

								<div class="col-sm-12">
								<% String typeform = "";
								if(flatFeeForm.getSericeRequestFormNumber().contains("A")){
									typeform = "บริการเครื่องมือวิทยาศาสตร์เเบบเหมา";
								}else if(flatFeeForm.getSericeRequestFormNumber().contains("E")){
									typeform = "บริการเครื่องมือวิทยาศาสตร์เเบบรายชั่วโมง";
								}
								
								%>
									<a href="SubmitProofOfPaymentPage?typeform=<%=typeform%>&formnumber=<%=flatFeeForm.getSericeRequestFormNumber()%>"><button type="button"
											
											<% if(!flatFeeForm.getBooking_status().equals("อนุมัติ")){%>
											disabled="disabled"
											 class="btn btn-secondary" 
											<% }else{ %>
											class="btn btn-success"
											<% } %>
											> 
											ยืนยันการจ่ายเงิน
											</button></a>

								</div>
							</div>
							<% } %>


							<div class="form-group row">

								<div class="col-sm-12">

									<a href="loadServicerequest"><button type="button"
											class="btn btn-success">ย้อนกลับ</button></a>

								</div>
							</div>




						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>

	<c:if test="${msg != null }">
		<script type="text/javascript">
		var msg = '${msg}';
		alert(msg);
</script>
	</c:if>
</body>
</html>