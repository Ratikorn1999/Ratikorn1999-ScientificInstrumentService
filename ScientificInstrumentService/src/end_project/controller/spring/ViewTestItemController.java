package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.TestItem;
import Manager.ViewTestItemManager;

@Controller
public class ViewTestItemController {

	
	@RequestMapping(value = "/do_ViewTestItem", method = RequestMethod.GET)
	public String loadViewTestItemPage(HttpSession session,HttpServletRequest request) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ViewTestItemManager vtim = new ViewTestItemManager();
			List<TestItem> listtestitem = vtim.getListTestItem() ;
			
		    request.setAttribute("listtestitem", listtestitem);
		return "ViewTestItemPage";
		} else {
			return "LoginPage";
		}
	}
	
}
