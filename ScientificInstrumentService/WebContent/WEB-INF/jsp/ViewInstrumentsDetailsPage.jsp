 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
SciInstruments sciInstruments = null;
AddInstrumentsManager aimm = new AddInstrumentsManager();
List<SciInstrumentsType> listSciInstrumentsType = aimm.getlistSciInstrumentsType();
List<Coursepresident> listCoursepresident = aimm.getlistCoursepresident(); 
List<Staff> listStaff = aimm.getlistStaff();
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
	e.printStackTrace();
}
try {
	sciInstruments = (SciInstruments) request.getAttribute("sciInstruments");
	session.setAttribute("key", sciInstruments.getSciInstruments_ID());
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
	
	<div class="container" style="margin-top: 35px;">
		<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
						<form  action="do_EditInstrumentsDetails"   name="frm" method="post" enctype="multipart/form-data">
						<h3>ข้อมูลเครื่องมือวิทยาศาสตร์</h3>
						<hr class="colorgraph">
						
						
						<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รูปเครื่องมือ</label>
									<div class="col-sm-4">
										<img src="./img/sciInstruments_img/<%= sciInstruments.getSciInstruments_ID() %>.png" name="photo" id="photo" class="hidden-xs"  alt="" width="300" height="300">
									</div>									
									<div class="col-sm-4" style="display: none;" id="btnloadfile">
										<input type="file" name="loadfile" id="loadfile"	onchange="loadFile(event)"  accept=" image/jpeg, image/png" >
									</div>	
								</div>
						
						<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รหัสเครื่องมือ</label>
									<div class="col-sm-4">
										<input type="text" name="sciInstruments_ID" id="sciInstruments_ID" class="form-control data" value="<%=sciInstruments.getSciInstruments_ID()%>" disabled="disabled">
									</div>									
									
								</div>
								
								
								
						<div class="form-group row">
									<label class="col-sm-2 col-form-label text-right">ชื่อเครื่องมือ</label>
									<div class="col-sm-10">
										<input type="text" name="sciInstruments_Name" id="sciInstruments_Name" class="form-control data" value="<%=sciInstruments.getSciInstruments_Name()%>" disabled="disabled">
									</div>	
							</div>	
								
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">หมวดหมู่</label>
									<div class="col-sm-10">
										
										<select class="form-control" name="sciInstrumentsType_ID" id="sciInstrumentsType_ID" disabled="disabled">
										<% for(int i = 0 ;i<listSciInstrumentsType.size();i++){ %>
										<option value="<%=listSciInstrumentsType.get(i).getSciInstrumentsType_ID()%>"
										<% if(listSciInstrumentsType.get(i).getSciInstrumentsType_ID().equals(sciInstruments.getSciInstrumentsType().getSciInstrumentsType_ID())) {%>
										selected="selected"
										<% } %>
										
										>
										<%=listSciInstrumentsType.get(i).getSciInstrumentsType_Name() %></option>
										<% } %>
										</select>
									
									</div>									
									
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รายละเอียด</label>
									<div class="col-sm-10">
										<textarea class="form-control  data" id=detail name="detail" rows="4" cols="50" style="resize:none;" disabled="disabled" maxlength="255" ><%=sciInstruments.getDetail()%></textarea>

									</div>									
									
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ผู้รับผิดชอบ</label>
									<div class="col-sm-4">
										<select class="form-control" name="staff" id="staff" disabled="disabled">
										<option   value="0" 
										<%if(sciInstruments.getStaff().getStaffid() != null){ %>
										selected="selected"
										<% } %>
										>
										
										ไม่มี</option>
										<% for(int i = 0 ; i < listStaff.size() ; i++) {%>
										<option value="<%=listStaff.get(i).getStaffid()%>"
										<% 
										if(sciInstruments.getStaff().getStaffid() != null){
										if(sciInstruments.getStaff().getStaffid().equals(listStaff.get(i).getStaffid())){ %>
										selected="selected"
										<% }} %>
										>
										
										<%= listStaff.get(i).getFirstname()+"  "+listStaff.get(i).getLastname() %></option>
										<% } %>
										</select>
									</div>	
									</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ประธานหลักสูตร</label>
									<div class="col-sm-4">
										<select class="form-control" name="coursepresident" id="coursepresident" disabled="disabled">
										<option     value="0" 
										>
										ไม่มี
										</option>
										<% for(int i = 0 ; i < listCoursepresident.size() ; i++) {%>
										<option value="<%=listCoursepresident.get(i).getCoursepresidentid()%>"
										<% 
										if(sciInstruments.getCoursepresident().getCoursepresidentid() != null){
										if(sciInstruments.getCoursepresident().getCoursepresidentid().equals(listCoursepresident.get(i).getCoursepresidentid())){ %>
										selected="selected"
										<% }} %>
										>
										<%= listCoursepresident.get(i).getFirstname()+"  "+listCoursepresident.get(i).getLastname() %></option>
										<% } %>
										</select>
									</div>	
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">สถานะเครื่องมือ</label>
									<div class="col-sm-4">
										
										<select class="form-control" name="status" id="status" disabled="disabled">
										<option  value="พร้อมใช้งาน"   <%if(sciInstruments.getStatus().equals("พร้อมใช้งาน")){ %>
										selected="selected"
										<% } %>>
										พร้อมใช้งาน</option>
										<option value="ไม่พร้อมใช้งาน" <%if(sciInstruments.getStatus().equals("ไม่พร้อมใช้งาน")){ %>
										selected="selected"
										<% } %>>    ไม่พร้อมใช้งาน</option>
										</select>
										
									</div>	
									<div class="col-sm-4">		
										<button type="button" name="change" id="change" disabled="disabled"    class="btn btn-success form-control"
										onclick="return updatestatus('<%= sciInstruments.getSciInstruments_ID()%>')" 
										>เปลี่ยนสถานะ</button>
									</div>											
								</div>

								<div class="form-group row">
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-10">
										<span class="form-control-input">
										<input id="checkBox" type="checkbox" name="checkBox" onclick="return showinputchange(this)">
										<label for="checkBox1">&nbsp;แก้ไขสถานะ (เลือกสถานะด้านบน จากนั้นกดปุ่ม "เปลี่ยนสถานะ")</label>
										</span>
									</div>	
								</div>
					
								<br>
									<hr >
								<h5>ราคา</h5>
								

								<div class="form-group row">
									<label class="col-sm-3 control-label  text-center">ประเภท ก</label>
									<label class="col-sm-3 control-label  text-center">ประเภท ข</label>
									<label class="col-sm-3 control-label  text-center">ประเภท ค</label>
									<label class="col-sm-3 control-label  text-center">ประเภท ง</label>
								</div>
								
								
								<div class="form-group row">
									<div class="col-sm-3">
										<input type="text" name="priceType_A" id="priceType_A" class="form-control data" value="<%=sciInstruments.getPriceType_A()%>" disabled="disabled">
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_B" id="priceType_B" class="form-control data" value="<%=sciInstruments.getPriceType_B()%>" disabled="disabled"> 
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_C" id="priceType_C" class="form-control data" value="<%=sciInstruments.getPriceType_C()%>" disabled="disabled"> 
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_D" id="priceType_D" class="form-control data"value="<%=sciInstruments.getPriceType_D()%>" disabled="disabled"> 
									</div>
								</div>
								
								
								<div class="form-group row">
									<label class="col-sm-3 control-label  text-center">ราคาเหมาจ่าย</label>
									<label class="col-sm-3 control-label  text-center"></label>
									<label class="col-sm-3 control-label  text-center"></label>
									<label class="col-sm-3 control-label  text-center"></label>
								</div>
								
								
								<div class="form-group row">
									<div class="col-sm-3">
									<input type="text" name="priceFlatFee" id="priceFlatFee" class="form-control data" value="<%=sciInstruments.getPriceFlatFee()%>" disabled="disabled">
									</div>	
									<div class="col-sm-3">
									</div>	
									<div class="col-sm-3">
									</div>	
									<div class="col-sm-3">
									</div>
								</div>

								
								<% if(personnel.getPersonnel_type().equals("Admin")){ %>
								<div class="form-group row">
								<div class="col-sm-12 ">
										<span class="form-control-input">
										<input id="checkBox2" type="checkbox" name="checkBox2" onclick="return edit(this)">
										<label for="checkBox2">&nbsp;แก้ไขข้อมูล (เมื่อแก้ไขข้อมุลเสร็จสิ้น จากนั้นกดปุ่ม "แก้ไขข้อมูล")</label>
										</span>
								</div>
								</div>
								<div class="form-group row">
								<div class="col-sm-12 text-center">
									<button type="submit" id="btnedit"	class="btn btn-success" disabled="disabled">แก้ไขข้อมูล</button>
									<a href="do_DeleteInstruments?sciInstruments_ID=<%=sciInstruments.getSciInstruments_ID()%>"><button type="button" id="btndelete" disabled="disabled"	class="btn btn-danger" onclick="return confirm('คุณต้องการลบเครื่องมือนี้หรือไม่ ');">ลบเครื่องมือ</button></a>
								</div>
								</div>
								<% } %>
								
						</form>	
					</div>
				</div>
			</div>
		</section>
	</div>



<jsp:include page="common/footer.jsp"></jsp:include>

	<script>
		var loadFile = function(event) {
			var image = document.getElementById('photo');
			image.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>
	<script type="text/javascript">
	function showinputchange(ck){
		if(ck.checked){
			document.getElementById('status').disabled = false;	
			document.getElementById('change').disabled = false;	
		}else{
			document.getElementById('status').disabled = true;
			document.getElementById('change').disabled = true;
		}
		
	}
	function edit(ck){
		if(ck.checked){
			document.getElementById('sciInstruments_ID').disabled = false;	
			document.getElementById('sciInstruments_Name').disabled = false;	
			document.getElementById('sciInstrumentsType_ID').disabled = false;	
			document.getElementById('detail').disabled = false;	
			document.getElementById('coursepresident').disabled = false;	
			document.getElementById('staff').disabled = false;	
			document.getElementById('priceType_A').disabled = false;	
			document.getElementById('priceType_B').disabled = false;	
			document.getElementById('priceType_C').disabled = false;	
			document.getElementById('priceType_D').disabled = false;
			document.getElementById('priceFlatFee').disabled = false;
			document.getElementById('btnedit').disabled = false;	
			document.getElementById('btndelete').disabled = false;	
			document.getElementById('btnloadfile').style.display = "inline";
		}else{
			document.getElementById('sciInstruments_ID').disabled = true;	
			document.getElementById('sciInstruments_Name').disabled = true;	
			document.getElementById('sciInstrumentsType_ID').disabled = true;	
			document.getElementById('detail').disabled = true;
			document.getElementById('coursepresident').disabled = true;	
			document.getElementById('staff').disabled = true;	
			document.getElementById('priceType_A').disabled = true;	
			document.getElementById('priceType_B').disabled = true;	
			document.getElementById('priceType_C').disabled = true;	
			document.getElementById('priceType_D').disabled = true;	
			document.getElementById('priceFlatFee').disabled = true;
			document.getElementById('btnedit').disabled = true;	
			document.getElementById('btndelete').disabled = true;	
			document.getElementById('btnloadfile').style.display = "none";
		}
		
	}
	</script>
	<script type="text/javascript">
	function updatestatus(sid){
		var id = sid;
		var status = document.getElementById('status').value;
		 location.href = "do_updateinstrumentsstatus?id="+id+"&status="+status;
	}
	</script>
</body>
</html>