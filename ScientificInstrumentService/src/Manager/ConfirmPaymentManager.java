package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import projectutil.ConnectionDB;

public class ConfirmPaymentManager {

	public boolean isConfirmpayment(int key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		boolean result = false;
			try {

				CallableStatement stmt = null;
				stmt = con.prepareCall("{call isConfirmpayment(?)}");
				stmt.setInt(1, key);
				stmt.execute();
				result = true;
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
		return result;

	}

}
