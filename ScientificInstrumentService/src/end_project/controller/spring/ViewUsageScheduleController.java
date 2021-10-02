package end_project.controller.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.FlatFeeForm;
import Manager.ViewUsageScheduleManager;

@Controller
public class ViewUsageScheduleController {

	@RequestMapping(value = "/loadUsageschedule", method = RequestMethod.GET)
	public String loadUsageschedule(HttpServletRequest request) {
		String formnumber = request.getParameter("formnumber");
		FlatFeeForm flatFeeForm = new FlatFeeForm();
		
		ViewUsageScheduleManager vusm = new ViewUsageScheduleManager();
		flatFeeForm = vusm.getUsageschedule(formnumber);
		request.setAttribute("flatFeeForm", flatFeeForm); 
		
		return "ViewUsageSchedulePage";
	}
	
}
