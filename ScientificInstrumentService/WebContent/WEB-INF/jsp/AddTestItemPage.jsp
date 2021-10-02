<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
AddTestItemManager atim = new AddTestItemManager();
List<TypeTestItem> listTypeTestItem = atim.getlistTypeTestItem() ;
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
	

	<form action="do_AddTestItem" name="frm" method="post" >
	
	<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
						<div class="container">
							<h3>เพิ่มรายการวิเคราะห์ทดสอบ</h3>
							<hr class="colorgraph">
								
								<h5>ข้อมูล</h5>
								<hr >
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รหัสการวิเคราะห์ทดสอบ</label>
									<div class="col-sm-4">
										<input type="text" name="testitem_ID" id="testitem_ID" class="form-control data"> 
									</div>									
										<label class="col-sm-2 col-form-label text-right">ชื่อการวิเคราะห์ทดสอบ</label>
									<div class="col-sm-4">
										<input type="text" name="testitem_Name" id="testitem_Name" class="form-control data"> 
									</div>	
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">วิธีการทดลอง</label>
									<div class="col-sm-4">
										<input type="text" name="experiment" id="experiment" class="form-control data"> 
									</div>									
										<label class="col-sm-2 col-form-label text-right">จำกัดขั้นต่ำการใช้งาน </label>
									<div class="col-sm-4">
										<input type="text" name="min_qty_samples" id="min_qty_samples" class="form-control data"> 
									</div>	
								</div>
							
								
							
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">หมวดหมู่</label>
									<div class="col-sm-4">
										<select class="form-control" name="typetestitem_ID">
										<option hidden  selected disabled >--กรุณาเลือกหมวดหมู่--</option>
										<% for(int i = 0 ;i<listTypeTestItem.size();i++){ %>
										<option value="<%=listTypeTestItem.get(i).getTypetestitem_ID()%>"><%=listTypeTestItem.get(i).getTypetestitem_Name() %></option>
										<% } %>
										</select>
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
								<label class="col-sm-2 col-form-label text-right">ราคาครั้งต่อไป</label>
									<div class="col-sm-4">
										<input type="text" name="nextprice" id="nextprice" class="form-control data"> 
									</div>									
										
								</div>
								
								
								
							
								
							
								
								
								
								
							
								
						
								
								<div class="form-group row">
								
									<div class="col-sm-12 text-center">
									
									<a href="#"><button type="submit" class="btn btn-success"> ยืนยัน </button></a>
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


</body>
</html>