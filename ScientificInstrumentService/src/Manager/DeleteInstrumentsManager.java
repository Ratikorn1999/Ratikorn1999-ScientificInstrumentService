package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class DeleteInstrumentsManager {

	
	public boolean isDeleteInstruments(String sciInstruments_ID) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		boolean result = false;
	
			try {

				CallableStatement stmt = con.prepareCall("{call isDeleteInstruments(?)}");
				stmt.setString(1, sciInstruments_ID);
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
