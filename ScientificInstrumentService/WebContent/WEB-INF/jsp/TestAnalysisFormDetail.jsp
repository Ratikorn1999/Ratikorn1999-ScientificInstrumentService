<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
	Personnel personnel = null;

TestAnalysisForm testAnalysisForm = new TestAnalysisForm();
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	testAnalysisForm = (TestAnalysisForm) request.getAttribute("testAnalysisForm");
} catch (Exception e) {}
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
							<h3>ตรวจสอบและยืนยันข้อมูล</h3>
							<hr class="colorgraph">
								
								<h5>ข้อมูลผู้ร้องขอ</h5>
								<hr >
								
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">นามลูกค้า</label>
									<div class="col-sm-10">
										<span class="form-control bg-txt data">
										<%= testAnalysisForm.getClient_name() %>
										</span>
									</div>									
										
								</div>


								<div class="form-group row">
									<label class="col-sm-2 control-label  text-right">ชื่อ-สกุล ผู้ติดต่อ</label>
									<div class="col-sm-4">
										<span class="form-control   bg-txt  data">
										<% if(testAnalysisForm.getMember().getMember_ID() != null){ %>
										<%=testAnalysisForm.getMember().getMember_Prefix() + " " + testAnalysisForm.getMember().getMember_FirstName() + "  " + testAnalysisForm.getMember().getMember_LastName()%>
										<% }else if(testAnalysisForm.getStudent().getStudentID() != null){ %>
										<%= testAnalysisForm.getStudent().getNameTh()+ " "+testAnalysisForm.getStudent().getSurnameTh() %>
										<% } else if(testAnalysisForm.getPersonnel().getPersonnel_ID() != null){%>
										<%=testAnalysisForm.getPersonnel().getPersonnel_Prefix()+" "+
										testAnalysisForm.getPersonnel().getPersonnel_FirstName()+" "+
										testAnalysisForm.getPersonnel().getPersonnel_LastName()%>
										<% }  %>
										</span>
									</div>
									<label class="col-sm-2 col-form-label text-right">ตำแหน่ง</label>									
									<div class="col-sm-4">
										<span class="form-control  bg-txt data">
										<% if(testAnalysisForm.getMember().getMember_ID() != null){ %>
										<%=testAnalysisForm.getMember().getPosition()  %>
										<% }else if(testAnalysisForm.getStudent().getStudentID() != null){ %>
										นักศึกษา
										<% } else if(testAnalysisForm.getPersonnel().getPersonnel_ID() != null){%>
										<%=  testAnalysisForm.getPersonnel().getPosition()%>
										<% } %>
										</span>
									</div>
								</div>

								<div class="form-group row">							
										<label class="col-sm-2 col-form-label text-right">โทรศัพท์</label>								
									<div class="col-sm-4">
										<span class="form-control  bg-txt  data">
										<% if(testAnalysisForm.getMember().getMember_ID() != null){ %>
										<%=testAnalysisForm.getMember().getPhonenumber()%>
										<% }else if(testAnalysisForm.getStudent().getStudentID() != null){ %>
										<%=testAnalysisForm.getStudent().getPhonenumber()%>
											<% } else if(testAnalysisForm.getPersonnel().getPersonnel_ID() != null){%>
										<%= testAnalysisForm.getPersonnel().getPhone() %>	
										<% } %>
										</span>
									</div>									
										<label class="col-sm-2 col-form-label text-right">โทรสาร(FAX)</label>								
									<div class="col-sm-4">
										<span class="form-control   bg-txt  data">
										-----ไม่มีข้อมูล-----
										</span>
									</div>
								</div>
								

								<div class="form-group row">
									<label class="col-sm-2 control-label  text-right">Email</label>
									<div class="col-sm-10">
										<span class="form-control   bg-txt  data">
										<% if(testAnalysisForm.getMember().getMember_ID() != null){ %>
									<%=testAnalysisForm.getMember().getEmail()%>
									<% }else if(testAnalysisForm.getStudent().getStudentID() != null){ %>
										<%=testAnalysisForm.getStudent().getEmail()%>
									<% } else if(testAnalysisForm.getPersonnel().getPersonnel_ID() != null){%>
										<%= testAnalysisForm.getPersonnel().getEmail() %>
									<% } %>
									</span>
									</div>
								</div>

								
								
								<br>
								<h5>ข้อมูลที่อยู่</h5>
								<hr >
								
								<div class="form-group row">
									<label class="col-sm-2 control-label  text-right">ที่อยู่</label>
									<div class="col-sm-10">
									<textarea class="form-control  bg-txt  data" style="resize:none;"><%=testAnalysisForm.getAddress()%></textarea>	
									</div>
								</div>
								
								<div class="form-group row">
									<label class="col-sm-2 control-label  text-right">ที่อยู่ที่ใช้ในการออกผลทดสอบ</label>
									<div class="col-sm-10">
										<textarea class="form-control  bg-txt  data" style="resize:none;"><%=testAnalysisForm.getReport_Address()%></textarea>	
									</div>
								</div>
								

								<div class="form-group row">
									<label class="col-sm-2 control-label  text-right">ชื่อที่อยู่ที่ใช้ในการออกใบเสร็จรับเงิน</label>
									<div class="col-sm-10">
										<textarea class="form-control  bg-txt  data" style="resize:none;"><%=testAnalysisForm.getBilling_Address()%></textarea>	
									</div>
								</div>
							
								
						<br>
								<h5>ข้อมูลของแบบฟอร์ม</h5>
								<hr >		
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">เลขที่คำขอ</label>
									<div class="col-sm-4">
										<span class="form-control  bg-txt  data"><%=testAnalysisForm.getTestAnalysisFormNumber()%></span>
									</div>									
										<label class="col-sm-2 col-form-label text-right">ภาษาผลทดสอบ</label>									
									<div class="col-sm-4">
										<span class="form-control  bg-txt  data"><%=testAnalysisForm.getLanguage()%></span>
									</div>
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ลักษณะบรรจุ</label>
									<div class="col-sm-4">
										<span class="form-control  bg-txt  data"><%=testAnalysisForm.getPacking_style()%></span>
									</div>									
										<label class="col-sm-2 col-form-label text-right">รายละเอียดการขอรับบริการเพิ่มเติม </label>									
									<div class="col-sm-4">
										<textarea class="form-control  bg-txt  data" style="resize:none;"><%=testAnalysisForm.getAdditional_details()%></textarea>
									</div>
								</div>
								
								<div class="form-group row">
								<label class="col-sm-2 col-form-label text-right">ตัวอย่างที่นำมาขอบริการ </label>
									<div class="col-sm-4">
										<span class="form-control  bg-txt  data"><%=testAnalysisForm.getReceive_Analysis_Result()%></span>
									</div>									
										<label class="col-sm-2 col-form-label text-right">ผลการทดสอบ</label>									
									<div class="col-sm-4">
										<span class="form-control  bg-txt  data"><%=testAnalysisForm.getResultsOfTheAnalysis()%></span>
									</div>
								</div>
								
								<br>
								<h5>รายการวิเคราะห์ทดสอบที่ขอใช้บริการ</h5>
								<hr >	
									
								
								
								<table class="table table-bordered">
								<thead>
								<tr align="center">
								<th>
								ชื่อตัวอย่าง
								<br>
								(Sample name)
								</th>
								<th>รหัส<br>(Code)</th>
								<th>น้ำหนัก/<br>ปริมาตร </th>
								<th>จำนวน </th>
								<!-- <th>ข้อที่ </th> -->
								<th>รายละเอียดที่ขอรับบริการ<br>(Test Item)</th>
								<!-- <th>หมายเลขปฏิบัติการ</th> -->
								<th>การเก็บรักษา<br>(อุณหภูมิ)</th>	
								<th>ค่าบำรุง<br>(บาท) </th>
								</tr>
								</thead>
								<tbody>
									<%for(int i = 0 ;i<testAnalysisForm.getListUseTestItem().size();i++){ %>
								<tr>
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getSampleName() %> </span></td>
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getCode() %> </span> </td>
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getWeight() %> </span></td>
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getQty() %> </span></td>
								<!-- <td><span class="form-control data"> </span></td> -->
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getTestItem().getTestitem_Name() %> </span></td>
								<!-- <td><span class="form-control data"> </span></td> -->
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getStorage() %></span></td>
								<td><span class="form-control bg-txt data"> <%=testAnalysisForm.getListUseTestItem().get(i).getMaintenancefee() %> </span></td>
								</tr>
								<% } %>
								</tbody>
								
								</table>
								
								
								<br>
							<h5>ข้อมูลการอนุมัติ</h5>
							<hr>
							<%
							int no = 1;
							int id = -99;
							boolean approveNo1 = false ;
							boolean approveNo2 = false ;
							boolean approveNo3 = false ;
							for (int i = 0; i < testAnalysisForm.getLsitApprovalTestAnalysisForm().size(); i++) {
								if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==1){
									approveNo1 = true;
								}
								if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==2){
									approveNo2 = true;
								}
								if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==3){
									approveNo3 = true;
								}
								
							}
							
							 		 
							for (int i = 0; i < testAnalysisForm.getLsitApprovalTestAnalysisForm().size(); i++) {
								
								if(approveNo1){
									if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo() == 1
									&& testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getStatus().equals("อนุมัติ"))
									{	
										if(approveNo2){
											no = 2;
										}else if(approveNo3){
											no = 3;
										}else{
											no = 4;
										}
										
									} else {
										id = testAnalysisForm.getLsitApprovalTestAnalysisForm().get(0).getApproval_ID();
										
									}
								}
								
								if (no == 2 ) {
									for (int k = 0; k < testAnalysisForm.getLsitApprovalTestAnalysisForm().size(); k++) {
								if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproveNo() == 2) {
									if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 3;
									} else {
										id = testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 3 ) {
									for (int k = 0; k < testAnalysisForm.getLsitApprovalTestAnalysisForm().size(); k++) {
								if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproveNo() == 3) {
									if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 4;
									} else {
										id = testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 4) {
									for (int k = 0; k < testAnalysisForm.getLsitApprovalTestAnalysisForm().size(); k++) {
								if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproveNo() == 4) {
									if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 5;
									} else {
										id = testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproval_ID();

									}
								}
									}
								} else if (no == 5) {
									for (int k = 0; k < testAnalysisForm.getLsitApprovalTestAnalysisForm().size(); k++) {
								if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproveNo() == 5) {
									if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("อนุมัติ")) {
										no = 6;
									} else {
										id = testAnalysisForm.getLsitApprovalTestAnalysisForm().get(k).getApproval_ID();

									}
								}
									}
								}

							}
							%>
							
								
						<% for(int i =0 ; i<testAnalysisForm.getLsitApprovalTestAnalysisForm().size();i++){ %>
							<div class="form-group row">
								<label class="col-sm-3 col-form-label text-left"> 
								<% if ( testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==1){ %>
									อาจารย์ที่ปรึกษา/หัวหน้าโครงการ
								<% } else if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==3){%>
									ประธานหลักสูตร
								<% } else if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==2){%>
									เจ้าหน้าที่ห้องปฏิบัติการ
								<% } else if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()==4){%>
									หัวหน้างานบริการวิชาการและวิจัย
								<% } else {%> 
									รองคณบดีฯ
								 <% } %>
								</label> 
								<label class="col-sm-4 col-form-label text-left"> 
								
								<% if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_ID() != null){ %>
								<%=testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_Prefix()+" "
								+testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_FirstName()+" "
								+testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_LastName()
								%>
								<% }else{ %>
								<% if(testAnalysisForm.getMember().getMember_ID() != null){ %>
								<%= testAnalysisForm.getMember().getAdvisor_name() %>
								<% }else if(testAnalysisForm.getStudent().getStudentID() != null) {%>
								<%= testAnalysisForm.getStudent().getAdvisor_name() %>
								<% } %>
								<% } %>
								
									
								</label>
								<div class="col-sm-3">
									<span class="form-control bg-txt data"> <%= testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getStatus()%>
									</span>
								</div>
								
							
									<%-- <br><%="Personnel_ID is" +testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_ID()%>
									<br><%=testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_ID()+ " = " + personnel.getPersonnel_ID()%>
									<br><%=id + " = " +  testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID()%>
									<br><%=no + " = " +  testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()%>
								 --%>
								<% 
								boolean check = false;
								 if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_ID() != null
								&&  personnel.getPersonnel_ID().equals(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_ID())
								&& testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID() == id
								&& testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo() == no
										 ){ 
									check = true;
								}else if (personnel.getPersonnel_ID().equals(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getPersonnel().getPersonnel_ID())
								&& testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID() == id
										){ 
									check = true;	
								} %>
								<% if(check) {%>
								<div class="col-sm-2">
								<button type="button" name="approve"  class="btn btn-success"
								onclick="location.href='doApproveTestAnalysis?approval_ID=<%=testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID()%>&formnumber=<%= testAnalysisForm.getTestAnalysisFormNumber()%>&approval_no=<%=testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()%>&status=อนุมัติ';"
								>
								อนุมัติ
								</button>
								<button type="button" name="noapprove"  class="btn btn-danger"
								onclick="location.href='doApproveTestAnalysis?approval_ID=<%=testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID()%>&formnumber=<%= testAnalysisForm.getTestAnalysisFormNumber()%>&approval_no=<%=testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproveNo()%>&status=ไม่อนุมัติ';"
								>
								ไม่อนุมัติ
								</button>
								</div>
								<% }else{
									if(testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getStatus().equals("รอการอนุมัติ")
										&&	testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID() != testAnalysisForm.getLsitApprovalTestAnalysisForm().get(0).getApproval_ID()){
									%>
								<label class="col-sm-2 col-form-label"> 
								รอการอนุมัติก่อนหน้า....
								</label>
								<%		} 
									} %>
								</div>
							
							<% } %>
							
								
								
								<div class="form-group row">
								
									<div class="col-sm-12">
									
									<a href="do_getListTestAnalysisForm"><button type="button" class="btn btn-success"> ย้อนกลับ </button></a>
										
									</div>
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