package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import Class_End_Project.TestItem;
import projectutil.ConnectionDB;

public class EditTestItemManager {

	public boolean isEditTestItem(String key,TestItem testItem) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isEditTestItem(?,?,?,?,?,?,?,?,?,?,?)}");
			stmt.setString(1, testItem.getTestitem_ID());
			stmt.setString(2, testItem.getTestitem_Name());
			stmt.setString(3, testItem.getExperiment());
			stmt.setString(4, testItem.getMin_qty_samples());
			stmt.setDouble(5, testItem.getPriceType_A());
			stmt.setDouble(6, testItem.getPriceType_B());
			stmt.setDouble(7, testItem.getPriceType_C());
			stmt.setDouble(8, testItem.getPriceType_D());
			stmt.setDouble(9, testItem.getNextprice());
			stmt.setString(10, testItem.getTypetestitem().getTypetestitem_ID());
			stmt.setString(11, key);
			
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
