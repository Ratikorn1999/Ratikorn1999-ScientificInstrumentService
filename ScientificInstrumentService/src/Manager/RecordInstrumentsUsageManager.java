package Manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Class_End_Project.Schedule;
import projectutil.ConnectionDB;

public class RecordInstrumentsUsageManager {

	public boolean isInstrumentsusage(List<Schedule> listschedule) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		
		try {
			CallableStatement stmt = null;
			for(int i = 0 ; i < listschedule.size() ; i++) {
			stmt = con.prepareCall("{call isInstrumentsusage(?,?)}");
			stmt.setString(1, listschedule.get(i).getSchedule_ID());
			stmt.setDouble(2, listschedule.get(i).getAdditionalcost());
			stmt.execute();
			
			}
			
			stmt.close();
			con.close();
			return true;
			
		} catch (SQLException er) {
			er.printStackTrace();
		}

		return false;
	}
	
}
