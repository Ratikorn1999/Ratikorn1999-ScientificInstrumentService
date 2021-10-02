<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,LoginMJU.*"%>
<%
Member member = null;
Student student = null;
Personnel personnel = null;
AuthLogin auths = null;
boolean checkauth = false ;
ListLaboratoryInstrumentsManager llim = new ListLaboratoryInstrumentsManager();
List<SciInstruments> listsciInstruments = llim.getListlaboratoryInstruments();
FilltheServiceRequestFormManager fsrfm = new FilltheServiceRequestFormManager(); 
List<SciInstruments> listsciInstrumentsFlatFee = fsrfm.getListlaboratoryInstrumentsforFlatFee();
try {
	student = (Student) session.getAttribute("student");
} catch (Exception e) {}
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {}
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	auths = (AuthLogin) session.getAttribute("checkauth");
	checkauth = auths.getResult();
	if(!checkauth){
		response.sendRedirect("Login");
	}
} catch (Exception e) {}
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
int year = today.get(Calendar.YEAR);
String mindate = year + "-" + month + "-" + day;
String maxdate = (year + 10) + "-" + month + "-" + day;
/*------------------- End  Create Date -------------------*/
%>
<!DOCTYPE html>
<html>
<head>

<title>บริการยืมอุปกรณ์วิทยาศาสตร์ : เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./img/logosci.png" rel="icon">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'	rel='stylesheet' type='text/css'>
<link rel="stylesheet"	href="./css/web_css.css">
</head>

<script type="text/javascript">
	function checkradiot() {
		var tcheck = document.getElementsByName('t');
		if (tcheck[0].checked) {
			document.getElementById('tn1').disabled = false;
			document.getElementById('tn2').disabled = true;
			document.getElementById('tn3').disabled = true;
			document.getElementById('branch').disabled = true;
			document.getElementById('faculty').disabled = true;
			document.getElementById('university').disabled = true;
			document.getElementById('advisorName').disabled = true;
			var rates = document.getElementsByName('degree');
			for (var i = 0; i < rates.length; i++) {
				rates[i].disabled = true;
			}
		}
		if (tcheck[1].checked) {
			document.getElementById('tn1').disabled = true;
			document.getElementById('tn2').disabled = false;
			document.getElementById('tn3').disabled = true;
			document.getElementById('branch').disabled = true;
			document.getElementById('faculty').disabled = true;
			document.getElementById('university').disabled = true;
			document.getElementById('advisorName').disabled = true;
			var rates = document.getElementsByName('degree');
			for (var i = 0; i < rates.length; i++) {
				rates[i].disabled = true;
			}
		}
		if (tcheck[2].checked) {
			document.getElementById('tn1').disabled = true;
			document.getElementById('tn2').disabled = true;
			document.getElementById('tn3').disabled = false;
			document.getElementById('branch').disabled = false;
			document.getElementById('faculty').disabled = false;
			document.getElementById('university').disabled = false;
			document.getElementById('advisorName').disabled = false;
			
			var rates = document.getElementsByName('degree');
			for (var i = 0; i < rates.length; i++) {
				rates[i].disabled = false;
			}
			

		}

	}

	function typeworkOtherClick() {
		var twcheck = document.getElementsByName('typework');
		if (twcheck[3].checked) {
			document.getElementById("othertypeword").disabled = false;
		} else {
			document.getElementById("othertypeword").disabled = true;
		}

	}

	function otherusage_characteristicsClick() {
		var uccheck = document.getElementsByName('usage_characteristics');
		if (uccheck[3].checked) {
			document.getElementById('otherusage_characteristics').disabled = false;
		} else {
			document.getElementById('otherusage_characteristics').disabled = true;
		}
	}

	function showForm(selectObject) {
		var value = selectObject.value;

		if (value == "2") {
			document.getElementById('flatFeeForm').style.display = "inline";
			document.getElementById('flatFeeFormdiv').style.display = "inline";
			document.getElementById('servicerequestFormdiv').style.display = "none";
			
		} else {
			document.getElementById('flatFeeForm').style.display = "none";
			document.getElementById('flatFeeFormdiv').style.display = "none";
			document.getElementById('servicerequestFormdiv').style.display = "inline";
			
		}

	}

	function checkradiot2() {
		var check = document.getElementsByName('typetitle');
		if (check[0].checked) {
			var rates = document.getElementsByName('type_course_Name');
			for (var i = 0; i < rates.length; i++) {
				rates[i].disabled = false;
			}
			document.getElementById('course_id').disabled = false;
			document.getElementById('course_Name').disabled = false;
			document.getElementById('major').disabled = false;
			document.getElementById('title').disabled = false;
			document.getElementById('research_project_Name').disabled = true;
			document.getElementById('funding_source').disabled = true;
			document.getElementById('projectleader').disabled = true;

		}
		if (check[1].checked) {
			document.getElementById('research_project_Name').disabled = false;
			document.getElementById('funding_source').disabled = false;
			document.getElementById('projectleader').disabled = false;
			var rates = document.getElementsByName('type_course_Name');
			for (var i = 0; i < rates.length; i++) {
				rates[i].disabled = true;
				rates[i].checked = false;
			}
			document.getElementById('course_id').disabled = true;
			document.getElementById('course_Name').disabled = true;
			document.getElementById('major').disabled = true;
			document.getElementById('title').disabled = true;

		}

	}

	function addrow(name,num,siid) {
		var rowCount = ($('table#servicerequestFormTable tr:last').index());
		

		if(rowCount<21){
			var sname = name;
			var id = num;
			var table = document.getElementById("servicerequestFormTable");
			var row = table.insertRow(table.length);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			
		cell1.innerHTML = "<input type='hidden' name='sciInstruments_ID' id='sciInstruments_ID' value='"+siid+"'>" + "<input type='text' name='sciInstruments_Name' id='sciInstruments_Name' value='"+sname+"' readonly  class='form-control'>";
		cell2.innerHTML = "<input type='date' name='date' id='date' min='<%=mindate%>' max='<%=maxdate %>' required>";
		cell3.innerHTML = "<div><input type='time' name='starttime' id='starttime' min='09:00' max='18:00'  required> ถึง <input type='time'  name='endtime' id='endtime' min='09:00' max='18:00'  required></div>";
		cell4.innerHTML = "<button  name='delete' id='delete' onclick='deleterow(this,"+id+")' class='btn btn-danger'><i class='fa fa-trash'></i></button>";
		
		}else{
			alert("สามารถยืมเครื่องมือวิทยาศาสตร์ได้สูงสุด 20 ");
		}
	
			

	}

	function addrow2(name,num,siid) {
		var rowCount = $('table#flatFeeFormTable tr:last').index();
		
		if(rowCount<21){
		var sname = name;
		var id = num;
		var table = document.getElementById("flatFeeFormTable");
		var row = table.insertRow(table.length);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
	
		cell1.innerHTML = "<input type='hidden' name='fsciInstruments_ID' id='fsciInstruments_ID' value='"+siid+"'>" + "<input type='text' name='sciInstruments_Name' id='sciInstruments_Name' value='"+sname+"' readonly  class='form-control'>";
		cell2.innerHTML = "<input type='date' name='startdate' id='startdate' min='<%=mindate%>' max='<%=maxdate %>' required>";
		cell3.innerHTML = "<input type='date' name='enddate' id='enddate' min='<%=mindate%>' max='<%=maxdate %>' required>";
		cell4.innerHTML = "<input type='text' name='term' id='term' value=''   class='form-control'>";
		cell5.innerHTML =  "<button  name='delete' id='delete' onclick='return deleterow2(this,"+id+")' class='btn btn-danger'><i class='fa fa-trash'></i></button>";
		}else{
			alert("สามารถยืมเครื่องมือวิทยาศาสตร์ได้สูงสุด 20 ");
		}
	}

	function deleterow(r,num) {	 
		 var i = r.parentNode.parentNode.rowIndex;
		 document.getElementById("servicerequestFormTable").deleteRow(i);
		 var x = "s_btnadd"+num;
		 //console.log(x);
		 document.getElementById(x).disabled = false;
	}
	
	function deleterow2(r,num) {	 
		 var i = r.parentNode.parentNode.rowIndex;
		 document.getElementById("flatFeeFormTable").deleteRow(i);
		 var x = "f_btnadd"+num;
		 //console.log(x);
		 document.getElementById(x).disabled = false; 
	}
	
</script>








<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container" style="margin-top: 35px;">

		<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">

					
							<h3>บริการยืมอุปกรณ์วิทยาศาสตร์</h3>
							<hr class="colorgraph">

							<form action="doAddservicerequestform" name="frm" method="post">


								<div class="form-group row">
									<label class="col-sm-4 col-form-label text-right">แบบฟอร์มการขอใช้เครื่องมือวิทยาศาสตร์</label>
									<div class="col-sm-4">
										<select id="typeform" class="form-control" name="typeform"
											onchange="return showForm(this)">
											<option value="1">แบบรายชั่วโมง</option>
											<option value="2">แบบเหมา</option>
										</select>
									</div>
								</div>

								<hr>


								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อผู้ขอใช้บริการ
									</label>
									<div class="col-sm-4">
										<input type="text" name="name" id="name"
											class="form-control data"
											<% if( member != null){ %>
											value="<%=member.getMember_Prefix() + " " + member.getMember_FirstName() + "  " + member.getMember_LastName()%>"
											<% }else if ( student != null){ %>
											value=" <%= student.getNameTh() + "  " + student.getSurnameTh()%>"
											<% }else if(personnel != null) {%>
											value="<%=personnel.getPersonnel_Prefix() +  " "+personnel.getPersonnel_FirstName()+" "+personnel.getPersonnel_LastName() %>"
											<% } %>
											>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									ตำแหน่ง
									</label>
									<div class="col-sm-4">
										<input type="text" name="position" id="position"
											class="form-control data"
											<% if( member != null){ %>
											 value="<%=member.getPosition()%>"
											 <% }else if ( student != null){ %>
											value="นักศึกษา"
											<% }else if(personnel != null) {%>
											value="<%=personnel.getPosition() %>"
											<% } %>
											 >
									</div>
								</div>



								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									โทรศัพท์
									</label>
									<div class="col-sm-4">
										<input type="text" name="phoneno" id="phoneno"
											class="form-control data" maxlength="10"
											<% if( member != null){ %>
											value="<%=member.getPhonenumber()%>"
											<% }else if ( student != null){ %>
											value="<%=student.getPhonenumber() %>"
											<% }else if(personnel != null){ %>
											<% } %>
											required="required"
											>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									อีเมล
									</label>
									<div class="col-sm-4">
										<input type="text" name="email" id="email"
											class="form-control  data" 
											<% if( member != null){ %>
											value="<%=member.getEmail()%>"
											<% }else if ( student != null){ %>
											value="<%=student.getEmail() %>"
										<% }else if(personnel != null){ %>
										value="<%=personnel.getEmail()  %>"
											<% } %>
											required="required"
											>
									</div>
								</div>
								
								

								<% if(personnel != null){ %>
								
								<%
								}else if(member != null){
									if (member.getPosition().equals("อาจารย์")) {
								%>

								
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									หลักสูตร/คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="tn1" id="tn1"
											class="form-control  data" value="<%=member.getFaculty()%>">
									</div>

								</div>

								<%
									} else if (member.getPosition().equals("หน่วยงานภายนอก")) {
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
											value="<%=member.getCompany_name()%>">
									</div>

								</div>
								<%
									} else if (member.getPosition().equals("นักศึกษา")) {
								%>
								<div class="form-group row">
									

									<label class="col-sm-2 col-form-label text-right">
									ชื่ออาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisorName" id="advisorName"
											<% if(member.getAdvisor_name() != null){ %>
											value="<%=member.getAdvisor_name()%>"
											<% }else { %>
											value=""
											<% } %>
											required="required"
											>
									</div>
									
									<label class="col-sm-2 col-form-label text-right">
									อีเมลอาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisoremail" id="advisoremail"
											<% if(member != null || member.getEmailadvisor() != null){ %>
											value="<%=member.getEmailadvisor()%>"
											<% }%>
										
											required="required"
											>
									</div>
									

								</div>

								<div class="form-group row">
								
								<label class="col-sm-2 col-form-label text-right">
										นักศึกษาระดับปริญญา 
									</label>

									<div class="col-sm-4">
										<%
											if ( member.getDegree().equals("ปริญญาตรี")) {
										%>
										<input type="text" name="degree" id="degree" value="ตรี"
											class="form-control  data">
										<%
											} else if ( member.getDegree().equals("ปริญญาโท")) {
										%>
										<input type="text" name="degree" id="degree" value="โท"
											class="form-control  data">
										<%
											} else if (member.getDegree().equals("ปริญญาเอก")) {
										%>
										<input type="text" name="degree" id="degree" value="เอก"
											class="form-control  data">
										<%
											}
										%>

									</div>
								
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
										<input type="text" name="branch" id="branch"
											class="form-control  data" value="<%=member.getBranch()%>">
									</div>
									
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="faculty" id="faculty"
											class="form-control  data" value="<%=member.getFaculty()%>">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									มหาวิทยาลัย
									</label>
									<div class="col-sm-4">
										<input type="text" name="university" id="university"
											class="form-control  data" id="university"
											value="<%=member.getUniversity()%>">
									</div>

								</div>

								<%
									}
									}else if (student != null){
								%>
								<div class="form-group row">
									

									<label class="col-sm-2 col-form-label text-right">
									ชื่ออาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisorName" id="advisorName"
											required="required" value="<%=student.getAdvisor_name() %>"
											>
									</div>
									
									<label class="col-sm-2 col-form-label text-right">
									อีเมลอาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisoremail" id="advisoremail"
											required="required" value="<%=student.getEmailadvisor() %>"
											>
									</div>
									

								</div>

								<div class="form-group row">
								
								<label class="col-sm-2 col-form-label text-right">
										ระดับการศึกษา
									</label>

									<div class="col-sm-4">
									
										<input type="text" name="degree" id="degree" value="<%= student.getLevelName() %>"
											class="form-control  data">
										

									</div>
								
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
										<input type="text" name="branch" id="branch"
											class="form-control  data" value="<%=student.getProgramNameTh()%>">
									</div>
									
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="faculty" id="faculty"
											class="form-control  data" value="<%=student.getFacultyNameTh()%>">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									มหาวิทยาลัย
									</label>
									<div class="col-sm-4">
										<input type="text" name="university" id="university"
											class="form-control  data" id="university"
											value="แม่โจ้">
									</div>

								</div>

								<% } %>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ประเภทของงาน
									</label>
									<div class="col-sm-10 ">
										<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="typework"
												id="typework1" onclick="return typeworkOtherClick()"
												required value="การเรียนการสอน"> <label
												class="form-check-label" for="typework1">การเรียนการสอน
											</label> 
											</div>
												<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="typework"
												id="typework2" onclick="return typeworkOtherClick()"
												required value="วิจัย"> <label
												class="form-check-label" for="typework2">วิจัย</label>
												</div>
												<div class="form-check form-check-inline">
											<input
												type="radio" class="form-check-input" name="typework"
												id="typework3" onclick="return typeworkOtherClick()"
												required value="วิทยานิพนธ์/ปัญหาพิเศษ"> <label
												class="form-check-label" for="typework3">วิทยานิพนธ์/ปัญหาพิเศษ
											</label> 
											</div>
												<div class="form-check form-check-inline">
											<input type="radio" class="form-check-input" name="typework"
												id="typework4" onclick="return typeworkOtherClick()"
												required value="othertypeword"> <label
												class="form-check-label" for="typework4">อื่นๆ
												(โปรดระบุ) </label>

										</div>
										<div class="form-check form-check-inline">
											<input type="text" class="form-control" name="othertypeword"
											id="othertypeword" disabled maxlength="30">
										</div>
									</div>

								</div>
								

								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อเรื่อง/ชื่อวิชา 
									</label>
									<div class="col-sm-10">
										<input type="text" name="subjectname"   class="form-control"
										id="subjectname">
									</div>

								</div>

								<div class="form-group row">
									<label class="col-sm-12 col-form-label text-center">
									(กรุณาแนบสำเนาโครงการวิจัย/โครงร่างงานวิจัย/โครงร่างวิทยานิพนธ์หรือปัญหาพิเศษ(ถ้ามี))
									</label>
								</div>

									
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ลักษณะการใช้งาน
									</label>
									<div class="col-sm-10">
										<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input"  name="usage_characteristics" id="usage_characteristics1"	onclick="return otherusage_characteristicsClick()" value="ใช้เครื่องมือด้วยตนเองอย่างเดียว">
										<label class="form-check-label" for="usage_characteristics1">
										ใช้เครื่องมือด้วยตนเองอย่างเดียว
										</label>	
										</div>
										<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" 	name="usage_characteristics" id="usage_characteristics2" onclick="return otherusage_characteristicsClick()" value="เตรียมตัวอย่าง และใช้เครื่องมือ">
										<label class="form-check-label" for="usage_characteristics2">
										เตรียมตัวอย่างและใช้เครื่องมือ
										</label>
										</div>
										<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input"  name="usage_characteristics" id="usage_characteristics3" onclick="return otherusage_characteristicsClick()" value="ใช้เครื่องมือโดยให้นักวิทยาศาสตร์เป็นผู้ดูแล">
										<label class="form-check-label" for="usage_characteristics3">
										ใช้เครื่องมือโดยให้นักวิทยาศาสตร์เป็นผู้ดูแล 
										</label>	
										</div>	
									

										<div class="form-check form-check-inline">
										<input	type="radio" class="form-check-input"  name="usage_characteristics"	id="usage_characteristics4"	onclick="return otherusage_characteristicsClick()" value="otherusage_characteristics">
										<label class="form-check-label" for="usage_characteristics4">
										อื่นๆ (โปรดระบุ)
										</label>
										</div>
										<div class="form-check form-check-inline">
											<input type="text" 	class="form-control  data" name="otherusage_characteristics" 
											id="otherusage_characteristics" disabled maxlength="150">
										</div>
									</div>
								</div>
								
								
								
	
								<div id="flatFeeForm" style="display: none;">
								
									<div class="form-group row">
									<label class="col-sm col-form-label text-right">
									</label>
										<div class="col-sm-11">
									<input type="radio" name="typetitle" id="typetitle1" value="1" class="form-check-input"
										onclick="checkradiot2()">
										<label class="form-check-label" for="typetitle1">
											นักศึกษาคณะวิทยาศาสตร์ที่ลงทะเบียนเรียนรายวิชาที่มีการทำวิจัย/ปัญหาพิเศษ/ค้นคว้าอิสระ/วิทยานิพนธ์/ดุษฎีนิพนธ์
										</label>
									</div>
								
									</div>
								
									<div class="form-group row">
										<label class="col-sm-2 col-form-label text-right"></label>
										<div class="col-sm-10">
											<div class="form-check form-check-inline">
												<input type="radio" class="form-check-input"  name="type_course_Name" id="type_course_Name1"	value="ปัญหาพิเศษ"  disabled>
												<label class="form-check-label" for="type_course_Name1">
												 ปัญหาพิเศษ 
												</label>	
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" class="form-check-input" 	name="type_course_Name" id="type_course_Name2" value="ค้นคว้าอิสระ" disabled>
												<label class="form-check-label" for="type_course_Name2">
												ค้นคว้าอิสระ
												</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="radio" class="form-check-input" name="type_course_Name" id="type_course_Name3"	value="วิทยานิพนธ์" disabled >
												<label class="form-check-label" for="type_course_Name3">
												วิทยานิพนธ์ 
												</label>	
											</div>	
										
											<div class="form-check form-check-inline">
												<input type="radio" class="form-check-input" name="type_course_Name" id="type_course_Name4"	value="ดุษฎีนิพนธ์" disabled >
												<label class="form-check-label" for="type_course_Name4">
												ดุษฎีนิพนธ์ 
												</label>	
											</div>	
										</div>
									</div>
								
								
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									</label>
									<label class="col-sm col-form-label ">
									รหัสวิชา
									</label>
									<div class="col-sm-4">
									<input type="text" name="course_id"  class="form-control" id="course_id" disabled>
									</div>
									<label class="col-sm col-form-label ">
									ชื่อวิชา
									</label>
									<div class="col-sm-4">
									<input type="text" name="course_Name"  class="form-control"		id="course_Name" disabled>
									</div>

								</div>
								
								<div class="form-group row">
									<label class="col-sm-3 col-form-label  text-right">
									สาขาวิชา
									</label>
									<div class="col-sm-4">
									<input type="text" name="major" id="major"  class="form-control" disabled>
									</div>
									<label class="col-sm-1 col-form-label ">
									ชื่อเรื่อง
									</label>
									<div class="col-sm-4">
									<input type="text"  class="form-control" name="title" id="title" disabled>
									</div>

								</div>
									
									

										<div class="form-group row">
									<label class="col-sm col-form-label text-right">
									</label>
										<div class="col-sm-11">
									<input type="radio" name="typetitle" id="typetitle1"  value="1" class="form-check-input"
										onclick="checkradiot2()">
										<label class="form-check-label" for="typetitle1">
											โครงการวิจัยที่มีนักวิจัยสังกัดคณะวิทยาศาสตร์
										</label>
									</div>
								
									</div>
									
									<div class="form-group row">
									<label class="col-sm-3 col-form-label  text-right">
									ชื่อเรื่อง
									</label>
									<div class="col-sm-4">
									<input type="text" name="research_project_Name" id="research_project_Name"  class="form-control"  disabled>
									</div>
									<label class="col-sm-1 col-form-label  text-right">
									แหล่งทุน
									</label>
									<div class="col-sm-4">
									 <input type="text" name="funding_source" id="funding_source"  class="form-control"  disabled>
									</div>

								</div>
								
								<div class="form-group row">
									<label class="col-sm-3 col-form-label  text-right">
									หัวหน้าโครงการ
									</label>
									<div class="col-sm-4">
									<input type="text"		name="projectleader" id="projectleader"  class="form-control"  disabled>
									</div>
									

								</div>
									
									
								

								</div>



								<!--########################### servicerequestFormTable ###########################-->
								<div id="servicerequestFormdiv">
								
									<h5>ค้นหาเครืองมือวิทยาศาสตร์</h5>
										<hr>
				

									<input type="text" id="myInput" onkeyup="myFunction()"
										placeholder="ใช่ชื่อเครื่องมือวิทยาศาสตร์"
										title="Type in a name" class="form-control">

									<table id="myTable" class="table table-hover listcontent">

										<tbody>
											<%
												for (int i = 0; i < listsciInstruments.size(); i++) {
											%>
											<tr style="display: none;">
												<td><%=listsciInstruments.get(i).getSciInstruments_Name()%></td>
												<td align="right">
													<%
														String name = listsciInstruments.get(i).getSciInstruments_Name();
													if (name.contains("(")) {
														name = name.substring(0, name.indexOf("("));
													}
													%>

													<button type="button"
														onclick="this.disabled=true;addrow('<%=name%>',<%=i%>,'<%=listsciInstruments.get(i).getSciInstruments_ID()%>')"
														id="s_btnadd<%=i%>" class="btn btn-success">
														<i class="fa fa-plus" aria-hidden="true"></i>
													</button>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>


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



									</table>

								</div>
								<!--########################### End servicerequestFormTable ###########################-->

								<!--########################### flatFeeFormTable ###########################-->
								<div id="flatFeeFormdiv" style="display: none;">

										<h5>ค้นหาเครืองมือวิทยาศาสตร์</h5>
										<hr>
				
									<input type="text" id="myInput2" onkeyup="myFunction2()"
										placeholder="Search for names.." title="Type in a name"
										class="form-control">
									<table id="myTable2" class="table table-hover">

										<tbody>
											<%
												for (int i = 0; i < listsciInstrumentsFlatFee.size(); i++) {
											%>
											<tr style="display: none;">
												<td><%=listsciInstrumentsFlatFee.get(i).getSciInstruments_Name()%></td>
												<td align="right">
													<%
														String name = listsciInstrumentsFlatFee.get(i).getSciInstruments_Name();
													if (name.contains("(")) {
														name = name.substring(0, name.indexOf("("));
													}
													%>

													<button type="button"
														onclick="this.disabled=true;addrow2('<%=name%>',<%=i%>,'<%=listsciInstrumentsFlatFee.get(i).getSciInstruments_ID()%>')"
														id="f_btnadd<%=i%>" class="btn btn-success">
														<i class="fa fa-plus" aria-hidden="true"></i>

													</button>

												</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>

									เครื่องมือวิทยาศาสตร์ที่ต้องการขอใช้มีดังนี้ <br>
									<table id="flatFeeFormTable" class="table table-bordered ">

										<tr align="center">
											<th rowspan="2">ชื่อเครื่องมือวิทยาศาสตร์</th>
											<th colspan="2">วันที่ขอใช้</th>
											<th rowspan="2">ภาคการศึกษาที่</th>
											
										</tr>
										<tr>
											<th>เริ่มต้น</th>
											<th>สิ้นสุด</th>
										</tr>
									</table>

								</div>
								<!--########################### End flatFeeFormTable ###########################-->




								<div class="form-group row">
									<div class="col-sm-12 text-center">
										<button type="submit" name="submit" class="btn btn-success"
											onclick="return checkform(frm)">ยืนยัน</button>
										<a href="#"><button type="reset" class="btn btn-warning">ยกเลิก</button></a>
									</div>
								</div>

							</form>




						
					</div>
				</div>
			</div>
		</section>

	</div>




	<jsp:include page="common/footer.jsp"></jsp:include>

	<script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue,n;
  n = 0;
  input = document.getElementById("myInput");

  filter = input.value.toUpperCase();
  
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
	 if(filter!=""){
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
   		
      	if (txtValue.toUpperCase().indexOf(filter) > -1) {
    	  		if(n<5){
       	 			tr[i].style.display = "";
        			n++;
    	 		 }else{
    				tr[i].style.display = "none";  
    	  		}
     	 } else {
        	tr[i].style.display = "none";
      	}
   		
    }    
	 } else{
		 tr[i].style.display = "none";
	 }
  }
}



function myFunction2() {
	  var input, filter, table, tr, td, i, txtValue,n;
	  n = 0;
	  input = document.getElementById("myInput2");

	  filter = input.value.toUpperCase();
	  
	  table = document.getElementById("myTable2");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
		 if(filter!=""){
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	   		
	      	if (txtValue.toUpperCase().indexOf(filter) > -1) {
	    	  		if(n<5){
	       	 			tr[i].style.display = "";
	        			n++;
	    	 		 }else{
	    				tr[i].style.display = "none";  
	    	  		}
	     	 } else {
	        	tr[i].style.display = "none";
	      	}
	   		
	    }    
		 } else{
			 tr[i].style.display = "none";
		 }
	  }
	}
	</script>




<c:if test="${msg != null }">
		<script type="text/javascript">
		var msg = '${msg}';
		alert(msg);
</script>
	</c:if>

</body>
</html>