<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	boolean error_msg = false;
try {
	error_msg = (boolean) request.getAttribute("error_msg");
} catch (Exception e) {
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login :
	เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="./img/logosci.png" rel="icon">
<script type="text/javascript">
function result(){
	alert("Invalid username or password");
}
</script>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

/* * {
	box-sizing: border-box;
} */

/* style the container */
.container2 {
	position: relative;
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px 0 30px 0;
}

/* style inputs and link buttons */
input, .btn {
	width: 100%;
	padding: 12px;
	border: none;
	border-radius: 4px;
	margin: 5px 0;
	opacity: 0.85;
	display: inline-block;
	font-size: 17px;
	line-height: 20px;
	text-decoration: none; /* remove underline from anchors */
}

input:hover, .btn:hover {
	opacity: 1;
}

/* add appropriate colors to fb, twitter and google buttons */
.mju {
	margin-top: 9%;
	background-color: #45a049;
	color: white;
}

/* style the submit button */
input[type=submit] {
	background-color: #04AA6D;
	color: white;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

/* style the submit button */
input[type=button] {
	background-color: #04AA6D;
	color: white;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

/* Two-column layout */
.col2 {
	float: left;
	width: 50%;
	margin: auto;
	padding: 0 50px;
	margin-top: 6px;
}

/* Clear floats after the columns */
.row2:after {
	content: "";
	display: table;
	clear: both;
}

/* vertical line */
.vl2 {
	position: absolute;
	left: 50%;
	transform: translate(-50%);
	border: 2px solid #ddd;
	height: 175px;
}

/* text inside the vertical line */
.vl-innertext2 {
	position: absolute;
	top: 50%;
	transform: translate(-50%, -50%);
	background-color: #f1f1f1;
	border: 1px solid #ccc;
	border-radius: 50%;
	padding: 8px 10px;
}

/* hide some text on medium and large screens */
.hide-md-lg2 {
	display: none;
}

/* bottom container */
.bottom-container2 {
	text-align: center;
	background-color: #666;
	border-radius: 0px 0px 4px 4px;
}

/* Responsive layout - when the screen is less than 650px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 650px) {
	.col2 {
		width: 100%;
		margin-top: 0;
	}
	/* hide the vertical line */
	.vl2 {
		display: none;
	}
	/* show the hidden text on small screens */
	.hide-md-lg2 {
		display: block;
		text-align: center;
	}
}
</style>

</head>

<body <%if (error_msg) {%> onload="return result()" <%}%>>

<br>
<br>

	<form action="varifylogin" name="frm" method="post">



		<div class="container2">

			<div class="row2">
				<h2 style="text-align: center"> เข้าสู่ระบบมหาวิทยาลัยแม่โจ้   หรือ เข้าสู่ระบบโดยสมาชิก</h2>
				<div class="vl2">
					<span class="vl-innertext2">or</span>
				</div>

				<div class="col2" align="center">
					<a
						href="https://passport.mju.ac.th/Login.aspx?W=6d42ae5e2a6545b69228f2b606cf9ffe"	class="mju btn"> เข้าสู่ระบบมหาวิทยาลัยแม่โจ้ </a>
				</div>

				<div class="col2">


					<input type="text" name="username" id="username"
						placeholder="Username" required> <input type="password"
						name="password" id="password" placeholder="Password" required>
					<label style="color: red; text-align: center;">
						${error_msg}</label>
					<div class="row2">
						<div class="col2">
							<input type="submit" value="เข้าสู่ระบบ">
						</div>
						<div class="col2">
							<a href="Register" style="color: white"> <input
								type="button" value="สมัครสมาชิก">
							</a>
						</div>

					</div>
				</div>

			</div>

		</div>



	</form>
	<c:if test="${msg != null }">
		<script type="text/javascript">
		var msg = '${msg}';
		alert(msg);
</script>
	</c:if>
</body>
</html>