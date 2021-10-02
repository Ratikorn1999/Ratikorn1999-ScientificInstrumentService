package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class CancelServiceRequestManager {

	public boolean isCancelservicerequest(String key,String typeform) {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		boolean result = false;
		
		if(typeform.equals("3")) {
			try {
				CallableStatement stmt = null;
				stmt = con.prepareCall("{call isCancelTestAnalysisForm(?)}");
				stmt.setString(1, key);
				stmt.execute();
				result = true;
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				CallableStatement stmt = null;
				stmt = con.prepareCall("{call isCancelservicerequest(?)}");
				stmt.setString(1, key);
				stmt.execute();
				result = true;
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		}
	
		
		return result;

	}

}
