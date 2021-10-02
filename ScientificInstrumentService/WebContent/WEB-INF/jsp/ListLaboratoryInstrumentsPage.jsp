<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,LoginMJU.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String keyword = "";

Member member = null;
Student student = null;
AuthLogin auths = null;
PersonnelMJU personnelMJU = null;

boolean checkauth = false;

try {
	student = (Student) session.getAttribute("student");
} catch (Exception e) {}
try {
	personnelMJU = (PersonnelMJU) session.getAttribute("personnelMJU");
} catch (Exception e) {}
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {}
try {
	auths = (AuthLogin) session.getAttribute("checkauth");
	checkauth = auths.getResult();
	if (!checkauth) {
		response.sendRedirect("Login");
	}
} catch (Exception e) {}
try {
	keyword = (String) request.getAttribute("keyword");
} catch (Exception e) {}
if (keyword == null) {
	keyword = "";
}
%>
<!DOCTYPE html>
<html>
<head>


<title>รายการเครื่องมือวิทยาศาสตร์ : เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
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
<style scoped='' type='text/css'>
.fixed-rightSd{position:fixed;top:60px;width:250px;height:570px;z-index:9999;transform:translateZ(0);}
.fixed-rightSd{right:0;}
.close-fixedSd{position:absolute;width:250px;height:30px;line-height:20px;font-size:15px;font-weight:400;top:-15px;left:0;text-align:center;background:#e0e0e0;color:red;padding:5px 5px;cursor:pointer}

</style>
<!--เรียกใช้ cdn ของ interact.js-->
		<script src="https://cdn.jsdelivr.net/npm/interactjs/dist/interact.min.js"></script>

		<script type="text/javascript">
			// กำหนด class ที่ชื่อ draggable ให้สามารถลากวางได้
                        interact('.fixed-rightSd').draggable({
				// กำหนดให้ scroll bar ขยายตามการลากวาง
                                autoScroll: true,
				
				// ส่วนนี้จะเป็นการกำหนดตำแหน่งของ block หลังจากลากวางแล้ว
                                onmove: function (event) {
                                        var target = event.target;
                                        var x = (parseFloat(target.getAttribute('data-x')) || 0) + event.dx;
                                        var y = (parseFloat(target.getAttribute('data-y')) || 0) + event.dy;
                                        target.style.webkitTransform =
                                                target.style.transform =
                                                'translate(' + x + 'px, ' + y + 'px)';
                                        target.setAttribute('data-x', x);
                                        target.setAttribute('data-y', y);
                                }
                        });
		</script>
		<script type="text/javascript">

    function contentsd_visibility(id) {
       var e = document.getElementById(id);
       if(e.style.display != 'block'){
          e.style.display = 'block';
          document.getElementById('txtsd').innerHTML = "CLOSE";
       }  else{
          e.style.display = 'none';
          document.getElementById('txtsd').innerHTML = "OPEN";
       }
    }

</script>
<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>

<div class="fixed-rightSd">
<div aria-label="Close Ads" class="close-fixedSd" role="button" tabindex="0" onclick="contentsd_visibility('contentsd')">
<label id="txtsd">CLOSE</label> 
</div>
<div   id="contentsd" style="background-color:#FFDEAD ;padding:0.5cm;" >
<div class="form-group row">
				<label class="col-sm-12 col-form-label "> <b>ประเภท ก </b> ได้แก่ บุคคลทั่วไป บุคลากรจากหน่วยงานเอกชน (บริษัท/โรงงานอุตสาหกรรม/ ห้างหุ้นส่วนจำกัด) </label>
				<label class="col-sm-12 col-form-label "><b>ประเภท ข </b> ได้แก่ บุคลากรจากหน่วยงานราชการภายนอก (หน่วยงานราชการและวิสาหกิจภายนอกมหาวิทยาลัยแม่โจ้/นักวิจัย/นักศึกษาปริญญาตรี โท เอกจากสถาบันการศึกษาอื่นๆ)</label>
				<label class="col-sm-12 col-form-label "><b>ประเภท ค </b> ได้แก่ บุคลากรภายในมหาวิทยาลัยแม่โจ้ (บุคลากร/ นักศึกษาปริญญาตรี โท เอก ของมหาวิทยาลัยแม่โจ้) </label>
				<label class="col-sm-12 col-form-label "><b>ประเภท ง </b> ได้แก่บุคลากรภายในคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้ (บุคลากร/นักศึกษาปริญญาตรี โท เอก ของคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้)</label>
</div>
</div>
</div>
	<div class="container" style="margin-top: 35px;">
		
			
		
		<form action="searchListlaboratoryInstruments" method="post">
			<div class="form-group row">
				<div class="col-sm-10 text-center">
					<input type="text" class="form-control" name="keyword"
						value="<%=keyword%>"
						placeholder="ค้นหาเครื่องมือวิทยาศาสตร์....."
						title="Type in a name">
				</div>
				<div class="col-sm-2 text-left">
					<button type="submit" class="btn btn-success">ค้นหา</button>
				</div>
			</div>


		</form>


		<table id="myTable" class="table table-bordered ">
			<thead>
				<tr>
					<th>รูปภาพ</th>
					<th style="width: 600px;">เครื่องมือวิทยาศาสตร์</th>
					<th>สถานะ</th>
					<th>รายละเอียด</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="i" items="${ listsciInstruments }" varStatus="loop">
					<tr>
						<td><img src="./img/sciInstruments_img/${ i.getSciInstruments_ID()}.png" width="150" height="150"></td>
						<td>${ i.getSciInstruments_Name()}</td>
						<td><label
							<c:if test="${ i.getStatus() == 'พร้อมใช้งาน' }">
							style="color: green;" 
							</c:if>
							<c:if test="${ i.getStatus() != 'พร้อมใช้งาน' }">
							 style="color : red;" 
							</c:if>
							 >
							${ i.getStatus()}
						</label>
						</td>
						<td><a href="ViewschedulePage?sciInstruments_ID=${i.getSciInstruments_ID()}">
								<button name="button" class="btn btn-success">
									ตารางการใช้งาน</button>
						</a></td>
					</tr>
					<tr >
					<td colspan="4" align="center">
				<div class="form-group row" >
					<label class="col-sm-12">ราคาเเต่ละประเภท  </label>
				</div>
				<div class="form-group row">
					<label class="col-sm-3 col-form-label  txt-center ">ประเภท ก  </label>
					<label class="col-sm-3 col-form-label  txt-center ">ประเภท ข  </label>
					<label class="col-sm-3 col-form-label  txt-center ">ประเภท ค </label>
					<label class="col-sm-3 col-form-label  txt-center ">ประเภท ง </label>
				</div>
				<div class="form-group row">
					<label class="col-sm-3 col-form-label  txt-center ">${ i.getPriceType_A()}  </label>
					<label class="col-sm-3 col-form-label  txt-center ">${ i.getPriceType_B()} </label>
					<label class="col-sm-3 col-form-label  txt-center ">${ i.getPriceType_C()}</label>
					<label class="col-sm-3 col-form-label  txt-center ">${ i.getPriceType_D()} </label>
				</div>

						</td>
					
					</tr>

				</c:forEach>
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