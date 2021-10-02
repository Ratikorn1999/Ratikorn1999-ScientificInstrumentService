package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.SciInstruments;
import Manager.ListLaboratoryInstrumentsManager;
import Manager.ListSciInstrumentsManager;

@Controller
public class ListSciInstrumentsController {

	@RequestMapping(value = "/ListSciInstrumentsPage", method = RequestMethod.GET)
	public String do_getinstrumentsstatus(HttpServletRequest request, HttpSession session) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {

			ListSciInstrumentsManager vism = new ListSciInstrumentsManager();
			List<SciInstruments> listsciInstruments = vism.getListSciInstruments();
			request.setAttribute("listsciInstruments", listsciInstruments);
			return "ListSciInstrumentsPage";

		} else {
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "/searchListlaboratoryInstruments2", method = RequestMethod.POST)
	public String searchListlaboratoryInstruments2(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		ListLaboratoryInstrumentsManager llim = new ListLaboratoryInstrumentsManager();
		List<SciInstruments> listsciInstruments = llim.searchListlaboratoryInstruments(keyword);
		request.setAttribute("listsciInstruments", listsciInstruments);
		request.setAttribute("keyword", keyword);
		return "ListSciInstrumentsPage";
	}

}
