package end_project.controller.spring;

import java.io.File;
import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.FlatFeeForm;
import Class_End_Project.Payment;
import Class_End_Project.TestAnalysisForm;
import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;
import Manager.SubmitProofOfPaymentManager;
import Manager.ViewServiceRequestManager;
import Manager.ViewTestItemDetailsManager;

@Controller
public class SubmitProofOfPaymentController {

	
	@RequestMapping(value = "/SubmitProofOfPaymentPage", method = RequestMethod.GET)
	public String loadSubmitProofOfPaymentPage(HttpServletRequest request, HttpSession session) {
		String typeform = request.getParameter("typeform");
		String formnumber = request.getParameter("formnumber");

		SubmitProofOfPaymentManager spopm = new SubmitProofOfPaymentManager();
		
		FlatFeeForm sericeRequestForm  =  null;
		TestAnalysisForm testAnalysisForm = null;
		if(formnumber.contains("T")) {
		testAnalysisForm = spopm.getTestAnalysisForm(formnumber);
		}else {
		sericeRequestForm  = spopm.getServiceRequestFormDetailByID(formnumber);
		}
		
		

		request.setAttribute("type_service", typeform);
		request.setAttribute("sericeRequestForm", sericeRequestForm);
		request.setAttribute("testAnalysisForm", testAnalysisForm);
		return "SubmitProofOfPaymentPage";
	}
	
	
	@RequestMapping(value = "/doAddPayment", method = RequestMethod.POST)
	public ModelAndView doAddPayment(HttpServletRequest request, Model model, HttpSession session)
			throws UnsupportedEncodingException {

		boolean status = false;
		String number = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			request.setCharacterEncoding("UTF-8");
			
			try {
				List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				String formnumber = data.get(0).getString("UTF-8");
				number = formnumber;
				System.out.println("formnumber :"+formnumber);
				String type_service = data.get(1).getString("UTF-8");
				System.out.println("type_service :"+type_service);
				String receipts_on_behalf = data.get(2).getString("UTF-8");
				System.out.println("receipts_on_behalf :"+receipts_on_behalf);
				Double totalprice = Double.parseDouble(data.get(3).getString("UTF-8"));
				System.out.println("totalprice :"+totalprice);
				String payment_type = data.get(4).getString("UTF-8");
				System.out.println("payment_type :"+payment_type);
				String filename = new File(data.get(5).getName()).getName();
				System.out.println("filename :"+filename);

				SubmitProofOfPaymentManager sppm = new SubmitProofOfPaymentManager();
				int payment_ID = sppm.getnextpaymentid();
				Payment payment = new Payment(payment_ID, "", payment_type, receipts_on_behalf, totalprice);
				status = sppm.isPayment(payment, formnumber, type_service);

				formnumber = formnumber.substring(0, formnumber.indexOf("/"));
				System.out.println(status);
				if (status) {
					String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF\\payment\\";
					data.get(5).write(new File(path.toString() + File.separator + formnumber + ".png"));

				}

			} catch (Exception e) {
			}

		}
		
		if(number.contains("T")) {
			ModelAndView mav = new ModelAndView("ViewTestItemDetailPage");
			ViewTestItemDetailsManager vtim = new ViewTestItemDetailsManager();
			List<TypeTestItem> listTypeTestItem = vtim.getlistTypeTestItem() ;
			TestItem testItem = vtim.getTestItemByID(number);
			request.setAttribute("listTypeTestItem", listTypeTestItem);
			request.setAttribute("testItem", testItem);
			if (status) {
			mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
			}else {
				mav.addObject("msg","บันทึกไม่สำเร็จสำเร็จ !!!!");
			}
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("ServiceRequestFormDetail");
			FlatFeeForm flatFeeForm = new FlatFeeForm();
			ViewServiceRequestManager vsrm = new ViewServiceRequestManager();
			flatFeeForm = vsrm.getServiceRequestFormDetailByID(number);
			request.setAttribute("flatFeeForm", flatFeeForm); 
			if (status) {
				mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
				}else {
				mav.addObject("msg","บันทึกไม่สำเร็จสำเร็จ !!!!");
				}
			return mav;
		}
		
	}

}
