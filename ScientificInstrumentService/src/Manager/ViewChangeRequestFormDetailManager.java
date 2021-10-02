package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import Class_End_Project.ApprovalChangeForm;
import Class_End_Project.ApprovalOfServiceRequestForm;
import Class_End_Project.ChangeForm;
import Class_End_Project.Changelist;
import Class_End_Project.Coursepresident;
import Class_End_Project.Member;
import Class_End_Project.Personnel;
import Class_End_Project.Schedule;
import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.Staff;
import LoginMJU.Student;
import projectutil.ConnectionDB;

public class ViewChangeRequestFormDetailManager {

	public ChangeForm getChangeRequestFormByid(String changeFormNumber) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		ChangeForm changeForm = new ChangeForm();
		try {

			stmt = con.createStatement();
			String sql = "SELECT cf.changeFormNumber,cf.createdate,cf.pickupdate,cf.type,cf.approveLV,cf.change_status"
					+ ",sf.sericeRequestFormNumber,sf.type,sf.pickupdate,type_Work_Name,sf.subject,sf.usage_characteristics_Name,sf.period_of_use,sf.sumPrice,sf.additionalcost,sf.booking_status,sf.approveLV,sf.createdate"
					+",m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.position,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name"
					+ ",stu.studentID,stu.nameTh,stu.surnameTh,stu.programNameTh,stu.facultyNameTh,stu.statusName,stu.status,stu.levelName,stu.idcard,stu.advisor_name,stu.emailadvisor,stu.phonenumber,stu.email"
					+ " ,pn.personnel_ID,pn.personnel_Prefix,pn.personnel_FirstName,pn.personnel_LastName,pn.email,pn.position,pn.personnel_type,pn.faculty"
					+" FROM (((changeform cf inner join sericerequestform sf on cf.sericeRequestFormNumber = sf.sericeRequestFormNumber) "
					+" left join member m on m.member_ID = sf.member_ID)"
					+ " left join student stu on stu.studentID = sf.studentID)" 
					+ " left join personnel pn on pn.personnel_ID = sf.personnel_ID" 
					+" where cf.changeFormNumber like '"+changeFormNumber+"'" ;
					
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				/* Set ChangeForm */
				String cf_changeFormNumber = rs.getString(1);
				String cf_type = rs.getString(4);
				Date cf_pickupdate  = rs.getDate(3);
				int cf_approveLV = rs.getInt(5);
				Timestamp cf_createdate = rs.getTimestamp(2);
				String cf_change_status = rs.getString(6);
				/* End Set ChangeForm */

				/* Set SericeRequestForm */
				String sericeRequestFormNumber = rs.getString(7);
				String type = rs.getString(8);
				Date pickupdate = rs.getDate(9);
				String type_Work_Name = rs.getString(10);
				String subject = rs.getString(11);
				String usage_characteristics_Name = rs.getString(12);
				int period_of_use = rs.getInt(13);
				Double sumPrice = rs.getDouble(14);
				Double additionalcost = rs.getDouble(15);
				String booking_status = rs.getString(16);
				int approveLV = rs.getInt(17);
				Timestamp createdate = rs.getTimestamp(18);
				SericeRequestForm sericeRequestForm = new SericeRequestForm(sericeRequestFormNumber, type, pickupdate,
						type_Work_Name, subject, usage_characteristics_Name, period_of_use, sumPrice, additionalcost,
						booking_status, approveLV);
				sericeRequestForm.setCreatedate(createdate);
				sericeRequestForm.setListSchedule(this.getlistschedule(sericeRequestFormNumber));
				/* End Set SericeRequestForm */

		
				

				/* Set Member */
				String member_ID = rs.getString(19);
				String member_Prefix = rs.getString(20);
				String member_FirstName = rs.getString(21);
				String member_LastName = rs.getString(22);
				String typeMember = rs.getString(23);
				String position =  rs.getString(24);
				String email = rs.getString(25);
				String phonenumber = rs.getString(26);
				String studentID = rs.getString(27);
				String degree = rs.getString(28);
				String branch = rs.getString(29);
				String faculty = rs.getString(30);
				String university = rs.getString(31);
				String advisor_name = rs.getString(32);
				String emailadvisor = rs.getString(33);
				String company_name = rs.getString(34);



				Member member = new Member(member_ID, member_Prefix, member_FirstName, member_LastName, email,
						typeMember,position, phonenumber, studentID, degree, branch, faculty, university, advisor_name,
						emailadvisor, company_name);
				
				sericeRequestForm.setMember(member);
				/* End Set Member */

				String stu_studentID = rs.getString(35);
				String stu_nameTh = rs.getString(36);
				String stu_surnameTh = rs.getString(37);
				String stu_programNameTh = rs.getString(38);
				String stu_facultyNameTh = rs.getString(39);
				String stu_statusName = rs.getString(40);
				String stu_status = rs.getString(41);
				String stu_levelName = rs.getString(42);
				String stu_idcard = rs.getString(43);
				String stu_advisor_name = rs.getString(44);
				String stu_emailadvisor = rs.getString(45);
				String stu_phonenumber = rs.getString(46);
				String stu_email = rs.getString(47);
				
				Student student = new Student(stu_studentID,stu_nameTh,stu_surnameTh,stu_programNameTh,stu_facultyNameTh,stu_statusName,stu_status,stu_levelName,stu_idcard);
				student.setAdvisor_name(stu_advisor_name);
				student.setEmail(stu_email);
				student.setEmailadvisor(stu_emailadvisor);
				student.setPhonenumber(stu_phonenumber);
				sericeRequestForm.setStudent(student);
			
				String p_personnel_ID = rs.getString(48);
				String p_personnel_Prefix = rs.getString(49);
				String p_personnel_FirstName = rs.getString(50);
				String p_personnel_LastName = rs.getString(51);
				String p_email = rs.getString(52);
				String p_position = rs.getString(53);
				String p_personnel_type = rs.getString(54);
				String p_faculty  = rs.getString(55);
				Personnel personnel = new Personnel(p_personnel_ID, p_personnel_Prefix, p_personnel_FirstName,
						p_personnel_LastName, p_email, p_position, p_personnel_type,p_faculty);
				sericeRequestForm.setPersonnel(personnel);
				
				changeForm = new ChangeForm(cf_changeFormNumber,cf_type,cf_pickupdate,cf_approveLV);
				changeForm.setChange_status(cf_change_status);
				changeForm.setCreatedate(cf_createdate);
				changeForm.setSericeRequestForm(sericeRequestForm);
				changeForm.setListApprovalChangeForm(this.getlistApprovalChangeForm(cf_changeFormNumber));
				changeForm.setListSchedule(this.getlistschedule(cf_changeFormNumber));
				changeForm.setChangelists(this.getlistChangelist(cf_changeFormNumber));
				

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return changeForm;
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
					+ " where sericeRequestFormNumber like '" + key + "'"
					+ " or changeFormNumber like '"+key+"'";

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
						sciInstruments_status, sciInstruments_detail, priceType_A, priceType_B, priceType_C,
						priceType_D, priceFlatFee);
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
				Personnel personnel = new Personnel(personnel_ID, personnel_Prefix, personnel_FirstName,
						personnel_LastName, email, position, personnel_type,faculty);

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

	public List<ApprovalChangeForm>  getlistApprovalChangeForm(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<ApprovalChangeForm>  listApprovalChangeForm = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = "	SELECT acf.approval_ID,acf.approveNo,acf.date_approve,acf.reason,acf.status,acf.emailadvisor"
					+ " ,p.personnel_ID,p.personnel_Prefix,p.personnel_FirstName,p.personnel_LastName,p.email,p.position,p.personnel_type,p.faculty"
					+ "	FROM approvalchangeform acf left join personnel p on acf.personnel_ID = p.personnel_ID"
					+ " where changeFormNumber like '" + key + "'" + " order by acf.approveNo";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int approval_ID = rs.getInt(1);
				int approveNo = rs.getInt(2);
				Date date_approve = rs.getDate(3);
				String reason = rs.getString(4);
				String status = rs.getString(5);
				String emailadvisor = rs.getString(6);


				String personnel_ID = rs.getString(7);
				String personnel_Prefix = rs.getString(8);
				String personnel_FirstName = rs.getString(9);
				String personnel_LastName = rs.getString(10);
				String email = rs.getString(11);
				String position = rs.getString(12);
				String personnel_type = rs.getString(13);
				String faculty = rs.getString(14);
				Personnel personnel = new Personnel(personnel_ID, personnel_Prefix, personnel_FirstName,
						personnel_LastName, email, position, personnel_type,faculty);

				ApprovalChangeForm approvalChangeForm = new ApprovalChangeForm(approval_ID, date_approve, status, reason, approveNo);
				approvalChangeForm.setEmailadvisor(emailadvisor);
				approvalChangeForm.setPersonnel(personnel);
				listApprovalChangeForm.add(approvalChangeForm);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listApprovalChangeForm;
	}
	
	public List<Changelist>  getlistChangelist(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Changelist>  listChangelist = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = "  SELECT cl.changelistID,cl.detail" + 
			
					" ,ssd.schedule_ID,ssd.startDateTime,ssd.endDateTime " + 
					" ,ss.sciInstruments_ID,ss.sciInstruments_Name,ss.status,ss.detail,ss.priceType_A,ss.priceType_B,ss.priceType_C,ss.priceType_D,ss.priceFlatFee " + 
					" ,sst.sciInstrumentsType_ID,sst.sciInstrumentsType_Name " + 
					" ,esd.schedule_ID,esd.startDateTime,esd.endDateTime " + 
					" ,es.sciInstruments_ID,es.sciInstruments_Name,es.status,es.detail,es.priceType_A,es.priceType_B,es.priceType_C,es.priceType_D,es.priceFlatFee " + 
					" ,est.sciInstrumentsType_ID,est.sciInstrumentsType_Name " + 
					" FROM (((((changelist cl left join schedule ssd  on ssd.schedule_ID = cl.historyscheduleID)" + 
					" left join sciinstruments ss on ssd.sciInstruments_ID = ss.sciInstruments_ID) " + 
					" left join sciinstrumentstype sst on ss.sciInstrumentsType_ID = sst.sciInstrumentsType_ID)" + 
					" left join schedule esd  on esd.schedule_ID = cl.newscheduleID)" + 
					" left join sciinstruments es on esd.sciInstruments_ID = es.sciInstruments_ID ) " + 
					" left join sciinstrumentstype est on es.sciInstrumentsType_ID = est.sciInstrumentsType_ID "
				  + " where cl.changeFormNumber like '" + key + "'" ;

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String changelistID = rs.getString(1);
				String detail = rs.getString(2);

				Changelist changelist = new Changelist(changelistID,detail);
				
				String schedule_ID = rs.getString(3);
				Timestamp startDateTime = rs.getTimestamp(4);
				Timestamp endDateTime = rs.getTimestamp(5);
				String sciInstruments_ID = rs.getString(6);
				String sciInstruments_Name = rs.getString(7);
				String sciInstruments_status = rs.getString(8);
				String sciInstruments_detail = rs.getString(9);
				Double priceType_A = rs.getDouble(10);
				Double priceType_B = rs.getDouble(11);
				Double priceType_C = rs.getDouble(12);
				Double priceType_D = rs.getDouble(13);
				Double priceFlatFee = rs.getDouble(14);
				String sciInstrumentsType_ID = rs.getString(15);
				String sciInstrumentsType_Name = rs.getString(16);
				SciInstrumentsType sciInstrumentsType = new SciInstrumentsType(sciInstrumentsType_ID,
						sciInstrumentsType_Name);
				SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name,
						sciInstruments_status, sciInstruments_detail, priceType_A, priceType_B, priceType_C,
						priceType_D, priceFlatFee);
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);

				Schedule historyschedule = new Schedule(schedule_ID, startDateTime, endDateTime);
				historyschedule.setSciInstruments(sciInstruments);
				
				String new_schedule_ID = rs.getString(17);
				Timestamp new_startDateTime = rs.getTimestamp(18);
				Timestamp new_endDateTime = rs.getTimestamp(19);
				String new_sciInstruments_ID = rs.getString(20);
				String new_sciInstruments_Name = rs.getString(21);
				String new_sciInstruments_status = rs.getString(22);
				String new_sciInstruments_detail = rs.getString(23);
				Double new_priceType_A = rs.getDouble(24);
				Double new_priceType_B = rs.getDouble(25);
				Double new_priceType_C = rs.getDouble(26);
				Double new_priceType_D = rs.getDouble(27);
				Double new_priceFlatFee = rs.getDouble(28);
				String new_sciInstrumentsType_ID = rs.getString(29);
				String new_sciInstrumentsType_Name = rs.getString(30);
				SciInstrumentsType new_sciInstrumentsType = new SciInstrumentsType(new_sciInstrumentsType_ID,
						new_sciInstrumentsType_Name);
				SciInstruments new_sciInstruments = new SciInstruments(new_sciInstruments_ID, new_sciInstruments_Name,
						new_sciInstruments_status, new_sciInstruments_detail, new_priceType_A, new_priceType_B, new_priceType_C,
						new_priceType_D, new_priceFlatFee);
				sciInstruments.setSciInstrumentsType(new_sciInstrumentsType);

				Schedule newschedule =  new Schedule(new_schedule_ID, new_startDateTime, new_endDateTime);
				newschedule.setSciInstruments(new_sciInstruments);
				
				changelist.setHistoryschedule(historyschedule);
				changelist.setNewschedule(newschedule);
				listChangelist.add(changelist);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listChangelist;
	}
	
}
