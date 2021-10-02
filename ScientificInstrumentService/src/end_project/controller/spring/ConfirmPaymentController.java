package end_project.controller.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Class_End_Project.Payment;
import Manager.ConfirmPaymentManager;
import Manager.ViewProofOfPaymentManager;

@Controller
public class ConfirmPaymentController {

	@RequestMapping(value = "/doAddConfirmpayment", method = RequestMethod.GET)
	public String doAddConfirmpayment(HttpSession session, HttpServletRequest request) {

		int payment_ID = Integer.parseInt(request.getParameter("payment_ID"));
		ConfirmPaymentManager cpm = new ConfirmPaymentManager();
		boolean status = cpm.isConfirmpayment(payment_ID);

		if (status) {
			ViewProofOfPaymentManager vpopm = new ViewProofOfPaymentManager();
			List<Payment> listPayment = vpopm.getpayment();
			request.setAttribute("listPayment", listPayment);
			return "ViewProofOfPaymentPage";
		} else {
			return "";
		}
	}

}
