<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
List<TestAnalysisForm> listTestAnalysisForm = new Vector<>();
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	listTestAnalysisForm = (List<TestAnalysisForm>) request.getAttribute("listTestAnalysisForm");
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
	<div class="container"  style="margin-top: 35px;">
	<h3>รายการวิเคราห์ทดสอบ</h3>
	<hr>

		<!--############################### tableTestAnalysisForm ###############################-->
		<div id="tableTestAnalysisForm">

			<table class="table table-bordered table-striped" id="myTable">
			<thead  class="table-info">
				<tr >
					<th>ลำดับที่</th>
					<th>วันที่ส่งแบบฟอร์ม</th>
					<th>ผู้ขอใช้บริการ</th>
					<th>สถานะการอนุมัติ</th>
					<th>สถานะการทดสอบ</th>
					<th>ดูรายละเอียด</th>
					<% if(personnel.getPersonnel_type().equals("Admin") || personnel.getPersonnel_type().equals("LaboratoryStaff")){ %>
					<th>จัดการ</th>
					<% } %>
				</tr>
			</thead>	
			<tbody >
				<% if (listTestAnalysisForm.size()>0){ %>
				<%
					for (int i = 0; i < listTestAnalysisForm.size(); i++) {
				%>
				<%
					String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
						.format(listTestAnalysisForm.get(i).getCreatedate());
				%>
				<tr >
					<td align="center"><%=i+1%></td>
					<td><%=date%></td>
					<td>
					<% if(listTestAnalysisForm.get(i).getMember().getMember_ID()!=null){ %>
					<%=listTestAnalysisForm.get(i).getMember().getMember_Prefix() + " "
					+ listTestAnalysisForm.get(i).getMember().getMember_FirstName() + " "
					+ listTestAnalysisForm.get(i).getMember().getMember_LastName()%>
					<%} else if(listTestAnalysisForm.get(i).getStudent().getStudentID() != null){ %>
					<%= listTestAnalysisForm.get(i).getStudent().getNameTh()+" "+listTestAnalysisForm.get(i).getStudent().getSurnameTh() %>
					<% }else if (listTestAnalysisForm.get(i).getPersonnel().getPersonnel_ID() != null) { %>
					<%= listTestAnalysisForm.get(i).getPersonnel().getPersonnel_Prefix() + " "
					+ listTestAnalysisForm.get(i).getPersonnel().getPersonnel_FirstName()+" "
					+listTestAnalysisForm.get(i).getPersonnel().getPersonnel_LastName()%>
<% } %>
		</td>
					<td
					<%if (listTestAnalysisForm.get(i).getApprovestatus().equals("อนุมัติ")) {%>
						style="color: green;"
						<%} else if (listTestAnalysisForm.get(i).getApprovestatus().equals("รอการอนุมัติ")) {%>
						style="color : orange;"
						<%} else{%> style="color : red;" <%}%>
					>
					
					<%=listTestAnalysisForm.get(i).getApprovestatus() %></td>
					<td
					<%if (listTestAnalysisForm.get(i).getTesting_status().equals("ทดสอบเสร็จสิ้น")) {%>
						style="color: green;"
						<%} else if (listTestAnalysisForm.get(i).getTesting_status().equals("รอการทดสอบ")) {%>
						style="color : orange;"
						<%} else if (listTestAnalysisForm.get(i).getTesting_status().equals("กำลังทดสอบ")) {%>
						style="color : blue;" <%} else {%> style="color : red;" <%}%>
					><%=listTestAnalysisForm.get(i).getTesting_status()%>
					
						</td>
					<td><a
						href="testanalysisformdetail?testAnalysisFormnumber=<%=listTestAnalysisForm.get(i).getTestAnalysisFormNumber()%>"
						><input type="button" name="btn" id="btn"
						class="btn btn-warning"	value="รายละเอียด"> </a></td>
					
					<% if(personnel.getPersonnel_type().equals("Admin") || personnel.getPersonnel_type().equals("LaboratoryStaff")){ %>
					<td>
					
					<% 
					String testAnalysisstatus = "";
					if(listTestAnalysisForm.get(i).getTesting_status().equals("รอการทดสอบ")) {
						testAnalysisstatus = "กำลังทดสอบ";
					} else if (listTestAnalysisForm.get(i).getTesting_status().equals("กำลังทดสอบ")) {
						testAnalysisstatus = "ทดสอบเสร็จสิ้น";
					} %>
					
					<input type="button" name="updatestatus" id="updatestatus" value="UpdateStatus" 
					 onclick="return confirm('คุณต้องการเปลี่ยนสถานะแบบฟอร์มนี้หรือไม่ '),location.href='do_UpdateStatus?testAnalysisFormNumber=<%=listTestAnalysisForm.get(i).getTestAnalysisFormNumber()%>&testAnalysisstatus=<%=testAnalysisstatus%>';"
					<% if(listTestAnalysisForm.get(i).getTesting_status().equals("ยกเลิก")
						|| listTestAnalysisForm.get(i).getTesting_status().equals("ทดสอบเสร็จสิ้น")
						|| listTestAnalysisForm.get(i).getApprovestatus().equals("รอการอนุมัติ")
							){ %>
					disabled
					class="btn btn-secondary"
					<% }else{ %>
					 class="btn btn-info"
					<%} %>
					
					>
					
					</td>
					<% } %>
					</tr>
				<%
					}
				%>
				<% 
					}else{
				%>
				<tr>
					<td colspan="6" align="center">     <h2>ไม่มีข้อมูล</h2> </td>
				</tr>	
				<% 
					}
				%>
				</tbody>
			</table>
		</div>
		<!--############################### End tableTestAnalysisForm ###############################-->



	</div>
	
	 <jsp:include page="common/footer.jsp"></jsp:include>
		
		
		
		<script type="text/javascript">
$(document).ready(function(){
    $('#myTable').after('<div id="nav" class="pagination"></div>');
    var rowsShown = 30;
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

</script>
</body>
</html>