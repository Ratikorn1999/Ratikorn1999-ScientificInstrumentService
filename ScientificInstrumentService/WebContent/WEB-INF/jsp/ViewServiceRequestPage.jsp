<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
List<SericeRequestForm> listsericeRequestForm = new Vector<>();
String date = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	listsericeRequestForm = (List<SericeRequestForm>) request.getAttribute("listsericeRequestForm");
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
	
</script>


<body >
	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container"  style=" margin-top: 35px;">
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
					<th>วันที่ส่งแบบฟอร์ม</th>
					<th>ผู้ขอใช้บริการ</th>
					<th>การอนุมัติ</th>
					<th>ดูรายละเอียด</th>
				</tr>
				</thead>
				<%
					int no = 1  ;
					for (int i = 0; i < listsericeRequestForm.size(); i++) {
				%>
				<%
					if (listsericeRequestForm.get(i).getSericeRequestFormNumber().contains("E")) {
				%>
				<%
					date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(listsericeRequestForm.get(i).getCreatedate());
				%>
				<tr 
				<% if(	listsericeRequestForm.get(i).getBooking_status().equals("ไม่อนุมัติ")
						||	listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")){ %>
				style="background-color: #FF4500 ;color: #FFFFFF;"
				<% } %>
				>
					<th><%=no%><% no++; %></th>
					<td><%=date%></td>
					<td>
					<% if(listsericeRequestForm.get(i).getMember().getMember_ID() != null ){ %>
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
					<td<%if (listsericeRequestForm.get(i).getBooking_status().equals("อนุมัติ")) {%>
						style="color: green;"
						<%} else if (listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")) {%>
						style="color : #FFFFFF;" <%} else {%> style="color : gray ;" <%}%>>
						<%=listsericeRequestForm.get(i).getBooking_status()%></td>
					<td><a	href="loadServiceRequestFormDetail?formnumber=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>">
						<input type="button" name="btn" id="btn" class="btn btn-success"
							value="รายละเอียด"> </a></td>
					
					
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
				<tr>
					<th>ลำดับ</th>
					<th>วันที่ส่งแบบฟอร์ม</th>
					<th>ผู้ขอใช้บริการ</th>
					<th>การอนุมัติ</th>
					<th>ดูรายละเอียด</th>
					
				</tr>
				</thead>
				<%
				int no2 = 1  ;
					for (int i = 0; i < listsericeRequestForm.size(); i++) {
				%>
				<%
					if (listsericeRequestForm.get(i).getSericeRequestFormNumber().contains("A")) {
				%>
				<%
					date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(listsericeRequestForm.get(i).getCreatedate());
				%>
				<tr
				<% if(	listsericeRequestForm.get(i).getBooking_status().equals("ไม่อนุมัติ")
						||	listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")){ %>
				style="background-color: #FF4500 ;color: #FFFFFF;"
				<% } %>
				>
					<th><%=no2%><% no2++; %></th>
					<td><%=date%></td>
					<td>
						<% if(listsericeRequestForm.get(i).getMember().getMember_ID() != null ){ %>
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
		
	
					<td<%if (listsericeRequestForm.get(i).getBooking_status().equals("อนุมัติ")) {%>
						style="color: green;"
						<%} else if (listsericeRequestForm.get(i).getBooking_status().equals("ยกเลิก")) {%>
						style="color : #FFFFFF;" <%} else {%> style="color : gray ;" <%}%>>
						<%=listsericeRequestForm.get(i).getBooking_status()%></td>
					<td><a
						href="loadServiceRequestFormDetail?formnumber=<%=listsericeRequestForm.get(i).getSericeRequestFormNumber()%>"><input type="button" name="btn" id="btn" class="btn btn-success"
							value="รายละเอียด"> </a></td>
				
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

			<script type="text/javascript">
	$(document).ready(function(){
    $('#myTable').after('<div id="nav" class="pagination"></div>');
    var rowsShown = 10;
    var rowsTotal = $('#myTable tbody tr').length;
    var numPages = rowsTotal/rowsShown;
    for(i = 0;i < numPages;i++) {
        var pageNum = i + 1;
        $('#nav').append('<a href="#" rel="'+i+'" >'+pageNum+'</a> ');
    }
    $('#myTable tbody tr').hide();
    $('#myTable tbody tr').slice(0, rowsShown).show();
    $('#nav a:first').addClass('active');
    $('#nav a').bind('click', function(){

        $('#nav a').removeClass('active');
        $(this).addClass('active');
        var currPage = $(this).attr('rel');
        var startItem = currPage * rowsShown;
        var endItem = startItem + rowsShown;
        $('#myTable tbody tr').css('opacity','0.0').hide().slice(startItem, endItem).
        css('display','table-row').animate({opacity:1}, 300);
    });
});

	$(document).ready(function(){
	    $('#myTable2').after('<div id="nav2" class="pagination"></div>');
	    var rowsShown = 10;
	    var rowsTotal = $('#myTable2 tbody tr').length;
	    var numPages = rowsTotal/rowsShown;
	    for(i = 0;i < numPages;i++) {
	        var pageNum = i + 1;
	        $('#nav2').append('<a href="#" rel="'+i+'" >'+pageNum+'</a> ');
	    }
	    $('#myTable2 tbody tr').hide();
	    $('#myTable2 tbody tr').slice(0, rowsShown).show();
	    $('#nav2 a:first').addClass('active');
	    $('#nav2 a').bind('click', function(){

	        $('#nav2 a').removeClass('active');
	        $(this).addClass('active');
	        var currPage = $(this).attr('rel');
	        var startItem = currPage * rowsShown;
	        var endItem = startItem + rowsShown;
	        $('#myTable2 tbody tr').css('opacity','0.0').hide().slice(startItem, endItem).
	        css('display','table-row').animate({opacity:1}, 300);
	    });
	});
</script>
</body>
</html>