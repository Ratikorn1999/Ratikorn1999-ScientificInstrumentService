<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*"%>
<%
	Member member = null;
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {
}
%>
<!DOCTYPE html>
<html>
<head>
<title>สมัครสมาชิก :
	เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="./img/logosci.png" rel="icon">
<link href="./img/logosci.png" rel="icon">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'	rel='stylesheet' type='text/css'>
<link rel="stylesheet"	href="./css/web_css.css">
<script type="text/javascript">
	function checkpwd(frm) {
		if (frm.conpwd.value != "") {
			if (frm.pwd.value != frm.conpwd.value) {
				document.getElementById("al").innerHTML = "Confirm Password Not Match";
			} else {
				document.getElementById("al").innerHTML = "";
			}
		} else {
			document.getElementById("al").innerHTML = "";
		}
	}

	function check(frm) {

		var checkmail = /^.+@.+\..{2,3}$/;
		if (!frm.email.value.match(checkmail)) {
			alert("กรุณากรอกอีเมล์ที่ถูกต้อง (ตัวอย่าง anonymous@gmail.com)");
			frm.email.value = "";
			return false;
		}

		if (frm.pwd.value == "") {
			alert("กรุณากรอกรหัสผ่านอย่างน้อย8ตัว");
			frm.pwd.value = "";
			return false;
		}
		var password = /^[A-Za-z0-9]{8,16}$/;
		if (!frm.pwd.value.match(password)) {
			alert("กรุณากรอกรหัสผ่านอย่างน้อย8ตัว");
			frm.pwd.value = "";
			return false;
		}

		if (frm.conpwd.value == "") {
			alert("กรุณากรอกรหัสผ่านอย่างน้อย8ตัว");
			frm.conpwd.value = "";
			return false;
		}
		var password2 = /^[A-Za-z0-9]{8,16}$/;
		if (!frm.conpwd.value.match(password2)) {
			alert("กรุณากรอกยืนยันรหัสผ่านอย่างน้อย8ตัว");
			frm.conpwd.value = "";
			return false;

		}

		if (frm.pwd.value != frm.conpwd.value) {
			alert("กรุณากรอกรหัสผ่านให้ตรงกัน");
			frm.conpwd.value = "";
			frm.pwd.value = "";
			return false;
		}

		var fname = /^[ก-์]{2,}$/;
		if (!frm.fname.value.match(fname)) {
			alert("กรุณากรอกชื่อจริงเป็นภาษาไทย");
			frm.fname.value = "";
			return false;
		}

		var lname = /^[ก-์]{2,}$/;
		if (!frm.lname.value.match(lname)) {
			alert("กรุณากรอกนามสกุลเป็นภาษาไทย");
			frm.lname.value = "";
			return false;
		}

		var gendercheck = document.getElementsByName('gender');
		var gendernull = false;
		for (var i = 0; i < gendercheck.length; i++) {
			if (gendercheck[i].checked) {
				gendernull = true;
				break;
			}
		}
		if (!gendernull) {
			alert("กรุณาเลือกเพศของคุณ");
			return false;

		}
	}

	function showForm(selectObject) {
		var value = selectObject.value;

		if (value == "1") {
			document.getElementById('table1').style.display = "inline";
			document.getElementById('table2').style.display = "none";
			document.getElementById('table3').style.display = "none";
		} else if (value == "2") {
			document.getElementById('table1').style.display = "none";
			document.getElementById('table2').style.display = "inline";
			document.getElementById('table3').style.display = "none";
		} else {
			document.getElementById('table1').style.display = "none";
			document.getElementById('table2').style.display = "none";
			document.getElementById('table3').style.display = "inline";
		}

	}
</script>
</head>
<body>
	<jsp:include page="common/navbar.jsp"></jsp:include>
	<div class="container" style="margin-top: 5%;">

		<form action="Register" id="frm" name="frm" method="post" novalidate>
			<h4>ลงทะเบียน</h4>
			<hr>


			<div class="form-group row">
				<label for="username" class="col-sm-3 col-form-label text-right">Username</label>
				<div class="col">
					<input type="text" class="form-control" id="username"
						name="username" autocomplete="off" value="" required>
					<div class="invalid-feedback">กรุณากรอก Username</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="pwd" class="col-sm-3 col-form-label text-right">Password</label>
				<div class="col">
					<input type="password" name="pwd" id="pwd" class="form-control"
						onkeyup="checkpwd(frm)" autocomplete="off" value="" required>
					<div class="invalid-feedback">กรุณากรอก Password</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="conpwd" class="col-sm-3 col-form-label text-right">ConfirmPassword</label>
				<div class="col">
					<input type="password" class="form-control" id="conpwd"
						name="conpwd" onkeyup="checkpwd(frm)" autocomplete="off" value=""
						required> <label style="color: red" id="al"></label>
					<div class="invalid-feedback">กรุณากรอก Password</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="typeMember" class="col-sm-3 col-form-label text-right"></label>
				<div class="col">
					<select name="typeMember" id="typeMember" class="form-control"
						onchange="return showForm(this)">
						<option value="1">นักศึกษา</option>
						<option value="2">อาจารย์ / บุคลากร</option>
						<option value="3">หน่วยงานภายนอก</option>
					</select>
				</div>
			</div>

			<div class="form-group row">
				<legend class="col-form-label col-sm-3 pt-0 text-right">คำนำหน้าชื่อ</legend>
				<div class="col">
					<div class="btn-group btn-group-toggle" data-toggle="buttons">
						<label class="btn btn-light"> <input type="radio"
							name="prefixuser" id="radio_gender_1" autocomplete="off"
							value="นาย" required> นาย
						</label> <label class="btn btn-light"> <input type="radio"
							name="prefixuser" id="radio_gender_2" autocomplete="off"
							value="นาง" required> นาง
						</label><label class="btn btn-light"> <input type="radio"
							name="prefixuser" id="radio_gender_2" autocomplete="off"
							value="นางสาว" required> นางสาว
						</label>
					</div>
				</div>
			</div>






			<div class="form-group row">
				<label for="fname" class="col-sm-3 col-form-label text-right">ชื่อ</label>
				<div class="col">
					<input type="text" class="form-control" name="fname" id="fname"
						autocomplete="off" value="" required pattern="^[ก-์]{2,}$">
					<div class="invalid-feedback">กรุณากรอกชื่อ</div>
				</div>
			</div>
			<div class="form-group row">
				<label for="lname" class="col-sm-3 col-form-label text-right">นามสกุล</label>
				<div class="col">
					<input type="text" class="form-control" name="lname" id="lname"
						autocomplete="off" value="" required pattern="^[ก-์]{2,}$">
					<div class="invalid-feedback">กรุณากรอกนามสกุล</div>
				</div>
			</div>

			<div id="table1">
				<div class="form-group row">
					<label for="studentID" class="col-sm-3 col-form-label text-right">รหัสนักศึกษา</label>
					<div class="col">
						<input type="text" name="studentID" id="studentID"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกรหัสนักศึกษา</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="degree" class="col-sm-3 col-form-label text-right">ระดับปริญญา</label>
					<div class="col">
						<select name="degree" id="degree" class="form-control">
							<option value="ปริญญาตรี">ปริญญาตรี</option>
							<option value="ปริญญาโท">ปริญญาโท</option>
							<option value="ปริญญาเอก">ปริญญาเอก</option>
						</select>

					</div>
				</div>
				<div class="form-group row">
					<label for="branch" class="col-sm-3 col-form-label text-right">ภาค/สาขาวิชา
					</label>
					<div class="col">
						<input type="text" name="branch" id="branch" class="form-control"
							autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกรหัสนักศึกษา</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="faculty" class="col-sm-3 col-form-label text-right">คณะ</label>
					<div class="col">
						<input type="text" name="faculty" id="faculty"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกคณะ</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="university" class="col-sm-3 col-form-label text-right">มหาวิทยาลัย</label>
					<div class="col">
						<input type="text" name="university" id="university"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกมหาวิทยาลัย</div>
					</div>
				</div>

				<div class="form-group row">
					<label for="advisor_name"
						class="col-sm-3 col-form-label text-right">ชื่ออาจารย์ที่ปรึกษา</label>
					<div class="col">
						<input type="text" name="advisor_name" id="advisor_name"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกชื่ออาจารย์ที่ปรึกษา
						</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="emailadvisor"
						class="col-sm-3 col-form-label text-right">อีเมลอาจารย์ที่ปรึกษา</label>
					<div class="col">

						<input type="email" name="emailadvisor" id="emailadvisor"
							pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
							placeholder="test@gmail.com" class="form-control"
							autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกชื่ออาจารย์ที่ปรึกษา
						</div>
					</div>
				</div>
			</div>

			<div id="table2" style="display: none;">
				<div class="form-group row">
					<label for="position" class="col-sm-3 col-form-label text-right">ตำแหน่ง</label>
					<div class="col">
						<input type="text" name="position" id="position"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกตำแหน่ง</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="branch_Personnel"
						class="col-sm-3 col-form-label text-right">ภาค/สาขาวิชา</label>
					<div class="col">
						<input type="text" name="branch_Personnel" id="branch_Personnel"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกภาค/สาขาวิชา</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="faculty_Personnel"
						class="col-sm-3 col-form-label text-right">คณะ </label>
					<div class="col">
						<input type="text" name="faculty_Personnel" id="faculty_Personnel"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกคณะ</div>
					</div>
				</div>
				<div class="form-group row">
					<label for="university_Personnel"
						class="col-sm-3 col-form-label text-right">มหาวิทยาลัย </label>
					<div class="col">
						<input type="text" name="university_Personnel"
							id="university_Personnel" class="form-control" autocomplete="off"
							value="">
						<div class="invalid-feedback">กรุณากรอกมหาวิทยาลัย</div>
					</div>
				</div>


			</div>


			<div id="table3" style="display: none;">
				<div class="form-group row">
					<label for="company_name"
						class="col-sm-3 col-form-label text-right">ชื่อหน่วยงาน/ชื่อบริษัท</label>
					<div class="col">
						<input type="text" name="company_name" id="company_name"
							class="form-control" autocomplete="off" value="">
						<div class="invalid-feedback">กรุณากรอกอีเมล</div>
					</div>
				</div>
			</div>


			<div class="form-group row">
				<label for="email" class="col-sm-3 col-form-label text-right">อีเมล</label>
				<div class="col">
					<input type="text" class="form-control" name="email" id="email"
						pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
						autocomplete="off" value="" required>
					<div class="invalid-feedback">กรุณากรอกอีเมล</div>
				</div>
			</div>
			<div class="form-group row">
				<label for="phonenumber" class="col-sm-3 col-form-label text-right">เบอร์โทรศัพท์</label>
				<div class="col">
					<input type="text" name="phonenumber" id="phonenumber"
						class="form-control" maxlength="10" autocomplete="off" value=""
						required>
					<div class="invalid-feedback">กรุณากรอกเบอร์โทรศัพท์</div>
				</div>
			</div>




			<div class="form-group row" align="center">
				   
				<div class="col-sm-3">
					<button type="submit" name="btn_submit" id="btn_submit" value="1"
						class="btn btn-primary btn-block">Register</button>
				</div>
				<div class="col-sm-3">
					<button type="reset" name="btn_reset" id="btn_reset" value="2"
						class="btn btn-warning btn-block">Reset</button>
				</div>

			</div>
		</form>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
	<script src="https://unpkg.com/jquery@3.3.1/dist/jquery.min.js"></script>
	<script
		src="https://unpkg.com/bootstrap@4.1.0/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#frm").on("submit", function() {
				var form = $(this)[0];
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			});
		});
	</script>
	<c:if test="${error_msg != null }">
		<script type="text/javascript">
			var error_msg = '${error_msg}';
			alert(error_msg);
		</script>
	</c:if>
</body>
</html>