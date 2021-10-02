package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.TestAnalysisForm;
import Manager.ListTestingAnalysisFormManager;

@Controller
public class ListTestingAnalysisFormController {

	@RequestMapping(value = "/do_getListTestAnalysisForm", method = RequestMethod.GET)
	public String do_getListTestAnalysisForm(HttpSession session, HttpServletRequest request) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			
			ListTestingAnalysisFormManager ltafm = new ListTestingAnalysisFormManager();
			List<TestAnalysisForm> listTestAnalysisForm = ltafm.getListTestAnalysisForm();
			request.setAttribute("listTestAnalysisForm", listTestAnalysisForm);
			return "ListTestingAnalysisFormPage";
		} else {
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/testanalysisformdetail", method = RequestMethod.GET)
	public String loadtestanalysisformdetailasdas(HttpSession session, HttpServletRequest request) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {	
			ListTestingAnalysisFormManager ltafm = new ListTestingAnalysisFormManager();
				String testAnalysisFormnumber= request.getParameter("testAnalysisFormnumber");
			  TestAnalysisForm testAnalysisForm = ltafm.getTestAnalysisForm(testAnalysisFormnumber);
			  request.setAttribute("testAnalysisForm", testAnalysisForm);
			 
			return "TestAnalysisFormDetail";
		} else {
			return "LoginPage";
		}
	}

	

}
