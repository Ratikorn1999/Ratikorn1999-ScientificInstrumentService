package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class ApproveManager {
	public boolean isApproverequest(int approval_ID, String staus) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isApproverequest(?,?)}");
			stmt.setInt(1, approval_ID);
			stmt.setString(2, staus);
			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public boolean isChangedStatus(String keyword, String status) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isChangedStatus(?,?)}");
			stmt.setString(1, keyword);
			stmt.setString(2, status);
			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public boolean isApproveTestAnalysis(int approval_ID, String staus) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isApproveTestAnalysis(?,?)}");
			stmt.setInt(1, approval_ID);
			stmt.setString(2, staus);
			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public boolean isChangedStatusTestAnalysisForm(String keyword, String status) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isChangedStatusTestAnalysisForm(?,?)}");
			stmt.setString(1, keyword);
			stmt.setString(2, status);
			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public boolean isApproveChangeForm(int approval_ID, String staus) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isApproveChangeForm(?,?)}");
			stmt.setInt(1, approval_ID);
			stmt.setString(2, staus);
			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public boolean isChangedStatusChangeForm(String keyword, String status) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isChangedStatusChangeForm(?,?)}");
			stmt.setString(1, keyword);
			stmt.setString(2, status);
			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}
	
	
}
