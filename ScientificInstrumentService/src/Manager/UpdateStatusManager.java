package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class UpdateStatusManager {

	
	public boolean isUpdateStatus(String testAnalysisFormNumber,String testAnalysisstatus) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		boolean result = false;
	
			try {

				CallableStatement stmt = con.prepareCall("{call isUpdateStatus(?,?)}");
				stmt.setString(1, testAnalysisFormNumber);
				stmt.setString(2, testAnalysisstatus);
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
