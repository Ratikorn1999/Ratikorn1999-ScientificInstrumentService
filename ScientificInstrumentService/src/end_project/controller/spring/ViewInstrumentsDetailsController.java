package end_project.controller.spring;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.SciInstruments;
import Manager.ViewInstrumentsDetailsManager;

@Controller
public class ViewInstrumentsDetailsController {

	@RequestMapping(value = "/do_getinstrumentsdetails", method = RequestMethod.GET)
	public String do_getinstrumentsdetails(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			
			String sciInstrumentsid = request.getParameter("sciInstrumentsid");
			ViewInstrumentsDetailsManager vimem = new ViewInstrumentsDetailsManager(); 
			SciInstruments sciInstruments = vimem.getInstrumentsByID(sciInstrumentsid) ;
			request.setAttribute("sciInstruments", sciInstruments);

			return "ViewInstrumentsDetailsPage";
		} else {
			return "LoginPage";
		}
	}
	
}
