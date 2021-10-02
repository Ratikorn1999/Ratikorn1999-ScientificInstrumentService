package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Personnel;

import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;
import Manager.AddTestItemManager;
import Manager.ViewTestItemManager;

@Controller
public class AddTestItemController {

	@RequestMapping(value = "/loadAddTestItemPage", method = RequestMethod.GET)
	public String loadAddTestItemPage(HttpSession session, HttpServletRequest request) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			return "AddTestItemPage";
		} else {
			return "LoginPage";
		}
	}

	@RequestMapping(value = "/do_AddTestItem", method = RequestMethod.POST)
	public String do_AddTestItem(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		String testitem_ID = request.getParameter("testitem_ID");
		String testitem_Name = request.getParameter("testitem_Name");
		String experiment = null;
		String min_qty_samples = null;
		Double priceType_A = Double.parseDouble(request.getParameter("priceType_A"));
		Double priceType_B = Double.parseDouble(request.getParameter("priceType_B"));
		Double priceType_C = Double.parseDouble(request.getParameter("priceType_C"));
		Double priceType_D = Double.parseDouble(request.getParameter("priceType_D"));
		Double nextprice = 0.0;
		String typetestitem_ID = request.getParameter("typetestitem_ID");
		try {
			experiment = request.getParameter("experiment");
		}catch (Exception e) {}
		try {
			min_qty_samples = request.getParameter("min_qty_samples");
		}catch (Exception e) {}
		try {
			nextprice = Double.parseDouble(request.getParameter("nextprice"));
		}catch (Exception e) {}
		
		
		
		TypeTestItem typetestitem = new TypeTestItem();
		typetestitem.setTypetestitem_ID(typetestitem_ID);

		TestItem testItem = new TestItem(testitem_ID, testitem_Name, experiment, min_qty_samples, priceType_A,
				priceType_B, priceType_C, priceType_D, nextprice);
		testItem.setTypetestitem(typetestitem);

		AddTestItemManager atim = new AddTestItemManager();
		boolean statusresult = atim.isAddTestItem(testItem);

		if (statusresult) {
			ViewTestItemManager vtim = new ViewTestItemManager();
			List<TestItem> listtestitem = vtim.getListTestItem() ;
		    request.setAttribute("listtestitem", listtestitem);
			return "ViewTestItemPage";
			
		} else {
			return "AddTestItemPage";
		}

	}

}
