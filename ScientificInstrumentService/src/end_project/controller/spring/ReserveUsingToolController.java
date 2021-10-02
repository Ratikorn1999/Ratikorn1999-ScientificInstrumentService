package end_project.controller.spring;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.Schedule;
import Class_End_Project.SciInstruments;
import Class_End_Project.TestAnalysisForm;
import LoginMJU.Student;
import Manager.ListAllRequestManager;
import Manager.ReserveUsingToolManager;

@Controller
public class ReserveUsingToolController {
	
	@RequestMapping(value = "/ReserveUsingToolPage", method = RequestMethod.GET)
	public String loadReserveUsingToolPage(HttpSession session, HttpServletRequest request) {

		Member member = (Member) session.getAttribute("member");
		if (member != null) {
			String key = request.getParameter("id");
			ReserveUsingToolManager rutm = new ReserveUsingToolManager();
			FlatFeeForm flatFeeForm = rutm.getServiceRequestFormDetailByID(key);
			request.setAttribute("flatFeeForm", flatFeeForm);
			
			return "ReserveUsingToolPage";
		} else {
			return "LoginPage";
		}
	}
	
	
	@RequestMapping(value = "/do_ReserveUsingTool", method = RequestMethod.POST)
	public String do_ReserveUsingTool(HttpSession session, HttpServletRequest request) {
		ReserveUsingToolManager rutm = new ReserveUsingToolManager();
		
		Member member = null;
		Student student = null;
		Personnel personnel = null;
		try {
			student = (Student) session.getAttribute("student");
		} catch (Exception e) {
		}
		try {
			member = (Member) session.getAttribute("member");
		} catch (Exception e) {
		}
		try {
			personnel = (Personnel) session.getAttribute("personnel");
		} catch (Exception e) {
		}

		if (member != null || student != null || personnel != null) {
			String sericeRequestFormNumber = request.getParameter("sericeRequestFormNumber");
			String sciInstruments_ID[] = request.getParameterValues("sciInstruments_ID");
			String[] date = request.getParameterValues("date");
			String[] starttime = request.getParameterValues("starttime");
			String[] endtime = request.getParameterValues("endtime");
			
			List<Schedule> listSchedule = new Vector<Schedule>();
			String schedule_ID = rutm.getnextscheduleID();
			for (int i = 0; i < sciInstruments_ID.length; i++) {
				System.out.println("*****************"+(i)+"*****************");
				String start = date[i] + " " + starttime[i] + ":00.0";
				String end = date[i] + " " + endtime[i] + ":00.0";
				Timestamp startDateTime = Timestamp.valueOf(start);
				Timestamp endDateTime = Timestamp.valueOf(end);
				
				
				schedule_ID = schedule_ID.substring(4);
				int num = Integer.parseInt(schedule_ID);
				num = num + 1;
				schedule_ID = "" + num;
				int countround = 7 - schedule_ID.length();
				String id = "";
				for (int k = 0; k < countround; k++) {
					id = id + "0";
				}
				schedule_ID = "SID" + id + num;
				
				SciInstruments sciInstruments = rutm.getSciInstruments(sciInstruments_ID[i]);
				
				long diff = endDateTime.getTime() - startDateTime.getTime();
				int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);

				double maintenancefee = 0.0;

				if (member != null) {
					if (member.getTypeMember().equals("ก")) {
						maintenancefee = hourDiff * sciInstruments.getPriceType_A();
					} else if (member.getTypeMember().equals("ข")) {
						maintenancefee = hourDiff * sciInstruments.getPriceType_B();
					} else if (member.getTypeMember().equals("ค")) {
						maintenancefee = hourDiff * sciInstruments.getPriceType_C();
					} else {
						maintenancefee = hourDiff * sciInstruments.getPriceType_D();
					}
				} else if (student != null) {
					if (student.getFacultyNameTh().equals("วิทยาศาสตร์")) {
						maintenancefee = hourDiff * sciInstruments.getPriceType_D();
					} else {
						maintenancefee = hourDiff * sciInstruments.getPriceType_C();
					}
				}
				
				Schedule schedule = new Schedule(schedule_ID, startDateTime, endDateTime);
				schedule.setSciInstruments(sciInstruments);
				schedule.setMaintenancefee(maintenancefee);
				schedule.setTime(String.valueOf(hourDiff));
				listSchedule.add(schedule);

			}
			
			
			boolean result = rutm.isReserveUsingTool(listSchedule, sericeRequestFormNumber);
			if (result) {
				
				ListAllRequestManager larm = new ListAllRequestManager();
				List<FlatFeeForm> listsericeRequestForm = larm.getListAllRequest(member.getMember_ID());
				List<TestAnalysisForm> listTestAnalysisForm = larm.getListAllRequest2(member.getMember_ID());
				request.setAttribute("listsericeRequestForm", listsericeRequestForm);
				request.setAttribute("listTestAnalysisForm", listTestAnalysisForm);

				return "ListAllRequestPage";
			} else {
				return "ReserveUsingToolPage";
			}
			
			
		} else {
			return "LoginPage";
		}
	}


}
