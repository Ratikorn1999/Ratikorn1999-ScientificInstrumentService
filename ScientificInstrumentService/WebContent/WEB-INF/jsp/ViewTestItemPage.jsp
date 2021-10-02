<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
Personnel personnel = null;
List<TestItem> listtestitem = null ;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
	e.printStackTrace();
}
try {
	listtestitem = (List<TestItem>) request.getAttribute("listtestitem");
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
	
	

		
		<h3>รายการวิเคราห์ทดสอบ</h3>
		
		
		<hr class="colorgraph">
								<a href="loadAddTestItemPage">
									<button	name="button" class="btn btn-success" > 
										<i class="fa fa-plus"></i>&nbsp;เพิ่มรายการวิเคราะห์ทดสอบ
									</button>
								</a>
								<br>
	<br>
	<table class="table table-bordered  table-hover" id=myTable>
	<thead class="table-info">
	<tr>
	<th>ลำดับ</th>
	<th>ชื่อ</th>
	<th>ข้อมูล</th>
<!-- 	<th>แก้ไขข้อมูล </th>
	<th>ลบข้อมูล </th> -->
	</tr>
	</thead>
	<tbody>
	<% if(listtestitem!=null){ %>
	<% for(int i = 0 ;i<listtestitem.size();i++){ %>
	<tr>
	<td><%= i+1 %></td>
	<td><%= listtestitem.get(i).getTestitem_Name() %></td>
	<td>
	<a href="ViewTestItemDetailPage?testitem_ID=<%=listtestitem.get(i).getTestitem_ID()%>"><button	name="button" class="btn btn-success" > 
					<i class="fa fa-book"></i>&nbsp;ดูรายละเอียด
					</button></a>

	</tr>
	<% } %>
	<% } else{ %>
	<tr align="center">
	<td colspan="5"><h2>ไม่มีข้อมูล</h2></td>
	</tr>
	<% } %>
	</tbody>
	</table>


	</div>
	
<jsp:include page="common/footer.jsp"></jsp:include>

	<script>
function btnshowdataclick(no) {
	var modal = document.getElementById("datatestitem"+no);
  modal.style.display = "block"; 
  window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
}
function spanclick(no) {
	var modal = document.getElementById("datatestitem"+no);
	modal.style.display = "none";
	window.onclick = function(event) {
		  if (event.target == modal) {
		    modal.style.display = "none";
		  }
		}
}
</script>

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