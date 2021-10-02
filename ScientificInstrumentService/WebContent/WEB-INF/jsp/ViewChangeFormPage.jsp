<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*,LoginMJU.*"%>
<%
	Personnel personnel = null;

List<ChangeForm> listChangeForm = null;

String date = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	listChangeForm = (List<ChangeForm>) request.getAttribute("listChangeForm");
} catch (Exception e) {
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
<div class="container"  style="margin-top: 35px;">
<h3>รายการเปลี่ยนแปลงบริการเครื่องมือวิทยาศาสตร์</h3>
<hr class="colorgraph">
<table  class="table table-bordered   table-hover" id="myTable">
<thead class="table-info">
<tr>
<th>ลำดับ </th>
<th>วันที่ส่งแบบฟอร์ม </th>
<th>ผู้ขอใช้บริการ </th>
<th>การอนุมัติ </th>
<th>ดูรายละเอียด </th>

</tr >
</thead>
<% 
if(listChangeForm.size()>0){
for(int i = 0 ;i < listChangeForm.size() ; i++) { %>
<% 
date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH")).format(listChangeForm.get(i).getCreatedate()); 
String numberform = listChangeForm.get(i).getChangeFormNumber().substring(0, listChangeForm.get(i).getChangeFormNumber().indexOf("/"));
%>
<tr >
<td><%= i+1 %></td>
<td><%= date %></td>
<td >
<% if(listChangeForm.get(i).getSericeRequestForm().getMember().getMember_ID() != null){ 
Member member = listChangeForm.get(i).getSericeRequestForm().getMember();
%>
  <%= member.getMember_Prefix()+" "+ member.getMember_FirstName()+" "+member.getMember_LastName() %>
 <% }else if(listChangeForm.get(i).getSericeRequestForm().getStudent().getStudentID() != null){ 
	 Student student = listChangeForm.get(i).getSericeRequestForm().getStudent();
 %>
 <%= student.getNameTh()+" "+student.getSurnameTh() %>
<% }else if (listChangeForm.get(i).getSericeRequestForm().getPersonnel().getPersonnel_ID() != null) { %>
					<%= listChangeForm.get(i).getSericeRequestForm().getPersonnel().getPersonnel_Prefix() + " "
					+ listChangeForm.get(i).getSericeRequestForm().getPersonnel().getPersonnel_FirstName()+" "
					+listChangeForm.get(i).getSericeRequestForm().getPersonnel().getPersonnel_LastName()%>
<% } %>
  </td>

<td><%= listChangeForm.get(i).getChange_status()%></td>
<td ><a href="ViewChangeRequestFormDetail?changeFormNumber=<%= listChangeForm.get(i).getChangeFormNumber()%>"><input type="button" class="btn btn-success" name="btn" id="btn"  value="รายละเอียด"></a></td>


</tr>
<%} %>
<% } else{ %>
<tr align="center">
<td colspan="5"><h2>ไม่มีข้อมูล</h2></td>
</tr>
<% } %>
</table>



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