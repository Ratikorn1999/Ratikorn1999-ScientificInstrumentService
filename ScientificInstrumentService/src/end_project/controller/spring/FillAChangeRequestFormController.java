package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
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

import Class_End_Project.ApprovalChangeForm;
import Class_End_Project.ChangeForm;
import Class_End_Project.Changelist;
import Class_End_Project.Coursepresident;
import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.Schedule;
import Class_End_Project.SciInstruments;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.Staff;
import Class_End_Project.TestAnalysisForm;
import LoginMJU.Student;
import Manager.FillAChangeRequestFormManager;
import Manager.ListAllRequestManager;

@Controller
public class FillAChangeRequestFormController {

	@RequestMapping(value = "/FillAChangeRequestFormPage", method = RequestMethod.GET)
	public String loadFillAChangeRequestFormPage(HttpSession session, HttpServletRequest request) {
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
			String sericeRequestFormNumber = request.getParameter("sericeRequestFormNumber");
			FillAChangeRequestFormManager facrfm = new FillAChangeRequestFormManager();
			SericeRequestForm sericeRequestForm = facrfm.getServiceRequestFormByID(sericeRequestFormNumber);
			request.setAttribute("sericeRequestForm", sericeRequestForm);
			return "FillAChangeRequestFormPage";
		} else {
			return "LoginPage";
		}
	}
	
	
	@RequestMapping(value = "/dochangerequestform", method = RequestMethod.POST)
	public ModelAndView dochangerequestform(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
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
			request.setCharacterEncoding("UTF-8");
			String formnumber = request.getParameter("formnumber");
			FillAChangeRequestFormManager facrfm = new FillAChangeRequestFormManager();
			SericeRequestForm sericeRequestForm = facrfm.getServiceRequestFormByID(formnumber);
			
			Calendar today = Calendar.getInstance();
			String year = String.valueOf(today.get(Calendar.YEAR));
			String changeFormNumber = facrfm.getnextchangeFormNumber();
			changeFormNumber = changeFormNumber.substring(1, changeFormNumber.indexOf("/"));
			int nf = Integer.parseInt(changeFormNumber);
			nf = nf + 1;
			changeFormNumber = "" + nf;
			if (changeFormNumber.length() == 1) {
				changeFormNumber = "00" + changeFormNumber;
			} else if (changeFormNumber.length() == 2) {
				changeFormNumber = "0" + changeFormNumber;
			}
			changeFormNumber = "C" + changeFormNumber + "/" + year;
			
			ChangeForm changeForm = new ChangeForm();
			changeForm.setChangeFormNumber(changeFormNumber);
			changeForm.setSericeRequestForm(sericeRequestForm);
			
			System.out.println(changeFormNumber);
			
			String[] cheangdetail = request.getParameterValues("cheangdetail");
			String[] sciInstruments_ID = request.getParameterValues("sciInstruments_ID");
			String[] sdate = request.getParameterValues("sdate");
			String[] edate = request.getParameterValues("edate");
			
			
			
			int approval_ID = facrfm.getnextchangeformapproval_ID();
			String schedule_ID = facrfm.getnextscheduleID();
			
			
			List<ApprovalChangeForm> listApprovalChangeForms = new Vector<>();
			List<Changelist> listChangelists = new Vector<>();
			List<Schedule> listSchedule = new Vector<Schedule>();
			String changelistID = facrfm.getnextchangelistID();
			
			
			
			
			
			boolean ckaddemailadvisor = true;
			for(int i = 0 ; i <  cheangdetail.length ;i++) {
				String detail = "";
				if(cheangdetail[i].equals("1")) {
					detail = "เปลี่ยนแปลงเวลา";
				}else if(cheangdetail[i].equals("2")){
					detail = "ยกเลิก";
				}else if(cheangdetail[i].equals("3")){
					detail = "อื่นๆ";
				}else {
					detail = "ไม่เปลี่ยนแปลง";
				}
				
			
				changelistID = changelistID.substring(5);
				int num2 = Integer.parseInt(changelistID);
				num2 = num2 + 1;
				changelistID = "" + num2;
				int countround2 = 6 - changelistID.length();
				String id2 = "";
				for (int k = 0; k < countround2; k++) {
					id2 = id2 + "0";
				}
				changelistID = "CLID" + id2 + num2;
				
				
				if(cheangdetail[i].equals("1")) {
					SciInstruments sciInstruments = facrfm.getSciInstruments(sciInstruments_ID[i]);
					
					
					schedule_ID = schedule_ID.substring(4);
					int num = Integer.parseInt(schedule_ID);
					num = num + 1;
					schedule_ID = "" + num;
					int countround = 7 - schedule_ID.length();
					String id = "";
					for (int k = 0; k < countround; k++) {
						id = id + "0";
					}
					schedule_ID = "SID" + id + num;
					sdate[i] = sdate[i]+":00.0";
					sdate[i] = sdate[i].replace("T"," ");
					edate[i] = edate[i]+":00.0";
					edate[i] = edate[i].replace("T"," ");
					
					Timestamp startDateTime = Timestamp.valueOf(sdate[i]);
					Timestamp endDateTime = Timestamp.valueOf(edate[i]);
					Schedule newschedule = new Schedule(schedule_ID, startDateTime, endDateTime);
					newschedule.setSciInstruments(sciInstruments);
					
					listSchedule.add(newschedule);

					
					
				
					if(ckaddemailadvisor) {
					System.out.println("********** Emailadvisor is not null *********************");
					
					ApprovalChangeForm approvalChangeForm = new ApprovalChangeForm();
					approval_ID = approval_ID + 1;
					approvalChangeForm.setApproval_ID(approval_ID);
					approvalChangeForm.setChangeForm(changeForm);
					if(member != null) {
					approvalChangeForm.setEmailadvisor(member.getEmailadvisor());
					}else {
					approvalChangeForm.setEmailadvisor(student.getEmailadvisor());	
					}
					approvalChangeForm.setPersonnel(null);
					listApprovalChangeForms.add(approvalChangeForm);	
					ckaddemailadvisor = false;
					}
				
					Staff staff = new Staff();
					Coursepresident coursepresident = new Coursepresident();
					staff.setStaffid(sciInstruments.getStaff().getStaffid());
					coursepresident.setCoursepresidentid(sciInstruments.getCoursepresident().getCoursepresidentid());
					if (coursepresident.getCoursepresidentid() != null && staff.getStaffid() != null) {
						System.out.println("********** Coursepresidentid && Staffid is null *********************");
						Personnel p = new Personnel();
						p.setPersonnel_ID(staff.getStaffid());
						p.setPersonnel_type("LaboratoryStaff");
						ApprovalChangeForm approvalChangeForm2 = new ApprovalChangeForm();
						approval_ID = approval_ID + 1;
						approvalChangeForm2.setApproval_ID(approval_ID);
						approvalChangeForm2.setChangeForm(changeForm);
						approvalChangeForm2.setPersonnel(p);
						approvalChangeForm2.setEmailadvisor(null);
						listApprovalChangeForms.add(approvalChangeForm2);
						System.out.println("add approvalChangeForm2");	
						
						Personnel p2 = new Personnel();
						p2.setPersonnel_ID(coursepresident.getCoursepresidentid());
						p2.setPersonnel_type("Coursepresident");
						ApprovalChangeForm approvalChangeForm3 = new ApprovalChangeForm();
						approval_ID = approval_ID + 1;
						approvalChangeForm3.setApproval_ID(approval_ID);
						approvalChangeForm3.setChangeForm(changeForm);
						approvalChangeForm3.setPersonnel(p2);
						approvalChangeForm3.setEmailadvisor(null);
						listApprovalChangeForms.add(approvalChangeForm3);
						System.out.println("add approvalChangeForm3");
					}
					if (staff.getStaffid() != null && coursepresident.getCoursepresidentid() == null) {
						System.out.println("********** Coursepresidentid is  null && Staffid is not null *********************");
						
						Personnel p = new Personnel();
						p.setPersonnel_ID(staff.getStaffid());
						p.setPersonnel_type("LaboratoryStaff");
						ApprovalChangeForm approvalChangeForm2 = new ApprovalChangeForm();
						approval_ID = approval_ID + 1;
						approvalChangeForm2.setApproval_ID(approval_ID);
						approvalChangeForm2.setChangeForm(changeForm);
						approvalChangeForm2.setPersonnel(p);
						approvalChangeForm2.setEmailadvisor(null);
						listApprovalChangeForms.add(approvalChangeForm2);
						

					} else if (staff.getStaffid() == null && coursepresident.getCoursepresidentid() != null) {
						System.out.println("********** Coursepresidentid  is not null && Staffid  is null *********************");
						Personnel p = new Personnel();
						p.setPersonnel_ID(coursepresident.getCoursepresidentid());
						p.setPersonnel_type("Coursepresident");
						ApprovalChangeForm approvalChangeForm3 = new ApprovalChangeForm();
						approval_ID = approval_ID + 1;
						approvalChangeForm3.setApproval_ID(approval_ID);
						approvalChangeForm3.setChangeForm(changeForm);
						approvalChangeForm3.setPersonnel(p);
						approvalChangeForm3.setEmailadvisor(null);
						listApprovalChangeForms.add(approvalChangeForm3);
					}

				
				
					Schedule historyschedule = sericeRequestForm.getListSchedule().get(i);
					
					
					
					Changelist changelist = new Changelist();
					changelist.setChangelistID(changelistID);
					changelist.setHistoryschedule(historyschedule);
					changelist.setNewschedule(newschedule);
					changelist.setDetail(detail);
					
					
					listChangelists.add(changelist);
					
					
				}else if(cheangdetail[i].equals("2")) {
					Changelist changelist = new Changelist();
					changelist.setChangelistID(changelistID);
					Schedule historyschedule = sericeRequestForm.getListSchedule().get(i);
					changelist.setHistoryschedule(historyschedule);
					changelist.setDetail(detail);
					listChangelists.add(changelist);
				}else {
					Changelist changelist = new Changelist();
					changelist.setChangelistID(changelistID);
					Schedule historyschedule = sericeRequestForm.getListSchedule().get(i);
					changelist.setHistoryschedule(historyschedule);
					changelist.setDetail(detail);
					listChangelists.add(changelist);
					
				}
				
				
				
				
				
			}
			
			for(int i = 0 ; i< listApprovalChangeForms.size();i++) {
				System.out.println("Approval_ID is "+listApprovalChangeForms.get(i).getApproval_ID());
			
			}
			
		
			String name = null;
			String advisoreName = null;
			String advisorEmail = null;
			if(sericeRequestForm.getMember().getMember_ID() != null) {
			name = changeForm.getSericeRequestForm().getMember().getMember_Prefix() + " " + changeForm.getSericeRequestForm().getMember().getMember_FirstName() + "  " + changeForm.getSericeRequestForm().getMember().getMember_LastName();
			advisoreName = changeForm.getSericeRequestForm().getMember().getAdvisor_name();
			advisorEmail = changeForm.getSericeRequestForm().getMember().getEmailadvisor();
			}else if(sericeRequestForm.getStudent().getStudentID() != null){ 
			name = changeForm.getSericeRequestForm().getStudent().getNameTh()+" "+changeForm.getSericeRequestForm().getStudent().getSurnameTh() ;
			advisoreName = changeForm.getSericeRequestForm().getStudent().getAdvisor_name();
			advisorEmail = changeForm.getSericeRequestForm().getStudent().getEmailadvisor();
			 }
			
			changeForm.setChangelists(listChangelists);
			changeForm.setListApprovalChangeForm(listApprovalChangeForms);
			
			boolean ck = false;
			if (member != null) {
				if (member.getPosition().equals("นักศึกษา")) {
					ck = true;
					System.out.println("Position is true ");
				}
			} else if(student != null) {
				ck = true;
				System.out.println("Position is true ");
			}
			boolean result = true;
			System.out.println("ck " + ck);
			if (ck) {
				result = this.sendemail(changeForm,name, advisoreName, advisorEmail);
			}
			boolean statusresult = false;
			if(result) {
			 statusresult = facrfm.ischangerequestform(changeForm);
			}
			
			
			ListAllRequestManager larm = new ListAllRequestManager();
			List<FlatFeeForm> listsericeRequestForm = null;
			List<TestAnalysisForm> listTestAnalysisForm = null;
			if(statusresult) {
			ModelAndView mav = new ModelAndView("ListAllRequestPage");
			if (member != null ) {
				listsericeRequestForm = larm.getListAllRequest(member.getMember_ID());
				listTestAnalysisForm = larm.getListAllRequest2(member.getMember_ID());
				request.setAttribute("listsericeRequestForm", listsericeRequestForm);
				request.setAttribute("listTestAnalysisForm", listTestAnalysisForm);
				
			}else if(student !=null) {
				listsericeRequestForm = larm.getListAllRequest(student.getStudentID());
				listTestAnalysisForm = larm.getListAllRequest2(student.getStudentID());
				request.setAttribute("listsericeRequestForm", listsericeRequestForm);
				request.setAttribute("listTestAnalysisForm", listTestAnalysisForm);
			}
			mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
			return mav;
			}else {
				ModelAndView mav = new ModelAndView("FillAChangeRequestFormPage");
				request.setAttribute("sericeRequestForm", sericeRequestForm);
				mav.addObject("msg","บันทึกไม่สำเร็จสำเร็จ !!!! ");
				return mav;
			}
		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}
	}
	
	public boolean  sendemail(ChangeForm changeForm, String name, String advisoreName,
			String advisorEmail) {

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
					+ " <br><b>เรื่อง </b><label>การขอเปลี่ยนแปลงการใช้บริการเครื่องมือวิทยาศาสตร์</lable>" 
					+ " <br>ตามที่ " + name
					+ " ดำเนินการขอเปลี่ยนแปลงการใช้บริการเครื่องมือวิทยาศาสตร์ ของคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้"
					+ "<br>มีเครื่องมือดังต่อไปนี้  <br>";
			for (int i = 0; i < changeForm.getChangelists().size(); i++) {
			
				messg = messg + (i + 1) + ")  "
						+ changeForm.getSericeRequestForm().getListSchedule().get(i).getSciInstruments().getSciInstruments_Name()
						+ " การเปลี่ยนแปลง  " + changeForm.getChangelists().get(i).getDetail()+ "<br>";
			}

			messg = messg

					+ " <br> ในฐานะอาจารย์ที่ปรึกษา ข้าพเจ้ายินดีรับผิดชอบความเสียหายใด ๆ ของอุปกรณ์/เครื่องมือวิทยาศาสตร์"
					+ " อันเกิดจากความประมาทระหว่างการปฏิบัติงานของนักศึกษา/ผู้ช่วยวิจัย ในห้องปฏิบัติการ"
					+ " และค่าบริการอื่น ๆ ทั้งหมด ของ " + name
					+ "<br>หากยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doApproverequetByAdvisorChange?approval_ID="
					+ changeForm.getListApprovalChangeForm().get(0).getApproval_ID()
					+ "'>คลิกที่นี่</a>"
					+ "<br>หากไม่ยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doNotApproverequetByAdvisorChange?approval_ID="
					+changeForm.getListApprovalChangeForm().get(0).getApproval_ID() + "&formnumber="
					+ changeForm.getChangeFormNumber() + "'>คลิกที่นี่</a>";
		
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
