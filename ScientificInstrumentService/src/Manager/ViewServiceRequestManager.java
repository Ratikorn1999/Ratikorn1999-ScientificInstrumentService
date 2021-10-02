package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import Class_End_Project.ApprovalOfServiceRequestForm;
import Class_End_Project.ChangeForm;
import Class_End_Project.Coursepresident;
import Class_End_Project.FlatFeeForm;
import Class_End_Project.Member;
import Class_End_Project.Payment;
import Class_End_Project.Personnel;
import Class_End_Project.Rentalschedule;
import Class_End_Project.Schedule;
import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.Staff;
import LoginMJU.Student;
import projectutil.ConnectionDB;

public class ViewServiceRequestManager {

	public List<SericeRequestForm> getListSericeRequestForm() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<SericeRequestForm> listsericeRequestForm = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = "SELECT sf.sericeRequestFormNumber,sf.type,sf.pickupdate,type_Work_Name,sf.subject,sf.usage_characteristics_Name,sf.period_of_use,sf.sumPrice,sf.additionalcost,sf.booking_status,sf.approveLV,sf.createdate"
					+ ",p.payment_ID,p.paymentDateTime,p.status,p.payment_type,p.receipts_on_behalf,p.totalprice "
					+ ",cf.changeFormNumber,cf.approveLV,cf.pickupdate,cf.type"
					+ ",m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.position,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name"
					+ ",stu.studentID,stu.nameTh,stu.surnameTh,stu.programNameTh,stu.facultyNameTh,stu.statusName,stu.status,stu.levelName,stu.idcard,stu.advisor_name,stu.emailadvisor,stu.phonenumber,stu.email"
					+ " ,pn.personnel_ID,pn.personnel_Prefix,pn.personnel_FirstName,pn.personnel_LastName,pn.email,pn.position,pn.personnel_type,pn.faculty"
					+ " FROM ((((sericerequestform sf left join payment p on sf.payment_ID = p.payment_ID) "
					+ " left join changeform cf on cf.sericeRequestFormNumber = sf.sericeRequestFormNumber) "
					+ " left join member m on m.member_ID = sf.member_ID)"
					+ " left join student stu on stu.studentID = sf.studentID)"
					+ "left join personnel pn on pn.personnel_ID = sf.personnel_ID" 
					+ " Order by sf.createdate desc";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				/* Set SericeRequestForm */
				String sericeRequestFormNumber = rs.getString(1);
				String type = rs.getString(2);
				Date pickupdate = rs.getDate(3);
				String type_Work_Name = rs.getString(4);
				String subject = rs.getString(5);
				String usage_characteristics_Name = rs.getString(6);
				int period_of_use = rs.getInt(7);
				Double sumPrice = rs.getDouble(8);
				Double additionalcost = rs.getDouble(9);
				String booking_status = rs.getString(10);
				int approveLV = rs.getInt(11);
				Timestamp createdate = rs.getTimestamp(12);
				/* End Set SericeRequestForm */

				/* Set Payment */
				int payment_ID = rs.getInt(13);
				Timestamp paymentDateTime = rs.getTimestamp(14);
				String status = rs.getString(15);
				String payment_type = rs.getString(16);
				String receipts_on_behalf = rs.getString(17);
				Double totalprice = rs.getDouble(18);
				Payment payment = new Payment(payment_ID, status, payment_type, receipts_on_behalf, totalprice);
				payment.setPaymentDateTime(paymentDateTime);
				/* End Set Payment */

				
				/* Set ChangeForm */
				String changeFormNumber = rs.getString(19);
				ChangeForm changeForm = new ChangeForm();
				changeForm.setChangeFormNumber(changeFormNumber);
				/* End Set ChangeForm */

				/* Set Member */
				String member_ID = rs.getString(23);
				String member_Prefix = rs.getString(24);
				String member_FirstName = rs.getString(25);
				String member_LastName = rs.getString(26);
				String typeMember = rs.getString(27);
				String position = rs.getString(28);
				String email = rs.getString(29);
				String phonenumber = rs.getString(30);
				String studentID = rs.getString(31);
				String degree = rs.getString(32);
				String branch = rs.getString(33);
				String faculty = rs.getString(34);
				String university = rs.getString(35);
				String advisor_name = rs.getString(36);
				String emailadvisor = rs.getString(37);
				String company_name = rs.getString(38);

				Member member = new Member(member_ID, member_Prefix, member_FirstName, member_LastName, email,
						typeMember, position, phonenumber, studentID, degree, branch, faculty, university, advisor_name,
						emailadvisor, company_name);

				String stu_studentID = rs.getString(39);
				String stu_nameTh = rs.getString(40);
				String stu_surnameTh = rs.getString(41);
				String stu_programNameTh = rs.getString(42);
				String stu_facultyNameTh = rs.getString(43);
				String stu_statusName = rs.getString(44);
				String stu_status = rs.getString(45);
				String stu_levelName = rs.getString(46);
				String stu_idcard = rs.getString(47);
				String stu_advisor_name = rs.getString(48);
				String stu_emailadvisor = rs.getString(49);
				String stu_phonenumber = rs.getString(50);
				String stu_email = rs.getString(51);
				
				Student student = new Student(stu_studentID,stu_nameTh,stu_surnameTh,stu_programNameTh,stu_facultyNameTh,stu_statusName,stu_status,stu_levelName,stu_idcard);
				student.setAdvisor_name(stu_advisor_name);
				student.setEmail(stu_email);
				student.setEmailadvisor(stu_emailadvisor);
				student.setPhonenumber(stu_phonenumber);
				
				
				String p_personnel_ID = rs.getString(52);
				String p_personnel_Prefix = rs.getString(53);
				String p_personnel_FirstName = rs.getString(54);
				String p_personnel_LastName = rs.getString(55);
				String p_email = rs.getString(56);
				String p_position = rs.getString(57);
				String p_personnel_type = rs.getString(58);
				String p_faculty  = rs.getString(59);
				Personnel personnel = new Personnel(p_personnel_ID, p_personnel_Prefix, p_personnel_FirstName,
						p_personnel_LastName, p_email, p_position, p_personnel_type,p_faculty);
				
				
				
				
				List<Schedule> lsitSchedule = this.getlistschedule(sericeRequestFormNumber);
				List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm = this.getlistApprovalOfServiceRequestForm(sericeRequestFormNumber);

				SericeRequestForm sericeRequestForm = new SericeRequestForm(sericeRequestFormNumber, type, pickupdate,
						type_Work_Name, subject, usage_characteristics_Name, period_of_use, sumPrice, additionalcost,
						booking_status, approveLV);
				sericeRequestForm.setCreatedate(createdate);
				sericeRequestForm.setPayment(payment);
				sericeRequestForm.setChangeForm(changeForm);
				sericeRequestForm.setMember(member);
				sericeRequestForm.setListSchedule(lsitSchedule);
				sericeRequestForm.setLsitApprovalOfServiceRequestForm(lsitApprovalOfServiceRequestForm);
				sericeRequestForm.setStudent(student);
				sericeRequestForm.setPersonnel(personnel);
				listsericeRequestForm.add(sericeRequestForm);

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return listsericeRequestForm;
	}
	
	

	public FlatFeeForm getServiceRequestFormDetailByID(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		FlatFeeForm form = null;

		try {

			stmt = con.createStatement();
			String sql = "SELECT sf.sericeRequestFormNumber,sf.type,sf.pickupdate,type_Work_Name,sf.subject,sf.usage_characteristics_Name,sf.period_of_use,sf.sumPrice,sf.additionalcost,sf.booking_status,sf.approveLV"
					+ "	,p.payment_ID,p.paymentDateTime,p.status,p.payment_type,p.receipts_on_behalf,p.totalprice "
					+ "	,cf.changeFormNumber,cf.approveLV,cf.pickupdate,cf.type"
					+ "	,m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.position,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name"
					+ "	,ff.type_course_Name,ff.course_id,ff.course_Name,ff.major,ff.title,ff.research_project_Name,ff.funding_source,ff.projectleader"
					+ ",stu.studentID,stu.nameTh,stu.surnameTh,stu.programNameTh,stu.facultyNameTh,stu.statusName,stu.status,stu.levelName,stu.idcard,stu.advisor_name,stu.emailadvisor,stu.phonenumber,stu.email"
					+ " ,pn.personnel_ID,pn.personnel_Prefix,pn.personnel_FirstName,pn.personnel_LastName,pn.email,pn.position,pn.personnel_type,pn.faculty"
					+ " FROM ((((((sericerequestform sf left join payment p on sf.payment_ID = p.payment_ID) "
					+ "	left join changeform cf on cf.sericeRequestFormNumber = sf.sericeRequestFormNumber) "
					+ "	left join member m on m.member_ID = sf.member_ID) "
					+ "	left join login l on m.username = l.username )"
					+ "  left join flatfeeform ff on sf.sericeRequestFormNumber = ff.sericeRequestFormNumber)"
					+ "  left join student stu on stu.studentID = sf.studentID)" 
					+ " left join personnel pn on pn.personnel_ID = sf.personnel_ID" 
					+ " where sf.sericeRequestFormNumber = '" + key + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				/* Set SericeRequestForm */
				String sericeRequestFormNumber = rs.getString(1);
				String type = rs.getString(2);
				Date pickupdate = rs.getDate(3);
				String type_Work_Name = rs.getString(4);
				String subject = rs.getString(5);
				String usage_characteristics_Name = rs.getString(6);
				int period_of_use = rs.getInt(7);
				Double sumPrice = rs.getDouble(8);
				Double additionalcost = rs.getDouble(9);
				String booking_status = rs.getString(10);
				int approveLV = rs.getInt(11);
				/* End Set SericeRequestForm */

				/* Set Payment */
				int payment_ID = rs.getInt(12);
				Timestamp paymentDateTime = rs.getTimestamp(13);
				String status = rs.getString(14);
				String payment_type = rs.getString(15);
				String receipts_on_behalf = rs.getString(16);
				Double totalprice = rs.getDouble(17);
				Payment payment = new Payment(payment_ID, status, payment_type, receipts_on_behalf, totalprice);
				payment.setPaymentDateTime(paymentDateTime);
				/* End Set Payment */

				/* Set ChangeForm */
				String changeFormNumber = rs.getString(18);
				ChangeForm changeForm = new ChangeForm();
				changeForm.setChangeFormNumber(changeFormNumber);
				/* End Set ChangeForm */

				/* Set member */
				String member_ID = rs.getString(22);
				String member_Prefix = rs.getString(23);
				String member_FirstName = rs.getString(24);
				String member_LastName = rs.getString(25);
				String typeMember = rs.getString(26);
				String position = rs.getString(27);
				String email = rs.getString(28);
				String phonenumber = rs.getString(29);
				String studentID = rs.getString(30);
				String degree = rs.getString(31);
				String branch = rs.getString(32);
				String faculty = rs.getString(33);
				String university = rs.getString(34);
				String advisor_name = rs.getString(35);
				String emailadvisor = rs.getString(36);
				String company_name = rs.getString(37);

				Member member = new Member(member_ID, member_Prefix, member_FirstName, member_LastName, email,
						typeMember, position, phonenumber, studentID, degree, branch, faculty, university, advisor_name,
						emailadvisor, company_name);

				/* End Set member */

				
				String type_course_Name = rs.getString(38);
				String course_id = rs.getString(39);
				String course_Name = rs.getString(40);
				String major = rs.getString(41);
				String title = rs.getString(42);
				String research_project_Name = rs.getString(43);
				String funding_source = rs.getString(44);
				String projectleader = rs.getString(45);
				
				String stu_studentID = rs.getString(46);
				String stu_nameTh = rs.getString(47);
				String stu_surnameTh = rs.getString(48);
				String stu_programNameTh = rs.getString(49);
				String stu_facultyNameTh = rs.getString(50);
				String stu_statusName = rs.getString(51);
				String stu_status = rs.getString(52);
				String stu_levelName = rs.getString(53);
				String stu_idcard = rs.getString(54);
				String stu_advisor_name = rs.getString(55);
				String stu_emailadvisor = rs.getString(56);
				String stu_phonenumber = rs.getString(57);
				String stu_email = rs.getString(58);
				
				Student student = new Student(stu_studentID,stu_nameTh,stu_surnameTh,stu_programNameTh,stu_facultyNameTh,stu_statusName,stu_status,stu_levelName,stu_idcard);
				student.setAdvisor_name(stu_advisor_name);
				student.setEmail(stu_email);
				student.setEmailadvisor(stu_emailadvisor);
				student.setPhonenumber(stu_phonenumber);

				String p_personnel_ID = rs.getString(59);
				String p_personnel_Prefix = rs.getString(60);
				String p_personnel_FirstName = rs.getString(61);
				String p_personnel_LastName = rs.getString(62);
				String p_email = rs.getString(63);
				String p_position = rs.getString(64);
				String p_personnel_type = rs.getString(65);
				String p_faculty  = rs.getString(66);
				Personnel personnel = new Personnel(p_personnel_ID, p_personnel_Prefix, p_personnel_FirstName,
						p_personnel_LastName, p_email, p_position, p_personnel_type,p_faculty);
				
				List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm = this
						.getlistApprovalOfServiceRequestForm(sericeRequestFormNumber);
				List<Schedule> lsitSchedule = this.getlistschedule(sericeRequestFormNumber);
				List<Rentalschedule> listrentalschedule = this.getlistrentalschedule(sericeRequestFormNumber) ; 
				form = new FlatFeeForm(sericeRequestFormNumber, type, pickupdate, type_Work_Name, subject,
						usage_characteristics_Name, period_of_use, sumPrice, additionalcost, booking_status, approveLV,
						 type_course_Name, course_id, course_Name, major, title, research_project_Name,
						funding_source, projectleader);

				form.setPayment(payment);
				form.setMember(member);
				form.setChangeForm(changeForm);
				form.setListSchedule(lsitSchedule);
				form.setLsitApprovalOfServiceRequestForm(lsitApprovalOfServiceRequestForm);
				form.setListrentalschedule(listrentalschedule);
				form.setStudent(student);
				form.setPersonnel(personnel);
				
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return form;
	}
	
	public List<Rentalschedule> getlistrentalschedule(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Rentalschedule> listrentalschedule = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT rs.rentalscheduleid,rs.startDate,rs.endDate,rs.term,rs.maintenancefee,rs.additionalcost"
					+ " ,s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee "
					+ " ,st.sciInstrumentsType_ID,st.sciInstrumentsType_Name "
					+ " FROM (rentalschedule rs inner join sciinstruments s on rs.sciInstruments_ID = s.sciInstruments_ID) "
					+ " inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID "
					+ " where rs.sericeRequestFormNumber like '" + key + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String rentalscheduleid = rs.getString(1);
				Date startDate = rs.getDate(2);
				Date endDate = rs.getDate(3);
				String term = rs.getString(4);
				double maintenancefee = rs.getDouble(5);
				double additionalcost = rs.getDouble(6);
				
				
				String sciInstruments_ID = rs.getString(7);
				String sciInstruments_Name = rs.getString(8);
				String sciInstruments_status = rs.getString(9);
				String sciInstruments_detail = rs.getString(10);
				Double priceType_A = rs.getDouble(11);
				Double priceType_B = rs.getDouble(12);
				Double priceType_C = rs.getDouble(13);
				Double priceType_D = rs.getDouble(14);
				Double priceFlatFee = rs.getDouble(15);
				String sciInstrumentsType_ID = rs.getString(16);
				String sciInstrumentsType_Name = rs.getString(17);
				SciInstrumentsType sciInstrumentsType = new SciInstrumentsType(sciInstrumentsType_ID,
						sciInstrumentsType_Name);
				SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name,
						sciInstruments_status, sciInstruments_detail, priceType_A, priceType_B, priceType_C,
						priceType_D, priceFlatFee);
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);

				Rentalschedule rentalschedules = new Rentalschedule(rentalscheduleid,startDate,endDate,term,maintenancefee);
				rentalschedules.setAdditionalcost(additionalcost);
				rentalschedules.setSciInstruments(sciInstruments);
				listrentalschedule.add(rentalschedules);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listrentalschedule;
	}
	

	public List<ApprovalOfServiceRequestForm> getlistApprovalOfServiceRequestForm(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = "	SELECT asf.approval_ID,asf.approveNo,asf.date_approve,asf.reason,asf.status"
					+ "	,sf.staffid,sf.prefix,sf.firstname,sf.lastname,sf.email,sf.phonenumber"
					+ "	,cp.coursepresidentid, cp.prefix, cp.firstname,cp.lastname,cp.email, cp.branch,cp.faculty, cp.university,cp.phonenumber"
					+ " ,p.personnel_ID,p.personnel_Prefix,p.personnel_FirstName,p.personnel_LastName,p.email,p.position,p.personnel_type,p.faculty"
					+ "	FROM ((approvalofservicerequestform asf left join staff sf on sf.staffid = asf.staffid)"
					+ "	 left join coursepresident cp on asf.coursepresidentid = cp.coursepresidentid )"
					+ "	left join personnel p on asf.personnel_ID = p.personnel_ID"
					+ " where sericeRequestFormNumber like '" + key + "'" + " order by asf.approveNo";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int approval_ID = rs.getInt(1);
				int approveNo = rs.getInt(2);
				Date date_approve = rs.getDate(3);
				String reason = rs.getString(4);
				String status = rs.getString(5);

				String sf_staffid = rs.getString(6);
				String sf_prefix = rs.getString(7);
				String sf_firstname = rs.getString(8);
				String sf_lastname = rs.getString(9);
				String sf_email = rs.getString(10);
				String sf_phonenumber = rs.getString(11);
				Staff staff = new Staff(sf_staffid, sf_prefix, sf_firstname, sf_lastname, sf_email, sf_phonenumber);

				String cp_coursepresidentid = rs.getString(12);
				String cp_prefix = rs.getString(13);
				String cp_firstname = rs.getString(14);
				String cp_lastname = rs.getString(15);
				String cp_email = rs.getString(16);
				String cp_branch = rs.getString(17);
				String cp_faculty = rs.getString(18);
				String cp_university = rs.getString(19);
				String cp_phonenumber = rs.getString(20);
				Coursepresident coursepresident = new Coursepresident(cp_coursepresidentid, cp_prefix, cp_firstname,
						cp_lastname, cp_email, cp_branch, cp_faculty, cp_university, cp_phonenumber);
				
				
				String personnel_ID = rs.getString(21); 
				String personnel_Prefix = rs.getString(22);
				String personnel_FirstName = rs.getString(23);
				String personnel_LastName = rs.getString(24);
				String email = rs.getString(25);
				String position = rs.getString(26);
				String personnel_type = rs.getString(27);	
				String faculty = rs.getString(28);
				Personnel personnel = new Personnel(personnel_ID,personnel_Prefix,personnel_FirstName,personnel_LastName,email,position,personnel_type,faculty);
				
				
				ApprovalOfServiceRequestForm approvalOfServiceRequestForm = new ApprovalOfServiceRequestForm(
						approval_ID, date_approve, status, reason, approveNo);
				approvalOfServiceRequestForm.setStaff(staff);
				approvalOfServiceRequestForm.setCoursepresident(coursepresident);
				approvalOfServiceRequestForm.setPersonnel(personnel);
				lsitApprovalOfServiceRequestForm.add(approvalOfServiceRequestForm);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return lsitApprovalOfServiceRequestForm;
	}

	public List<Schedule> getlistschedule(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Schedule> lsitSchedule = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT sd.schedule_ID,sd.startDateTime,sd.endDateTime " + 
					" ,s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee " + 
					" ,st.sciInstrumentsType_ID,st.sciInstrumentsType_Name " + 
					" ,sf.staffid,sf.prefix,sf.firstname,sf.lastname,sf.email,sf.phonenumber" + 
					" ,cp.coursepresidentid, cp.prefix, cp.firstname,cp.lastname,cp.email, cp.branch,cp.faculty, cp.university,cp.phonenumber" + 
					" FROM (((schedule sd inner join sciinstruments s on sd.sciInstruments_ID = s.sciInstruments_ID) " + 
					" inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID )" + 
					" left join staff sf on sf.staffid = s.staffid)" + 
					" left join coursepresident cp on s.coursepresidentid = cp.coursepresidentid "
					+ " where sericeRequestFormNumber like '" + key + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String schedule_ID = rs.getString(1);
				Timestamp startDateTime = rs.getTimestamp(2);
				Timestamp endDateTime = rs.getTimestamp(3);
				String sciInstruments_ID = rs.getString(4);
				String sciInstruments_Name = rs.getString(5);
				String sciInstruments_status = rs.getString(6);
				String sciInstruments_detail = rs.getString(7);
				Double priceType_A = rs.getDouble(8);
				Double priceType_B = rs.getDouble(9);
				Double priceType_C = rs.getDouble(10);
				Double priceType_D = rs.getDouble(11);
				Double priceFlatFee = rs.getDouble(12);
				
				String sciInstrumentsType_ID = rs.getString(13);
				String sciInstrumentsType_Name = rs.getString(14);
				SciInstrumentsType sciInstrumentsType = new SciInstrumentsType(sciInstrumentsType_ID,
						sciInstrumentsType_Name);
				
				String sf_staffid = rs.getString(15);
				String sf_prefix = rs.getString(16);
				String sf_firstname = rs.getString(17);
				String sf_lastname = rs.getString(18);
				String sf_email = rs.getString(19);
				String sf_phonenumber = rs.getString(20);
				Staff staff = new Staff(sf_staffid, sf_prefix, sf_firstname, sf_lastname, sf_email, sf_phonenumber);

				String cp_coursepresidentid = rs.getString(21);
				String cp_prefix = rs.getString(22);
				String cp_firstname = rs.getString(23);
				String cp_lastname = rs.getString(24);
				String cp_email = rs.getString(25);
				String cp_branch = rs.getString(26);
				String cp_faculty = rs.getString(27);
				String cp_university = rs.getString(28);
				String cp_phonenumber = rs.getString(29);
				Coursepresident coursepresident = new Coursepresident(cp_coursepresidentid, cp_prefix, cp_firstname,
						cp_lastname, cp_email, cp_branch, cp_faculty, cp_university, cp_phonenumber);

				
				SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name,
						sciInstruments_status,sciInstruments_detail, priceType_A, priceType_B, priceType_C, priceType_D,priceFlatFee);
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);
				sciInstruments.setCoursepresident(coursepresident);
				sciInstruments.setStaff(staff);

				Schedule schedule = new Schedule(schedule_ID, startDateTime, endDateTime);
				schedule.setSciInstruments(sciInstruments);
				lsitSchedule.add(schedule);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return lsitSchedule;
	}

}
