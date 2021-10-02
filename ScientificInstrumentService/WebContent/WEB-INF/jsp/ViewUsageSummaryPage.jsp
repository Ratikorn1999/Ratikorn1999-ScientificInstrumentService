<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,java.text.*,java.sql.*"%>
<%
Personnel personnel = null;
ResultSet rs = null;
ResultSet rs2 = null;
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {}
try {
	rs = (ResultSet) request.getAttribute("rs");
} catch (Exception e) {}
try {
	rs2 = (ResultSet) request.getAttribute("rs2");
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

	<div class="container" style="margin-top: 35px;">
		<div>
		<form action="searchSummary" method="post">
		เริ่มต้น    	<input type="date"  id="startdate"  name="startdate" required="required" value="${startdate }" > 
		 - สิ้นสุด     <input type="date"  id="enddate"  name="enddate" required="required" value="${enddate }" >
		 <button type="submit" class="btn btn-success" >ค้นหา</button>
		</form>
		</div>
		
		<br>
		<div>
		รายการใช้เครื่องมือส่วนกลาง
		
		</div>
		
		<table class="table table-bordered">
		<thead  style="background-color: #FFCC66;">
		<tr align="center">
		<th>ชื่อเครื่องมือ</th>
		<th>จำนวนครั้ง</th>
		<th>จำนวนชั่วโมง</th>
		<th>จำนวนเงิน</th>
		</tr>
		</thead>
		<tbody>
		
	<%
	int totalsumuse = 0;
	int totalsumtime = 0 ;
	double totalsumprice = 0.0;
	
	int sumuse = 0;
	int sumtime = 0 ;
	double sumprice = 0.0;
	try{
		while (rs.next()) { %>
		<tr>
		<td > <%= rs.getString(1) %></td>
		<td align="center"><%= String.format("%,d",rs.getInt(2)) %></td>
		<td align="center"><%=	String.format("%,d", rs.getInt(3))  %></td>
		<td align="right"><%=   String.format("%,.2f",rs.getDouble(4))  %></td>
		</tr>
		<%	
		sumuse = sumuse + rs.getInt(2);
		sumtime = sumtime +  rs.getInt(3) ;
		sumprice = sumprice + rs.getDouble(4);
		} %>
		<% 
		
	}catch(Exception e){
		//e.printStackTrace();
		} %>
		</tbody>
		<tfoot>
		<tr  style="background-color:#FFCC99;">
		<td  >สรุป</td>
		<td align="center" ><%= String.format("%,d",sumuse)%></td>
		<td  align="center"><%= String.format("%,d",sumtime) %></td>
		<td  align="right"><%=String.format("%,.2f",sumprice)  %></td>
		</tr>
		</tfoot>
		</table>
<%
totalsumuse += sumuse;
totalsumtime += sumtime ;
totalsumprice += sumprice;
%>


		<div>
		รายการใช้เครื่องมือหลักสูตร
		</div>
		
		<table class="table table-bordered">
		<thead  class="table-info">
		<tr align="center">
		<th style="width:  25%;">ชื่อเครื่องมือ</th>
		<th style="width:  25%;">จำนวนครั้ง</th>
		<th style="width:  25%;">จำนวนชั่วโมง</th>
		<th style="width:  25%;">จำนวนเงิน</th>
	
		</tr>
		</thead>
		<tbody>
		<%
	sumuse = 0;
	sumtime = 0 ;
	sumprice = 0.0;
	String checkb = "";
	try{
		while (rs2.next()) { %>
		<% if(!checkb.equals(rs2.getString(5))){ %>
		<% checkb =  rs2.getString(5);%>
		<tr>
		<td colspan="4" align="center" style="background-color:#D3D3D3;"><%= rs2.getString(5) %></td>
		</tr>
		
		
		<% } %>
		<tr>
		<td> <%= rs2.getString(1) %></td>
		<td align="center"><%= String.format("%,d",rs2.getInt(2)) %></td>
		<td align="center"><%=	String.format("%,d", rs2.getInt(3))  %></td>
		<td align="right"><%= String.format("%,.2f",rs2.getDouble(4)) %></td>
		
		</tr>
		<%	
		sumuse = sumuse + rs2.getInt(2);
		sumtime = sumtime +  rs2.getInt(3) ;
		sumprice = sumprice + rs2.getDouble(4);
		} 
		rs2.close();
		}catch(Exception e){
		//e.printStackTrace();
		} %>
		</tbody>
		<tfoot>
		<tr  style="background-color:#ADD8E6;">
		<td >สรุป</td>
	<td  align="center"><%= String.format("%,d",sumuse)%></td>
		<td  align="center"><%= String.format("%,d",sumtime) %></td>
			<td  align="right"><%=String.format("%,.2f",sumprice)  %></td>
		</tr>
		</tfoot>
		</table>
	
	<%
totalsumuse += sumuse;
totalsumtime += sumtime ;
totalsumprice += sumprice;
%>
	
		<table class="table table-bordered">
		
		<tr class="table-info"  align="center">
		<th style="width:  25%;" >รวมทั้งหมด</th>
		<th style="width:  25%;" ><%=  String.format("%,d",totalsumuse) %></th>
		<th style="width:  25%;" ><%=  String.format("%,d", totalsumtime)%></th>
		<td style="width:  25%;" align="right"><b><%= String.format("%,.2f", totalsumprice)%></b></td>
		</tr>
		
		</table>
		
		
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>