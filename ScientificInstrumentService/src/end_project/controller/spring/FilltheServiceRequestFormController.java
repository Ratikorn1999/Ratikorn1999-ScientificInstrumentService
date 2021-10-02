package end_project.controller.spring;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.log.Log;

import Class_End_Project.ApprovalOfServiceRequestForm;
import Class_End_Project.Coursepresident;
import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.Rentalschedule;
import Class_End_Project.Schedule;
import Class_End_Project.SciInstruments;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.Staff;
import Class_End_Project.TestAnalysisForm;
import LoginMJU.Student;
import Manager.FilltheServiceRequestFormManager;
import Manager.ListAllRequestManager;

@Controller
public class FilltheServiceRequestFormController {

	@RequestMapping(value = "/doAddservicerequestform", method = RequestMethod.POST)
	public ModelAndView doAddservicerequestform(HttpServletRequest request, HttpSession session)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		Calendar today = Calendar.getInstance();
		String year = String.valueOf(today.get(Calendar.YEAR));
		FilltheServiceRequestFormManager fsrfm = new FilltheServiceRequestFormManager();

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
		String x = " xxx ";
		if (member != null || student != null || personnel != null) {
			String type_Work_Name = request.getParameter("typework");
			if ("othertypeword".equals(type_Work_Name)) {
				type_Work_Name = request.getParameter("othertypeword");
			}
			String usage_characteristics_Name = request.getParameter("usage_characteristics");
			if (usage_characteristics_Name.equals("otherusage_characteristics")) {
				usage_characteristics_Name = request.getParameter("otherusage_characteristics");
			}
			String subject = request.getParameter("subjectname");
			String typeform = request.getParameter("typeform");
			int period_of_use = 0;
			Double sumPrice = 0.0;
			Double additionalcost = 0.0;
			String name = request.getParameter("name");
			String advisoremail = "";
			String advisorName = "";
			if (member != null) {
				if (member.getPosition().equals("นักศึกษา")) {
					advisoremail = request.getParameter("advisoremail");
					advisorName = request.getParameter("advisorName");
				}
			}
			if (student != null) {
				advisoremail = request.getParameter("advisoremail");
				advisorName = request.getParameter("advisorName");
				String email = request.getParameter("email");
				String phoneno = request.getParameter("phoneno");
				student.setEmailadvisor(advisoremail);
				student.setEmail(email);
				student.setAdvisor_name(advisorName);
				student.setPhonenumber(phoneno);
				fsrfm.updateStudent(student);
			}
			System.out.println("Name is "+name);
			System.out.println("Name Advisor is "+advisorName);
			System.out.println("Email Advisor is "+advisoremail);

			List<Schedule> listSchedule = new Vector<Schedule>();
			List<ApprovalOfServiceRequestForm> listApprovalOfServiceRequestForm = new Vector<ApprovalOfServiceRequestForm>();
			boolean status = false;
			if (typeform.equals("1")) {
				/*------------------- Make sericeRequestFormNumber -------------------*/
				String sericeRequestFormNumber = fsrfm.getnextsericeRequestFormNumber();
				sericeRequestFormNumber = sericeRequestFormNumber.substring(1, sericeRequestFormNumber.indexOf("/"));
				int n = Integer.parseInt(sericeRequestFormNumber);
				n = n + 1;
				sericeRequestFormNumber = "" + n;
				if (sericeRequestFormNumber.length() == 1) {
					sericeRequestFormNumber = "00" + sericeRequestFormNumber;
				} else if (sericeRequestFormNumber.length() == 2) {
					sericeRequestFormNumber = "0" + sericeRequestFormNumber;
				}
				sericeRequestFormNumber = "E" + sericeRequestFormNumber + "/" + year;
				/*------------------- End make sericeRequestFormNumber -------------------*/

				String sciInstruments_ID[] = request.getParameterValues("sciInstruments_ID");
				String[] date = request.getParameterValues("date");
				String[] starttime = request.getParameterValues("starttime");
				String[] endtime = request.getParameterValues("endtime");

				String schedule_ID = fsrfm.getnextscheduleID();
				int approval_ID = fsrfm.getnextapproval_ID();
				SericeRequestForm sericeRequestForm = new SericeRequestForm();
				sericeRequestForm.setSericeRequestFormNumber(sericeRequestFormNumber);

				if (personnel == null) {
					boolean ck = false;
					if (member != null) {
						if (member.getPosition().equals("นักศึกษา")) {
							ck = true;
						}
					} else if (student != null) {
						ck = true;
					}
					if (ck) {
						ApprovalOfServiceRequestForm approvalOfServiceRequestForm = new ApprovalOfServiceRequestForm();
						Staff staff1 = new Staff();
						Coursepresident coursepresident1 = new Coursepresident();
						staff1.setStaffid(null);
						coursepresident1.setCoursepresidentid(null);
						approval_ID = approval_ID + 1;
						approvalOfServiceRequestForm.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm.setEmailadvisor(advisoremail);
						approvalOfServiceRequestForm.setCoursepresident(coursepresident1);
						approvalOfServiceRequestForm.setStaff(staff1);
						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm);
					}
				}
				for (int i = 0; i < sciInstruments_ID.length; i++) {
					String start = date[i] + " " + starttime[i] + ":00.0";
					String end = date[i] + " " + endtime[i] + ":00.0";
					Timestamp startDateTime = Timestamp.valueOf(start);
					Timestamp endDateTime = Timestamp.valueOf(end);
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

					SciInstruments sciInstruments = fsrfm.getSciInstruments(sciInstruments_ID[i]);

					Staff staff = new Staff();
					Coursepresident coursepresident = new Coursepresident();
					staff.setStaffid(sciInstruments.getStaff().getStaffid());
					coursepresident.setCoursepresidentid(sciInstruments.getCoursepresident().getCoursepresidentid());

					if (coursepresident.getCoursepresidentid() != null && staff.getStaffid() != null) {

						ApprovalOfServiceRequestForm approvalOfServiceRequestForm2 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						approvalOfServiceRequestForm2.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm2.setStaff(staff);
						approvalOfServiceRequestForm2.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm2.setCoursepresident(coursepresident);
						approvalOfServiceRequestForm2.setEmailadvisor(null);
						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm2);

						ApprovalOfServiceRequestForm approvalOfServiceRequestForm3 = new ApprovalOfServiceRequestForm();
						SericeRequestForm sericeRequestForm3 = new SericeRequestForm();
						sericeRequestForm3.setSericeRequestFormNumber(sericeRequestFormNumber);
						approval_ID = approval_ID + 1;
						approvalOfServiceRequestForm3.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm3.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm3.setStaff(staff);
						approvalOfServiceRequestForm3.setEmailadvisor(null);
						approvalOfServiceRequestForm3.setCoursepresident(coursepresident);

						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm3);

					}
					if (staff.getStaffid() != null && coursepresident.getCoursepresidentid() == null) {
						ApprovalOfServiceRequestForm approvalOfServiceRequestForm4 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						approvalOfServiceRequestForm4.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm4.setStaff(staff);
						approvalOfServiceRequestForm4.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm4.setCoursepresident(coursepresident);
						approvalOfServiceRequestForm4.setEmailadvisor(null);

						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm4);

					} else if (staff.getStaffid() == null && coursepresident.getCoursepresidentid() != null) {
						ApprovalOfServiceRequestForm approvalOfServiceRequestForm5 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						approvalOfServiceRequestForm5.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm5.setStaff(staff);
						approvalOfServiceRequestForm5.setCoursepresident(coursepresident);
						approvalOfServiceRequestForm5.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm5.setEmailadvisor(null);

						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm5);
					}

					Schedule schedule = new Schedule(schedule_ID, startDateTime, endDateTime);

					long diff = endDateTime.getTime() - startDateTime.getTime();
					int hourDiff = (int) (diff / (60 * 60 * 1000) % 24);

					double maintenancefee = 0.0;

					if (member != null) {
						if (member.getTypeMember().equals("ก")) {
							maintenancefee = hourDiff * sciInstruments.getPriceType_A();
						} else if (member.getTypeMember().equals("ข")) {
							maintenancefee = hourDiff * sciInstruments.getPriceType_B();
						} else if (member.getTypeMember().equals("ค")) {
							maintenancefee = hourDiff * sciInstruments.getPriceType_C();
						} else {
							maintenancefee = hourDiff * sciInstruments.getPriceType_D();
						}
					} else if (student != null) {
						if (student.getFacultyNameTh().equals("วิทยาศาสตร์")) {
							maintenancefee = hourDiff * sciInstruments.getPriceType_D();
						} else {
							maintenancefee = hourDiff * sciInstruments.getPriceType_C();
						}
					}

					schedule.setSciInstruments(sciInstruments);
					schedule.setTime(String.valueOf(hourDiff));
					schedule.setMaintenancefee(maintenancefee);
					listSchedule.add(schedule);

				}

				sericeRequestForm = new SericeRequestForm(sericeRequestFormNumber, "", null, type_Work_Name, subject,
						usage_characteristics_Name, period_of_use, sumPrice, additionalcost, "", 0);

				if (member != null) {
					sericeRequestForm.setMember(member);
				}
				if (student != null) {
					sericeRequestForm.setStudent(student);
				}
				if (personnel != null) {
					sericeRequestForm.setPersonnel(personnel);
				}

				sericeRequestForm.setListSchedule(listSchedule);
				sericeRequestForm.setLsitApprovalOfServiceRequestForm(listApprovalOfServiceRequestForm);

				boolean result = true;
				boolean ck = false;
				if (member != null) {
					if (member.getPosition().equals("นักศึกษา")) {
						ck = true;
						System.out.println("Position is true ");
					}
				} else if (student != null) {
					ck = true;
					System.out.println("Position is true ");
				}
				System.out.println("ck " + ck);
				System.out.println("Email Advisor is " + advisoremail);
				if (ck) {
					result = this.sendemail(sericeRequestForm, name, advisorName, advisoremail);
				}
				if (result) {
					status = fsrfm.isFillTheServiceRequestForm(sericeRequestForm);
			}
			} else {
				/*------------------- Make flatFeeFormNumber -------------------*/
				String flatFeeFormNumber = fsrfm.getnextFlatFeeFormNumber();
				flatFeeFormNumber = flatFeeFormNumber.substring(1, flatFeeFormNumber.indexOf("/"));
				int nf = Integer.parseInt(flatFeeFormNumber);
				nf = nf + 1;
				flatFeeFormNumber = "" + nf;
				if (flatFeeFormNumber.length() == 1) {
					flatFeeFormNumber = "00" + flatFeeFormNumber;
				} else if (flatFeeFormNumber.length() == 2) {
					flatFeeFormNumber = "0" + flatFeeFormNumber;
				}
				flatFeeFormNumber = "A" + flatFeeFormNumber + "/" + year;

				/*------------------- End make flatFeeFormNumber -------------------*/
				String typetitle = request.getParameter("typetitle");
				String type_course_Name = null;
				String course_id = null;
				String course_Name = null;
				String major = null;
				String title = null;
				String research_project_Name = null;
				String funding_source = null;
				String projectleader = null;

				System.out.println("Type Title is " + typetitle);
				if (typetitle.equals("1")) {
					type_course_Name = request.getParameter("type_course_Name");
					course_id = request.getParameter("course_id");
					course_Name = request.getParameter("course_Name");
					major = request.getParameter("major");
					title = request.getParameter("title");
				} else if (typetitle.equals("2")) {
					research_project_Name = request.getParameter("research_project_Name");
					funding_source = request.getParameter("funding_source");
					projectleader = request.getParameter("projectleader");
				}
				List<Rentalschedule> listRentalschedules = new Vector<>();
				String sciInstruments_ID[] = request.getParameterValues("fsciInstruments_ID");
				String startdate[] = request.getParameterValues("startdate");
				String enddate[] = request.getParameterValues("enddate");
				String term[] = request.getParameterValues("term");

				int approval_ID = fsrfm.getnextapproval_ID();
				SericeRequestForm sericeRequestForm = new SericeRequestForm();
				sericeRequestForm.setSericeRequestFormNumber(flatFeeFormNumber);

				if (personnel == null) {
					boolean ck = false;
					if (member != null) {
						if (member.getPosition().equals("นักศึกษา")) {
							ck = true;
						}
					} else if (student != null) {
						ck = true;
					}
					if (ck) {
				ApprovalOfServiceRequestForm approvalOfServiceRequestForm = new ApprovalOfServiceRequestForm();
				Staff staff1 = new Staff();
				Coursepresident coursepresident1 = new Coursepresident();
				staff1.setStaffid(null);
				coursepresident1.setCoursepresidentid(null);
				approval_ID = approval_ID + 1;
				approvalOfServiceRequestForm.setApproval_ID(approval_ID);
				approvalOfServiceRequestForm.setSericeRequestForm(sericeRequestForm);
				approvalOfServiceRequestForm.setEmailadvisor(advisoremail);
				approvalOfServiceRequestForm.setCoursepresident(coursepresident1);
				approvalOfServiceRequestForm.setStaff(staff1);
				listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm);
				}
				}
				
				String rentalscheduleid = fsrfm.getnextrentalscheduleid();
				for (int i = 0; i < sciInstruments_ID.length; i++) {
					String start = startdate[i];
					String end = enddate[i];

					Date startDate = Date.valueOf(start);
					Date endDate = Date.valueOf(end);

					rentalscheduleid = rentalscheduleid.substring(4);
					int num = Integer.parseInt(rentalscheduleid);
					num = num + 1;
					rentalscheduleid = "" + num;
					int countround = 7 - rentalscheduleid.length();
					String id = "";
					for (int k = 0; k < countround; k++) {
						id = id + "0";
					}
					rentalscheduleid = "RID" + id + num;

					SciInstruments sciInstruments = fsrfm.getSciInstruments(sciInstruments_ID[i]);

					Staff staff = new Staff();
					Coursepresident coursepresident = new Coursepresident();
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					staff.setStaffid(sciInstruments.getStaff().getStaffid());
					System.out.println("staff id is " + staff.getStaffid());
					coursepresident.setCoursepresidentid(sciInstruments.getCoursepresident().getCoursepresidentid());
					System.out.println("Coursepresident id is " + coursepresident.getCoursepresidentid());
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

					if (coursepresident.getCoursepresidentid() != null && staff.getStaffid() != null) {

						ApprovalOfServiceRequestForm approvalOfServiceRequestForm2 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						System.out.println("approval_ID 1 = " + approval_ID);
						approvalOfServiceRequestForm2.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm2.setStaff(staff);
						approvalOfServiceRequestForm2.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm2.setCoursepresident(coursepresident);
						approvalOfServiceRequestForm2.setEmailadvisor(null);
						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm2);
						System.out.println("staff id is " + approvalOfServiceRequestForm2.getStaff().getStaffid());
						System.out.println("Coursepresident id is "
								+ approvalOfServiceRequestForm2.getCoursepresident().getCoursepresidentid());

						ApprovalOfServiceRequestForm approvalOfServiceRequestForm3 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						System.out.println("approval_ID 2 = " + approval_ID);
						approvalOfServiceRequestForm3.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm3.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm3.setStaff(staff);
						approvalOfServiceRequestForm3.setEmailadvisor(null);
						approvalOfServiceRequestForm3.setCoursepresident(coursepresident);

						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm3);
						System.out.println("staff id is " + approvalOfServiceRequestForm2.getStaff().getStaffid());
						System.out.println("Coursepresident id is "
								+ approvalOfServiceRequestForm2.getCoursepresident().getCoursepresidentid());

					}
					if (staff.getStaffid() != null && coursepresident.getCoursepresidentid() == null) {
						ApprovalOfServiceRequestForm approvalOfServiceRequestForm4 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						System.out.println("approval_ID 1 = " + approval_ID);
						approvalOfServiceRequestForm4.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm4.setStaff(staff);
						approvalOfServiceRequestForm4.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm4.setCoursepresident(coursepresident);
						approvalOfServiceRequestForm4.setEmailadvisor(null);

						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm4);

					} else if (staff.getStaffid() == null && coursepresident.getCoursepresidentid() != null) {
						ApprovalOfServiceRequestForm approvalOfServiceRequestForm5 = new ApprovalOfServiceRequestForm();
						approval_ID = approval_ID + 1;
						approvalOfServiceRequestForm5.setApproval_ID(approval_ID);
						approvalOfServiceRequestForm5.setStaff(staff);
						approvalOfServiceRequestForm5.setCoursepresident(coursepresident);
						approvalOfServiceRequestForm5.setSericeRequestForm(sericeRequestForm);
						approvalOfServiceRequestForm5.setEmailadvisor(null);

						listApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm5);
					}

					Rentalschedule rentalschedules = new Rentalschedule(rentalscheduleid, startDate, endDate, term[i],
							sciInstruments.getPriceFlatFee());
					rentalschedules.setSciInstruments(sciInstruments);
					listRentalschedules.add(rentalschedules);

				}

				FlatFeeForm flatFeeForm = new FlatFeeForm(flatFeeFormNumber, "", null, type_Work_Name, subject,
						usage_characteristics_Name, 0, 0.0, additionalcost, "", 0, type_course_Name, course_id,
						course_Name, major, title, research_project_Name, funding_source, projectleader);
				flatFeeForm.setMember(member);
				flatFeeForm.setStudent(student);
				flatFeeForm.setListrentalschedule(listRentalschedules);
				flatFeeForm.setLsitApprovalOfServiceRequestForm(listApprovalOfServiceRequestForm);

				boolean result = true;
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
				
				System.out.println("ck " + ck);
				if (ck) {
					result = this.sendemailbyff(flatFeeForm, name, advisorName, advisoremail);
				}
				if (result) {
					status = fsrfm.isFillTheFlatFeeForm(flatFeeForm);
				}
				System.out.println(status);
			}

			if (status) {
				ListAllRequestManager larm = new ListAllRequestManager();
				List<FlatFeeForm> listsericeRequestForm = null;
				List<TestAnalysisForm> listTestAnalysisForm = null;
				ModelAndView mav = new ModelAndView("ListAllRequestPage");
				if (member != null) {
					listsericeRequestForm = larm.getListAllRequest(member.getMember_ID());
					listTestAnalysisForm = larm.getListAllRequest2(member.getMember_ID());
				}else if(student !=null) {
					listsericeRequestForm = larm.getListAllRequest(student.getStudentID());
					listTestAnalysisForm = larm.getListAllRequest2(student.getStudentID());
					mav.addObject("listsericeRequestForm", listsericeRequestForm);
					mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
					return mav;
				}else if(personnel !=null) {
					listsericeRequestForm = larm.getListAllRequest(personnel.getPersonnel_ID());
					listTestAnalysisForm = larm.getListAllRequest2(personnel.getPersonnel_ID());
					mav.addObject("listsericeRequestForm", listsericeRequestForm);
					mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
				}
				mav.addObject("listsericeRequestForm", listsericeRequestForm);
				mav.addObject("listTestAnalysisForm", listTestAnalysisForm);
				mav.addObject("x",x);
				mav.addObject("msg","บันทึกสำเร็จสำเร็จ !!!!");
				
				return mav;
			} else {
				ModelAndView mav = new ModelAndView("FilltheServiceRequestFormPage");
				mav.addObject("msg","บันทึกไม่สำเร็จสำเร็จ !!!! "+x);
				mav.addObject("x",x);
				return mav;
			}
		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}

	}

	@RequestMapping(value = "/Service", method = RequestMethod.GET)
	public ModelAndView loadFilltheServiceRequestFormPage(HttpSession session) {

		Member member = null;
		Student student = null;
		Personnel personnel = null;
		try {
			student = (Student) session.getAttribute("student");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			member = (Member) session.getAttribute("member");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			personnel = (Personnel) session.getAttribute("personnel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (member != null || student != null || personnel != null) {
			ModelAndView mav = new ModelAndView("FilltheServiceRequestFormPage");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("LoginPage");
			mav.addObject("msg","เซสชั่นหมดอายุ กรุณาล็อคอินใหม่!!!!");
			return mav;
		}
	}

	public boolean  sendemail(SericeRequestForm sericeRequestForm, String name, String advisoreName,
			String advisorEmail) {

		// Creating a result for getting status that messsage is delivered or not!
		String result;
		String s = null;
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
					+ " <br><b>เรื่อง </b><label>การขอใช้บริการเครื่องมือวิทยาศาสตร์</lable>" + " <br>ตามที่ " + name
					+ " ดำเนินการขอใช้บริการเครื่องมือวิทยาศาสตร์ ของคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้"
					+ "<br>มีเครื่องมือดังต่อไปนี้  <br>";
			for (int i = 0; i < sericeRequestForm.getListSchedule().size(); i++) {
				String sdate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
						.format(sericeRequestForm.getListSchedule().get(i).getStartDateTime());
				String edate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("th", "TH"))
						.format(sericeRequestForm.getListSchedule().get(i).getEndDateTime());
				messg = messg + (i + 1) + ")  "
						+ sericeRequestForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_Name()
						+ " ระยะเวลา  " + sdate + "  ถึง  " + edate + "<br>";
			}

			messg = messg

					+ " <br> ในฐานะอาจารย์ที่ปรึกษา ข้าพเจ้ายินดีรับผิดชอบความเสียหายใด ๆ ของอุปกรณ์/เครื่องมือวิทยาศาสตร์"
					+ " อันเกิดจากความประมาทระหว่างการปฏิบัติงานของนักศึกษา/ผู้ช่วยวิจัย ในห้องปฏิบัติการ"
					+ " และค่าบริการอื่น ๆ ทั้งหมด ของ " + name
					+ "<br>หากยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doApproverequetByAdvisor?approval_ID="
					+ sericeRequestForm.getLsitApprovalOfServiceRequestForm().get(0).getApproval_ID()
					+ "'>คลิกที่นี่</a>"
					+ "<br>หากไม่ยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doNotApproverequetByAdvisor?approval_ID="
					+ sericeRequestForm.getLsitApprovalOfServiceRequestForm().get(0).getApproval_ID() + "&formnumber="
					+ sericeRequestForm.getSericeRequestFormNumber() + "'>คลิกที่นี่</a>";
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
			 s = mex.getMessage();
			result = "Error: unable to send mail....";
		}
		System.out.println(result);
		return status;
	}
	public boolean sendemailbyff(FlatFeeForm f, String name, String advisoreName,
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
					+ " <br><b>เรื่อง </b><label>การขอใช้บริการเครื่องมือวิทยาศาสตร์</lable>" + " <br>ตามที่ " + name
					+ " ดำเนินการขอใช้บริการเครื่องมือวิทยาศาสตร์ ของคณะวิทยาศาสตร์ มหาวิทยาลัยแม่โจ้"
					+ "<br>มีเครื่องมือดังต่อไปนี้  <br>";
			for (int i = 0; i < f.getListrentalschedule().size(); i++) {
				String sdate = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"))
						.format( f.getListrentalschedule().get(i).getStartDate());
				String edate = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"))
						.format(f.getListrentalschedule().get(i).getEndDate());
				messg = messg + (i + 1) + ")  "
						+ f.getListrentalschedule().get(i).getSciInstruments().getSciInstruments_Name()
						+ " ระยะเวลา  " + sdate + "  ถึง  " + edate + "<br>";
			}

			messg = messg

					+ " <br> ในฐานะอาจารย์ที่ปรึกษา ข้าพเจ้ายินดีรับผิดชอบความเสียหายใด ๆ ของอุปกรณ์/เครื่องมือวิทยาศาสตร์"
					+ " อันเกิดจากความประมาทระหว่างการปฏิบัติงานของนักศึกษา/ผู้ช่วยวิจัย ในห้องปฏิบัติการ"
					+ " และค่าบริการอื่น ๆ ทั้งหมด ของ " + name
					+ "<br>หากยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doApproverequetByAdvisor?approval_ID="
					+ f.getLsitApprovalOfServiceRequestForm().get(0).getApproval_ID()
					+ "'>คลิกที่นี่</a>"
					+ "<br>หากไม่ยินดีกด <a href='http://www.itsci.mju.ac.th/ScientificInstrumentService/doNotApproverequetByAdvisor?approval_ID="
					+ f.getLsitApprovalOfServiceRequestForm().get(0).getApproval_ID() + "&formnumber="
					+ f.getSericeRequestFormNumber() + "'>คลิกที่นี่</a>";
			
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
