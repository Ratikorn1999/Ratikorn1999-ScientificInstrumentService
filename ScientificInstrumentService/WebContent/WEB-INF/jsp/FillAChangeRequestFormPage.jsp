<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*,LoginMJU.*"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <% 
    Member member = null;
    Student student = null;

    SericeRequestForm sericeRequestForm =null;
    ListLaboratoryInstrumentsManager llim = new ListLaboratoryInstrumentsManager();
    List<SciInstruments> listsciInstruments = llim.getListlaboratoryInstruments();
    try {
    	member = (Member) session.getAttribute("member");
    } catch (Exception e) {
    }
    try {
    	student = (Student) session.getAttribute("student");
    } catch (Exception e) {
    }
    try {
    	sericeRequestForm =(SericeRequestForm) request.getAttribute("sericeRequestForm");
    } catch (Exception e) {}
    String date = null;
    
    /*------------------- Create Date -------------------*/
    Calendar today = Calendar.getInstance();
    String time = new SimpleDateFormat("HH:mm", new Locale("th", "TH")).format(today.getTime().getTime()) ;
    String day = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
    if (day.length() < 2) {
    	day = "0" + day;
    }
    String month = String.valueOf(today.get(Calendar.MONTH) + 1);
    if (month.length() < 2) {
    	month = "0" + month;
    }
    int year = (today.get(Calendar.YEAR)) ;
    
    String mindate = year + "-" + month + "-" + day+"T"+time;
    String maxdate = (year + 1) + "-" + month + "-" + day+"T"+time;
    /*------------------- End  Create Date -------------------*/
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'	rel='stylesheet' type='text/css'>
<link rel="stylesheet"	href="./css/web_css.css">

<title>FillAChangeRequestPage</title>
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
		}
		if (tcheck[1].checked) {
			document.getElementById('tn1').disabled = true;
			document.getElementById('tn2').disabled = false;
			document.getElementById('tn3').disabled = true;
			document.getElementById('branch').disabled = true;
			document.getElementById('faculty').disabled = true;
			document.getElementById('university').disabled = true;
			document.getElementById('advisorName').disabled = true;
		}
		if (tcheck[2].checked) {
			document.getElementById('tn1').disabled = true;
			document.getElementById('tn2').disabled = true;
			document.getElementById('tn3').disabled = false;
			document.getElementById('branch').disabled = false;
			document.getElementById('faculty').disabled = false;
			document.getElementById('university').disabled = false;
			document.getElementById('advisorName').disabled = false;
			
			
			
			
		}

	}

	function typeserviceOtherClick() {

		var twcheck = document.getElementsByName('typeservice');
		if (twcheck[2].checked) {
			document.getElementById("othertypeservice").disabled = false;
		} else {
			document.getElementById("othertypeservice").disabled = true;
		}

	}

	
	
	

	
</script>

<body>

	<jsp:include page="common/navbar.jsp"></jsp:include>
	
	<div class="container" style="margin-top: 35px;">
	
		<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
		
		
				
				<h3>แบบฟอร์มเปลี่ยนแปลงการใช้เครื่องมือวิทยาศาสตร์</h3>
				
				<hr class="colorgraph">
					

					<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อผู้ขอใช้บริการ
									</label>
									<div class="col-sm-4">
										<input type="text" name="name" id="name"
											class="form-control data"
											<% if(sericeRequestForm.getMember().getMember_ID() != null) {%>
											value="<%=sericeRequestForm.getMember().getMember_Prefix() + " " + sericeRequestForm.getMember().getMember_FirstName() + "  " + sericeRequestForm.getMember().getMember_LastName()%>"
											<% }else if(sericeRequestForm.getStudent().getStudentID() != null){ %>
											value="<%= sericeRequestForm.getStudent().getNameTh()+" "+sericeRequestForm.getStudent().getSurnameTh() %>"
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
											<% if(sericeRequestForm.getMember().getMember_ID() != null) {%>
											 value="<%=sericeRequestForm.getMember().getPosition()%>"
											<% }else if(sericeRequestForm.getStudent().getStudentID() != null){ %>
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
											<% if(sericeRequestForm.getMember().getMember_ID() != null) {%>
											value="<%=sericeRequestForm.getMember().getPhonenumber()%>"
											<% }else if(sericeRequestForm.getStudent().getStudentID() != null){ %>
											value="<%=sericeRequestForm.getStudent().getPhonenumber()%>"
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
												<% if(sericeRequestForm.getMember().getMember_ID() != null) {%>
											value="<%=sericeRequestForm.getMember().getEmail()%>"
											<% }else if(sericeRequestForm.getStudent().getStudentID() != null){ %>
											value="<%=sericeRequestForm.getStudent().getEmail()%>"
											<% } %>
											readonly="readonly"
											>
									</div>
								</div>


								<%
									if(sericeRequestForm.getMember().getMember_ID() != null){
										if (sericeRequestForm.getMember().getPosition().equals("อาจารย์")) {
								%>

								<div class="form-group row">
									
								</div>
								<div class="form-group row">
									<label class="col-sm-4 col-form-label text-right">
									หลักสูตร/คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="tn1" id="tn1"
											class="form-control  data" value="<%=sericeRequestForm.getMember().getFaculty()%>"
											readonly="readonly">
									</div>

								</div>

								<%
									} else if (sericeRequestForm.getMember().getPosition().equals("หน่วยงานภายนอก")) {
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
											value="<%=sericeRequestForm.getMember().getCompany_name()%>"
											readonly="readonly">
									</div>

								</div>
								<%
									} else if (sericeRequestForm.getMember().getPosition().equals("นักศึกษา")) {
								%>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
										นักศึกษาระดับปริญญา 
									</label>

									<div class="col-sm-4">
										<%
											if (sericeRequestForm.getMember().getDegree().equals("ปริญญาตรี")) {
										%>
										<input type="text" name="degree" id="degree" value="ตรี"
											class="form-control  data" readonly="readonly">
										<%
											} else if (sericeRequestForm.getMember().getDegree().equals("ปริญญาโท")) {
										%>
										<input type="text" name="degree" id="degree" value="โท"
											class="form-control  data" readonly="readonly">
										<%
											} else if (sericeRequestForm.getMember().getDegree().equals("ปริญญาเอก")) {
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
											value="<%=sericeRequestForm.getMember().getAdvisor_name()%>" readonly="readonly">
									</div>

								</div>

								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
										<input type="text" name="branch" id="branch"
											class="form-control  data" value="<%=sericeRequestForm.getMember().getBranch()%>" readonly="readonly">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="faculty" id="faculty"
											class="form-control  data" value="<%=sericeRequestForm.getMember().getFaculty()%>" readonly="readonly">
									</div>
								</div>
								
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									มหาวิทยาลัย
									</label>
									<div class="col-sm-4">
										<input type="text"  name="university" id="university"
											class="form-control  data" id="university"
											value="<%=sericeRequestForm.getMember().getUniversity()%>" readonly="readonly">
									</div>

								</div>

								<%
										}
									}else if (sericeRequestForm.getStudent().getStudentID() != null){
								%>
								<div class="form-group row">
									

									<label class="col-sm-2 col-form-label text-right">
									ชื่ออาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisorName" id="advisorName"
											required="required" value="<%=sericeRequestForm.getStudent().getAdvisor_name() %>"
											readonly="readonly">
									</div>
									
									<label class="col-sm-2 col-form-label text-right">
									อีเมลอาจารย์ที่ปรึกษา
									</label>
									<div class="col-sm-4">
										<input type="text" class="form-control  data"
											name="advisoremail" id="advisoremail"
											required="required" value="<%=sericeRequestForm.getStudent().getEmailadvisor() %>"
											readonly="readonly"
											>
									</div>
									

								</div>

								<div class="form-group row">
								
								<label class="col-sm-2 col-form-label text-right">
										ระดับการศึกษา
									</label>

									<div class="col-sm-4">
									
										<input type="text" name="degree" id="degree" value="<%= sericeRequestForm.getStudent().getLevelName() %>"
											class="form-control  data" readonly="readonly">
										

									</div>
								
									<label class="col-sm-2 col-form-label text-right">
									ภาค/สาขาวิชา
									</label>
									<div class="col-sm-4">
										<input type="text" name="branch" id="branch"
											class="form-control  data" value="<%=sericeRequestForm.getStudent().getProgramNameTh()%>" readonly="readonly">
									</div>
									
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">
									คณะ
									</label>
									<div class="col-sm-4">
										<input type="text" name="faculty" id="faculty"
											class="form-control  data" value="<%=sericeRequestForm.getStudent().getFacultyNameTh()%>" readonly="readonly">
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
				<form action="dochangerequestform" name="frm" method="post">
							<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ตามใบคำขอเลขที่
									</label>
									<div class="col-sm-4">
									 <input type="text" class="form-control  data" name="formnumber" id="formnumber" readonly value="<%= sericeRequestForm.getSericeRequestFormNumber()%>">
									</div>
									<label class="col-sm-2 col-form-label text-right">
									เมื่อวันที่ 
									</label>
									<div class="col-sm-4">
										<%date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(sericeRequestForm.getCreatedate());%>
										 <input type="text"  class="form-control  data" name="formnumber" id="formnumber" readonly value="<%= date %>">
									</div>
								</div>
						
							<div class="form-group row">
										<label class="col-sm-2 col-form-label text-right">ได้รับอนุมัติให้ใช้บริการ</label>
										<div class="col-sm-10">
											<div class="form-check form-check-inline">
												<input type="radio"  class="form-check-input" name="typeservice" id="typeservice1" onclick="return typeserviceOtherClick()"  <%if(sericeRequestForm!=null){ %> checked <%} %>value="เครื่องมือวิทยาศาสตร์" > 
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
					<thead align="center" class="table-info">
						<tr >
							<th rowspan="2">    ลำดับ<br>ที่ </th>
							<th colspan="3"> รายละเอียดการใช้บริการ(เดิม)</th>
							<th rowspan="2"> รายละเอียดการใช้บริการ <br> ที่ขอเปลี่ยนแปลง (ใหม่) </th>
						</tr>
						<tr>
						<th style="width: 300px;"> ชื่อเครื่องมือ</th>
						<th>เวลาเริ่มใช้</th>
						<th>เวลาสิ้นสุด</th>
						</tr>
						
					</thead>	
					<tbody>
					<% if(sericeRequestForm.getListSchedule()!=null){ %>
						<% for (int i = 0 ; i <sericeRequestForm.getListSchedule().size() ; i++) { %>
							
							<tr>
								<td><%= i+1 %></td>
								<td>
								<input type="hidden" name="sciInstruments_ID" id="sciInstruments_ID" value="<%=sericeRequestForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_ID()%>">
								<%= sericeRequestForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_Name() %></td>
								<td>
								<%= date = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH")).format(sericeRequestForm.getListSchedule().get(i).getStartDateTime()) %>
								<div id="sdate<%=i+1%>"  style="display: none;">
								<br>
								<br>
								<input type='datetime-local' name='sdate' id='sdate' min='<%=mindate%>' max='<%=maxdate %>' >
								</div>
								</td>
								<td>
								<%= date = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("th", "TH")).format(sericeRequestForm.getListSchedule().get(i).getEndDateTime()) %>
								<div id="edate<%=i+1%>"  style="display: none;">
								<br>
								<br>
								<input type='datetime-local' name='edate' id='edate' min='<%=mindate%>' max='<%=maxdate %>' >
								</div>
								</td>
								
								
								<td>
								<select name="cheangdetail" class="form-control data"  onchange="return check(this,<%=i+1%>)">
								<option value="0"> 
								------------
								</option>
								<option value="1"> 
								เปลี่ยนแปลงเวลา 
								</option>
								<option value="2">
								 ยกเลิก 
								 </option >
								
								</select>
								
								</td>
								
								
							</tr>
							
							
							
						<% } %>	
					<% } %>	
					</tbody>	
					</table>
					
				</div>

				
				
				
								<div class="form-group row">
									<div class="col-sm-12 text-center">
										<button type="submit" name="submit" class="btn btn-success"	>ยืนยัน</button>
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
		
		<script type="text/javascript">
		function check(selectObject,num) {
			var value = selectObject.value;
			
			
			 if(value == "1"){
				document.getElementById('edate'+num).style.display = "inline";
				document.getElementById('sdate'+num).style.display = "inline";
				
			}else{
				document.getElementById('edate'+num).style.display = "none";
				document.getElementById('sdate'+num).style.display = "none";
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