package Manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import Class_End_Project.ApprovalTestAnalysisForm;
import Class_End_Project.Member;
import Class_End_Project.Payment;
import Class_End_Project.Personnel;
import Class_End_Project.TestAnalysisForm;
import Class_End_Project.TestItem;
import Class_End_Project.UseTestItem;
import LoginMJU.Student;
import projectutil.ConnectionDB;

public class ListTestingAnalysisFormManager {

	public List<TestAnalysisForm> getListTestAnalysisForm() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<TestAnalysisForm> listTestAnalysisForm = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = "SELECT tf.testAnalysisFormNumber,tf.type,tf.pickupdate,tf.language,tf.client_name,tf.address,tf.report_Address,tf.billing_Address"
					+ ",tf.packing_style,tf.additional_details,tf.receive_Analysis_Result"
					+ ",tf.resultsOfTheAnalysis,tf.approveLV,tf.createdate,tf.testing_status,tf.approvestatus  "
					+ ",p.payment_ID,p.paymentDateTime,p.status,p.payment_type,p.receipts_on_behalf,p.totalprice "
					+ ",m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name"
					+ ",ut.testItem_testitem_ID "
					+ ",stu.studentID,stu.nameTh,stu.surnameTh,stu.programNameTh,stu.facultyNameTh,stu.statusName,stu.status,stu.levelName,stu.idcard,stu.advisor_name,stu.emailadvisor,stu.phonenumber,stu.email"
					+ " ,pn.personnel_ID,pn.personnel_Prefix,pn.personnel_FirstName,pn.personnel_LastName,pn.email,pn.position,pn.personnel_type,pn.faculty"
					+ " FROM ((((testanalysisform tf inner join usetestitem ut on tf.testAnalysisFormNumber = ut.testAnalysisForm_testAnalysisFormNumber )"
					+ " left join payment p on tf.payment_ID = p.payment_ID )"
					+ " left join member m on m.member_ID = tf.member_ID)" 
					+ " left join student stu on stu.studentID = tf.studentID)"
					+ "left join personnel pn on pn.personnel_ID = tf.personnel_ID" 
					+ " Order by tf.createdate desc";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				/* Set TestAnalysisForm */
				String testAnalysisFormNumber = rs.getString(1);
				String type = rs.getString(2);
				Date pickupdate = rs.getDate(3);
				String language = rs.getString(4);
				String address = rs.getString(5);
				String client_name = rs.getString(6);
				String report_Address = rs.getString(7);
				String billing_Address = rs.getString(8);
				String packing_style = rs.getString(9);
				String additional_details = rs.getString(10);
				String receive_Analysis_Result = rs.getString(11);
				String resultsOfTheAnalysis = rs.getString(12);
				int approveLV = rs.getInt(13);
				Timestamp createdate = rs.getTimestamp(14);
				String testing_status = rs.getString(15);
				String approvestatus = rs.getString(16);
				/* End Set TestAnalysisForm */

				/* Set Payment */
				int payment_ID = rs.getInt(17);
				Timestamp paymentDateTime = rs.getTimestamp(18);
				String status = rs.getString(19);
				String payment_type = rs.getString(20);
				String receipts_on_behalf = rs.getString(21);
				Double totalprice = rs.getDouble(22);
				Payment payment = new Payment(payment_ID, status, payment_type, receipts_on_behalf, totalprice);
				payment.setPaymentDateTime(paymentDateTime);
				/* End Set Payment */

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

				/* End Set Member */
				
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
				
				List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm = new Vector<>();

				TestAnalysisForm testAnalysisForm = new TestAnalysisForm(testAnalysisFormNumber, type, pickupdate,
						language, client_name, address, report_Address, billing_Address, packing_style,
						additional_details, receive_Analysis_Result, resultsOfTheAnalysis, approveLV);
				testAnalysisForm.setApprovestatus(approvestatus);
				testAnalysisForm.setCreatedate(createdate);
				testAnalysisForm.setTesting_status(testing_status);
				testAnalysisForm.setPayment(payment);
				testAnalysisForm.setMember(member);
				testAnalysisForm.setStudent(student);
				testAnalysisForm.setLsitApprovalTestAnalysisForm(lsitApprovalTestAnalysisForm);
				testAnalysisForm.setPersonnel(personnel);
				listTestAnalysisForm.add(testAnalysisForm);

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return listTestAnalysisForm;
	}

	public TestAnalysisForm getTestAnalysisForm(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		TestAnalysisForm testanalysisform = new TestAnalysisForm();
		try {

			stmt = con.createStatement();
			String sql = "SELECT tf.testAnalysisFormNumber,tf.type,tf.pickupdate,tf.language,tf.address,tf.client_name,tf.report_Address,tf.billing_Address"
					+ ",tf.packing_style,tf.additional_details,tf.receive_Analysis_Result"
					+ ",tf.resultsOfTheAnalysis,tf.approveLV,tf.createdate,tf.testing_status "
					+ ",p.payment_ID,p.paymentDateTime,p.status,p.payment_type,p.receipts_on_behalf,p.totalprice "
					+ ",m.member_ID,m.member_Prefix,m.member_FirstName,m.member_LastName,m.typeMember,m.position,m.email,m.phonenumber,m.studentID,m.degree,m.branch,m.faculty,m.university,m.advisor_name,m.emailadvisor,m.company_name"
					+ ",stu.studentID,stu.nameTh,stu.surnameTh,stu.programNameTh,stu.facultyNameTh,stu.statusName,stu.status,stu.levelName,stu.idcard,stu.advisor_name,stu.emailadvisor,stu.phonenumber,stu.email"
					+ " ,pn.personnel_ID,pn.personnel_Prefix,pn.personnel_FirstName,pn.personnel_LastName,pn.email,pn.position,pn.personnel_type,pn.faculty"
					+ " FROM (((testanalysisform tf left join payment p on tf.payment_ID = p.payment_ID )"
					+ " left join member m on m.member_ID = tf.member_ID)"
					+ " left join student stu on stu.studentID = tf.studentID)" 
					+ "left join personnel pn on pn.personnel_ID = tf.personnel_ID" 
					+ " where tf.testAnalysisFormNumber like '"
					+ key + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				/* Set TestAnalysisForm */
				String testAnalysisFormNumber = rs.getString(1);
				String type = rs.getString(2);
				Date pickupdate = rs.getDate(3);
				String language = rs.getString(4);
				String address = rs.getString(5);
				String client_name = rs.getString(6);
				String report_Address = rs.getString(7);
				String billing_Address = rs.getString(8);
				String packing_style = rs.getString(9);
				String additional_details = rs.getString(10);
				String receive_Analysis_Result = rs.getString(11);
				String resultsOfTheAnalysis = rs.getString(12);
				int approveLV = rs.getInt(13);
				Timestamp createdate = rs.getTimestamp(14);
				String testing_status = rs.getString(15);
				/* End Set TestAnalysisForm */

				/* Set Payment */
				int payment_ID = rs.getInt(16);
				Timestamp paymentDateTime = rs.getTimestamp(17);
				String status = rs.getString(18);
				String payment_type = rs.getString(19);
				String receipts_on_behalf = rs.getString(20);
				Double totalprice = rs.getDouble(21);
				Payment payment = new Payment(payment_ID, status, payment_type, receipts_on_behalf, totalprice);
				payment.setPaymentDateTime(paymentDateTime);
				/* End Set Payment */

				/* Set Member */
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

				/* End Set Member */
				
				
				String stu_studentID = rs.getString(38);
				String stu_nameTh = rs.getString(39);
				String stu_surnameTh = rs.getString(40);
				String stu_programNameTh = rs.getString(41);
				String stu_facultyNameTh = rs.getString(42);
				String stu_statusName = rs.getString(43);
				String stu_status = rs.getString(44);
				String stu_levelName = rs.getString(45);
				String stu_idcard = rs.getString(46);
				String stu_advisor_name = rs.getString(47);
				String stu_emailadvisor = rs.getString(48);
				String stu_phonenumber = rs.getString(49);
				String stu_email = rs.getString(50);
				
				Student student = new Student(stu_studentID,stu_nameTh,stu_surnameTh,stu_programNameTh,stu_facultyNameTh,stu_statusName,stu_status,stu_levelName,stu_idcard);
				student.setAdvisor_name(stu_advisor_name);
				student.setEmail(stu_email);
				student.setEmailadvisor(stu_emailadvisor);
				student.setPhonenumber(stu_phonenumber);
				
				String p_personnel_ID = rs.getString(51);
				String p_personnel_Prefix = rs.getString(52);
				String p_personnel_FirstName = rs.getString(53);
				String p_personnel_LastName = rs.getString(54);
				String p_email = rs.getString(55);
				String p_position = rs.getString(56);
				String p_personnel_type = rs.getString(57);
				String p_faculty  = rs.getString(58);
				Personnel personnel = new Personnel(p_personnel_ID, p_personnel_Prefix, p_personnel_FirstName,
						p_personnel_LastName, p_email, p_position, p_personnel_type,p_faculty);

				List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm = this.getlistApprovalTestAnalysisForm(key);
				List<UseTestItem> lsitUseTestItem = this.getlistUseTestItem(key);

				testanalysisform = new TestAnalysisForm(testAnalysisFormNumber, type, pickupdate, language, client_name,
						address, report_Address, billing_Address, packing_style, additional_details,
						receive_Analysis_Result, resultsOfTheAnalysis, approveLV);
				testanalysisform.setCreatedate(createdate);
				testanalysisform.setTesting_status(testing_status);
				testanalysisform.setPayment(payment);
				testanalysisform.setMember(member);
				testanalysisform.setLsitApprovalTestAnalysisForm(lsitApprovalTestAnalysisForm);
				testanalysisform.setListUseTestItem(lsitUseTestItem);
				testanalysisform.setStudent(student);
				testanalysisform.setPersonnel(personnel);

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return testanalysisform;
	}

	public List<UseTestItem> getlistUseTestItem(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<UseTestItem> lsitUseTestItem = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT ut.usetestitemID,ut.sampleName,ut.code,ut.weight,ut.qty,ut.articleNo,ut.number_Operating,ut.storage,ut.maintenancefee"
					+ " ,ti.testitem_ID,ti.testitem_Name,ti.experiment,ti.min_qty_samples,ti.priceType_A,ti.priceType_B,ti.priceType_C,ti.priceType_D,ti.nextprice"
					+ " ,ti.typetestitem_ID"
					+ " FROM usetestitem ut inner join testitem ti on ti.testitem_ID = ut.testItem_testitem_ID"
					+ " where ut.testAnalysisForm_testAnalysisFormNumber like '" + key + "'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String usetestitemID = rs.getString(1);
				String sampleName = rs.getString(2);
				String code = rs.getString(3);
				String weight = rs.getString(4);
				int qty = rs.getInt(5);
				String articleNo = rs.getString(6);
				String number_Operating = rs.getString(7);
				String storage = rs.getString(8);
				double maintenancefee = rs.getDouble(9);

				String testitem_ID = rs.getString(10);
				String testitem_Name = rs.getString(11);
				String experiment = rs.getString(12);
				String min_qty_samples = rs.getString(13);
				Double priceType_A = rs.getDouble(14);
				Double priceType_B = rs.getDouble(15);
				Double priceType_C = rs.getDouble(16);
				Double priceType_D = rs.getDouble(17);
				Double nextprice = rs.getDouble(18);

				TestItem testItem = new TestItem(testitem_ID, testitem_Name, experiment, min_qty_samples, priceType_A,
						priceType_B, priceType_C, priceType_D, nextprice);
				UseTestItem useTestItem = new UseTestItem(usetestitemID, sampleName, code, weight, qty, articleNo,
						number_Operating, storage, maintenancefee);
				useTestItem.setTestItem(testItem);
				lsitUseTestItem.add(useTestItem);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return lsitUseTestItem;
	}

	public List<ApprovalTestAnalysisForm> getlistApprovalTestAnalysisForm(String key) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm = new Vector<>();
		try {
			stmt = con.createStatement();
			String sql = "SELECT atf.approval_ID,atf.approveNo,atf.date_approve,atf.reason,atf.status"
					+ "	,p.personnel_ID,p.personnel_Prefix,p.personnel_FirstName,p.personnel_LastName,p.email,p.position,p.personnel_type,p.faculty"
					+ "	FROM approvaltestanalysisform atf left join personnel p on atf.personnel_ID = p.personnel_ID"
					+ " where testAnalysisFormNumber like '" + key + "'" + " order by atf.approveNo";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int approval_ID = rs.getInt(1);
				int approveNo = rs.getInt(2);
				Date date_approve = rs.getDate(3);
				String reason = rs.getString(4);
				String status = rs.getString(5);

				String personnel_ID = rs.getString(6);
				String personnel_Prefix = rs.getString(7);
				String personnel_FirstName = rs.getString(8);
				String personnel_LastName = rs.getString(9);
				String email = rs.getString(10);
				String position = rs.getString(11);
				String personnel_type = rs.getString(12);
				String faculty = rs.getString(13);
				Personnel personnel = new Personnel(personnel_ID, personnel_Prefix, personnel_FirstName,
						personnel_LastName, email, position, personnel_type,faculty);

				ApprovalTestAnalysisForm approvalTestAnalysisForm = new ApprovalTestAnalysisForm(approval_ID,
						date_approve, status, reason, approveNo);
				approvalTestAnalysisForm.setPersonnel(personnel);
				lsitApprovalTestAnalysisForm.add(approvalTestAnalysisForm);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return lsitApprovalTestAnalysisForm;
	}

}
