package end_project.controller.spring;



import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.SciInstruments;
import Manager.ChangeInstrumentsStatusManager;
import Manager.ViewInstrumentsDetailsManager;



@Controller
public class ChangeInstrumentsStatusController {

	@RequestMapping(value = "/do_updateinstrumentsstatus", method = RequestMethod.GET)
	public String do_updateinstrumentsstatus(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			String id =  request.getParameter("id");
			String status = request.getParameter("status");
			System.out.println(status);
			ChangeInstrumentsStatusManager cimsm = new ChangeInstrumentsStatusManager();
			boolean resultstatus = cimsm.isChangeInstrumentsstatus(id, status);
			
			ViewInstrumentsDetailsManager vimem = new ViewInstrumentsDetailsManager(); 
			SciInstruments sciInstruments = vimem.getInstrumentsByID(id) ;
			request.setAttribute("sciInstruments", sciInstruments);
			
			return "ViewInstrumentsDetailsPage";
		} else {
			return "LoginPage";
		}
	}
	
	
	
}
