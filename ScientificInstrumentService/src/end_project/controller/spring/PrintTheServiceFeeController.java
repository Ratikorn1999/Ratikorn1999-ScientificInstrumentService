package end_project.controller.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Member;



@Controller
public class PrintTheServiceFeeController {

	@RequestMapping(value = "/do_getServiceFee", method = RequestMethod.GET)
	public String do_getServiceFee(HttpSession session,HttpServletRequest request) {
		
		Member member = (Member) session.getAttribute("member");
		if (member != null) {
			return "PrintTheServiceFeePage";
		} else {
			return "LoginPage";
		}
	}
	
}
