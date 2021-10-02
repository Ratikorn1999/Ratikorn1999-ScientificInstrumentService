package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import Class_End_Project.TestItem;
import Class_End_Project.TypeTestItem;
import projectutil.ConnectionDB;

public class ViewTestItemDetailsManager {

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
	
	public TestItem getTestItemByID(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		TestItem testitem = null;
		try {

			stmt = con.createStatement();
			String sql = "SELECT ti.testitem_ID,ti.testitem_Name,ti.experiment,ti.min_qty_samples,ti.priceType_A,ti.priceType_B,ti.priceType_C,ti.priceType_D,ti.nextprice "
					+ ",tti.typetestitem_ID, tti.typetestitem_Name "
					+ " FROM testitem ti join typetestitem tti on ti.typetestitem_ID = tti.typetestitem_ID"
					+ " where ti.testitem_ID like '"+key+"'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String testitem_ID = rs.getString(1);
				String testitem_Name = rs.getString(2);
				String experiment = rs.getString(3);
				String min_qty_samples = rs.getString(4);
				Double priceType_A  = rs.getDouble(5);
				Double priceType_B  = rs.getDouble(6);
				Double priceType_C  = rs.getDouble(7);
				Double priceType_D  = rs.getDouble(8);
				Double nextprice  = rs.getDouble(9);
				
				
				String typetestitem_ID = rs.getString(10);
				String typetestitem_Name = rs.getString(11);
				
				TypeTestItem typetestitem = new TypeTestItem(typetestitem_ID,typetestitem_Name);
				
				
				testitem = new TestItem(testitem_ID,testitem_Name,experiment,min_qty_samples,priceType_A,
						priceType_B,priceType_C,priceType_D,nextprice);
				testitem.setTypetestitem(typetestitem);
				
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("catch");
			e.printStackTrace();
		}
		return testitem;
	}
	
}
