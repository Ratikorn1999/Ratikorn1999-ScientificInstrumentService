package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import projectutil.ConnectionDB;

public class ViewIncomeSummaryManager {

	
	public double getIncomeSummary(String typeform) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		
		double income = 0.0 ;
		try {

			stmt = con.createStatement();
			String sql = null ;
			if(typeform.equals("servicerequestform")) {
			sql = "SELECT Sum(p.totalprice) FROM  (payment p  join sericerequestform sf on p.payment_ID = sf.payment_ID) ";
			}else if(typeform.equals("testanalysisform")) {
			sql = "SELECT Sum(p.totalprice) FROM  (payment p join testanalysisform tf on p.payment_ID = tf.payment_ID) ";	
			}
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				income =  rs.getDouble(1);
			}

		
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return income;
	}

	
}
