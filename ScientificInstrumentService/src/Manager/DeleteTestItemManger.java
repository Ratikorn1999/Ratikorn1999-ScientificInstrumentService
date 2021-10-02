package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class DeleteTestItemManger {


	public boolean isDeleteTestItem(String testitem_ID) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		boolean result = false;
	
			try {

				CallableStatement stmt = con.prepareCall("{call isDeleteTestItem(?)}");
				stmt.setString(1, testitem_ID);
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
