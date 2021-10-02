package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class ChangeInstrumentsStatusManager {


	public boolean isChangeInstrumentsstatus(String id ,String status) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Boolean result = false;
		try {
			CallableStatement stmt = con.prepareCall("{call isChangeInstrumentsstatus(?,?)}");
			stmt.setString(1, id);
			stmt.setString(2,status);
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
