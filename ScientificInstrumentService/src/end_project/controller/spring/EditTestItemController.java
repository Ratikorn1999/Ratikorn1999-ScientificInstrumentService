package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;
import Manager.EditTestItemManager;
import Manager.ViewTestItemDetailsManager;

@Controller
public class EditTestItemController {

	@RequestMapping(value = "/do_EditTestItem", method = RequestMethod.POST)
	public String do_EditTestItem(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		String key =(String) session.getAttribute("key");
		String testitem_ID = request.getParameter("testitem_ID");
		String testitem_Name = request.getParameter("testitem_Name");
		String experiment = null;
		String min_qty_samples = null;
		Double priceType_A = Double.parseDouble(request.getParameter("priceType_A"));
		Double priceType_B = Double.parseDouble(request.getParameter("priceType_B"));
		Double priceType_C = Double.parseDouble(request.getParameter("priceType_C"));
		Double priceType_D = Double.parseDouble(request.getParameter("priceType_D"));
		String typetestitem_ID = request.getParameter("typetestitem_ID");
		Double nextprice = 0.0;
		
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

		EditTestItemManager etim = new EditTestItemManager();
		boolean statusresult = etim.isEditTestItem(key,testItem);
		System.out.println(statusresult);
		
		
		ViewTestItemDetailsManager vtim = new ViewTestItemDetailsManager();
		List<TypeTestItem> listTypeTestItem = vtim.getlistTypeTestItem() ;
		
		TestItem testItem2 = vtim.getTestItemByID(testitem_ID);
		request.setAttribute("listTypeTestItem", listTypeTestItem);
		request.setAttribute("testItem", testItem2);
		session.removeAttribute("key");
		return "ViewTestItemDetailPage";
		
		

	}

}
