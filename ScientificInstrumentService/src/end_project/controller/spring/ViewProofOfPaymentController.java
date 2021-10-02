package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Payment;
import Class_End_Project.Personnel;
import Manager.ViewProofOfPaymentManager;

@Controller
public class ViewProofOfPaymentController {

	@RequestMapping(value = "/do_getpayment", method = RequestMethod.GET)
	public String do_getpayment(HttpServletRequest request, HttpSession session) {
		Personnel personnel = (Personnel) session.getAttribute("personnel");
		if (personnel != null) {
			ViewProofOfPaymentManager vpopm = new ViewProofOfPaymentManager();
			List<Payment> listPayment = vpopm.getpayment();
			request.setAttribute("listPayment", listPayment);
			return "ViewProofOfPaymentPage";
		} else {
			return "LoginPage";
		}
	}
	
	

}
