package end_project.controller.spring;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Manager.ViewIncomeSummaryManager;

@Controller
public class ViewIncomeSummaryController {

	@RequestMapping(value = "/do_getincomesummary", method = RequestMethod.GET)
	public String do_getincomesummary(HttpSession session, HttpServletRequest request) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ViewIncomeSummaryManager vism = new ViewIncomeSummaryManager();
			double servicerequestformincome = vism.getIncomeSummary("servicerequestform") ;
			double testanalysisformincome= vism.getIncomeSummary("testanalysisform") ;
			 request.setAttribute("servicerequestformincome", servicerequestformincome);
			 request.setAttribute("testanalysisformincome", testanalysisformincome);
			return "ViewIncomeSummaryPage";
		} else {
			return "LoginPage";
		}

	}

}
