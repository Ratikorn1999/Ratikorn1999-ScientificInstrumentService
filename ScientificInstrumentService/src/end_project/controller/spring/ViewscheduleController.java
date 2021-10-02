package end_project.controller.spring;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.SciInstruments;
import Manager.ListLaboratoryInstrumentsManager;




@Controller
public class ViewscheduleController {

	@RequestMapping(value = "/ViewschedulePage", method = RequestMethod.GET)
	public String loadViewschedulePage(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String sciInstruments_ID = request.getParameter("sciInstruments_ID");
		ListLaboratoryInstrumentsManager llim = new ListLaboratoryInstrumentsManager();
		SciInstruments sciInstruments = llim.getViewschedule(sciInstruments_ID);
		
		request.setAttribute("sciInstruments", sciInstruments);
		return "ViewschedulePage";
		
	}
	
}
