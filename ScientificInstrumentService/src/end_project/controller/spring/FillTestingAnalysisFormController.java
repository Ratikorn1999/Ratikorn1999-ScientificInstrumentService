package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Class_End_Project.ApprovalTestAnalysisForm;
import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.TestAnalysisForm;
import Class_End_Project.TestItem;
import Class_End_Project.UseTestItem;
import LoginMJU.Student;
import Manager.FillTestingAnalysisFormManager;
import Manager.ListAllRequestManager;

@Controller
public class FillTestingAnalysisFormController {

	@RequestMapping(value = "/TestingAnalysis", method = RequestMethod.GET)
	public String loadFillTestingAnalysisFormPage(HttpSession session, HttpServletRequest request) {
		Member member = null;
		Student student = null;
		Personnel personnel = null;
		try {
			student = (Student) session.getAttribute("student");
		} catch (Exception e) {
		}
		try {
			member = (Member) session.getAttribute("member");
		} catch (Exception e) {
		}
		try {
			personnel = (Personnel) session.getAttribute("personnel");
		} catch (Exception e) {
		}

		if (member != null || student != null || personnel != null) {
			FillTestingAnalysisFormManager ftafm = new FillTestingAnalysisFormManager();
			List<TestItem> listtestitem = ftafm.getListTestItem();
			request.setAttribute("listtestitem", listtestitem);
			return "FillTestingAnalysisFormPage";
		} else {
			return "LoginPage";
		}
	}

	@RequestMapping(value = "/doAddTestingAnalysisform", method = RequestMethod.POST)
	public ModelAndView doAddTestingAnalysisform(HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {
		
		Calendar today = Calendar.getInstance();
		int year = (today.get(Calendar.YEAR));
		FillTestingAnalysisFormManager ftafm = new FillTestingAnalysisFormManager();
		request.setCharacterEncoding("UTF-8");
		Member member = null;
		Student student = null;
		Personnel personnel = null;
		try {
			student = (Student) session.getAttribute("student");
		} catch (Exception e) {
		}
		try {
			member = (Member) session.getAttribute("member");
		} catch (Exception e) {
		}
		try {
			personnel = (Personnel) session.getAttribute("personnel");
		} catch (Exception e) {
		}

		if (member != null || student != null || personnel != null) {

			/* Make testAnalysisFormNumber */

			String testAnalysisFormNumber = ftafm.getnexttestAnalysisFormNumber();

			if (testAnalysisFormNumber.equals("T001/")) {
				testAnalysisFormNumber = testAnalysisFormNumber + year;
			} else {
				testAnalysisFormNumber = testAnalysisFormNumber.substring(1, testAnalysisFormNumber.indexOf("/"));
				int n = Integer.parseInt(testAnalysisFormNumber);
				n = n + 1;
				testAnalysisFormNumber = "" + n;
				if (testAnalysisFormNumber.length() == 1) {
					testAnalysisFormNumber = "00" + testAnalysisFormNumber;
				} else if (testAnalysisFormNumber.length() == 2) {
					testAnalysisFormNumber = "0" + testAnalysisFormNumber;
				}
				testAnalysisFormNumber = "T" + testAnalysisFormNumber + "/" + year;
			}

			/* End make sericeRequestFormNumber */

			String type = "";
			Date pickupdate = null;
			String language = request.getParameter("language");
			String client_name = request.getParameter("client_name");
			String address = request.getParameter("address");
			String report_Address = request.getParameter("report_Address");
			String billing_Address = request.getParameter("billing_Address");
			String packing_style = request.getParameter("packing_style");
			String additional_details = request.getParameter("additional_details");
			String receive_Analysis_Result = request.getParameter("receive_Analysis_Result");
			String resultsOfTheAnalysis = request.getParameter("resultsOfTheAnalysis");

			String[] sampleName = request.getParameterValues("sampleName");
			String[] code = request.getParameterValues("code");
			String[] weight = request.getParameterValues("weight");
			String[] qty = request.getParameterValues("qty");
			String[] testItemid = request.getParameterValues("testItemid");
			String[] storage = request.getParameterValues("storage");

			System.out.println(sampleName.length);
			List<UseTestItem> listUseTestItem = new Vector<UseTestItem>();
			String usetestitemID = ftafm.getnextUsetestitemID();
			for (int i = 0; i < sampleName.length; i++) {

				usetestitemID = usetestitemID.substring(2);
				int number = usetestitemID.length();
				int no = Integer.parseInt(usetestitemID) + 1;
				String length = "" + no;
				usetestitemID = "UT";
				number = number - length.length();
				for (int n = 0; n < number; n++) {
					usetestitemID = usetestitemID + "0";
				}
				usetestitemID = usetestitemID + no;

				System.out.println(usetestitemID + "," + sampleName[i] + "," + code[i] + "," + weight[i] + ","
						+ Integer.parseInt(qty[i]) + "," + "++" + "," + "+++" + "," + storage[i] + ",");

				System.out.println("testItemid[i] " + testItemid[i]);

				TestItem testItem = ftafm.getTestItemByID(testItemid[i]);

				double maintenancefee = 0.0;

				if (member != null) {
					if (member.getTypeMember().equals("ก")) {
						maintenancefee = Integer.parseInt(qty[i]) * testItem.getPriceType_A();
					} else if (member.getTypeMember().equals("ข")) {
						maintenancefee = Integer.parseInt(qty[i]) * testItem.getPriceType_B();
					}
				} else if (student != null) {
					if (student.getFacultyNameTh().equals("วิทยาศาสตร์")) {
						maintenancefee = Integer.parseInt(qty[i]) * testItem.getPriceType_D();
					} else {
						maintenancefee = Integer.parseInt(qty[i]) * testItem.getPriceType_C();
					}
				} else if (personnel != null) {
					if (personnel.getFaculty().equals("วิทยาศาสตร์")) {
						maintenancefee = Integer.parseInt(qty[i]) * testItem.getPriceType_D();
					} else {
						maintenancefee = Integer.parseInt(qty[i]) * testItem.getPriceType_C();
					}
				}

				UseTestItem usetestitem = new UseTestItem(usetestitemID, sampleName[i], code[i], weight[i],
						Integer.parseInt(qty[i]), "", "", storage[i], maintenancefee);

				usetestitem.setTestItem(testItem);
				listUseTestItem.add(usetestitem);
			}

			TestAnalysisForm testAnalysisForm = new TestAnalysisForm();
			testAnalysisForm.setTestAnalysisFormNumber(testAnalysisFormNumber);
			testAnalysisForm.setLanguage(language);
			testAnalysisForm.setClient_name(client_name);
			testAnalysisForm.setAddress(address);
			testAnalysisForm.setReport_Address(report_Address);
			testAnalysisForm.setBilling_Address(billing_Address);
			testAnalysisForm.setPacking_style(packing_style);
			testAnalysisForm.setAdditional_details(additional_details);
			testAnalysisForm.setReceive_Analysis_Result(receive_Analysis_Result);
			testAnalysisForm.setResultsOfTheAnalysis(resultsOfTheAnalysis);
			testAnalysisForm.setListUseTestItem(listUseTestItem);
			if (member != null) {
				testAnalysisForm.setMember(member);
			}
			if (student != null) {
				testAnalysisForm.setStudent(student);
			}
			if (personnel != null) {
				testAnalysisForm.setPersonnel(personnel);
			}

			if (personnel == null) {
				List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm = new Vector<ApprovalTestAnalysisForm>();
				int approval_ID = ftafm.getnextapproval_ID();

				ApprovalTestAnalysisForm approvalTestAnalysisForm = new ApprovalTestAnalysisForm();
				approval_ID = approval_ID + 1;
				approvalTestAnalysisForm.setApproval_ID(approval_ID);

				if (member != null) {
					if (member.getPosition().equals("นักศึกษา")) {
						approvalTestAnalysisForm.setEmailadvisor(member.getEmailadvisor());
					}
				} else if (student != null) {
					approvalTestAnalysisForm.setEmailadvisor(student.getEmailadvisor());
				}
				lsitApprovalTestAnalysisForm.add(approvalTestAnalysisForm);
				testAnalysisForm.setLsitApprovalTestAnalysisForm(lsitApprovalTestAnalysisForm);
			}

			boolean result = true;
			boolean ck = false;
			if (member != null || student != null) {
				String advisoremail = "";
				String advisorName = "";
				String name = request.getParameter("name");
				if (member != null) {
					if (member.getPosition().equals("นักศึกษา")) {
						advisorName = member.getAdvisor_name();
						advisoremail = member.getEmailadvisor();
						ck = true;
					}
				} else {
					if (student.getAdvisor_name() != null || student.getEmailadvisor() != null) {
						advisorName = student.getAdvisor_name();
						advisoremail = student.getEmailadvisor();
						ck = true;
					} else {
						ck = false;
					}
				}
				if (ck) {
					result = this.sendemail(testAnalysisForm, name, advisorName, advisoremail);
				}
			}
			boolean status = false;
			if (result) {
				status = ftafm.isTestingAnalysisform(testAnalysisForm);
			}
			System.out.println(status);

			ListAllRequestManager larm = new ListAllRequestManager();
			List<FlatFeeForm> listsericeRequestForm = null;
			List<TestAnalysisForm> listTestAnalysisForm = null;

			if (status) {
				ModelAndView mav = new ModelAndView("ListAllRequestPage");
				if (member != null) {
					listsericeRequestForm = larm.getListAllRequest(member.getMember_ID());
					listTestAnalysisForm = larm.getListAllRequest2(member.getMember_ID());
				} else if(student != null) {
					listsericeRequestForm = larm.getListAllRequest(student.getStudentID());
					listTestAnalysisForm = larm.getListAllRequest2(student.getStudentID());
				}else {
					listsericeRequestForm = larm.getListAllRequest(personnel.getPersonnel_ID());
					listTestAnalysisForm = larm.getListAllRequest2(personnel.getPersonnel_ID());	
				}
				request.setAttribute("listsericeRequestForm", listsericeRequestForm);
				request.setAttribute("listTestAnalysisForm", listTestAnalysisForm);
				mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
				return mav;
				
			} else {
				ModelAndView mav = new ModelAndView("FillTestingAnalysisFormPage");
				List<TestItem> listtestitem = ftafm.getListTestItem();
				request.setAttribute("listtestitem", listtestitem);
				mav.addObject("msg","บันทึกไม่สำเร็จสำเร็จ !!!!");
				return mav;
			}

		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}

	}

	public boolean sendemail(TestAnalysisForm form, String name, String advisoreName, String advisorEmail) {
		// Creating a result for getting status that messsage is delivered or not!
		String result;
		boolean status = false;
		// Get recipient's email-ID, message & subject-line from index.html page
		final String to = advisorEmail;
		final String subject = "พิจารณาการให้บริการเครื่องมือวิทยาศาสตร์";

		// Sender's email ID and password needs to be mentioned
		final String from = "scientificinstrumentservice@gmail.com";
		final String pass = "Sci@2562";

		// Defining the gmail host
		String host = "smtp.gmail.com";

		// Creating Properties object
		Properties props = new Properties();

		// Defining properties
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.localhost", "itsci.mju.ac.th");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.user", from);
		props.put("mail.password", pass);
		props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.port", "465");

		// Authorized the Session object.
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			}
		});

		try {

			String messg = " <br><b>เรียน </b><label>" + advisoreName + "</lable>"
					+ " <br><b>เรื่อง </b><label>การขอใช้บริการการวิเคราะห์ทดสอบ</lable>" + " <br>ตามที่ " + name
					+ " ดำเนินการขอใช้บริการ การวิเคราะห์ทดสอบ ของคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้"
					+ "<br>มีการวิเคราะห์ทดสอบดังต่อไปนี้  <br>";
			for (int i = 0; i < form.getListUseTestItem().size(); i++) {
				messg = messg + (i + 1) + ")  " + form.getListUseTestItem().get(i).getTestItem().getTestitem_Name();
			}

			messg = messg

					+ " <br> ในฐานะอาจารย์ที่ปรึกษา ข้าพเจ้ายินดีรับผิดชอบ ค่าบริการ หรือค่าใช้จ่ายเพิ่มเติมทั้งหมด"
					+ " อันเกิดจากความประมาทระหว่างการปฏิบัติงานของนักศึกษา/ผู้ช่วยวิจัย ในห้องปฏิบัติการ"
					+ " และค่าบริการอื่น ๆ ทั้งหมด ของ " + name
					+ "<br>หากยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doApproveTestAnalysisFormByAdvisor?approval_ID="
					+ form.getLsitApprovalTestAnalysisForm().get(0).getApproval_ID() + "'>คลิกที่นี่</a>"
					+ "<br>หากไม่ยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doNotApproveTestAnalysisFormrByAdvisor?approval_ID="
					+ form.getLsitApprovalTestAnalysisForm().get(0).getApproval_ID() + "&formnumber="
					+ form.getTestAnalysisFormNumber() + "'>คลิกที่นี่</a>";
			// + "\r\nหากยินดีกดคลิกที่นี่
			// http://localhost:8083//ScientificInstrumentService/doApproverequetByAdvisor?approval_ID="+sericeRequestForm.getLsitApprovalOfServiceRequestForm().get(0).getApproval_ID()
			// + "\r\nหากไม่ยินดีกดคลิกที่นี่
			// http://localhost:8083//ScientificInstrumentService/doNotApproverequetByAdvisor?approval_ID="+sericeRequestForm.getLsitApprovalOfServiceRequestForm().get(0).getApproval_ID()+"&formnumber="+sericeRequestForm.getSericeRequestFormNumber()

			MimeMessage message = new MimeMessage(mailSession);
			// Set From Field: adding senders email to from field.
			message.setFrom(new InternetAddress(from));
			// Set To Field: adding recipient's email to from field.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: subject of the email
			message.setSubject(subject);
			// set body of the email.
			message.setContent(messg, "text/html;charset=utf-8");
			// Send email.
			Transport.send(message);
			result = "Your mail sent successfully....";
			status = true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			result = "Error: unable to send mail....";
		}
		System.out.println(result);
		return status;
	}

}
