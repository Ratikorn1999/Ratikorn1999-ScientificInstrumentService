package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import projectutil.ConnectionDB;

public class ListSciInstrumentsManager {

	public List<SciInstruments> getListSciInstruments() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<SciInstruments> listSciInstruments = new Vector<SciInstruments>();
		try {

			stmt = con.createStatement();
			String sql = "SELECT s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee "
					+ ",st.sciInstrumentsType_ID,st.sciInstrumentsType_Name " + 
						"FROM sciinstruments s inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID ";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String sciInstruments_ID = rs.getString(1);
				String sciInstruments_Name = rs.getString(2);
				String status = rs.getString(3);
				String detail = rs.getString(4);
				Double priceType_A = rs.getDouble(5);
				Double priceType_B = rs.getDouble(6);
				Double priceType_C = rs.getDouble(7);
				Double priceType_D = rs.getDouble(8);
				Double priceFlatFee = rs.getDouble(9);
				String sciInstrumentsType_ID = rs.getString(10);
				String sciInstrumentsType_Name = rs.getString(11);

				SciInstrumentsType sciInstrumentsType = new SciInstrumentsType(sciInstrumentsType_ID,
						sciInstrumentsType_Name);

				SciInstruments sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name, status,detail, priceType_A,
						priceType_B, priceType_C, priceType_D,priceFlatFee);
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);
				listSciInstruments.add(sciInstruments);
			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listSciInstruments;
	}
	
	
}
