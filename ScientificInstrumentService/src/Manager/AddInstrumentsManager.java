package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import Class_End_Project.Coursepresident;
import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.Staff;
import projectutil.ConnectionDB;

public class AddInstrumentsManager {

	public boolean isAddinstruments(SciInstruments sciInstruments) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isAddinstruments(?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, sciInstruments.getSciInstruments_ID());
			stmt.setString(2, sciInstruments.getSciInstruments_Name());
			stmt.setString(3, sciInstruments.getStatus());
			stmt.setString(4, sciInstruments.getDetail());
			stmt.setString(5, sciInstruments.getSciInstrumentsType().getSciInstrumentsType_ID());
			stmt.setDouble(6, sciInstruments.getPriceType_A());
			stmt.setDouble(7, sciInstruments.getPriceType_B());
			stmt.setDouble(8, sciInstruments.getPriceType_C());
			stmt.setDouble(9, sciInstruments.getPriceType_D());
			stmt.setDouble(10, sciInstruments.getPriceFlatFee());
			stmt.setString(11, sciInstruments.getStaff().getStaffid());
			stmt.setString(12, sciInstruments.getCoursepresident().getCoursepresidentid());
			

			stmt.execute();
			result = true;
			con.close();
			stmt.close();
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return result;
	}

	public List<SciInstrumentsType> getlistSciInstrumentsType() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<SciInstrumentsType> listSciInstrumentsType = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT sciInstrumentsType_ID,sciInstrumentsType_Name" + " FROM sciinstrumentstype;";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String sciInstrumentsType_ID = rs.getString(1);
				String sciInstrumentsType_Name = rs.getString(2);
				listSciInstrumentsType.add(new SciInstrumentsType(sciInstrumentsType_ID, sciInstrumentsType_Name));
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listSciInstrumentsType;
	}

	public List<Coursepresident> getlistCoursepresident() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Coursepresident> listCoursepresident = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT coursepresidentid,prefix,firstname,lastname,email,branch,faculty,university,phonenumber "
					+ " FROM coursepresident";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String coursepresidentid = rs.getString(1);
				String prefix = rs.getString(2);
				String firstname = rs.getString(3);
				String lastname = rs.getString(4);
				String email = rs.getString(5);
				String branch = rs.getString(6);
				String faculty = rs.getString(7);
				String university = rs.getString(8);
				String phonenumber = rs.getString(9);
				listCoursepresident.add(new Coursepresident(coursepresidentid, prefix,firstname,lastname,email,branch,faculty,university,phonenumber));
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listCoursepresident;
	}

	
	public List<Staff> getlistStaff() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Staff> listStaff = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = 
					"SELECT staffid,prefix,firstname,lastname,email,phonenumber " + 
					" FROM staff;";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String staffid = rs.getString(1);
				String prefix = rs.getString(2);
				String firstname = rs.getString(3);
				String lastname = rs.getString(4);
				String email = rs.getString(5);
				String phonenumber = rs.getString(6);
				listStaff.add(new Staff(staffid, prefix,firstname,lastname,email,phonenumber));
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listStaff;
	}

}
