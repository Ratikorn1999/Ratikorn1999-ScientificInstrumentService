package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import Class_End_Project.TestAnalysisForm;
import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;
import projectutil.ConnectionDB;

public class FillTestingAnalysisFormManager {

	public String getnexttestAnalysisFormNumber() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "T001/";
		try {

			CallableStatement stmt = con.prepareCall("{call getnexttestAnalysisFormNumber()}");

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
	
	
	public String getnextUsetestitemID() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "UT00000000";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextUsetestitemID()}");

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
	
	public int getnextapproval_ID() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		int result = 0;
		try {
			CallableStatement stmt = con.prepareCall("{call getnextapproval_ID_TestAnalysisForm()}");
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

	public Boolean isTestingAnalysisform(TestAnalysisForm testAnalysisForm) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isTestingAnalysisform(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, testAnalysisForm.getTestAnalysisFormNumber());
			stmt.setString(2, testAnalysisForm.getLanguage());
			stmt.setString(3, testAnalysisForm.getClient_name());
			stmt.setString(4, testAnalysisForm.getAddress());
			stmt.setString(5, testAnalysisForm.getReport_Address());
			stmt.setString(6, testAnalysisForm.getBilling_Address());
			stmt.setString(7, testAnalysisForm.getPacking_style());
			stmt.setString(8, testAnalysisForm.getAdditional_details());
			stmt.setString(9, testAnalysisForm.getReceive_Analysis_Result());
			stmt.setString(10, testAnalysisForm.getResultsOfTheAnalysis());
			if(testAnalysisForm.getMember() != null) {
				stmt.setString(11, testAnalysisForm.getMember().getMember_ID());	
			}else {
				stmt.setString(11, null);
			}
			if(testAnalysisForm.getStudent() != null) {
				stmt.setString(12, testAnalysisForm.getStudent().getStudentID());
			}else {
				stmt.setString(12, null);
			}
			if(testAnalysisForm.getPersonnel() != null) {
				stmt.setString(13, testAnalysisForm.getPersonnel().getPersonnel_ID());
			}else {
				stmt.setString(13, null);
			}
			
			
			
			stmt.execute();
			for(int i = 0 ; i < testAnalysisForm.getListUseTestItem().size();i++) {
				CallableStatement stmt2 = con.prepareCall("{call insertUseTestItem(?,?,?,?,?,?,?,?,?)}");
				stmt2.setString(1, testAnalysisForm.getListUseTestItem().get(i).getUsetestitemID());
				stmt2.setString(2, testAnalysisForm.getListUseTestItem().get(i).getSampleName());
				stmt2.setString(3, testAnalysisForm.getListUseTestItem().get(i).getCode());
				stmt2.setString(4,testAnalysisForm.getListUseTestItem().get(i).getWeight());
				stmt2.setInt(5, testAnalysisForm.getListUseTestItem().get(i).getQty());
				stmt2.setString(6, testAnalysisForm.getListUseTestItem().get(i).getStorage());
				stmt2.setDouble(7, testAnalysisForm.getListUseTestItem().get(i).getMaintenancefee());
				stmt2.setString(8, testAnalysisForm.getListUseTestItem().get(i).getTestItem().getTestitem_ID());
				stmt2.setString(9, testAnalysisForm.getTestAnalysisFormNumber());
				stmt2.execute();
				stmt2.close();
			}
			
			CallableStatement stmt3 = null;
			int approval_ID = 0;
			for(int i = 0 ; i < testAnalysisForm.getLsitApprovalTestAnalysisForm().size();i++) {
				if (testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getEmailadvisor() != null) {
					stmt3 = con.prepareCall("{call isapprovaltestanalysisform1(?,?,?)}");
					stmt3.setInt(1, testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID());
					stmt3.setString(2, testAnalysisForm.getTestAnalysisFormNumber());
					stmt3.setString(3,testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getEmailadvisor());
					stmt3.execute();
				}
				approval_ID = testAnalysisForm.getLsitApprovalTestAnalysisForm().get(i).getApproval_ID();
			}
			if(approval_ID==0) {
				approval_ID = this.getnextapproval_ID();
			}
			approval_ID = approval_ID+1;
			stmt3 = con.prepareCall("{call isapprovaltestanalysisform3(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, testAnalysisForm.getTestAnalysisFormNumber());
			stmt3.execute();
			
			approval_ID = approval_ID+1;
			stmt3 = con.prepareCall("{call isapprovaltestanalysisform4(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2, testAnalysisForm.getTestAnalysisFormNumber());
			stmt3.execute();
			
			approval_ID = approval_ID+1;
			stmt3 = con.prepareCall("{call isapprovaltestanalysisform5(?,?)}");
			stmt3.setInt(1, approval_ID);
			stmt3.setString(2,  testAnalysisForm.getTestAnalysisFormNumber());
			stmt3.execute();
			
			
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public List<TestItem> getListTestItem() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<TestItem> listTestItem = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = "SELECT ti.testitem_ID,ti.testitem_Name,ti.experiment,ti.min_qty_samples,ti.priceType_A,ti.priceType_B,ti.priceType_C,ti.priceType_D,ti.nextprice "
					+ ",tti.typetestitem_ID, tti.typetestitem_Name "
					+ " FROM testitem ti join typetestitem tti on ti.typetestitem_ID = tti.typetestitem_ID";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String testitem_ID = rs.getString(1);
				String testitem_Name = rs.getString(2);
				String experiment = rs.getString(3);
				String min_qty_samples = rs.getString(4);
				Double priceType_A  = rs.getDouble(5);
				Double priceType_B  = rs.getDouble(6);
				Double priceType_C  = rs.getDouble(7);
				Double priceType_D  = rs.getDouble(8);
				Double nextprice  = rs.getDouble(9);
				
				
				String typetestitem_ID = rs.getString(10);
				String typetestitem_Name = rs.getString(11);
				
				TypeTestItem typetestitem = new TypeTestItem(typetestitem_ID,typetestitem_Name);
				
				
				TestItem testitem = new TestItem(testitem_ID,testitem_Name,experiment,min_qty_samples,priceType_A,
						priceType_B,priceType_C,priceType_D,nextprice);
				testitem.setTypetestitem(typetestitem);
				listTestItem.add(testitem);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return listTestItem;
	}
	
	
	public TestItem getTestItemByID(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		TestItem testitem = null;
		try {

			stmt = con.createStatement();
			String sql = "SELECT ti.testitem_ID,ti.testitem_Name,ti.experiment,ti.min_qty_samples,ti.priceType_A,ti.priceType_B,ti.priceType_C,ti.priceType_D,ti.nextprice "
					+ ",tti.typetestitem_ID, tti.typetestitem_Name "
					+ " FROM testitem ti join typetestitem tti on ti.typetestitem_ID = tti.typetestitem_ID"
					+ " where ti.testitem_ID like '"+key+"'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String testitem_ID = rs.getString(1);
				String testitem_Name = rs.getString(2);
				String experiment = rs.getString(3);
				String min_qty_samples = rs.getString(4);
				Double priceType_A  = rs.getDouble(5);
				Double priceType_B  = rs.getDouble(6);
				Double priceType_C  = rs.getDouble(7);
				Double priceType_D  = rs.getDouble(8);
				Double nextprice  = rs.getDouble(9);
				
				
				String typetestitem_ID = rs.getString(10);
				String typetestitem_Name = rs.getString(11);
				
				TypeTestItem typetestitem = new TypeTestItem(typetestitem_ID,typetestitem_Name);
				
				
				testitem = new TestItem(testitem_ID,testitem_Name,experiment,min_qty_samples,priceType_A,
						priceType_B,priceType_C,priceType_D,nextprice);
				testitem.setTypetestitem(typetestitem);
				
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return testitem;
	}
	
	
}
