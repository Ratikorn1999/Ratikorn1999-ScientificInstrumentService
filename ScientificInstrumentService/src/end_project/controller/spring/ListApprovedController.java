package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Personnel;
import Manager.ListApprovedManager;


@Controller
public class ListApprovedController {

	
	@RequestMapping(value = "/ListApprovedPage", method = RequestMethod.GET)
	public String do_getinstrumentsstatus(HttpServletRequest request, HttpSession session) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			
			ListApprovedManager lam = new ListApprovedManager();
			List<FlatFeeForm> listFlatFeeForm = lam.getlistApproved();
			request.setAttribute("listFlatFeeForm", listFlatFeeForm); 	
			return "ListApprovedPage";
		} else {
			return "LoginPage";
		}
	}
	
}
