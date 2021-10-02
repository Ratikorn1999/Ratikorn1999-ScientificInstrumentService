<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
AddInstrumentsManager aimm = new AddInstrumentsManager();
List<SciInstrumentsType> listSciInstrumentsType = aimm.getlistSciInstrumentsType();
List<Coursepresident> listCoursepresident = aimm.getlistCoursepresident(); 
List<Staff> listStaff = aimm.getlistStaff();
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

<div class="container" style="margin-top: 35px;">
	
	<form action="doAddinstruments" name="frm" method="post"enctype="multipart/form-data">
	
	<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
						<div class="container">
							<h3>เพิ่มเครื่องมือวิทยาศาสตร์</h3>
							<hr class="colorgraph">
								
								<h5>ข้อมูล</h5>
								<hr >
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รหัสเครื่องมือ</label>
									<div class="col-sm-4">
										<input type="text" name="sciInstruments_id" id="sciInstruments_id" class="form-control data"> 
									</div>									
										<label class="col-sm-2 col-form-label text-right">ชื่อเครื่องมือ</label>
									<div class="col-sm-4">
										<input type="text" name="sciInstruments_Name" id="sciInstruments_Name" class="form-control data"> 
									</div>	
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">หมวดหมู่</label>
									<div class="col-sm-10">
										<select class="form-control" name="s">
										<option hidden  selected disabled >--กรุณาเลือกหมวดหมู่--</option>
										<% for(int i = 0 ;i<listSciInstrumentsType.size();i++){ %>
										<option value="<%=listSciInstrumentsType.get(i).getSciInstrumentsType_ID()%>"><%=listSciInstrumentsType.get(i).getSciInstrumentsType_Name() %></option>
										<% } %>
										</select>
									</div>									
									
								</div>

								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รายละเอียด</label>
									<div class="col-sm-10">
										<textarea class="form-control  data" id="detail" name="detail" rows="4" cols="50" style="resize:none;"  maxlength="255"> </textarea>
									</div>											
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ผู้รับผิดชอบ</label>
									<div class="col-sm-4">
										<select class="form-control" name="staff">
										<option   selected  value="0" >ไม่มี</option>
										<% for(int i = 0 ; i < listStaff.size() ; i++) {%>
										<option value="<%=listStaff.get(i).getStaffid()%>"><%= listStaff.get(i).getFirstname()+"  "+listStaff.get(i).getLastname() %></option>
										<% } %>
										</select>
									</div>									
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ประธานหลักสูตร</label>
									<div class="col-sm-4">
										<select class="form-control" name="coursepresident">
										<option   selected  value="0" >ไม่มี</option>
										<% for(int i = 0 ; i < listCoursepresident.size() ; i++) {%>
										<option value="<%=listCoursepresident.get(i).getCoursepresidentid()%>"><%= listCoursepresident.get(i).getFirstname()+"  "+listCoursepresident.get(i).getLastname() %></option>
										<% } %>
										</select>
									</div>									
									
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">สถานะเครื่องมือ</label>
									<div class="col-sm-4">
										<select class="form-control" name="status">
										<option value="พร้อมใช้งาน">พร้อมใช้งาน</option>
										<option value="ไม่พร้อมใช้งาน">    ไม่พร้อมใช้งาน</option>
									
										</select>
									</div>									
									
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">อัพโหลดรูปภาพ</label>
									<div class="col-sm-10">
										
										
										<label class="btn btn-primary" for="photo1" >
   											<input type="file" name="photo1" id="photo1"  style="display:none" required onchange="sendName(this.value)" accept=" image/jpeg, image/png">
    										Choose File...
										</label>
										<label id="pname" style="border:solid 1px #ccc; padding:7px; border-radius:5px; color:#cc0000; font-size:small; background-color:#fdfdfd;">กดปุ่ม Choose File เลือกไฟล์รูปจากเครื่องคอมพิวเตอร์ของท่าน </label>
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
										<input type="text" name="priceType_A" id="priceType_A" class="form-control data">
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_B" id="priceType_B" class="form-control data"> 
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_C" id="priceType_C" class="form-control data"> 
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_D" id="priceType_D" class="form-control data"> 
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
									<input type="text" name="priceFlatFee" id="priceFlatFee" class="form-control data" >
									</div>	
									<div class="col-sm-3">
									</div>	
									<div class="col-sm-3">
									</div>	
									<div class="col-sm-3">
									</div>
								</div>
								
							
								
							
								
								
								
								
							
								
						
								
								<div class="form-group row">
								
									<div class="col-sm-12 text-center">
									
									<button type="submit" class="btn btn-success"> ยืนยัน </button>
									<button type="reset" class="btn btn-warning"> ยกเลิก </button>
									</div>
								</div>

							

							
							</div>
						</div>
					</div>	
			</div>
		</section>

	</form>


</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
<script type="text/javascript">
    function sendName(pname) {
        $('#pname').html(pname);
    } //fn
</script>

</body>
</html>