package Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import Class_End_Project.Payment;
import Class_End_Project.SericeRequestForm;
import Class_End_Project.TestAnalysisForm;
import projectutil.ConnectionDB;

public class ViewProofOfPaymentManager {

	public List<Payment> getpayment() {
		ConnectionDB condb = new ConnectionDB();
		Connection con = condb.getConection();
		Statement stmt = null;

		List<Payment> listPayment = new Vector<Payment>();
		try {

			stmt = con.createStatement();
			String sql = "SELECT p.payment_ID,p.paymentDateTime,p.status,p.payment_type,p.receipts_on_behalf,p.totalprice"
					+ "	,sf.sericeRequestFormNumber" 
					+ " ,tf.testAnalysisFormNumber"
					+ " FROM (payment p left join sericerequestform sf on p.payment_ID = sf.payment_ID) "
					+ " left join testanalysisform tf on p.payment_ID = tf.payment_ID "
					+ " Order by p.paymentDateTime DESC"
					;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int payment_ID = rs.getInt(1);
				Timestamp paymentDateTime = rs.getTimestamp(2);
				String status = rs.getString(3);
				String payment_type = rs.getString(4);
				String receipts_on_behalf = rs.getString(5);
				Double totalprice = rs.getDouble(6);
				String sericeRequestFormNumber = rs.getString(7);
				String testAnalysisFormNumber = rs.getString(8);
			
				
				SericeRequestForm sericeRequestForm = new SericeRequestForm();
				sericeRequestForm.setSericeRequestFormNumber(sericeRequestFormNumber);
				TestAnalysisForm testAnalysisForm = new TestAnalysisForm();
				testAnalysisForm.setTestAnalysisFormNumber(testAnalysisFormNumber);
				
				
				Payment payment = new Payment(payment_ID, status, payment_type, receipts_on_behalf,totalprice);
				payment.setPaymentDateTime(paymentDateTime);
				payment.setSericeRequestForm(sericeRequestForm);
				payment.setTestAnalysisForm(testAnalysisForm);
				listPayment.add(payment);
			}

		
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listPayment;
	}

}
