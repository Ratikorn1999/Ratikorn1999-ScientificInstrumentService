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
import Manager.UpdateStatusManager;

@Controller
public class UpdateStatusController {

	@RequestMapping(value = "/do_UpdateStatus", method = RequestMethod.GET)
	public String do_UpdateStatus(HttpSession session, HttpServletRequest request) {

		String testAnalysisFormNumber = request.getParameter("testAnalysisFormNumber");
		String testAnalysisstatus = request.getParameter("testAnalysisstatus");

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {

			UpdateStatusManager usm = new UpdateStatusManager();
			boolean status = usm.isUpdateStatus(testAnalysisFormNumber, testAnalysisstatus);

			ListTestingAnalysisFormManager ltafm = new ListTestingAnalysisFormManager();
			List<TestAnalysisForm> listTestAnalysisForm = ltafm.getListTestAnalysisForm();
			request.setAttribute("listTestAnalysisForm", listTestAnalysisForm);
			return "ListTestingAnalysisFormPage";
		} else {
			return "LoginPage";
		}

	}

}
