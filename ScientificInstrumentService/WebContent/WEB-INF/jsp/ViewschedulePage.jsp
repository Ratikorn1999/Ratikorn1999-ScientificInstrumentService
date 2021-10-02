<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,Manager.*,Class_End_Project.*,LoginMJU.*"%>
<%
	Member member = null;
Student student = null;
AuthLogin auths = null;
Personnel personnel = null;
boolean checkauth = false;
SciInstruments sciInstruments = null;

try {
	student = (Student) session.getAttribute("student");
} catch (Exception e) {
}
try {
	personnel = (Personnel) session.getAttribute("personnel");
} catch (Exception e) {
}
try {
	member = (Member) session.getAttribute("member");
} catch (Exception e) {
}

try {
	sciInstruments = (SciInstruments) request.getAttribute("sciInstruments");
} catch (Exception e) {
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
int year = today.get(Calendar.YEAR);
String date = year + "-" + month + "-" + day;
/*------------------- End  Create Date -------------------*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<link 	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css"	rel="stylesheet" />
<link href="https://unpkg.com/@fullcalendar/core@4.3.1/main.min.css"	rel="stylesheet" />
<link href="https://unpkg.com/@fullcalendar/daygrid@4.3.0/main.min.css"	rel="stylesheet" />
<link href="https://unpkg.com/@fullcalendar/timegrid@4.3.0/main.min.css"	rel="stylesheet" />
<link href="https://unpkg.com/@fullcalendar/list@4.3.0/main.min.css"	rel="stylesheet" />

<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/@fullcalendar/core@4.3.1/main.min.js"></script>
<script	src="https://unpkg.com/@fullcalendar/interaction@4.3.0/main.min.js"></script>
<script src="https://unpkg.com/@fullcalendar/daygrid@4.3.0/main.min.js"></script>
<script src="https://unpkg.com/@fullcalendar/timegrid@4.3.0/main.min.js"></script>
<script src="https://unpkg.com/@fullcalendar/list@4.3.0/main.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href='https://fonts.googleapis.com/css?family=Kanit'	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="./css/web_css.css">
<link href="./img/logosci.png" rel="icon">
<title>เว็บไซต์ขอใช้บริการและให้บริการเครื่องมือวิทยาศาสตร์</title>

</head>
<script type="text/javascript">
$(document).ready(function() {

    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: ['interaction', 'dayGrid', 'timeGrid', 'list'],
      defaultView: 'dayGridMonth',
      defaultDate: '<%=date%>',
     
      header: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },  
      navLinks: true, // can click day/week names to navigate views
      businessHours: {
    	  // days of week. an array of zero-based day of week integers (0=Sunday)
    	  daysOfWeek: [ 1, 2, 3, 4 , 5 ], // Monday - Thursday

    	  startTime: '08:00', // a start time (10am in this example)
    	  endTime: '18:00', // an end time (6pm in this example)
    	}, // display business hours
      editable: false,
      locale: 'th',
      selectable: true,
      selectHelper: true,
      
      
      
    events: [
    	<%for (int i = 0; i < sciInstruments.getLsitSchedule().size(); i++) {%>
      {
        
        title: 'ไม่ว่าง',
        description: 'มีการใช้งานเเล้ว',
        start: '<%=sciInstruments.getLsitSchedule().get(i).getStartDateTime()%>',
        end: '<%=sciInstruments.getLsitSchedule().get(i).getEndDateTime()%>',
        extendedProps: {
           // status: 'done'
          }
      }
      <%if (sciInstruments.getLsitSchedule().size() > 1) {%>
      ,
      <%}%>
      <%}%>
 
    ],
    eventRender: function(info) {
        if (info.event.extendedProps.status === 'done') {

          // Change background color of row
          info.el.style.backgroundColor = 'red';

          // Change color of dot marker
          var dotEl = info.el.getElementsByClassName('fc-event-dot')[0];
          if (dotEl) {
            dotEl.style.backgroundColor = 'white';
          }
        }
      
      }
});
    calendar.render();

    
    
});
</script>

<script type="text/javascript"></script>
<body>

	<jsp:include page="common/navbar.jsp"></jsp:include>

	<div class="container" style="margin-top: 35px;" align="center">
		<h3 >เครื่องมือ : <%= sciInstruments.getSciInstruments_Name() %>     มีการใช้งานเเล้ว <%= sciInstruments.getLsitSchedule().size() %> ครั้ง </h3>
		<div>
			<div id='calendar'></div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>