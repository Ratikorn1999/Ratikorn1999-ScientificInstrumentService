<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
	Personnel personnel = null;
FlatFeeForm sericeRequestForm = null;
TestAnalysisForm testAnalysisForm = null;
String type_service = null;
double totalprice = 0.0;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	sericeRequestForm = (FlatFeeForm) request.getAttribute("sericeRequestForm");
} catch (Exception e) {
}
try {
	testAnalysisForm = (TestAnalysisForm) request.getAttribute("testAnalysisForm");
} catch (Exception e) {
}
try {
	type_service = (String) request.getAttribute("type_service");
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="./css/web_css.css">
</head>
<script type="text/javascript">
	function checkpayment_type() {

		var checkpayment_type = document.getElementsByName('payment_type');
		if (checkpayment_type[2].checked) {
			document.getElementById("Agency").disabled = false;
		} else {
			document.getElementById("Agency").disabled = true;
		}

	}
</script>
<body>

	<jsp:include page="common/navbar.jsp"></jsp:include>


	<div class="container" style="margin-top: 35px;">
		<form action="doAddPayment" name="frm" method="post"
			enctype="multipart/form-data">

			<table class="table table-bordered">
				<tr>
					<td>เลขที่รายการ</td>
					<td>

						<p>
							<%
								if (sericeRequestForm != null) {
							%>
							<%=sericeRequestForm.getSericeRequestFormNumber()%>
							<%
								} else if (testAnalysisForm != null) {
							%>
							<%=testAnalysisForm.getTestAnalysisFormNumber()%>
							<%
								}
							%>
						
						<p>

							<input type="hidden" name="formNumber"		id="sericeRequestFormNumber"		<%if (sericeRequestForm != null) {%>		value="<%=sericeRequestForm.getSericeRequestFormNumber()%>"
								<%} else if (testAnalysisForm != null) {%>
								value="<%=testAnalysisForm.getTestAnalysisFormNumber()%>" <%}%>
								readonly>
					</td>
				</tr>
				<tr>
					<td>ประเภทบริการ</td>
					<td>
						<p><%=type_service%></p> <input type="hidden" name="type_service"
						id="type_service" value="<%=type_service%>">
					</td>
				</tr>
				<tr>
					<td>ชื่อ ผู้ชำระ</td>
					<td><input type="text" name="receipts_on_behalf"
						class="form-control" id="receipts_on_behalf"
						<%if (sericeRequestForm != null) {%>
						<%if (sericeRequestForm.getMember().getMember_ID() != null) {%>
						value="<%=sericeRequestForm.getMember().getMember_Prefix() + " " + sericeRequestForm.getMember().getMember_FirstName()
		+ "  " + sericeRequestForm.getMember().getMember_LastName()%>"
						<%} else if (sericeRequestForm.getStudent().getStudentID() != null) {%>
						value="<%=sericeRequestForm.getStudent().getNameTh() + " " + sericeRequestForm.getStudent().getSurnameTh()%>"
						<%}%> <%} else if (testAnalysisForm != null) {%>
						value="<%=testAnalysisForm.getMember().getMember_Prefix() + " " + testAnalysisForm.getMember().getMember_FirstName()
		+ "  " + testAnalysisForm.getMember().getMember_LastName()%>">
						<%
							}
						%> ></td>
				</tr>
				<tr>
					<td>จ่ายใช้จ่าย</td>
					<td>
						<%
							if (sericeRequestForm != null) {
						%>
						<table class="table table-bordered table-responsive-sm ">
							<%
								if (sericeRequestForm != null) {
								if (sericeRequestForm.getSericeRequestFormNumber().contains("E")) {
									for (int n = 0; n < sericeRequestForm.getListSchedule().size(); n++) {
							%>
							<tr>
								<td><%=sericeRequestForm.getListSchedule().get(n).getSciInstruments().getSciInstruments_Name()%></td>
								<td>
									<%
										double price = 0.0;
									price = sericeRequestForm.getListSchedule().get(n).getMaintenancefee();
									totalprice = totalprice + price;
									%> <%=price + "  บาท"%>
								</td>
							</tr>
							<%
								}
							} else if (sericeRequestForm.getSericeRequestFormNumber().contains("A")) {
							for (int n = 0; n < sericeRequestForm.getListrentalschedule().size(); n++) {
							%>
							<tr>
								<td><%=sericeRequestForm.getListrentalschedule().get(n).getSciInstruments().getSciInstruments_Name()%></td>
								<td>
									<%
										double price = 0.0;
									price =sericeRequestForm.getListrentalschedule().get(n).getMaintenancefee();
									totalprice = totalprice + price;
									%> <%=price + "  บาท"%>
								</td>
							</tr>
							<%
								}
							%>
							<%
								}
							}
							%>
							<tr>
								<td>รวม</td>
								<td><%=totalprice + "  บาท"%></td>
							</tr>
						</table> <%
 	}
 %> <input type="hidden" name="totalprice" id="totalprice"
						value="<%=totalprice%>">
					</td>
				</tr>
				<%
					if (type_service.equals("บริการเครื่องมือวิทยาศาสตร์เเบบเหมา")) {
				%>
				<tr>
					<td>ค่าใช้จ่ายเพิ่มเติม</td>
					<td></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td>วิธีชำระ</td>
					<td>
						<div class="form-group row">
							<div class="col-sm-12">
								<input type="radio" name="payment_type" id="payment_type"
									onclick="return checkpayment_type()" required
									value="ชำระเงินสด">&nbsp;&nbsp;ชำระเงินสด (ติดต่อที่
								งานคลังและพัสดุ คณะวิทยาศาสตร์ ชั้น 1 อาคารจุฬาภรณ์ในวัน –
								เวลาราชการ) <br>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-12">
								<input type="radio" name="payment_type" id="payment_type"
									onclick="return checkpayment_type()" required
									value="ชำระเงินผ่านบัญชี">&nbsp;&nbsp;
								ชำระเงินผ่านบัญชีธนาคารกรุงเทพ
								สาขามหาวิทยาลัยแม่โจ้ชื่อบัญชีมหาวิทยาลัยแม่โจ้เลขที่บัญชี678
								–004800 – 2 <br>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-7">
								<input type="radio" name="payment_type" id="payment_type"
									onclick="return checkpayment_type()"
									value="เบิกจ่ายผ่านหน่วยงาน">&nbsp;&nbsp;
								เบิกจ่ายผ่านหน่วยงาน(เฉพาะหน่วยงานภายในมหาวิทยาลัยแม่โจ้)
								โปรดระบุ
							</div>
							<div class="col-sm-5">
								<input type="text" name="Agency" id="Agency" required disabled
									class="form-control">
							</div>
						</div>
					</td>

				</tr>
				<tr>
					<td>แนบไฟล์รูป</td>
					<td>
						<p>
							<img id="output" width="200" />
						</p> <input type="file" name="photo" id="photo"
						onchange="loadFile(event)" required
						accept=" image/jpeg, image/png">
					</td>
				</tr>
			</table>

			<input type="submit" name="submit" id="submit"
				class="btn btn-success" value="ส่งหลักฐาน">
		</form>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	<script>
		var loadFile = function(event) {
			var image = document.getElementById('output');
			image.src = URL.createObjectURL(event.target.files[0]);
		};
	</script>
</body>
</html>