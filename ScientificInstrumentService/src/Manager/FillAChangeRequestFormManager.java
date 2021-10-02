package Manager;

import java.sql.CallableStatement;
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
import Class_End_Project.Member;
import Class_End_Project.Payment;
import Class_End_Project.Schedule;
import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.Staff;
import LoginMJU.Student;
import projectutil.ConnectionDB;

public class FillAChangeRequestFormManager {
	
	public String getnextscheduleID() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "SID0000000";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextscheduleID()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) != null) {
					result = rs.getString(1);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getnextchangelistID() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "CLID000000";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextchangelistID()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) != null) {
					result = rs.getString(1);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean ischangerequestform(ChangeForm changeForm) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call ischangerequestform(?,?)}");
			stmt.setString(1, changeForm.getChangeFormNumber());
			stmt.setString(2, changeForm.getSericeRequestForm().getSericeRequestFormNumber());
			
			stmt.execute();

			
			
			CallableStatement stmt1 = null;
			CallableStatement stmt2 = null;
			
			if(changeForm.getChangelists().size()>0) {
				for(int i = 0 ; i < changeForm.getChangelists().size() ;i++) {
					System.out.println("Round = "+(i+1));
					if(changeForm.getChangelists().get(i).getNewschedule() != null) {
					stmt1 = con.prepareCall("{call isNewScheduleChangeForm(?,?,?,?,?)}");
					stmt1.setString(1, changeForm.getChangelists().get(i).getNewschedule().getSchedule_ID());
					stmt1.setTimestamp(2, changeForm.getChangelists().get(i).getNewschedule().getStartDateTime());
					stmt1.setTimestamp(3, changeForm.getChangelists().get(i).getNewschedule().getEndDateTime());
					stmt1.setString(4,	changeForm.getChangelists().get(i).getNewschedule().getSciInstruments().getSciInstruments_ID());
					stmt1.setString(5, changeForm.getChangeFormNumber());
					stmt1.execute();
					stmt1.close();
				}
					stmt2 = con.prepareCall("{call insertChangelists(?,?,?,?,?)}");
					stmt2.setString(1, changeForm.getChangelists().get(i).getChangelistID());
					stmt2.setString(2, changeForm.getChangelists().get(i).getHistoryschedule().getSchedule_ID());
					if(changeForm.getChangelists().get(i).getNewschedule() != null) {
					stmt2.setString(3, changeForm.getChangelists().get(i).getNewschedule().getSchedule_ID());
					}else {
						stmt2.setString(3, null);	
					}
					stmt2.setString(4,changeForm.getChangelists().get(i).getDetail());
					stmt2.setString(5,changeForm.getChangeFormNumber());
					
					stmt2.execute();
					stmt2.close();
				}
			}
			

			CallableStatement stmt3 = null;
			int approval_ID = 0;
			System.out.println("size is " + changeForm.getListApprovalChangeForm().size());
			if(changeForm.getListApprovalChangeForm().size() > 0) {
			for (int i = 0; i < changeForm.getListApprovalChangeForm().size(); i++) {
				

				if (changeForm.getListApprovalChangeForm().get(i).getEmailadvisor() != null) {
					System.out.println("advisor");
					stmt3 = con.prepareCall("{call isapprovalofchangerequestform1(?,?,?)}");
					stmt3.setInt(1, changeForm.getListApprovalChangeForm().get(i).getApproval_ID());
					stmt3.setString(2, changeForm.getListApprovalChangeForm().get(i).getChangeForm().getChangeFormNumber());
					stmt3.setString(3,changeForm.getListApprovalChangeForm().get(i).getEmailadvisor());
					stmt3.execute();
				
				} else if (changeForm.getListApprovalChangeForm().get(i).getEmailadvisor() == null
						&& changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID() != null
						&& changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_type().equals("LaboratoryStaff")) {
					System.out.println("if LaboratoryStaff");
					stmt3 = con.prepareCall("{call isapprovalofchangerequestform2(?,?,?,?)}");
					stmt3.setInt(1, changeForm.getListApprovalChangeForm().get(i).getApproval_ID());
					stmt3.setString(2, changeForm.getListApprovalChangeForm().get(i).getChangeForm().getChangeFormNumber());
					stmt3.setString(3,changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID());
					stmt3.setInt(4,2);	
					stmt3.execute();


				}else if(changeForm.getListApprovalChangeForm().get(i).getEmailadvisor() == null
						&& changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID() != null
						&& changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_type().equals("Coursepresident")) {
					System.out.println("if Coursepresident");
					stmt3 = con.prepareCall("{call isapprovalofchangerequestform2(?,?,?,?)}");
					stmt3.setInt(1, changeForm.getListApprovalChangeForm().get(i).getApproval_ID());
					stmt3.setString(2, changeForm.getListApprovalChangeForm().get(i).getChangeForm().getChangeFormNumber());
					stmt3.setString(3,changeForm.getListApprovalChangeForm().get(i).getPersonnel().getPersonnel_ID());
					stmt3.setInt(4,3);	
					stmt3.execute();
				}
				approval_ID = changeForm.getListApprovalChangeForm().get(i).getApproval_ID();

			}

			approval_ID = approval_ID + 1;
			stmt3 = con.prepareCall("{call isapprovalofchangerequestform3(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, changeForm.getChangeFormNumber());
			stmt3.execute();

			approval_ID = approval_ID + 1;
			stmt3 = con.prepareCall("{call isapprovalofchangerequestform4(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2,changeForm.getChangeFormNumber());
			stmt3.execute();

			stmt3.close();
			}
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public SericeRequestForm getServiceRequestFormByID(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		SericeRequestForm sericeRequestForm = null;
		List<Schedule> lsitSchedule = new Vector<>();

		try {

			stmt = con.createStatement();
			String sql = "SELECT sf.sericeRequestFormNumber,sf.type,sf.pickupdate,sf.type_Work_Name,sf.subject,sf.usage_characteristics_Name,sf.period_of_use,sf.sumPrice,sf.additionalcost,sf.booking_status,sf.approveLV,sf.createdate"
					+ ",p.payment_ID,p.paymentDateTime,p.status,p.payment_type,p.receipts_on_behalf,p.totalprice "
					+ ",cf.changeFormNumber,cf.approveLV,cf.pickupdate,cf.type"
					+ ",m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.position,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name"
					+ ",stu.studentID,stu.nameTh,stu.surnameTh,stu.programNameTh,stu.facultyNameTh,stu.statusName,stu.status,stu.levelName,stu.idcard,stu.advisor_name,stu.emailadvisor,stu.phonenumber,stu.email"
					+ " FROM (((sericerequestform sf left join payment p on sf.payment_ID = p.payment_ID) "
					+ " left join changeform cf on cf.sericeRequestFormNumber = sf.sericeRequestFormNumber) "
					+ " left join member m on m.member_ID = sf.member_ID) "
					+ " left join student stu on stu.studentID = sf.studentID" 
					+ " where sf.sericeRequestFormNumber like '"
					+ key + "'";
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

				/* Set member */
				String member_ID = rs.getString(23);
				String member_Prefix = rs.getString(24);
				String member_FirstName = rs.getString(25);
				String member_LastName = rs.getString(26);
				String typeMember = rs.getString(27);
				String position =  rs.getString(28);
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
						typeMember,position, phonenumber, studentID, degree, branch, faculty, university, advisor_name,
						emailadvisor, company_name);
				
				/* End Set member */
				
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
				

				List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm = new Vector<>();

				sericeRequestForm = new SericeRequestForm(sericeRequestFormNumber, type, pickupdate, type_Work_Name,
						subject, usage_characteristics_Name, period_of_use, sumPrice, additionalcost, booking_status,
						approveLV);
				sericeRequestForm.setCreatedate(createdate);
				sericeRequestForm.setPayment(payment);
				sericeRequestForm.setMember(member);
				sericeRequestForm.setChangeForm(changeForm);
				sericeRequestForm.setLsitApprovalOfServiceRequestForm(lsitApprovalOfServiceRequestForm);
				lsitSchedule = this.getlistschedule(sericeRequestFormNumber);
				sericeRequestForm.setListSchedule(lsitSchedule);
				sericeRequestForm.setStudent(student);

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return sericeRequestForm;
	}

	public List<Schedule> getlistschedule(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Schedule> lsitSchedule = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT sd.schedule_ID,sd.startDateTime,sd.endDateTime "
					+ " ,s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee "
					+ " ,st.sciInstrumentsType_ID,st.sciInstrumentsType_Name "
					+ " FROM (schedule sd inner join sciinstruments s on sd.sciInstruments_ID = s.sciInstruments_ID) "
					+ " inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID "
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
				SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name,
						sciInstruments_status,sciInstruments_detail, priceType_A, priceType_B, priceType_C, priceType_D,priceFlatFee);
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);

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
	
	
	public String getnextchangeFormNumber() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "C000/0000";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextchangeFormNumber()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) != null) {
					result = rs.getString(1);
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getnextchangeformapproval_ID() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		int result = 0;
		try {

			CallableStatement stmt = con.prepareCall("{call getnextchangeformapproval_ID()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	public SciInstruments getSciInstruments(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		SciInstruments sciInstruments = new SciInstruments();
		try {

			stmt = con.createStatement();
			String sql = " SELECT  s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee "
					+ " ,st.sciInstrumentsType_ID,st.sciInstrumentsType_Name "
					+ " ,sf.staffid,sf.prefix,sf.firstname,sf.lastname,sf.email,sf.phonenumber"
					+ " ,cp.coursepresidentid, cp.prefix, cp.firstname,cp.lastname,cp.email, cp.branch,cp.faculty, cp.university,cp.phonenumber"
					+ " FROM  ((sciinstruments s  inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID )"
					+ " left join staff sf on sf.staffid = s.staffid)"
					+ " left join coursepresident cp on s.coursepresidentid = cp.coursepresidentid"
					+ " where  s.sciInstruments_ID = '" + key + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String sciInstruments_ID = rs.getString(1);
				String sciInstruments_Name = rs.getString(2);
				String status = rs.getString(3);
				String detail = rs.getString(4);
				Double priceType_A = rs.getDouble(5);
				Double priceType_B = rs.getDouble(6);
				Double priceType_C = rs.getDouble(7);
				Double priceType_D = rs.getDouble(8);
				Double priceFlatFee = rs.getDouble(9);
				sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name, status, detail, priceType_A,
						priceType_B, priceType_C, priceType_D, priceFlatFee);

				String sciInstrumentsType_ID = rs.getString(10);
				String sciInstrumentsType_Name = rs.getString(11);
				SciInstrumentsType sciInstrumentsType = new SciInstrumentsType(sciInstrumentsType_ID,
						sciInstrumentsType_Name);

				String sf_staffid = rs.getString(12);
				String sf_prefix = rs.getString(13);
				String sf_firstname = rs.getString(14);
				String sf_lastname = rs.getString(15);
				String sf_email = rs.getString(16);
				String sf_phonenumber = rs.getString(17);
				Staff staff = new Staff(sf_staffid, sf_prefix, sf_firstname, sf_lastname, sf_email, sf_phonenumber);

				String cp_coursepresidentid = rs.getString(18);
				String cp_prefix = rs.getString(19);
				String cp_firstname = rs.getString(20);
				String cp_lastname = rs.getString(21);
				String cp_email = rs.getString(22);
				String cp_branch = rs.getString(23);
				String cp_faculty = rs.getString(24);
				String cp_university = rs.getString(25);
				String cp_phonenumber = rs.getString(26);
				Coursepresident coursepresident = new Coursepresident(cp_coursepresidentid, cp_prefix, cp_firstname,
						cp_lastname, cp_email, cp_branch, cp_faculty, cp_university, cp_phonenumber);

				sciInstruments.setSciInstrumentsType(sciInstrumentsType);
				sciInstruments.setStaff(staff);
				sciInstruments.setCoursepresident(coursepresident);

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sciInstruments;
	}
}
