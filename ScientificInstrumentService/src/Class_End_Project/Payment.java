package Class_End_Project;

import java.sql.Timestamp;


public class Payment {
	
	private int payment_ID;
	private Timestamp  paymentDateTime;
	private String status;
	private String payment_type;
	private String receipts_on_behalf;
	private Double totalprice;
	
	
	private TestAnalysisForm testAnalysisForm;
	private SericeRequestForm sericeRequestForm;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Payment(int payment_ID, String status, String payment_type,
			String receipts_on_behalf, Double totalprice) {
		super();
		this.payment_ID = payment_ID;
		this.status = status;
		this.payment_type = payment_type;
		this.receipts_on_behalf = receipts_on_behalf;
		this.totalprice = totalprice;
	}



	public int getPayment_ID() {
		return payment_ID;
	}

	public void setPayment_ID(int payment_ID) {
		this.payment_ID = payment_ID;
	}

	public Timestamp getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(Timestamp paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getReceipts_on_behalf() {
		return receipts_on_behalf;
	}

	public void setReceipts_on_behalf(String receipts_on_behalf) {
		this.receipts_on_behalf = receipts_on_behalf;
	}

	public TestAnalysisForm getTestAnalysisForm() {
		return testAnalysisForm;
	}

	public void setTestAnalysisForm(TestAnalysisForm testAnalysisForm) {
		this.testAnalysisForm = testAnalysisForm;
	}

	public SericeRequestForm getSericeRequestForm() {
		return sericeRequestForm;
	}

	public void setSericeRequestForm(SericeRequestForm sericeRequestForm) {
		this.sericeRequestForm = sericeRequestForm;
	}



	public Double getTotalprice() {
		return totalprice;
	}



	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
}
