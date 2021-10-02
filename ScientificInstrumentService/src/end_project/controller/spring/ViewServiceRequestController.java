package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Personnel;
import Class_End_Project.SericeRequestForm;

import Manager.ViewServiceRequestManager;

@Controller
public class ViewServiceRequestController {
	
	@RequestMapping(value = "/loadServicerequest", method = RequestMethod.GET)
	public String loadServicerequest(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ViewServiceRequestManager vsrm = new ViewServiceRequestManager();
			List<SericeRequestForm> listsericeRequestForm = vsrm.getListSericeRequestForm();
			request.setAttribute("listsericeRequestForm", listsericeRequestForm);
			return "ViewServiceRequestPage";
		} else {
			return "LoginPage";
		}
	}
	
	
	@RequestMapping(value = "/loadServiceRequestFormDetail", method = RequestMethod.GET)
	public ModelAndView loadServiceRequestFormDetail(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
	
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ModelAndView mav = new ModelAndView("ServiceRequestFormDetail");
			String formnumber = request.getParameter("formnumber");
			FlatFeeForm flatFeeForm = new FlatFeeForm();
			
			ViewServiceRequestManager vsrm = new ViewServiceRequestManager();
			flatFeeForm = vsrm.getServiceRequestFormDetailByID(formnumber);
			request.setAttribute("flatFeeForm", flatFeeForm); 
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}
	}

}
