<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
List<FlatFeeForm> listsericeRequestForm = new Vector<>();

String date = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	listsericeRequestForm = (List<FlatFeeForm>) request.getAttribute("listFlatFeeForm");
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
<script type="text/javascript">
	function getvalue(selectObject) {
		var value = selectObject.value;

		if (value == "1") {
			document.getElementById('tableSericeRequestForm').style.display = "inline";
			document.getElementById('tableFlatFeeForm').style.display = "none";
		} else if (value == "2") {
			document.getElementById('tableSericeRequestForm').style.display = "none";
			document.getElementById('tableFlatFeeForm').style.display = "inline"
		}

	}
	function showTable() {
		document.getElementById('tableSericeRequestForm').style.display = "inline";
		document.getElementById('tableFlatFeeForm').style.display = "none";
	}
</script>


<body >
	<jsp:include page="common/navbar.jsp"></jsp:include>
	
	<div class="container" style="margin-top: 35px;">
			<h3>รายการขอรับบริการเครื่องมือวิทยาศาสตร์</h3>
		
		
		<hr class="colorgraph">


								<div class="row">
									<label class="col-sm-2 col-form-label text-right">หมวดหมู่</label>
									<div class="col-sm-10">
										<select name="type" id="type" class="form-control col-lg-4"  onchange="return getvalue(this)">
											<option value="1">แบบรายชั่วโมง</option>
											<option value="2">แบบเหมา</option>
										</select>
									</div>									
									
								</div><br>



		<!--############################### tableServicerequestForm ###############################-->
		<div id="tableSericeRequestForm">
			<table class="table table-bordered" id="myTable">
				<thead class="table-info">
				<tr>
					<th>ลำดับ</th>
					<th>เลขที่เเบบฟอร์ม</th>
					<th>วันที่ส่งแบบฟอร์ม</th>
					<th>ผู้ขอใช้บริการ</th>
					<% if(personnel.getPersonnel_type().equals("Admin")|| personnel.getPersonnel_type().equals("LaboratoryStaff")){ %>
					<th>จัดการ</th>
					<% } %>
				</tr>
				</thead>
				<%
				int no = 1;
					for (int i = 0; i < listsericeRequestForm.size(); i++) {
				%>
				<%
					if (listsericeRequestForm.get(i).getSericeRequestFormNumber().contains("E")) {
				%>
				<%
					date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(listsericeRequestForm.get(i).getCreatedate());
				%>
				<tr>
					<th><%=no%><%no++; %></th>
					<td><%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%></td>
					<td><%=date%></td>
					<td><% if(listsericeRequestForm.get(i).getMember().getMember_ID() != null ){ %>
					<%=listsericeRequestForm.get(i).getMember().getMember_Prefix() + " "
					+ listsericeRequestForm.get(i).getMember().getMember_FirstName() + " "
					+ listsericeRequestForm.get(i).getMember().getMember_LastName()%>
					<% }else if(listsericeRequestForm.get(i).getStudent().getStudentID() != null){ %>
					<%= listsericeRequestForm.get(i).getStudent().getNameTh()+ " "+listsericeRequestForm.get(i).getStudent().getSurnameTh()%>
					<% }else if (listsericeRequestForm.get(i).getPersonnel().getPersonnel_ID() != null) { %>
					<%=  listsericeRequestForm.get(i).getPersonnel().getPersonnel_Prefix() + " "
					+ listsericeRequestForm.get(i).getPersonnel().getPersonnel_FirstName()+" "
					+ listsericeRequestForm.get(i).getPersonnel().getPersonnel_LastName()%>
					<% } %>
		
		</td>
				<td>
				
					<a href="loadUsageschedule?formnumber=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>">
						<button name="button" class="btn btn-info">
							<i class="fa fa-calendar">&nbsp;ตารางใช้งาน	</i>
						</button>
					</a>
					</td>
					
				</tr>
				
				<%
					}
				%>
				<%
					}
				%>
			</table>
		</div>
		<!--############################### End tableServicerequestForm ###############################-->

		<!--############################### tableFlatFeeForm ###############################-->
		<div id="tableFlatFeeForm" style="display: none;">
			<table class="table table-bordered" id="myTable2">
			<thead class="table-info">
				<tr	>
					<th>ลำดับ</th>
					<th>เลขที่เเบบฟอร์ม</th>
					<th>วันที่ส่งแบบฟอร์ม</th>
					<th>ผู้ขอใช้บริการ</th>
					<th>จัดการ</th>
				
				</tr>
				</thead>
				<%
				int no2 = 1;
					for (int i = 0; i < listsericeRequestForm.size(); i++) {
				%>
				<%
					if (listsericeRequestForm.get(i).getSericeRequestFormNumber().contains("A")) {
				%>
				<%
					date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(listsericeRequestForm.get(i).getCreatedate());
				%>
				<tr>
					<th><%=no2%><%no2++; %></th>
					<td><%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%></td>
					<td><%=date%></td>
					<td><% if(listsericeRequestForm.get(i).getMember().getMember_ID() != null ){ %>
					<%=listsericeRequestForm.get(i).getMember().getMember_Prefix() + " "
					+ listsericeRequestForm.get(i).getMember().getMember_FirstName() + " "
					+ listsericeRequestForm.get(i).getMember().getMember_LastName()%>
					<% }else if(listsericeRequestForm.get(i).getStudent().getStudentID() != null){ %>
					<%= listsericeRequestForm.get(i).getStudent().getNameTh()+ " "+listsericeRequestForm.get(i).getStudent().getSurnameTh()%>
					<% }else if (listsericeRequestForm.get(i).getPersonnel().getPersonnel_ID() != null) { %>
					<%=  listsericeRequestForm.get(i).getPersonnel().getPersonnel_Prefix() + " "
					+ listsericeRequestForm.get(i).getPersonnel().getPersonnel_FirstName()+" "
					+ listsericeRequestForm.get(i).getPersonnel().getPersonnel_LastName()%>
					<% } %>
					</td>
					
					<td>
					<a href="loadUsageschedule?formnumber=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>">
						<button name="button" class="btn btn-info">
							<i class="fa fa-calendar">&nbsp;ตารางใช้งาน	</i>
						</button>
					</a>
					</td>
					
				</tr>
				<%
					}
				%>
				<%
					}
				%>
			</table>
		</div>
		<!--############################### End tableFlatFeeForm ###############################-->



	</div>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>