package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import projectutil.ConnectionDB;

public class ViewUsageSummaryManager {

	public ResultSet getsummary(String s, String e) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = con.createStatement();
			String sql = " SELECT s.sciInstruments_Name"
					+ "    ,count(s.sciInstruments_ID) as numberuse,sum(sd.time) as timeuse,sum(maintenancefee) as totalpeice"
					+ "	 FROM (schedule sd inner join sciinstruments s on sd.sciInstruments_ID = s.sciInstruments_ID) "
					+ "	 inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID "
					+ "	 where (coursepresidentid like '' or coursepresidentid is null )"
					+ "     and sd.startDateTime >=  '" + s + "'" + "     and endDateTime <=  '" + e + "'"
					+ "     group by s.sciInstruments_ID";

			rs = stmt.executeQuery(sql);

			return rs;
		} catch (Exception e1) {
			System.out.println("catch");
			e1.printStackTrace();
		}

		return rs;
	}

	public ResultSet getsummarybybranch(String s, String e) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = con.createStatement();
			String sql = " 	SELECT s.sciInstruments_Name"
					+ "    ,count(s.sciInstruments_ID) as numberuse,sum(sd.time) as timeuse,sum(maintenancefee) as totalpeice"
					+ "    ,cp.branch"
					+ "	 FROM (schedule sd inner join sciinstruments s on sd.sciInstruments_ID = s.sciInstruments_ID)"
					+ "	 inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID"
					+ "     inner join coursepresident cp on cp.coursepresidentid = s.coursepresidentid"
					+ "	 where  (sd.startDateTime >=  '"+s+"' and endDateTime <= '"+e+"') "
					+ "     group by s.sciInstruments_ID "
					+ "	   order by cp.branch";

			rs = stmt.executeQuery(sql);

			return rs;
		} catch (Exception e1) {
			System.out.println("catch");
			e1.printStackTrace();
		}

		return rs;
	}

}
