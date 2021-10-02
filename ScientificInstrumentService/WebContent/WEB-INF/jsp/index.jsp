<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,Manager.*,LoginMJU.*,Class_End_Project.*"%>

<%
Member member = null;
Student student = null;
AuthLogin auths = null;
Personnel personnel = null;
boolean checkauth = false ;


try {
	student = (Student) session.getAttribute("student");
} catch (Exception e) {}
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {}

 try {
	auths = (AuthLogin) session.getAttribute("checkauth");
	checkauth = auths.getResult();
	if(!checkauth){
		response.sendRedirect("Login");
	} 
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
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href='https://fonts.googleapis.com/css?family=Kanit'	rel='stylesheet' type='text/css'>
<link rel="stylesheet"	href="./css/web_css.css">
<style type="text/css">
.zoom {
  padding: 50px;
  background-color: green;
  transition: transform .2s;
  width: 200px;
  height: 200px;
  margin: 0 auto;
}

.zoom:hover {
  -ms-transform: scale(1.5); /* IE 9 */
  -webkit-transform: scale(1.5); /* Safari 3-8 */
  transform: scale(1.5); 
}
</style>
<body >

	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container" style="margin-top: 35px;">
		<hr>
		<section id="content">
			<div class="container" >
				<div class="row">
					<div class="col-lg-12">
						<div
							style="margin-bottom: 5px; border: dashed 1px #eee; color: red; height: 35px; line-height: 30px;">
							<marquee onmouseover="this.stop()" onmouseout="this.start()">
								<a href="#" >เว็บไซต์ขอใช้บริการเเละให้บริการเครื่องมือวิทยาศาสตร์ ของคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้</a>
							</marquee>
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-lg-12" align="center">
					<h3> ขั้นตอนการใช้บริการ </h3>
					</div>
				</div>
			</div>
		<hr class="colorgraph">
		
			<div class="row">
					<div class="col-lg-6" >
						<iframe src="./PDF/service.pdf" type="application/pdf" width="100%" height="800px"  onclick="return btnshow(1)"> </iframe>
							<div	id="show1"	class="modal">
								<!-- Modal content -->
								<div class="modal-content">
									<span class="close"	onclick="return spanclick(1)">&times;</span>
									<embed src="./PDF/service.pdf" type="application/pdf" width="100%" height="700px" />
									
								</div>
							</div>
					</div>
					
					<div class="col-lg-6">
					<iframe  src="./PDF/fatfee.pdf" type="application/pdf" width="100%" height="800px" onclick="return btnshow(2)"> </iframe>
						
							<div	id="show2"	class="modal">
								<!-- Modal content -->
								<div class="modal-content">
									<span class="close"	onclick="return spanclick(2)">&times;</span>
										<embed src="./PDF/fatfee.pdf" type="application/pdf" width="100%" height="700px" />
								</div>
							</div>
					</div>
				</div>
				
		
		
	
		</section>

	</div>


<br><br><br><br><br><br><br>




	<jsp:include page="common/footer.jsp"></jsp:include>



	<script>
		function btnshow(no) {
			var modal = document.getElementById("show" + no);
			modal.style.display = "block";
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		}

		function spanclick(no) {
			var modal = document.getElementById("show" + no);
			modal.style.display = "none";
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		}
	</script>


</body>
</html>