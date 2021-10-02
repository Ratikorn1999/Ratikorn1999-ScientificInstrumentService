package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;


import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;
import projectutil.ConnectionDB;

public class AddTestItemManager {
	
	public List<TypeTestItem> getlistTypeTestItem() {

		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<TypeTestItem> listTypeTestItem = new Vector<>();
		try {

			stmt = con.createStatement();
			String sql = " SELECT typetestitem_ID,typetestitem_Name" + 
						" FROM typetestitem;";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String typetestitem_ID = rs.getString(1);
				String typetestitem_Name = rs.getString(2);	
				listTypeTestItem.add(new TypeTestItem(typetestitem_ID,typetestitem_Name));
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}

		return listTypeTestItem;
	}

	
	public boolean isAddTestItem(TestItem testItem) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();

		Boolean result = false;

		try {

			CallableStatement stmt = con.prepareCall("{call isAddTestItem(?,?,?,?,?,?,?,?,?,?)}");
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
