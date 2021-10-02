package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Class_End_Project.Coursepresident;
import Class_End_Project.SciInstruments;
import Class_End_Project.SciInstrumentsType;
import Class_End_Project.Staff;
import projectutil.ConnectionDB;

public class ViewInstrumentsDetailsManager {

	public SciInstruments getInstrumentsByID(String key) {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		SciInstruments sciInstruments = new SciInstruments();
		try {

			stmt = con.createStatement();
			String sql = " SELECT s.sciInstruments_ID,s.sciInstruments_Name,s.status,s.detail,s.priceType_A,s.priceType_B,s.priceType_C,s.priceType_D,s.priceFlatFee"
					+ "	,st.sciInstrumentsType_ID,st.sciInstrumentsType_Name "
					+ "	,sf.staffid,sf.prefix,sf.firstname,sf.lastname,sf.email,sf.phonenumber"
					+ "	,cp.coursepresidentid, cp.prefix, cp.firstname,cp.lastname,cp.email, cp.branch,cp.faculty, cp.university,cp.phonenumber"
					+ "	FROM ((sciinstruments s inner join sciinstrumentstype st on s.sciInstrumentsType_ID = st.sciInstrumentsType_ID )"
					+ " left join staff sf on sf.staffid = s.staffid)"
					+ " left join coursepresident cp on s.coursepresidentid = cp.coursepresidentid"
					+ " where  s.sciInstruments_ID = '" + key + "'";
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
				sciInstruments = new SciInstruments(sciInstruments_ID, sciInstruments_Name, status, detail, priceType_A,
						priceType_B, priceType_C, priceType_D, priceFlatFee);
				
				String sciInstrumentsType_ID = rs.getString(10);
				String sciInstrumentsType_Name = rs.getString(11);
				SciInstrumentsType sciInstrumentsType = new SciInstrumentsType(sciInstrumentsType_ID,
						sciInstrumentsType_Name);
				
				
				String sf_staffid = rs.getString(12);
				String sf_prefix = rs.getString(13);
				String sf_firstname = rs.getString(14);
				String sf_lastname = rs.getString(15);
				String sf_email = rs.getString(16);
				String sf_phonenumber = rs.getString(17);
				Staff staff = new Staff(sf_staffid,sf_prefix,sf_firstname,sf_lastname,sf_email,sf_phonenumber);
				
				
				
				String cp_coursepresidentid = rs.getString(18);
				String cp_prefix = rs.getString(19);
				String cp_firstname = rs.getString(20);
				String cp_lastname = rs.getString(21);
				String cp_email = rs.getString(22);
				String cp_branch = rs.getString(23);
				String cp_faculty = rs.getString(24);
				String cp_university = rs.getString(25) ;
				String cp_phonenumber = rs.getString(26);
				Coursepresident coursepresident = new Coursepresident(cp_coursepresidentid,cp_prefix,cp_firstname,cp_lastname,cp_email,cp_branch,cp_faculty,cp_university,cp_phonenumber);

				
				sciInstruments.setSciInstrumentsType(sciInstrumentsType);
				sciInstruments.setStaff(staff);
				sciInstruments.setCoursepresident(coursepresident);

			}

			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sciInstruments;
	}

}
