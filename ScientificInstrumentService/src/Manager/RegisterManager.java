package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Class_End_Project.Member;
import projectutil.ConnectionDB;

public class RegisterManager {

	
	public boolean  register(Member m) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		boolean result = false;
		try {

			CallableStatement stmt = con.prepareCall("{call register(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			stmt.setString(1, m.getMember_ID());
			stmt.setString(2, m.getMember_Prefix());
			stmt.setString(3, m.getMember_FirstName());
			stmt.setString(4, m.getMember_LastName());
			stmt.setString(5, m.getTypeMember());
			stmt.setString(6, m.getPosition());
			stmt.setString(7, m.getEmail());
			stmt.setString(8, m.getPhonenumber());
			stmt.setString(9, m.getStudentID());
			stmt.setString(10, m.getDegree());
			stmt.setString(11, m.getBranch());
			stmt.setString(12, m.getFaculty());
			stmt.setString(13, m.getUniversity());
			stmt.setString(14, m.getAdvisor_name());
			stmt.setString(15, m.getEmailadvisor());
			stmt.setString(16, m.getCompany_name());
			stmt.setString(17, m.getLogin().getUsername());
			stmt.setString(18, m.getLogin().getPassword());

			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("result is "+result);
		return result;

	}
	
	
	
	public String getnextmemberid() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		String result = "M000000000";
		try {

			CallableStatement stmt = con.prepareCall("{call getnextmemberid()}");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1) ;
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
