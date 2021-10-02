package end_project.controller.spring;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.ChangeForm;
import Class_End_Project.Personnel;
import Manager.ViewChangeRequestFormDetailManager;


@Controller
public class ViewChangeRequestFormDetailController {

	@RequestMapping(value = "/ViewChangeRequestFormDetail", method = RequestMethod.GET)
	public String ViewChangeRequestFormDetail(HttpSession session, HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			String changeFormNumber = request.getParameter("changeFormNumber");
			ViewChangeRequestFormDetailManager vcrfdm = new ViewChangeRequestFormDetailManager();
			ChangeForm cf = vcrfdm.getChangeRequestFormByid(changeFormNumber);
			request.setAttribute("cf", cf);
			return "ViewChangeRequestFormDetailPage";
		} else {
			return "LoginPage";
		}
	}
	
}
