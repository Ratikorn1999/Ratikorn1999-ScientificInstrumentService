package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import Class_End_Project.Coursepresident;
import Class_End_Project.FlatFeeForm;

import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.Staff;
import LoginMJU.Student;
import projectutil.ConnectionDB;

public class FilltheServiceRequestFormManager {

	public String getnextsericeRequestFormNumber() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "E000/";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextsericeRequestFormNumber()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getnextFlatFeeFormNumber() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "A000/";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextFlatFeeFormNumber()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

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

	public String getnextrentalscheduleid() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "RID0000000";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextrentalscheduleid()}");

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

	public int getnextapproval_ID() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		int result = 0;
		try {

			CallableStatement stmt = con.prepareCall("{call getnextapproval_ID()}");

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

	public boolean updateStudent(Student stu) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		boolean resule = false;

		try {

			CallableStatement stmt2 = con.prepareCall("{call updateStudent(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt2.setString(1, stu.getStudentID());
			stmt2.setString(2, stu.getNameTh());
			stmt2.setString(3, stu.getSurnameTh());
			stmt2.setString(4, stu.getProgramNameTh());
			stmt2.setString(5, stu.getFacultyNameTh());
			stmt2.setString(6, stu.getStatusName());
			stmt2.setString(7, stu.getStatus());
			stmt2.setString(8, stu.getLevelName());
			stmt2.setString(9, stu.getIdcard());
			stmt2.setString(10, stu.getAdvisor_name());
			stmt2.setString(11, stu.getEmailadvisor());
			stmt2.setString(12, stu.getPhonenumber());
			stmt2.setString(13, stu.getEmail());

			stmt2.execute();
			resule = true;

			con.close();
			stmt2.close();

		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return resule;
	}

	public Boolean isFillTheServiceRequestForm(SericeRequestForm serviceRequestForm) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {
			CallableStatement stmt = con.prepareCall("{call isFillTheServiceRequestForm(?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, serviceRequestForm.getSericeRequestFormNumber());
			stmt.setDouble(2, serviceRequestForm.getAdditionalcost());
			stmt.setInt(3, serviceRequestForm.getPeriod_of_use());
			stmt.setString(4, serviceRequestForm.getSubject());
			stmt.setDouble(5, serviceRequestForm.getSumPrice());
			stmt.setString(6, serviceRequestForm.getType_Work_Name());
			stmt.setString(7, serviceRequestForm.getUsage_characteristics_Name());
			if (serviceRequestForm.getMember() == null) {
				stmt.setString(8, null);
			} else {
				stmt.setString(8, serviceRequestForm.getMember().getMember_ID());
			}
			if (serviceRequestForm.getStudent() == null) {
				stmt.setString(9, null);
			} else {
				stmt.setString(9, serviceRequestForm.getStudent().getStudentID());
			}
			if (serviceRequestForm.getPersonnel() == null) {
				stmt.setString(10, null);
			} else {
				stmt.setString(10, serviceRequestForm.getPersonnel().getPersonnel_ID());
			}

			stmt.execute();

			CallableStatement stmt2 = null;
			for (int i = 0; i < serviceRequestForm.getListSchedule().size(); i++) {
				stmt2 = con.prepareCall("{call isSchedule(?,?,?,?,?,?,?)}");
				stmt2.setString(1, serviceRequestForm.getListSchedule().get(i).getSchedule_ID());
				stmt2.setTimestamp(2, serviceRequestForm.getListSchedule().get(i).getStartDateTime());
				stmt2.setTimestamp(3, serviceRequestForm.getListSchedule().get(i).getEndDateTime());
				stmt2.setString(4,
						serviceRequestForm.getListSchedule().get(i).getSciInstruments().getSciInstruments_ID());
				stmt2.setString(5, serviceRequestForm.getSericeRequestFormNumber());
				stmt2.setString(6, serviceRequestForm.getListSchedule().get(i).getTime());
				stmt2.setDouble(7, serviceRequestForm.getListSchedule().get(i).getMaintenancefee());
				stmt2.execute();

			}
			stmt2.close();

			CallableStatement stmt3 = null;

			int approval_ID = 0;
			System.out.println("size is " + serviceRequestForm.getLsitApprovalOfServiceRequestForm().size());
			for (int i = 0; i < serviceRequestForm.getLsitApprovalOfServiceRequestForm().size(); i++) {
				System.out.println("=============================================");
				System.out.println("Round is " + i);
				System.out.println("C " + serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
						.getCoursepresident().getCoursepresidentid());
				System.out.println(
						"S " + serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid());
				System.out.println(
						"E " + serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor());

				if (serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor() != null) {
					System.out.println("else if no 1");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform1(?,?,?)}");
					stmt3.setInt(1, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getSericeRequestForm().getSericeRequestFormNumber());
					stmt3.setString(3,
							serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor());
					stmt3.execute();
				} else if (serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor() == null
						&& serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
								.getCoursepresidentid() != null
						&& serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff()
								.getStaffid() != null) {

					System.out.println("if no 1");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform2(?,?,?)}");
					stmt3.setInt(1, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getSericeRequestForm().getSericeRequestFormNumber());
					stmt3.setString(3,
							serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid());
					stmt3.execute();

					i++;
					System.out.println("if no 2");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform3(?,?,?)}");
					stmt3.setInt(1, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getSericeRequestForm().getSericeRequestFormNumber());
					stmt3.setString(3, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getCoursepresident().getCoursepresidentid());
					stmt3.execute();

				} else if (serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff()
						.getStaffid() != null
						&& serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
								.getCoursepresidentid() == null) {
					System.out.println("else if no 1");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform2(?,?,?)}");
					stmt3.setInt(1, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getSericeRequestForm().getSericeRequestFormNumber());
					stmt3.setString(3,
							serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid());
					stmt3.execute();
				} else if (serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
						.getCoursepresidentid() != null
						&& serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff()
								.getStaffid() == null) {
					System.out.println("else if no 2");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform3(?,?,?)}");
					stmt3.setInt(1, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getSericeRequestForm().getSericeRequestFormNumber());
					stmt3.setString(3, serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i)
							.getCoursepresident().getCoursepresidentid());
					stmt3.execute();
				}

				approval_ID = serviceRequestForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID();

			}

			approval_ID = approval_ID + 1;
			stmt3 = con.prepareCall("{call isapprovalofservicerequestform4(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, serviceRequestForm.getSericeRequestFormNumber());
			stmt3.execute();

			approval_ID = approval_ID + 1;
			stmt3 = con.prepareCall("{call isapprovalofservicerequestform5(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, serviceRequestForm.getSericeRequestFormNumber());
			stmt3.execute();

			stmt3.close();

			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public boolean isFillTheFlatFeeForm(FlatFeeForm flatFeeForm) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con
					.prepareCall("{call isFillTheFlatFeeForm(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, flatFeeForm.getSericeRequestFormNumber());
			stmt.setDouble(2, flatFeeForm.getAdditionalcost());
			stmt.setInt(3, flatFeeForm.getPeriod_of_use());
			stmt.setString(4, flatFeeForm.getSubject());
			stmt.setDouble(5, flatFeeForm.getSumPrice());
			stmt.setString(6, flatFeeForm.getType_Work_Name());
			stmt.setString(7, flatFeeForm.getUsage_characteristics_Name());
			if (flatFeeForm.getMember() == null) {
				stmt.setString(8, null);
			} else {
				stmt.setString(8, flatFeeForm.getMember().getMember_ID());
			}
			stmt.setString(9, flatFeeForm.getType_course_Name());
			stmt.setString(10, flatFeeForm.getCourse_id());
			stmt.setString(11, flatFeeForm.getCourse_Name());
			stmt.setString(12, flatFeeForm.getMajor());
			stmt.setString(13, flatFeeForm.getTitle());
			stmt.setString(14, flatFeeForm.getResearch_project_Name());
			stmt.setString(15, flatFeeForm.getFunding_source());
			stmt.setString(16, flatFeeForm.getProjectleader());
			if (flatFeeForm.getStudent() != null) {
				stmt.setString(17, flatFeeForm.getStudent().getStudentID());
			} else {
				stmt.setString(17, null);
			}
			if (flatFeeForm.getPersonnel() == null) {
				stmt.setString(18, null);
			} else {
				stmt.setString(18, flatFeeForm.getPersonnel().getPersonnel_ID());
			}
			stmt.execute();

			for (int i = 0; i < flatFeeForm.getListrentalschedule().size(); i++) {
				CallableStatement stmt2 = con.prepareCall("{call insertRentalschedule(?,?,?,?,?,?,?)}");
				stmt2.setString(1, flatFeeForm.getListrentalschedule().get(i).getRentalscheduleid());
				stmt2.setDate(2, flatFeeForm.getListrentalschedule().get(i).getStartDate());
				stmt2.setDate(3, flatFeeForm.getListrentalschedule().get(i).getEndDate());
				stmt2.setString(4, flatFeeForm.getListrentalschedule().get(i).getTerm());
				stmt2.setDouble(5, flatFeeForm.getListrentalschedule().get(i).getMaintenancefee());
				stmt2.setString(6,
						flatFeeForm.getListrentalschedule().get(i).getSciInstruments().getSciInstruments_ID());
				stmt2.setString(7, flatFeeForm.getSericeRequestFormNumber());
				stmt2.execute();
			}

			CallableStatement stmt3 = null;

			int approval_ID = 0;
			System.out.println("size is " + flatFeeForm.getLsitApprovalOfServiceRequestForm().size());
			for (int i = 0; i < flatFeeForm.getLsitApprovalOfServiceRequestForm().size(); i++) {
				System.out.println("=============================================");
				System.out.println("Round is " + i);
				System.out.println("C " + flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
						.getCoursepresidentid());
				System.out.println(
						"S " + flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid());
				System.out.println("E " + flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor());

				if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor() != null) {
					System.out.println("else if no 1");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform1(?,?,?)}");
					stmt3.setInt(1, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getSericeRequestForm()
							.getSericeRequestFormNumber());
					stmt3.setString(3, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor());
					stmt3.execute();
				} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getEmailadvisor() == null
						&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
								.getCoursepresidentid() != null
						&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null) {

					System.out.println("if no 1");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform2(?,?,?)}");
					stmt3.setInt(1, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getSericeRequestForm()
							.getSericeRequestFormNumber());
					stmt3.setString(3,
							flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid());
					stmt3.execute();

					i++;
					System.out.println("if no 2");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform3(?,?,?)}");
					stmt3.setInt(1, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getSericeRequestForm()
							.getSericeRequestFormNumber());
					stmt3.setString(3, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
							.getCoursepresidentid());
					stmt3.execute();

				} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() != null
						&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
								.getCoursepresidentid() == null) {
					System.out.println("else if no 1");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform2(?,?,?)}");
					stmt3.setInt(1, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getSericeRequestForm()
							.getSericeRequestFormNumber());
					stmt3.setString(3,
							flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid());
					stmt3.execute();
				} else if (flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
						.getCoursepresidentid() != null
						&& flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getStaff().getStaffid() == null) {
					System.out.println("else if no 2");
					stmt3 = con.prepareCall("{call isapprovalofservicerequestform3(?,?,?)}");
					stmt3.setInt(1, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID());
					stmt3.setString(2, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getSericeRequestForm()
							.getSericeRequestFormNumber());
					stmt3.setString(3, flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getCoursepresident()
							.getCoursepresidentid());
					stmt3.execute();
				}

				approval_ID = flatFeeForm.getLsitApprovalOfServiceRequestForm().get(i).getApproval_ID();

			}

			approval_ID = approval_ID + 1;
			stmt3 = con.prepareCall("{call isapprovalofservicerequestform4(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, flatFeeForm.getSericeRequestFormNumber());
			stmt3.execute();

			approval_ID = approval_ID + 1;
			stmt3 = con.prepareCall("{call isapprovalofservicerequestform5(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, flatFeeForm.getSericeRequestFormNumber());
			stmt3.execute();

			stmt3.close();

			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
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

	public List<SciInstruments> getListlaboratoryInstrumentsforFlatFee() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<SciInstruments> listSciInstruments = new Vector<SciInstruments>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee"
					+ "	,st.sciInstrumentsType_ID,st.sciInstrumentsType_Name "
					+ "	,sf.staffid,sf.prefix,sf.firstname,sf.lastname,sf.email,sf.phonenumber"
					+ "	,cp.coursepresidentid, cp.prefix, cp.firstname,cp.lastname,cp.email, cp.branch,cp.faculty, cp.university,cp.phonenumber"
					+ "	FROM ((sciinstruments s inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID )"
					+ " left join staff sf on sf.staffid = s.staffid)"
					+ " left join coursepresident cp on s.coursepresidentid = cp.coursepresidentid"
					+ " where s.status like 'พร้อมใช้งาน'  and s.priceFlatFee > 0"

			;
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

				SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name, status,
						detail, priceType_A, priceType_B, priceType_C, priceType_D, priceFlatFee);
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);
				sciInstruments.setStaff(staff);
				sciInstruments.setCoursepresident(coursepresident);
				listSciInstruments.add(sciInstruments);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listSciInstruments;
	}

}
