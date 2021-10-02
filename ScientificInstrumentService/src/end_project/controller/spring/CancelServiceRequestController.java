package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.TestAnalysisForm;
import LoginMJU.Student;
import Manager.CancelServiceRequestManager;
import Manager.ListAllRequestManager;

@Controller
public class CancelServiceRequestController {

	@RequestMapping(value = "/do_Cancelservicerequest", method = RequestMethod.GET)
	public ModelAndView do_Cancelservicerequest(HttpSession session,HttpServletRequest request) {
		
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
		String typeform = request.getParameter("typeform");
		String formnumber = request.getParameter("formnumber");
		CancelServiceRequestManager csrm = new CancelServiceRequestManager();
		ListAllRequestManager larm = new ListAllRequestManager();
		List<FlatFeeForm> listsericeRequestForm = null;
		List<TestAnalysisForm> listTestAnalysisForm = null;
		ModelAndView mav = new ModelAndView("ListAllRequestPage");
		
		if(csrm.isCancelservicerequest(formnumber,typeform)) {
		mav.addObject("msg","ยกเลิกสำเร็จสำเร็จ !!!! ");
		}else {
		mav.addObject("msg","ยกเลิกไม่สำเร็จสำเร็จ !!!! ");
		}

		if (member != null) {
			listsericeRequestForm = larm.getListAllRequest(member.getMember_ID());
			listTestAnalysisForm = larm.getListAllRequest2(member.getMember_ID());
		}else if(student !=null) {
			listsericeRequestForm = larm.getListAllRequest(student.getStudentID());
			listTestAnalysisForm = larm.getListAllRequest2(student.getStudentID());
			mav.addObject("listsericeRequestForm", listsericeRequestForm);
			mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
			return mav;
		}else if(personnel !=null) {
			listsericeRequestForm = larm.getListAllRequest(personnel.getPersonnel_ID());
			listTestAnalysisForm = larm.getListAllRequest2(personnel.getPersonnel_ID());
			mav.addObject("listsericeRequestForm", listsericeRequestForm);
			mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
		}
		mav.addObject("listsericeRequestForm", listsericeRequestForm);
		mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
		return mav;
		
		}else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}
	}
	
	
	
	

}
