<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.*,Manager.*,Class_End_Project.*"%>
    <% 
    Member member =null; 
    try {
    	member =(Member) session.getAttribute("member");
    } catch (Exception e) {
    	e.printStackTrace();
    } 
    
    

    /*------------------- Create Date -------------------*/
    Calendar today = Calendar.getInstance();
    String day = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
    if (day.length() < 2) {
    	day = "0" + day;
    }
    String month = String.valueOf(today.get(Calendar.MONTH) + 1);
    if (month.length() < 2) {
    	month = "0" + month;
    }
    int year = (today.get(Calendar.YEAR)) - 543;
    String date =   day+ " /" + month+ " /" + year ;
  
    
    /*------------------- End  Create Date -------------------*/
    
    %>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>Print The Service Fee Page</title>
</head>
<body>


<div class="container" style="margin-top: 5%;">


 <div class="row">
    <div class="col" align="center">
    <img src="./img/logosci.png" width="150" height="150">
    </div>
    <div class="col-6" align="center">
      <h4>แบบสรุปค่าบำรุงการใช้บริการเครื่องมือวิทยาศาสตร์</h4>
      <h5>ศูนย์บริการวิชาการด้านวิทยาศาสตร์และเทคโนโลยี</h5>
      <h5>คณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ</h5>
    </div>
    <div class="col">
     <table class="table table-bordered">
     <tr>
     <td>
     <u>สำหรับเจ้าหน้าที่ของศูนย์บริการวิชาการฯ</u>
   		<br> ใบคำขอเลขที่ .....................................
		<br> ประเภท...............................................
		<br> วันที่รับ ..............................................
     </td>
     </tr>
     </table>
    </div>
  </div>
<center>ชื่อผู้ขอใช้บริการ  ..............<%=member.getMember_Prefix() + " " + member.getMember_FirstName() + "....." + member.getMember_LastName()%>......... กำหนดชำระค่าบำรุงภายในวันที่.........................................</center>
<table class="table table-bordered">
<thead align="center">
<tr>
<th rowspan="2"> ข้อที่** </th>
<th rowspan="2"> ชื่อเครื่องมือวิทยาศาสตร์  </th>
<th colspan="2"> วัน เวลา ที่ขอใช้ </th>
<th rowspan="2"> ระยะเวลาใช้งาน<br> (ชั่วโมง)** </th>
<th colspan="2"> ค่าบำรุงการใช้เครื่องมือ (บาท)**  </th>
<th rowspan="2"> ส่วนลด<br>(บาท)**</th>
<th rowspan="2"> ค่าใช้จ่ายเพิ่มเติม**<br>(บาท)** </th>
</tr>
<tr>
<th>วันที่</th>
<th>เวลา</th>
<th>ค่าบำรุงต่อชั่วโมง</th>
<th>รวม</th>
</tr>
</thead>


<tbody>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>

<tfoot>
<tr>
<td colspan="6" align="right"> รวมเงิน / ยอดยกไป (บาท) </td>
<th style="background-color:gray;"></th>
<th></th>
<th></th>
</tr>
</tfoot>
</table>

<br><br>

<table class="table table-bordered"> 
<tr>
<td colspan="2">
สรุปค่าบำรุงการใช้งานเครื่องมือ ดังนี้ 
 <div class="row">
    <div class="col">

    </div>
    <div class="col-8">
     	<br>ค่าบำรุงการใช้บริการเครื่องมือวิทยาศาสตร์ จำนวน..........รายการ ................................................ บาท
		<br>ค่าใช้จ่ายเพิ่มเติม จำนวน..........รายการ ................................................ บาท
		<br>รวมค่าใช้จ่าย (ตัวอักษร.....................................................................................) ................................................ บาท
 		
 		<div class="row">
  		<div class="col"></div>
  		<div class="col"></div>
 		<div class="col2"><br>.......................................................................เจ้าหน้าที่ห้องปฏิบัติการ
 		<br>(.......................................................................) .........../................/...........</div>
  		
		</div>
 		
    </div>
    <div class="col">
    </div>
  </div>

</td>
</tr>

<tr>
<td colspan="2">
<u>สำหรับผู้ขอใช้บริการ </u> <br>

	ได้ตรวจสอบรายการถูกต้องแล้ว  <br>
		ลงชื่อ.............................................................ผู้ขอใช้บริการ  <br>
 ( <%=member.getMember_Prefix() + " " + member.getMember_FirstName() + "  " + member.getMember_LastName()%> ) <%= date %>
</td>
</tr>

<tr>
<td>
<u>ความเห็นของหัวหน้างานบริการวิชาการและวิจัย</u>
<br><input type="radio" name="comment1"> ได้ตรวจสอบรายการถูกต้องแล้ว
<br><input type="radio" name="comment1"> <input type="text" name="textcomment1" disabled>
<br>....................................................หัวหน้างานบริการวิชาการและวิจัย
<br>(.....................................................) .........../................/............
</td>

<td>
<u>ความเห็นของรองคณบดีฯ ฝ่ายวิจัยและบริการวิชาการ หรือผู้ที่ได้รับมอบหมาย</u>
<br><input type="radio" name="comment2"> ได้ตรวจสอบรายการถูกต้องแล้ว
<br><input type="radio" name="comment2"> <input type="text" name="textcomment2" disabled>
<br>..................................................... รองคณบดีฯ ฝ่ายวิจัยและบริการวิชาการ หรือผู้ที่ได้รับมอบหมาย 
<br>(..................................................) ................./...................../.................
</td>
</tr>

<tr>
<td colspan="2">
<u> สำหรับงานคลังและพัสดุ   </u>
<br><input type="checkbox" name="check"> ได้รับค่าบำรุงการใช้บริการเรียบร้อยแล้ว ตามใบเสร็จรับเงิน เลขที่....................................... วันที่ .......................................................
<br><input type="checkbox" name="check"> ได้ตรวจสอบเอกสารการโอนเงินเรียบร้อยแล้ว (ชำระผ่านบัญชีธนาคาร เมื่อวันที่ ........................................... เวลา.......................น.)
	<br> ได้ตรวจสอบเอกสารเบิกจ่ายผ่านหน่วยงาน (เฉพาะมหาวิทยาลัยแม่โจ้) เลขที่....................................... วันที่ ........................................................
<br><input type="checkbox" name="check"> ได้ตรวจสอบเอกสารเบิกจ่ายผ่านหน่วยงาน (เฉพาะมหาวิทยาลัยแม่โจ้) เลขที่....................................... วันที่ ........................................................

<br>..................................................................ผู้รับเงิน/ผู้ตรวจสอบ
 <br>(.................................................................) ................./......................../.................
</td>
</tr>
</table>


<input name="print" type="submit" id="print" value="Print" onClick="window.print()"/>
</div>

</body>
</html>