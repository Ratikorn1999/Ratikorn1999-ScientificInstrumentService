package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.SciInstruments;
import Manager.ListLaboratoryInstrumentsManager;

@Controller
public class ListLaboratoryInstrumentsController {

	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String loadRegisterpage(HttpSession session) {
		return "RegisterPage";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String loadLoginpage(HttpSession session) {
		session.removeAttribute("member");
		return "LoginPage";
	}

	@RequestMapping(value = "/loadListLaboratoryInstrumentsPage", method = RequestMethod.GET)
	public ModelAndView loadListLaboratoryInstrumentsPage(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("ListLaboratoryInstrumentsPage");
		ListLaboratoryInstrumentsManager llim = new ListLaboratoryInstrumentsManager();
		List<SciInstruments> listsciInstruments = llim.getListlaboratoryInstruments();
		
		mav.addObject("listsciInstruments",listsciInstruments);
		//request.setAttribute("listsciInstruments", listsciInstruments);
		
		
		return mav;
	}
	
	@RequestMapping(value = "/searchListlaboratoryInstruments", method = RequestMethod.POST)
	public ModelAndView searchListlaboratoryInstruments(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("ListLaboratoryInstrumentsPage");
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		ListLaboratoryInstrumentsManager llim = new ListLaboratoryInstrumentsManager();
		List<SciInstruments> listsciInstruments = llim.searchListlaboratoryInstruments(keyword);
		mav.addObject("listsciInstruments",listsciInstruments);
		request.setAttribute("keyword", keyword);
		return mav;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		return "index";
	}

	

	
}
