<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
List<SciInstruments> listsciInstruments = new Vector<>();
String keyword = "";
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
	e.printStackTrace();
}
try {
	keyword = (String) request.getAttribute("keyword");
} catch (Exception e) {}
if(keyword == null){
	keyword = "";
}
try {
	listsciInstruments = (List<SciInstruments>) request.getAttribute("listsciInstruments");
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


<script>
	function searchTable() {
		var input, filter, found, table, tr, td, i, j;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 1; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td");
			for (j = 0; j < td.length; j++) {
				if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
					found = true;
				}
			}
			if (found) {
				tr[i].style.display = "";
				found = false;
			} else {
				tr[i].style.display = "none";
			}
		}
	}
</script>
<body>
			
<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container" style="margin-top: 35px;">
		<h3>รายการเครื่องมือวิทยาศาสตร์</h3>
		<hr class="colorgraph">
	<% if(personnel.getPersonnel_type().equals("Admin")){ %>	
	<a href="loadAddInstrumentsPage">
	<button	name="button" class="btn btn-success" > 
	<i class="fa fa-plus"></i>&nbsp;เพิ่มเครื่องมือ
	</button>
	</a>
	<% } %>
	<br>
	<br>
		<form action="searchListlaboratoryInstruments2" method="post">
			<div class="form-group row">
					<div class="col-sm-10 text-center">
						<input type="text" class="form-control" name="keyword" value="<%= keyword%>" placeholder="ค้นหาเครื่องมือวิทยาศาสตร์....." title="Type in a name">
					</div>
					<div class="col-sm-2 text-left">
						<button type="submit" class="btn btn-success"> ค้นหา</button>				
					</div>
			</div>
			
			
			</form>
	
		<table id="myTable" class="table table-bordered ">
			<thead class="table-info">
				<tr>
					<th scope="col">ลำดับที่</th>
					<th scope="col">รูปภาพ</th>
					<th scope="col">เครื่องมือวิทยาศาสตร์</th>
					<th scope="col">สถานะ</th>
					<th scope="col">รายละเอียด</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (listsciInstruments != null) {
				%>
				<%
					for (int i = 0; i < listsciInstruments.size(); i++) {
				%>
				<tr>
					<td align="center"><%=i+1%></td>
					<td><img src="./img/sciInstruments_img/<%=listsciInstruments.get(i).getSciInstruments_ID() %>.png" width="150" height="150"></td>
					<td
					style="width: 400px;"
					><%=listsciInstruments.get(i).getSciInstruments_Name()%></td>
					<td>
					<label
					<% if(listsciInstruments.get(i).getStatus().equals("พร้อมใช้งาน")){ %>
					style="color : green;"
					<% }else{ %>
					style="color : red;"
					<% } %>
					>
					<%=listsciInstruments.get(i).getStatus()  %>
					</label>
					</td>
					<td>
					<a href="do_getinstrumentsdetails?sciInstrumentsid=<%=listsciInstruments.get(i).getSciInstruments_ID()%>"><button	name="button" class="btn btn-success" > 
					<i class="fa fa-book"></i>&nbsp;ดูรายละเอียด
					</button></a>
					
					</td>
				
				</tr>

				<%
					}
				%>
				<%
					}else{
				%>
				
				<tr>
					<td colspan="5"> ไม่มีข้อมูล </td>
				</tr>
				<% 
					}
				%>
			</tbody>
		</table>

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

</script>
</body>
</html>