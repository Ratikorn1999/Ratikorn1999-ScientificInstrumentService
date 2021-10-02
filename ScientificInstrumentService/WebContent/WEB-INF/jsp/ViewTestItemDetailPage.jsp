 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
List<TypeTestItem> listTypeTestItem = null ;
TestItem testItem = null;

try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
	e.printStackTrace();
}
try {
	listTypeTestItem = (List<TypeTestItem>) request.getAttribute("listTypeTestItem");
} catch (Exception e) {
	e.printStackTrace();
}
try {
	testItem = (TestItem) request.getAttribute("testItem");
	session.setAttribute("key",testItem.getTestitem_ID() );
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
<body >
	<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container" style="margin-top: 35px;">
	<form name="frm" action="do_EditTestItem" method="post">
		<section id="content">
			<div class="container" style="margin-top: -20px">
				<div class="row">
					<div class="col-lg-12">
						<div class="container">
							<h3>ข้อมูลวิเคราะห์ทดสอบ</h3>
							<hr class="colorgraph">
								
								<h5>ข้อมูล</h5>
								<hr >
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">รหัสการวิเคราะห์ทดสอบ</label>
									<div class="col-sm-4">
										<input type="text" name="testitem_ID" id="testitem_ID" class="form-control data" value="<%= testItem.getTestitem_ID() %>" disabled="disabled"> 
									</div>									
										<label class="col-sm-2 col-form-label text-right">ชื่อการวิเคราะห์ทดสอบ</label>
									<div class="col-sm-4">
										<input type="text" name="testitem_Name" id="testitem_Name" class="form-control data" value="<%=testItem.getTestitem_Name()%>" disabled="disabled"> 
									</div>	
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">วิธีการทดลอง</label>
									<div class="col-sm-4">
										<input type="text" name="experiment" id="experiment" class="form-control data" 
										<% if(testItem.getExperiment()==null){ %>
										value=""
										<% }else{ %>
										value="<%=testItem.getExperiment()%>" 
										<% } %>
									
										disabled="disabled"> 
									</div>									
										<label class="col-sm-2 col-form-label text-right">จำกัดขั้นต่ำการใช้งาน </label>
									<div class="col-sm-4">
										<input type="text" name="min_qty_samples" id="min_qty_samples" class="form-control data"  
										<% if(testItem.getMin_qty_samples()==null){ %>
										value=""
										<% }else{ %>
									value="<%=testItem.getMin_qty_samples()%>"
										<% } %>
										disabled="disabled"> 
									</div>	
								</div>
							
								
							
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">หมวดหมู่</label>
									<div class="col-sm-4">
										<select class="form-control" name="typetestitem_ID" id="typetestitem_ID" disabled="disabled">							
										<% for(int i = 0 ;i<listTypeTestItem.size();i++){ %>
										<option value="<%=listTypeTestItem.get(i).getTypetestitem_ID()%>"
										<% if(testItem.getTypetestitem().getTypetestitem_ID().equals(listTypeTestItem.get(i).getTypetestitem_ID())) {%>
										selected="selected"
										<% } %>
										><%=listTypeTestItem.get(i).getTypetestitem_Name() %></option>
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
										<input type="text" name="priceType_A" id="priceType_A" class="form-control data" value="<%=testItem.getPriceType_A()%>" disabled="disabled">
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_B" id="priceType_B" class="form-control data" value="<%=testItem.getPriceType_B()%>" disabled="disabled"> 
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_C" id="priceType_C" class="form-control data" value="<%=testItem.getPriceType_C()%>" disabled="disabled"> 
									</div>	
									<div class="col-sm-3">
										<input type="text" name="priceType_D" id="priceType_D" class="form-control data" value="<%=testItem.getPriceType_D()%>" disabled="disabled"> 
									</div>
								</div>
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ราคาครั้งต่อไป</label>
									<div class="col-sm-4">
										<input type="text" name="nextprice" id="nextprice" class="form-control data" 
										<% if( testItem.getNextprice()  == 0.0){ %>
										value="" 
										<% }else{ %>
										value="<%=testItem.getNextprice()%>" 
										<% } %>
										
										disabled="disabled"> 
									</div>									
										
								</div>
								
	
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
									<a href="do_DeleteTestItem?testitem_ID=<%= testItem.getTestitem_ID()%>">
									<input type="button" name="delete" id="delete" value="ลบ" class="btn btn-danger" disabled="disabled"
									onclick="return confirm('คุณต้องการลบการวิเคราห์ทดสอบนี้หรือไม่ ');">
									</a>
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

	function edit(ck){
		if(ck.checked){
			document.getElementById('testitem_ID').disabled = false;	
			document.getElementById('testitem_Name').disabled = false;	
			document.getElementById('experiment').disabled = false;
			document.getElementById('min_qty_samples').disabled = false;	
			document.getElementById('typetestitem_ID').disabled = false;	
			document.getElementById('nextprice').disabled = false;		
			document.getElementById('priceType_A').disabled = false;	
			document.getElementById('priceType_B').disabled = false;	
			document.getElementById('priceType_C').disabled = false;	
			document.getElementById('priceType_D').disabled = false;
			document.getElementById('btnedit').disabled = false;	
			document.getElementById('delete').disabled = false;	
		
		}else{
			document.getElementById('testitem_ID').disabled = true;	
			document.getElementById('testitem_Name').disabled = true;	
			document.getElementById('experiment').disabled = true;
			document.getElementById('min_qty_samples').disabled = true;	
			document.getElementById('typetestitem_ID').disabled = true;	
			document.getElementById('nextprice').disabled = true;	
			document.getElementById('priceType_A').disabled = true;	
			document.getElementById('priceType_B').disabled = true;	
			document.getElementById('priceType_C').disabled = true;	
			document.getElementById('priceType_D').disabled = true;	
			document.getElementById('btnedit').disabled = true;	
			document.getElementById('delete').disabled = true;	
		}
		
	}
	</script>
</body>
</html>