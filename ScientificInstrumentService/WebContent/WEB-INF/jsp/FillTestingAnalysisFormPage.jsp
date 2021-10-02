<%@page import="java.awt.event.WindowListener"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,LoginMJU.*"%>
<% 
    Member member = null;
    Student student = null;
    Personnel personnel = null;
    AuthLogin auths = null;
    boolean checkauth = false ;
    List<TestItem> listtestitem = null ;
    try {
    	student = (Student) session.getAttribute("student");
    } catch (Exception e) {
    	e.printStackTrace();
    }
    try {
    	member = (Member) session.getAttribute("member");
    } catch (Exception e) {
    	e.printStackTrace();
    }
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
    try {
    	listtestitem = (List<TestItem>) request.getAttribute("listtestitem");
    } catch (Exception e) {
    	e.printStackTrace();
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
%>
<!DOCTYPE html>
<html>
<head>
<title>บริการวิเคราะห์ทดสอบ : เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
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
function addrow() {
	var rowCount = $('table#myTable tr:last').index() ;
	if(rowCount<20){
	var table = document.getElementById("myTable");
	var row = table.insertRow(table.length);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	var cell7 = row.insertCell(6);


	rowCount = $('table#myTable tr:last').index() ;
	
	var btn = document.createElement("BUTTON");
	btn.innerHTML = "Delete";
	
	cell1.innerHTML = "<input type='text' name='sampleName' id='sampleName' class='form-control  data'>";
	cell2.innerHTML = "<input type='text' name='code' id='code' class='form-control  data'>";
	cell3.innerHTML = "<input type='text' name='weight' id='weight' class='form-control  data'>";
	cell4.innerHTML = "<input type='number' name='qty' id='qty' min='1' max='20' value='1' class='form-control  data'>";
	cell5.innerHTML = 
		"<select class='form-control data' name='testItemid' id='testItemid' ><option hidden  selected disabled >--กรุณาเลือกรายละเอียด--</option>"+
		<% for(int i = 0 ;i<listtestitem.size();i++){ %>
		+"<option value='<%=listtestitem.get(i).getTestitem_ID()%>'><%= listtestitem.get(i).getTestitem_Name() %></option>"
		<% } %>
		+"</select>"
	cell6.innerHTML = "<input type='text' name='storage' id='storage' class='form-control  data'>";
	cell7.innerHTML =  "<button type='button'  name='delete' id='delete' onclick='return deleterow(this)' class='btn btn-danger'><i class='fa fa-trash'></i></button>";
	}else{
		document.getElementById("addbutton").disabled = false;
	}
}
function deleterow(r) {	 
	 var i = r.parentNode.parentNode.rowIndex;
	 document.getElementById("myTable").deleteRow(i);
}
</script>
<body>
<jsp:include page="common/navbar.jsp"></jsp:include>
	
	<div class="container" style="margin-top: 35px;">
		
		<form action="doAddTestingAnalysisform" name="frm" method="post">
			<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
			<h3>แบบฟอร์มการขอใช้บริการวิเคราะห์ทดสอบ</h3>
			
				<hr class="colorgraph">
				
				<div class="form-group row">
									<label class="col-sm-3 col-form-label text-right">
									ต้องการออกผลทดสอบเป็นภาษา
									</label>
									<div class="col-sm-9">
										<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" id="language1" name="language" value="ไทย">
										<label class="form-check-label" for="language1">
										ไทย/Thai
										</label>	
										</div>
										<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" id="language2" name="language" value="อังกฤษ" >
										<label class="form-check-label" for="language2">
										 อังกฤษ/English
										</label>
										</div>
										<div class="form-check form-check-inline">
										<input type="radio" class="form-check-input" id="language3" name="language" value="ทั้งสองภาษา" >
										<label class="form-check-label" for="language3">
										 ทั้งสองภาษา (คิดค่าบริการออกผลเพิ่มตัวอย่างละ 100 บาท)		
										</label>	
										</div>	
										<br>
										<div class="form-check form-check-inline">
										<label class="form-check-label" >
										หากต้องการออกผลเป็นภาษาอังกฤษ กรุณาเขียนชื่อ-ที่อยู่ที่ใช้ในการออกผลทดสอบ/Report Address เป็นภาษาอังกฤษ
										</label>	
										</div>	
										
										
										
										
										</div>
							</div>					
			
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									นามลูกค้า
									</label>
									<div class="col-sm-10">
										<input type="text" class="form-control data" name="client_name" id="client_name" 	required="required">
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ที่อยู่
									</label>
									<div class="col-sm-4">
										<textarea class="form-control  data" id="address" name="address" rows="4" cols="50" style="resize:none;" 	required="required"></textarea>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									ชื่อ-ที่อยู่ที่ใช้ในการออกผลทดสอบ
									</label>
									<div class="col-sm-4">
										<textarea class="form-control  data" id="report_Address" name="report_Address" rows="4" cols="50" style="resize:none;" 	required="required"></textarea>
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อ-สกุล ผู้ติดต่อ
									</label>
									<div class="col-sm-4">
									<% if(member != null){ %>
										<input type="text" class="form-control data" name="name" id="name" value="<%=member.getMember_Prefix() + " " + member.getMember_FirstName() + "  " + member.getMember_LastName()%>">
									<%} else if(student != null){ %>	
										<input type="text" class="form-control data" name="name" id="name" value="<%=student.getNameTh() + "  " + student.getSurnameTh()%>">
									<% }else if(personnel != null){
									%>
									<input type="text" class="form-control data" name="name" id="name" 	value="<%=personnel.getPersonnel_Prefix() +  " "+personnel.getPersonnel_FirstName()+" "+personnel.getPersonnel_LastName() %>">
										<% } %>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									ตำแหน่ง
									</label>
									<div class="col-sm-4">
									<% if(member != null){ %>
									<input type="text" name="position" id="position" class="form-control data" value="<%=member.getPosition()%>">
									<% } else if(student != null){ %>
									<input type="text" name="position" id="position" class="form-control data" value="นักศึกษา">
									<% }else if(personnel != null){ %>
										<input type="text" name="position" id="position" class="form-control data" value="<%=personnel.getPosition()%>">
									<% } %>
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									โทรศัพท์
									</label>
									<div class="col-sm-4">
										<input type="text" name="phoneno" id="phoneno"
											class="form-control data" maxlength="10"
											<% if(member != null){ %>
											value="<%=member.getPhonenumber()%>"
											<% }  else if(student != null){ %>	
											value="<%=student.getPhonenumber()%>"
										<% }else if(personnel != null){ %>
											value=""
										<% } %>
												required="required"
											>
									</div>
									<label class="col-sm-2 col-form-label text-right">
									Email
									</label>
									<div class="col-sm-4">
										<input type="text" name="email" id="email"
											class="form-control  data" 
											<% if(member != null){ %>
											value="<%=member.getEmail()%>"
											<% }  else if(student != null){ %>	
											value="<%=student.getEmail()%>"
										<% }else if(personnel != null){ %>
											value="<%= personnel.getEmail() %>"
										<% } %>
											required="required"
											>
									</div>
								</div>
									<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									โทรสาร/Fax
									</label>
									<div class="col-sm-4">
										<input type="text" name="fax" id="fax" class="form-control  data">
									</div>
									
								</div>
							<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ชื่อที่อยู่ที่ใช้ในการออกใบเสร็จรับเงิน
									</label>
									<div class="col-sm-4">
									<textarea class="form-control  data" id="billing_Address" name="billing_Address" rows="4" cols="50" style="resize:none;" 	required="required"></textarea>
									</div>
								</div>
			
			
				
				
				
				
				<div >
					รายการวิเคราะห์ทดสอบที่ขอใช้บริการ <br>
					<table  id="myTable" class="table table-bordered">
						<tr align="center">
							<th>
							ชื่อตัวอย่าง
							<br>
							(Sample name)
							</th>
							<th>รหัส<br>(Code)</th>
							<th>น้ำหนัก/<br>ปริมาตร </th>
							<th>จำนวน </th>
							<th>รายละเอียดที่ขอรับบริการ<br>(Test Item)</th>
							<th>การเก็บรักษา<br>(อุณหภูมิ)</th>
						
						</tr>
						<tr >
							<td><input type='text' name='sampleName' id='sampleName' class="form-control  data"></td>
							<td><input type='text' name='code' id='code' class='form-control  data'></td>
							<td><input type='text' name='weight' id='weight' class='form-control  data'></td>
							<td><input type='number' name='qty' id='qty' min='1' max='20' value='1' class='form-control  data'></td>
							<td>
							<select class='form-control data' name='testItemid' id='testItemid' >
							<option hidden  selected disabled >--กรุณาเลือกรายละเอียด--</option>
							<% for(int i = 0 ;i<listtestitem.size();i++){ %>
							<option value="<%=listtestitem.get(i).getTestitem_ID()%>"><%= listtestitem.get(i).getTestitem_Name() %></option>
							<% } %>
							</select>
							</td>
							<td><input type='text' name='storage' id='storage' class='form-control  data'></td>
							
						</tr>
						


					</table>
					<button id="addbutton" class="btn btn-info" name="addbutton" type="button" onclick="return addrow()">Add</button>
				</div>
				
				
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ลักษณะบรรจุ
									</label>
									<div class="col-sm-4">
										 <input type="text" class="form-control data" name="packing_style" id="packing_style">
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									รายละเอียดการขอรับบริการเพิ่มเติม 
									</label>
									<div class="col-sm-10">
									<input type="radio" name="additional_details" id="additional_details" value="Uncertainty"> Uncertainty
									<input type="radio" name="additional_details" id="additional_details" value="LOD"> LOD
									<input type="radio" name="additional_details" id="additional_details" value="LOQ"> LOQ
									<input type="radio" name="additional_details" id="additional_details" value="MRL"> MRL
									<input type="radio" name="additional_details" id="additional_details" value="DL"> DL
									<input type="radio" name="additional_details" id="additional_details" value="other"> ระบุหน่วยเป็น <input type="text" name="other_additional_details" id="other_additional_details"> 
				
									</div>
								</div>
				
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ตัวอย่างที่นำมาขอบริการ 
									</label>
									<div class="col-sm-10">
									<input type="radio" name="receive_Analysis_Result" id="receive_Analysis_Result" value="ไม่ขอรับคืน"> ไม่ขอรับคืน 
									<input type="radio" name="receive_Analysis_Result" id="receive_Analysis_Result" value="ขอรับคืนโดยมารับด้วยตนเอง"> ขอรับคืนโดยมารับด้วยตนเอง 
									<input type="radio" name="receive_Analysis_Result" id="receive_Analysis_Result" value="ส่งให้ทางไปรษณีย์"> ส่งให้ทางไปรษณีย์(คิดค่าบริการเพิ่มตัวอย่างละ 100 บาท)		
									</div>
								</div>
								
								<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">
									ผลการทดสอบ
									</label>
									<div class="col-sm-10">
									<input type="radio" name="resultsOfTheAnalysis" id="resultsOfTheAnalysis" value="มารับด้วยตนเอง"> มารับด้วยตนเอง
									<input type="radio" name="resultsOfTheAnalysis" id="resultsOfTheAnalysis" value="ส่งให้ทางไปรษณีย์ EMS"> ส่งให้ทางไปรษณีย์ EMS (คิดค่าบริการเพิ่มตัวอย่างละ 100 บาท)
									</div>
								</div>
			
								<div class="form-group row">
									<label class="col-sm-12 col-form-label text-center" style="color: red">
									 หากท่านมีความต้องการพิเศษเกี่ยวกับการวิเคราะห์หรือการเตรียมตัวอย่างหรืออื่น ๆ โปรดระบุในช่องรายละเอียดที่ขอรับบริการหรือแจ้งกับเจ้าหน้าที่บริการลูกค้า <br>
									If you have special requirement associated with the sample analysis or preparation or any requirement. <br>
									Please specify clearly intended in Test item or inform customer service officer.</label>
									
								</div>
			
			
			
									<div class="form-group row">
									<div class="col-sm-12 text-center">
										<button type="submit" name="submit" class="btn btn-success"
											onclick="return checkform(frm)">ยืนยัน</button>
										<a href="#"><button type="reset" class="btn btn-warning">ยกเลิก</button></a>
									</div>
								</div>	
							
						</div>
				</div>
			</div>
		</section>
		</form>


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