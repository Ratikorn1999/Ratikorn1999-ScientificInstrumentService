package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;

import Manager.ViewTestItemDetailsManager;


@Controller
public class ViewTestItemDetailsController {

	
	@RequestMapping(value = "/ViewTestItemDetailPage", method = RequestMethod.GET)
	public String loadViewTestItemDetailPage(HttpServletRequest request, HttpSession session) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			String testitem_ID = request.getParameter("testitem_ID");
			ViewTestItemDetailsManager vtim = new ViewTestItemDetailsManager();
			List<TypeTestItem> listTypeTestItem = vtim.getlistTypeTestItem() ;
			TestItem testItem = vtim.getTestItemByID(testitem_ID);
			request.setAttribute("listTypeTestItem", listTypeTestItem);
			request.setAttribute("testItem", testItem);
			return "ViewTestItemDetailPage";

		} else {
			return "LoginPage";
		}
	}

	
	
}
