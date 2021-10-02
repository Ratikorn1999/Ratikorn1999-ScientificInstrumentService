package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import Class_End_Project.SciInstruments;
import projectutil.ConnectionDB;

public class EditInstrumentsDetailsManager {
	
	public boolean isEditInstrumentsDetails(String key,SciInstruments sciInstruments) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isEditInstrumentsDetails(?,?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, sciInstruments.getSciInstruments_ID());
			stmt.setString(2, sciInstruments.getSciInstruments_Name());
			stmt.setString(3, sciInstruments.getSciInstrumentsType().getSciInstrumentsType_ID());
			stmt.setString(4, sciInstruments.getDetail());
			stmt.setDouble(5, sciInstruments.getPriceType_A());
			stmt.setDouble(6, sciInstruments.getPriceType_B());
			stmt.setDouble(7, sciInstruments.getPriceType_C());
			stmt.setDouble(8, sciInstruments.getPriceType_D());
			stmt.setDouble(9, sciInstruments.getPriceFlatFee());
			stmt.setString(10, sciInstruments.getStaff().getStaffid());
			stmt.setString(11, sciInstruments.getCoursepresident().getCoursepresidentid());
			stmt.setString(12, key);
			

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
