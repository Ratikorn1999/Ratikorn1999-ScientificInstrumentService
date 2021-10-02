package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.ChangeForm;
import Class_End_Project.Personnel;
import Manager.ViewChangeFormManager;

@Controller
public class ViewChangeFormController {

	@RequestMapping(value = "/loadChangRequestForm", method = RequestMethod.GET)
	public String loadChangRequestForm(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ViewChangeFormManager vcfm = new ViewChangeFormManager();
			List<ChangeForm> listChangeForm = vcfm.getChangeRequestForm();
			request.setAttribute("listChangeForm", listChangeForm);
			return "ViewChangeFormPage";
		} else {
			return "LoginPage";
		}
	}

}
