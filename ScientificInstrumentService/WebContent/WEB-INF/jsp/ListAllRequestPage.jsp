<%@page import="org.hibernate.result.Output"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page
	import="java.util.*,Manager.*,LoginMJU.*,Class_End_Project.*,java.text.*"%>
<%
Member member = null;
Student student = null;
AuthLogin auths = null;
boolean checkauth = false;
List<FlatFeeForm> listsericeRequestForm = new Vector<>();
List<TestAnalysisForm> listTestAnalysisForm = new Vector<>();
ListAllRequestManager larm = new ListAllRequestManager(); 
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {
}
try {
	student = (Student) session.getAttribute("student");
} catch (Exception e) {
}
 try {
	listsericeRequestForm = (List<FlatFeeForm>) request.getAttribute("listsericeRequestForm");
	listTestAnalysisForm = (List<TestAnalysisForm>) request.getAttribute("listTestAnalysisForm");
} catch (Exception e) {
} 
try {
	auths = (AuthLogin) session.getAttribute("checkauth");
	checkauth = auths.getResult();
	if (!checkauth) {
		response.sendRedirect("Login");
	}
} catch (Exception e) {
}
%>
<!DOCTYPE html>
<html>
<head>
<title>รายการร้องขอ : เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
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
	function getvalue(selectObject) {
		var value = selectObject.value;

		if (value == "1") {
			document.getElementById('tableSericeRequestForm').style.display = "inline";
			document.getElementById('tableFlatFeeForm').style.display = "none";
			document.getElementById('tableTestAnalysisForm').style.display = "none";
		} else if (value == "2") {
			document.getElementById('tableSericeRequestForm').style.display = "none";
			document.getElementById('tableFlatFeeForm').style.display = "inline"
			document.getElementById('tableTestAnalysisForm').style.display = "none";

		} else {
			document.getElementById('tableSericeRequestForm').style.display = "none";
			document.getElementById('tableFlatFeeForm').style.display = "none";
			document.getElementById('tableTestAnalysisForm').style.display = "inline";
		}

	}

</script>

<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container" style="margin-top: 35px;">
		<div class="form-group row">
			<label for="pwd" class="col-sm-0 col-form-label text-right">รายการขอรับบริการ</label>
			<div class="col">
				<select name="type" id="type" class="form-control col-lg-6"
					onchange="return getvalue(this)">
					<option value="1">บริการเครื่องมือวิทยาศาสตร์แบบรายชั่วโมง</option>
					<option value="2">บริการเครื่องมือวิทยาศาสตร์แบบเหมา</option>
					<option value="3">บริการวิเคราะห์ทดสอบ</option>
				</select>
			</div>
		</div>

		<br>

		<!--############################### tableServicerequestForm ###############################-->
		<div id="tableSericeRequestForm">
			
			<c:forEach var="listsericeRequestForm"		items="${ listsericeRequestForm }" varStatus="loop">
 					<c:if test="${ fn:contains(listsericeRequestForm.getSericeRequestFormNumber(), 'E')}"> 
		
				<table class="table table-bordered table-responsive-sm ">
					<thead class="table-info">
						<tr align="center">
							<th>เลขที่รายการ</th>
							<th>ประเภทรายการ</th>
							<th>สถานะการอนุมัติ</th>
							<th>สถานะการชำระเงิน</th>
							<th style="width: 550px;">จัดการ</th>

						</tr>
					</thead>
					<tr>
						<td>${ listsericeRequestForm.getSericeRequestFormNumber()}</td>
						<td></td>
						<td
							<c:if test="${listsericeRequestForm.getBooking_status() == 'อนุมัติ' }">
								style="color: green;"
								</c:if>
							<c:if test="${listsericeRequestForm.getBooking_status() == 'ยกเลิก' }">
									style="color :red;"
								</c:if>
							<c:if test="${listsericeRequestForm.getBooking_status() == 'ไม่อนุมัติ' }">
								style="color : red ;"
								</c:if>
							<c:if test="${listsericeRequestForm.getBooking_status() == 'รอการอนุมัติ' }">
							 	style="color : yellow ;"
								</c:if>>
							${ listsericeRequestForm.getBooking_status()}</td>

						<td><c:if
								test="${listsericeRequestForm.getPayment().getPayment_ID() == 0 }">
								<p style="color: red;">ยังไม่ได้ชำระเงิน</p>
							</c:if> <c:if
								test="${listsericeRequestForm.getPayment().getPayment_ID() != 0 }">
								<p style="color: green;">ชำระเงินเรียบร้อย</p>
							</c:if></td>


						<td>
							<%-- <%
									boolean ckapp = false;
								for (int m = 0; m < listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().size(); m++) {
									if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(m).getStatus().equals("อนุมัติ")) {
										ckapp = true;
										break;
									}
								}
								%>  --%>
								<span>
								<button type="button" name="change"
									onclick="location.href='FillAChangeRequestFormPage?sericeRequestFormNumber=${listsericeRequestForm.getSericeRequestFormNumber() }';"
									id="change"
									<c:choose>
   									
  										   <c:when test="${listsericeRequestForm.getBooking_status() == 'อนุมัติ' }">
       								  class="btn btn-primary"
  										  </c:when>
   									
  				 					 	<c:otherwise>
      										disabled
       									 class="btn btn-secondary" 
    								</c:otherwise>
									</c:choose>>
									<i class="fa fa-edit"></i> แก้ไข
								</button>

						</span> <span>

								<button type="button" name="cancel" id="cancel"
									onclick="location.href='do_Cancelservicerequest?formnumber=${listsericeRequestForm.getSericeRequestFormNumber() }&typeform=1';"
									<c:choose>
   										 <c:when test="${listsericeRequestForm.getBooking_status() == 'ยกเลิก' }">
      									disabled
      									 class="btn btn-secondary" 
   										 </c:when>
   										 <c:when test="${listsericeRequestForm.getBooking_status() == 'อนุมัติ' }">
       									disabled
       									 class="btn btn-secondary" 
  										  </c:when>
  										   <c:when test="${listsericeRequestForm.getBooking_status() == 'ไม่อนุมัติ' }">
       									disabled
       									 class="btn btn-secondary" 
  										  </c:when>
  				 					 	<c:otherwise>
      									  class="btn btn-danger" 
    								</c:otherwise>
									</c:choose>
									onclick="return confirm('คุณต้องการยกเลิกแบบฟอร์มนี้หรือไม่ ');">
									<i class="fa fa-close"></i> ยกเลิก
								</button>
						</span> <span>

								<button type="button" name="print" id="ptint"
									onclick="window.open('Form?id=${listsericeRequestForm.getSericeRequestFormNumber() }&typeform=1','_blank')"
									<c:if test="${listsericeRequestForm.getBooking_status() == 'ยกเลิก' }">
										disabled class="btn btn-secondary" 
									</c:if>
									<c:if test="${listsericeRequestForm.getBooking_status() == 'รอการอนุมัติ' }">
										disabled class="btn btn-secondary" 
									</c:if>
									<c:if test="${listsericeRequestForm.getBooking_status() == 'อนุมัติ' }">
										class="btn btn-info" 
										</c:if>>
									<i class="fa fa-print"></i> ดาวน์โหลด
								</button>

						</span>




						</td>



					</tr>

					<tr>
						<th colspan="4" style="text-align: center;">
							รายชื่อเครื่องมือ</th>
						<th colspan="1" style="text-align: center;">
							รายชื่อผู้อนุมัติ</th>
					</tr>

					<tr>
						<td colspan="4"><c:forEach var="listSchedule"
								items="${ listsericeRequestForm.getListSchedule() }"
								varStatus="loop">
							${loop.index+1}
							${listSchedule.getSciInstruments().getSciInstruments_Name()}
							<br>
							</c:forEach></td>
						<td colspan="1"><c:forEach var="a" items="${listsericeRequestForm.getLsitApprovalOfServiceRequestForm() }"
								varStatus="loop">
							
								<div class="form-group row">
									<label class="col-sm-9 col-form-label "> 
									<c:set	value="${a.getStaff()}" var="s" />
									 <c:if test="${s.getStaffid() != null}">
										${s.getPrefix()} 	
										${s.getFirstname()} 	
										${s.getLastname()} 
										</c:if> 
										<c:set value="${a.getCoursepresident()}" var="c" />
										 <c:if 	test="${c.getCoursepresidentid() != null}">
										${c.getPrefix()} 	
										${c.getFirstname()} 	
										${c.getLastname()} 
										
										</c:if>
										 <c:set value="${a.getPersonnel()}" var="p" /> 
										 <c:if test="${p.getPersonnel_ID() != null}">
										${p.getPersonnel_Prefix()} 	
										${p.getPersonnel_FirstName()} 	
										${p.getPersonnel_LastName()} 
										</c:if> 
										
										<c:if test="${loop.index  == 0}">
										 <c:if test="${member != null}">
										 ${member.getAdvisor_name()}
 										</c:if> 
 										 <c:if test="${student != null}">
 										${student.getAdvisor_name() }
										</c:if>	 
										</c:if>
										<br>
										<c:if test="${a.getApproveNo() == 1}">
										( อาจารย์ที่ปรึกษา/หัวหน้าโครงการ ) 
										</c:if> 
										<c:if test="${ a.getApproveNo() == 2}">
										  ( เจ้าหน้าที่ห้องปฏิบัติการ )
										</c:if> 
										<c:if test="${a.getApproveNo() == 3}">
										( ประธานหลักสูตร ) 
										</c:if>
										 <c:if test="${ a.getApproveNo() == 4}">
										  ( หัวหน้างานบริการวิชาการและวิจัย ) 
										 </c:if> 
										<c:if test="${ a.getApproveNo() == 5}">
										   ( รองคณบดีฯ )
										 </c:if>
										</label> 
										<label class="col-sm-3 col-form-label " 
										<c:if test="${a.getStatus() == 'อนุมัติ'}">
										style="color: green;"
										</c:if>
										<c:if test="${a.getStatus() == 'ไม่อนุมัติ'}">
										style="color: red;"
										</c:if>
										<c:if test="${a.getStatus() == 'รอการอนุมัติ'}">
										style="color: yellow;"
										</c:if>
										>
										${a.getStatus()} 
										<c:if test="${a.getStatus() == 'อนุมัติ'}">
										<i class="fa fa-check"></i>
										</c:if>
										<c:if test="${a.getStatus() == 'ไม่อนุมัติ'}">
										<i class="fa fa-close"></i>
										</c:if>
										
										 </label>
								</div>
							</c:forEach></td>

					</tr>
					 <c:set value="${listsericeRequestForm.getChangeForm()}" var="cf" /> 
					<c:if test="${cf.getChangeFormNumber() !=null }">
					<tr align="center" style="background-color: #FFCC00;">
						<th colspan="5">รายการเปลี่ยนแปลง</th>
					</tr>
					<tr>
						<td>
						${ cf.getChangeFormNumber()}
				
						</td>
						<td>
						${ cf.getType()}
				
						</td>
						<td
						<c:if test="${cf.getChange_status() == 'อนุมัติ' }">
							style="color: green;"
						</c:if>
						<c:if test="${cf.getChange_status() == 'ยกเลิก' }">
							style="color :red;"
						</c:if>
						<c:if test="${cf.getChange_status() == 'ไม่อนุมัติ' }">
							style="color : red ;"
						</c:if>
						<c:if test="${cf.getChange_status() == 'รอการอนุมัติ' }">
						style="color : yellow ;" 
						</c:if>
						>
							${ cf.getChange_status()}
					
							</td>
						<td></td>
						<td><span>
								<button type="button" name="cancel" id="cancel"
									onclick="location.href='ReserveUsingToolPage?id=${listsericeRequestForm.getSericeRequestFormNumber()}';"
									
									<c:choose>
   										 <c:when test="${cf.getChange_status() == 'ยกเลิก' }">
      									disabled
      									 class="btn btn-secondary" 
   										 </c:when>
   										 <c:when test="${cf.getChange_status() == 'อนุมัติ' }">
       									disabled
       									 class="btn btn-secondary" 
  										  </c:when>
  										   <c:when test="${cf.getChange_status() == 'ไม่อนุมัติ' }">
       									disabled
       									 class="btn btn-secondary" 
  										  </c:when>
  				 					 	<c:otherwise>
      									  class="btn btn-danger" 
    								</c:otherwise>
									</c:choose>
									onclick="return confirm('คุณต้องการยกเลิกแบบฟอร์มนี้หรือไม่ ');">
									<i class="fa fa-close"></i> ยกเลิก
								</button>
						</span> <span>

								<button type="button" name="print" id="ptint"
									onclick="window.open('Form?id=${cf.getChangeFormNumber()}&typeform=4','_blank')"
									<c:if test="${cf.getChange_status() == 'ยกเลิก' }">
									disabled class="btn btn-secondary" 
									</c:if>
									<c:if test="${cf.getChange_status() == 'รอการอนุมัติ' }">
											disabled class="btn btn-secondary" 
									</c:if>
									<c:if test="${cf.getChange_status() != 'รอการอนุมัติ' }">
									class="btn btn-info"
									</c:if>
								   >
									<i class="fa fa-print"></i> ดาวน์โหลด
								</button>

						</span></td>
					</tr>
					<tr>
						<th colspan="4" style="text-align: center;">
							รายชื่อเครื่องมือ</th>
						<th colspan="1" style="text-align: center;">
							รายชื่อผู้อนุมัติ</th>
					</tr>
					<tr>
						<td colspan="4">
							<c:forEach var="listsChangelist"		items="${ listsericeRequestForm.getChangeForm().getChangelists()}" varStatus="loop">
							<div class="form-group row">
								<label class="col-sm-6 col-form-label ">
								${loop.index+1}
								${listsChangelist.getHistoryschedule().getSciInstruments().getSciInstruments_Name() }
								</label>
								<label class="col-sm-3 col-form-label ">
								${ listsChangelist.getDetail()}
								</label>
								<div class="col-sm-3"></div>
							</div> 
							</c:forEach>
						</td>
						<td colspan="1">
							<c:forEach var="listaApprovalChangeForms"		items="${ listsericeRequestForm.getChangeForm().getListApprovalChangeForm()}" varStatus="loop">
							<div class="form-group row">
								<label class="col-sm-9 col-form-label "> 
								 <c:set value="${listaApprovalChangeForms.getPersonnel()}" var="p" /> 
										 <c:if test="${p.getPersonnel_ID() != null}">
										${p.getPersonnel_Prefix()} 	
										${p.getPersonnel_FirstName()} 	
										${p.getPersonnel_LastName()} 
										</c:if> 
										<c:if test="${loop.index  == 0}">
										 <c:if test="${member != null}">
										 ${member.getAdvisor_name()}
 										</c:if> 
 										 <c:if test="${student != null}">
 										${student.getAdvisor_name() }
										</c:if>	 
										</c:if>
										<br>
										<c:if test="${listaApprovalChangeForms.getApproveNo() == 1}">
										( อาจารย์ที่ปรึกษา/หัวหน้าโครงการ ) 
										</c:if> 
										<c:if test="${ listaApprovalChangeForms.getApproveNo() == 2}">
										  ( เจ้าหน้าที่ห้องปฏิบัติการ )
										</c:if> 
										<c:if test="${listaApprovalChangeForms.getApproveNo() == 3}">
										( ประธานหลักสูตร ) 
										</c:if>
										 <c:if test="${ listaApprovalChangeForms.getApproveNo() == 4}">
										  ( หัวหน้างานบริการวิชาการและวิจัย ) 
										 </c:if> 
										<c:if test="${ listaApprovalChangeForms.getApproveNo() == 5}">
										   ( รองคณบดีฯ )
										 </c:if>
								</label> <label class="col-sm-3 col-form-label "
									<c:if test="${ listaApprovalChangeForms.getStatus() == 'อนุมัติ'}">
										style="color: green;"
										</c:if>
										<c:if test="${ listaApprovalChangeForms.getStatus() == 'ไม่อนุมัติ'}">
										style="color: red;"
										</c:if>
										<c:if test="${ listaApprovalChangeForms.getStatus() == 'รอการอนุมัติ'}">
										style="color: yellow;"
										</c:if>
										>
										${ listaApprovalChangeForms.getStatus()} 
										<c:if test="${ listaApprovalChangeForms.getStatus() == 'อนุมัติ'}">
										<i class="fa fa-check"></i>
										</c:if>
										<c:if test="${ listaApprovalChangeForms.getStatus() == 'ไม่อนุมัติ'}">
										<i class="fa fa-close"></i>
										</c:if>
										
								</label>


							</div> 
							</c:forEach>
						</td>
					</tr>
					</c:if>
				</table>
				
				
			</c:if>
			
			</c:forEach>
			<%-- 	<% 	int count = 0; %>
				<c:forEach var="listsericeRequestFormck"		items="${ listsericeRequestForm }" varStatus="loop">
 					<c:if test="${ fn:contains(listsericeRequestFormck.getSericeRequestFormNumber(), 'E')}"> 
 					<% count = count+1; %>
 					
 					</c:if>
 				</c:forEach>	
			<% if(count !=0){ %>
			ไม่มีข้อมูล
			<% } %> --%>
		</div>
		<!--############################### End tableServicerequestForm ###############################-->

		<!--############################### tableFlatFeeForm ###############################-->
		<div id="tableFlatFeeForm" style="display: none;">
			<%
		int	count = 0;
			for(int i = 0;i<listsericeRequestForm.size();i++)
			{
				if (listsericeRequestForm.get(i).getSericeRequestFormNumber().contains("A")) {
					count++;
					break;
				}
			}
			%>
			<%
				if(count!=0)
			{
			%>
			<%
				for (int i = 0; i < listsericeRequestForm.size(); i++) {
			%>
			<%
				if (listsericeRequestForm.get(i).getSericeRequestFormNumber().contains("A")) {
			%>
			<table class="table table-bordered">
				<thead class="table-info">
					<tr align="center">
						<th>เลขที่รายการ</th>
						<th>ประเภทรายการ</th>
						<th>สถานะการอนุมัติ</th>
						<th>สถานะการชำระเงิน</th>
						<th style="width: 550px;">จัดการ</th>

					</tr>
				</thead>

				<tr>
					<td><%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%></td>
					<td></td>

					<td
						<%if (listsericeRequestForm.get(i).getBooking_status().equals("อนุมัติ")) {%>
						style="color: green;"
						<%} else if (listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")) {%>
						style="color :red;"
						<%} else if (listsericeRequestForm.get(i).getBooking_status().equals("ไม่อนุมัติ")) {%>
						style="color : red ;" <%} else {%> style="color : yellow ;" <%}%>>
						<%=listsericeRequestForm.get(i).getBooking_status()%>

					</td>
					<td>
						<%
							if (listsericeRequestForm.get(i).getPayment().getPayment_ID() == 0) {
						%>
						<p style="color: red;">ยังไม่ได้ชำระเงิน</p> <%
 	} else {
 %>
						<p style="color: green;">ชำระเงินเรียบร้อย</p> <%
 	}
 %>
					</td>
					<td><span>
							<button type="button" name="reserveUsingTool"
								onclick="location.href='ReserveUsingToolPage?id=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>';"
								<%if (!listsericeRequestForm.get(i).getBooking_status().equals("อนุมัติ")
		|| listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")) {%>
								disabled class="btn btn-secondary" <%} else {%>
								class="btn btn-primary" <%}%>>
								<i class="fa fa-wrench"></i> จองเครื่องมือ
							</button>
					</span> <span>
							<button type="button" name="change"
								onclick="location.href='FillAChangeRequestFormPage?sericeRequestFormNumber=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>';"
								id="change"
								<%if (!listsericeRequestForm.get(i).getBooking_status().equals("อนุมัติ")
		|| listsericeRequestForm.get(i).getPayment().getPayment_ID() == 0) {%>
								disabled class="btn btn-secondary" <%} else {%>
								class="btn btn-primary" <%}%>>
								<i class="fa fa-edit"></i> แก้ไข
							</button>

					</span> <span>
							<button type="button" name="cancel" id="cancel"
								onclick="location.href='do_Cancelservicerequest?formnumber=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>&typeform=2';"
								<%if (listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")
		|| listsericeRequestForm.get(i).getBooking_status().equals("อนุมัติ")
		|| listsericeRequestForm.get(i).getBooking_status().equals("ไม่อนุมัติ")) {%>
								disabled class="btn btn-secondary" <%} else {%>
								class="btn btn-danger" <%}%>
								onclick="return confirm('คุณต้องการยกเลิกแบบฟอร์มนี้หรือไม่ ');">
								<i class="fa fa-close"></i> ยกเลิก
							</button>
					</span> <span>
							<button type="button" class="btn btn-info" name="print"
								id="ptint"
								onclick="window.open('Form?id=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>&typeform=2','_blank')">
								<i class="fa fa-print"></i> ดาวน์โหลด
							</button>
					</span></td>
				</tr>

				<tr>
					<th colspan="4" style="text-align: center;">รายชื่อเครื่องมือ
					</th>
					<th colspan="1" style="text-align: center;">รายชื่อผู้อนุมัติ
					</th>
				</tr>
				<tr>
					<td colspan="4">
						<%
							for (int n = 0; n < listsericeRequestForm.get(i).getListrentalschedule().size(); n++) {
						%>
						<div class="form-group row">
							<label class="col-sm-9 col-form-label "><%=listsericeRequestForm.get(i).getListrentalschedule().get(n).getSciInstruments().getSciInstruments_Name()%></label>
							<div class="col-sm-3">
								<button class="btn btn-success"
									onclick="return btnshow('<%=listsericeRequestForm.get(i).getListrentalschedule().get(n).getRentalscheduleid()%>')">
									ตารางใช้งาน</button>
							</div>

							<div
								id="rentalschedule<%=listsericeRequestForm.get(i).getListrentalschedule().get(n).getRentalscheduleid()%>"
								class="modal">
								<!-- Modal content -->
								<div class="modal-content">
									<span class="close"
										onclick="return spanclick('<%=listsericeRequestForm.get(i).getListrentalschedule().get(n).getRentalscheduleid()%>')">&times;</span>
									<%
										if (listsericeRequestForm.get(i).getListSchedule().size() > 0) {
										for (int k = 0; k < listsericeRequestForm.get(i).getListSchedule().size(); k++) {
											String strat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
											.format(listsericeRequestForm.get(i).getListSchedule().get(k).getStartDateTime());
											String end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
											.format(listsericeRequestForm.get(i).getListSchedule().get(k).getEndDateTime());
									%>
									<div class="form-group row">
										<div class="col-sm-6">
											<span class="form-control  bg-txt data"><%=listsericeRequestForm.get(i).getListSchedule().get(k).getSciInstruments().getSciInstruments_Name()%></span>
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
									} else {
									%>
									<div class="form-group row">
										<label class="col-sm-12 col-form-label text-center">ยังไม่ได้ลงทะเบียนจองเครื่องมือ</label>

									</div>
									<%
										}
									%>
								</div>
							</div>


						</div> <br> <%
 	}
 %>
					</td>
					<td colspan="1">
						<%
							for (int k = 0; k < listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().size(); k++) {
						%>
						<div class="form-group row">
							<label class="col-sm-9 col-form-label "> <%
 	if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStaff().getStaffid() != null) {
 %> <%=listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStaff().getPrefix() + " "
		+ listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStaff().getFirstname() + " "
		+ listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStaff().getLastname()%>
								<%
									} else if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getCoursepresident()
										.getCoursepresidentid() != null) {
								%> <%=listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getCoursepresident().getPrefix()
		+ " "
		+ listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getCoursepresident().getFirstname()
		+ " "
		+ listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getCoursepresident().getLastname()%>
								<%
									} else if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getPersonnel()
										.getPersonnel_ID() != null) {
								%> <%=listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getPersonnel().getPersonnel_Prefix()
		+ " "
		+ listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getPersonnel()
				.getPersonnel_FirstName()
		+ " " + listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getPersonnel()
				.getPersonnel_LastName()%> <%
 	} else {
 %> <%
 	if (member != null) {
 %> <%=member.getAdvisor_name()%> <%
 	} else if (student != null) {
 %> <%=student.getAdvisor_name()%> <%
 	}
 %> <%
 	}
 %> <br> <%
 	if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 1) {
 %> อาจารย์ที่ปรึกษา/หัวหน้าโครงการ <%
 	} else if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 3) {
 %> ประธานหลักสูตร <%
 	} else if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 2) {
 %> เจ้าหน้าที่ห้องปฏิบัติการ <%
 	} else if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getApproveNo() == 4) {
 %> หัวหน้างานบริการวิชาการและวิจัย <%
 	} else {
 %> รองคณบดีฯ <%
 	}
 %>
							</label> <label class="col-sm-3 col-form-label "
								<% if(listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("อนุมัติ")) {%>
								style="color: green;"
								<% }else if (listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("รอการอนุมัติ")){ %>
							style="color: yellow;"
								<%}else { %>
								style="color: red;"
								<%} %>
							> 
							
							<%=listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStatus()%>
							<% if(listsericeRequestForm.get(i).getLsitApprovalOfServiceRequestForm().get(k).getStatus().equals("อนุมัติ")) {%>
							<i class="fa fa-check"></i>
							<%} %>
							</label>
						

						</div> <%
 	}
 %>

					</td>
				</tr>


			</table>
			<%
				}
			%>
			<%
				}
			%>
			<%
				}else
			{
			%>
			ไม่มีข้อมูล
			<%
				}
			%>
		</div>
		<!--############################### End tableFlatFeeForm ###############################-->


		<!--############################### tableTestAnalysisForm ###############################-->
		<div id="tableTestAnalysisForm" style="display: none;">


			<%
				if(listTestAnalysisForm.size()!=0)
			{
			%>
			<%
				for (int i = 0; i < listTestAnalysisForm.size(); i++) {
			%>
			<table class="table table-bordered">
				<thead>
					<tr class="table-info">
						<th>เลขที่รายการ</th>
						<th>สถานะการอนุมัติ</th>
						<th>สถานะการทดสอบ</th>
						<th>สถานะการชำระเงิน</th>
						<th>จัดการ</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><%=listTestAnalysisForm.get(i).getTestAnalysisFormNumber()%></td>
						<td
						<%if(listTestAnalysisForm.get(i).getApprovestatus().equals("รอการอนุมัติ")){ %>
							style="color : yellow;"
						<%}else if(listTestAnalysisForm.get(i).getApprovestatus().equals("ไม่อนุมัติ")){ %>
							style="color : red;" 
						<%}else{ %>
						style="color: green;"
						<% } %>
						>
						<%=listTestAnalysisForm.get(i).getApprovestatus()%>
						</td>
						<td
							<%if (listTestAnalysisForm.get(i).getTesting_status().equals("Finished Testing")) {%>
							style="color: green;"
							<%} else if (listTestAnalysisForm.get(i).getTesting_status().equals("Wait for Testing")) {%>
							style="color : orange;"
							<%} else if (listTestAnalysisForm.get(i).getTesting_status().equals("Testing Now")) {%>
							style="color : blue;" <%} else {%> style="color : red;" <%}%>>

							<%=listTestAnalysisForm.get(i).getTesting_status()%>

						</td>
						<td>
							<%
								if (listTestAnalysisForm.get(i).getPayment().getPayment_ID() == 0) {
							%>
							<p style="color: red;">ยังไม่ได้ชำระเงิน</p> <%
 	} else {
 %>
							<p style="color: green;">ชำระเงินเรียบร้อย</p> <%
 	}
 %>
						</td>
						<td><span>
								<button type="button" name="cancel" id="cancel"
									onclick="location.href='do_Cancelservicerequest?formnumber=<%=listTestAnalysisForm.get(i).getTestAnalysisFormNumber()%>&typeform=3';"
									<%if (listTestAnalysisForm.get(i).getTesting_status().equals("ยกเลิก")
		|| listTestAnalysisForm.get(i).getTesting_status().equals("การทดสอบเสร็จสิ้น")) {%>
									disabled class="btn btn-secondary" <%} else {%>
									class="btn btn-danger" <%}%>
									onclick="return confirm('คุณต้องการยกเลิกแบบฟอร์มนี้หรือไม่ ');">
									<i class="fa fa-close"></i> ยกเลิก
								</button>
						</span> <span>
								<button type="button"  name="print"
									id="ptint" onclick="window.open('Form?id=<%=listTestAnalysisForm.get(i).getTestAnalysisFormNumber()%>&typeform=3','_blank')"
									<% if(!listTestAnalysisForm.get(i).getApprovestatus().equals("อนุมัติ") && !listTestAnalysisForm.get(i).getTesting_status().equals("การทดสอบเสร็จสิ้น")){ %>
										class="btn btn-secondary"
									disabled 
									<%}else{ %>
									class="btn btn-info"
									<% } %>
									>
									<i class="fa fa-print"></i> ดาวน์โหลด
								</button>
						</span></td>

					</tr>

					<tr>
						<th colspan="4" style="text-align: center;">
							รายชื่อตัวอย่าง</th>
						<th colspan="1" style="text-align: center;">
							รายชื่อผู้อนุมัติ</th>
					</tr>
					<tr>
						<td colspan="4">
							<%
								for (int n = 0; n < listTestAnalysisForm.get(i).getListUseTestItem().size(); n++) {
							%> <%=(n + 1) + ".  " + listTestAnalysisForm.get(i).getListUseTestItem().get(n).getSampleName()%>
							<br> <%
 	}
 %>
						</td>
						<td colspan="1">
							<%
								for (int k = 0; k < listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().size(); k++) {
							%>
							<div class="form-group row">

								<label class="col-sm-8 col-form-label text-left"> <%
 	if (listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getPersonnel().getPersonnel_ID() != null) {
 %> <%=listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getPersonnel().getPersonnel_Prefix()
		+ " "
		+ listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getPersonnel().getPersonnel_FirstName()
		+ " "
		+ listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getPersonnel().getPersonnel_LastName()%>
									<%
										} else {
									%> <%
 	if (member != null) {
 %> <%=member.getAdvisor_name()%> <%
 	} else if (student != null) {
 %> <%=student.getAdvisor_name()%> <%
 	}
 %> <%
 	}
 %>


								</label> <label class="col-sm-4 col-form-label text-left"
								<% if(listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("อนุมัติ")) {%>
								style="color: green;"
								<% }else if (listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("รอการอนุมัติ")){ %>
							style="color: yellow;"
								<%}else { %>
								style="color: red;"
								<%} %>
								> 
								<%=listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getStatus()%>
								<% if(listTestAnalysisForm.get(i).getLsitApprovalTestAnalysisForm().get(k).getStatus().equals("อนุมัติ")) {%>
							<i class="fa fa-check"></i>
							<%} %>
								</label>
							</div> <%
 	}
 %>

						</td>
					</tr>
				</tbody>
			</table>
			<%
				}
			%>

			<%
				}else
			{
			%>
		
						ไม่มีข้อมูล
			<%
				}
			%>


		</div>
		<!--############################### End tableTestAnalysisForm ###############################-->

	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>

	<script>
		function btnshow(no) {
			var modal = document.getElementById("rentalschedule" + no);
			modal.style.display = "block";
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		}

		function spanclick(no) {
			var modal = document.getElementById("rentalschedule" + no);
			modal.style.display = "none";
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
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