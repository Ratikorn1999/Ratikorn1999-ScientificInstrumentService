<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*"%>
<%
	Personnel personnel = null;

List<Payment> listPayment = new Vector<Payment>();
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	listPayment = (List<Payment>) request.getAttribute("listPayment");
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

	<div class="container" style="margin-top: 35px;">

		<h3>รายการชำระเงิน</h3>


		<hr class="colorgraph">

		<table class="table table-bordered   table-hover">
			<thead class="table-info">
				<tr>
					<th>เลขที่รายการ</th>
					<th>เลขที่แบบฟอร์ม</th>
					<th>วันที่ยืนยันการชำระ</th>
					<th>ชื่อผู้ชำระ</th>
					<th>ค่าใช้จ่าย</th>
					<th>สถานะ</th>
					<th>หลักฐานการชำระเงิน</th>
					<th>อนุมัติการชำระเงิน</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (listPayment.size() != 0) {
					String imgscr = "";
				%>
				<%
					for (int i = 0; i < listPayment.size(); i++) {
				%>
				<tr>
					<td><%=i + 1%></td>
					<td>
						<%
							if (listPayment.get(i).getSericeRequestForm().getSericeRequestFormNumber() != null) {
						%> <%
 	imgscr = listPayment.get(i).getSericeRequestForm().getSericeRequestFormNumber().substring(0,
 		listPayment.get(i).getSericeRequestForm().getSericeRequestFormNumber().indexOf("/"));
 %>
						<%=listPayment.get(i).getSericeRequestForm().getSericeRequestFormNumber()%>
						<%
							} else if (listPayment.get(i).getTestAnalysisForm().getTestAnalysisFormNumber() != null) {
						%> <%
 	imgscr = listPayment.get(i).getTestAnalysisForm().getTestAnalysisFormNumber().substring(0,
 		listPayment.get(i).getTestAnalysisForm().getTestAnalysisFormNumber().indexOf("/"));
 %>
						<%=listPayment.get(i).getTestAnalysisForm().getTestAnalysisFormNumber()%>
						<%
							}
						%> <%
 	out.print(imgscr);
 %>
					</td>
					<td>
						<%
							String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
								.format(listPayment.get(i).getPaymentDateTime());
						%> <%=date%></td>
					<td><%=listPayment.get(i).getReceipts_on_behalf()%></td>
					<td><%=listPayment.get(i).getTotalprice() + "  "%> บาท</td>
					<td>
						<p
							<%if (listPayment.get(i).getStatus().equals("Wait for Approval")) {%>
							style="color: red;" <%} else {%> style="color: green;" <%}%>>
							<%=listPayment.get(i).getStatus()%>
						</p>
					</td>
					<td><input type="button" name="seeevidence " id="seeevidence"
						class="btn btn-warning" value="ดูหลักฐาน"
						onclick="return btnshowpaymentclick(<%=listPayment.get(i).getPayment_ID()%>)">

						<div id="payment<%=listPayment.get(i).getPayment_ID()%>"
							class="modal">
							<!-- Modal content -->
							<div class="modal-content">
								<span class="close"
									onclick="return spanclick(<%=listPayment.get(i).getPayment_ID()%>)">&times;</span>

								<img alt="imgpayment" src="./payment/<%=imgscr%>.png"
									class="img">
							</div>
						</div></td>
					<td><a
						href="doAddConfirmpayment?payment_ID=<%=listPayment.get(i).getPayment_ID()%>">
							<input type="button" name="approve" id="approve"
							onclick="return confirm('คุณต้องการยืนยันหรือไม่ ');"
							value="ยืนยันการชำระเงิน"
							<%if (listPayment.get(i).getStatus().equals("Approval")) {%>
							disabled class="btn btn-secondary" <%} else {%>
							class="btn btn-info" <%}%>>

					</a></td>
				</tr>


				<%
					}
				%>
				<%
					} else {
				%>
				<tr>
					<td colspan="8" align="center" style="color: red;">
						ไม่มีข้อมูล</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	
	<script>
function btnshowpaymentclick(no) {
  var modal = document.getElementById("payment"+no);
  modal.style.display = "block";
  window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
}
}

function spanclick(no) {
	var modal = document.getElementById("payment"+no);
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