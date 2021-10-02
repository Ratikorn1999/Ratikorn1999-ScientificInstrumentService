package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;
import Class_End_Project.TestItem;
import Manager.DeleteTestItemManger;
import Manager.ViewTestItemManager;

@Controller
public class DeleteTestItemController {

	@RequestMapping(value = "/do_DeleteTestItem", method = RequestMethod.GET)
	public String do_DeleteTestItem(HttpSession session, HttpServletRequest request) {

		Personnel personnel = (Personnel) session.getAttribute("personnel");
		String  testitem_ID =  request.getParameter("testitem_ID");
		if (personnel != null) {
			
			DeleteTestItemManger dtim = new DeleteTestItemManger();
			boolean status = dtim.isDeleteTestItem(testitem_ID);
			System.out.println("Status Delete is "+status);
			
			ViewTestItemManager vtim = new ViewTestItemManager();
			List<TestItem> listtestitem = vtim.getListTestItem() ;
		    request.setAttribute("listtestitem", listtestitem);
			
			
			return "ViewTestItemPage";
			
		} else {
			return "LoginPage";
		}
	}

}
