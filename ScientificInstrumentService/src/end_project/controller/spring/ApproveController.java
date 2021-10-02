package end_project.controller.spring;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.ChangeForm;
import Class_End_Project.FlatFeeForm;
import Class_End_Project.Personnel;
import Class_End_Project.TestAnalysisForm;
import Manager.ApproveManager;
import Manager.ListTestingAnalysisFormManager;
import Manager.ViewChangeRequestFormDetailManager;
import Manager.ViewServiceRequestManager;

@Controller
public class ApproveController {

	@RequestMapping(value = "/doApproverequet", method = RequestMethod.GET)
	public ModelAndView doApproverequet(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			request.setCharacterEncoding("UTF-8");
			int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
			String status = request.getParameter("status");
			int approval_no = Integer.parseInt(request.getParameter("approval_no"));
			String formnumber = request.getParameter("formnumber");
			ApproveManager arm = new ApproveManager();

			boolean result = false;
			boolean result2 = false;
			if (approval_no == 5 && status.equals("อนุมัติ")) {
				result = arm.isApproverequest(approval_ID, status);
				result2 = arm.isChangedStatus(formnumber, status);
			} else if (status.equals("อนุมัติ")) {
				result = arm.isApproverequest(approval_ID, status);
			} else {
				result = arm.isApproverequest(approval_ID, status);
				result2 = arm.isChangedStatus(formnumber, status);
			}
			System.out.println(result);
			System.out.println(result2);

			FlatFeeForm flatFeeForm = new FlatFeeForm();
			ViewServiceRequestManager vsrm = new ViewServiceRequestManager();
			flatFeeForm = vsrm.getServiceRequestFormDetailByID(formnumber);
			request.setAttribute("flatFeeForm", flatFeeForm);
			ModelAndView mav = new ModelAndView("ServiceRequestFormDetail");		
			mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}
	}

	@RequestMapping(value = "/doApproveTestAnalysis", method = RequestMethod.GET)
	public ModelAndView doApproveTestAnalysis(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			request.setCharacterEncoding("UTF-8");
			int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
			String status = request.getParameter("status");
			int approval_no = Integer.parseInt(request.getParameter("approval_no"));
			String formnumber = request.getParameter("formnumber");

			ApproveManager arm = new ApproveManager();

			boolean result = false;
			boolean result2 = false;
			if (approval_no == 5 && status.equals("อนุมัติ")) {
				result = arm.isApproveTestAnalysis(approval_ID, status);
				result2 = arm.isChangedStatusTestAnalysisForm(formnumber, status);
			} else if (status.equals("อนุมัติ")) {
				result = arm.isApproveTestAnalysis(approval_ID, status);
			} else {
				result = arm.isApproveTestAnalysis(approval_ID, status);
				result2 = arm.isChangedStatusTestAnalysisForm(formnumber, status);
			}

			ListTestingAnalysisFormManager ltafm = new ListTestingAnalysisFormManager();
			TestAnalysisForm testAnalysisForm = ltafm.getTestAnalysisForm(formnumber);
			request.setAttribute("testAnalysisForm", testAnalysisForm);
			ModelAndView mav = new ModelAndView("TestAnalysisFormDetail");		
			mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}
	}

	@RequestMapping(value = "/doApproveChangeForm", method = RequestMethod.GET)
	public String doApproveChangeForm(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {

			request.setCharacterEncoding("UTF-8");
			int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
			String status = request.getParameter("status");
			int approval_no = Integer.parseInt(request.getParameter("approval_no"));
			String formnumber = request.getParameter("formnumber");

			ApproveManager arm = new ApproveManager();

			boolean result = false;
			boolean result2 = false;
			if (approval_no == 5 && status.equals("อนุมัติ")) {
				result = arm.isApproveChangeForm(approval_ID, status);
				result2 = arm.isChangedStatusChangeForm(formnumber, status);
			} else if (status.equals("อนุมัติ")) {
				result = arm.isApproveChangeForm(approval_ID, status);
			} else {
				result = arm.isApproveChangeForm(approval_ID, status);
				result2 = arm.isChangedStatusChangeForm(formnumber, status);
			}
			System.out.println(result);
			System.out.println(result2);

			ViewChangeRequestFormDetailManager vcrfdm = new ViewChangeRequestFormDetailManager();
			ChangeForm cf = vcrfdm.getChangeRequestFormByid(formnumber);
			request.setAttribute("cf", cf);

			return "ViewChangeRequestFormDetailPage";
		} else {
			return "LoginPage";
		}
	}

	@RequestMapping(value = "/doApproverequetByAdvisor", method = RequestMethod.GET)
	public String doApproverequetByAdvisor(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
		String status = "อนุมัติ";
		ApproveManager arm = new ApproveManager();
		arm.isApproverequest(approval_ID, status);

		return "ApproveForAdvisorPage";

	}

	@RequestMapping(value = "/doNotApproverequetByAdvisor", method = RequestMethod.GET)
	public String doNotApproverequetByAdvisor(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
		String status = "ไม่อนุมัติ";
		String formnumber = request.getParameter("formnumber");
		ApproveManager arm = new ApproveManager();
		arm.isApproverequest(approval_ID, status);
		arm.isChangedStatus(formnumber, status);

		return "ApproveForAdvisorPage";

	}

	@RequestMapping(value = "/doApproveTestAnalysisFormByAdvisor", method = RequestMethod.GET)
	public String doApproveTestAnalysisFormByAdvisor(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
		String status = "อนุมัติ";
		ApproveManager arm = new ApproveManager();
		arm.isApproveTestAnalysis(approval_ID, status);
		return "ApproveForAdvisorPage";

	}

	@RequestMapping(value = "/doNotApproveTestAnalysisFormrByAdvisor", method = RequestMethod.GET)
	public String doNotApproveTestAnalysisFormrByAdvisor(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
		String status = "ไม่อนุมัติ";
		String formnumber = request.getParameter("formnumber");
		ApproveManager arm = new ApproveManager();

		arm.isApproveTestAnalysis(approval_ID, status);
		arm.isChangedStatusTestAnalysisForm(formnumber, status);

		return "ApproveForAdvisorPage";

	}

	@RequestMapping(value = "/doApproverequetByAdvisorChange", method = RequestMethod.GET)
	public String doApproverequetByAdvisorChange(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
		String status = "อนุมัติ";
		ApproveManager arm = new ApproveManager();
		arm.isApproveChangeForm(approval_ID, status);

		return "ApproveForAdvisorPage";

	}

	@RequestMapping(value = "/doNotApproverequetByAdvisorChange", method = RequestMethod.GET)
	public String doNotApproverequetByAdvisorChange(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		int approval_ID = Integer.parseInt(request.getParameter("approval_ID"));
		String status = "ไม่อนุมัติ";
		String formnumber = request.getParameter("formnumber");
		ApproveManager arm = new ApproveManager();
		arm.isApproveChangeForm(approval_ID, status);
		arm.isChangedStatusChangeForm(formnumber, status);

		return "ApproveForAdvisorPage";

	}
	
}
