package Class_End_Project;

import java.util.Date;

public class ApprovalTestAnalysisForm {

	private int approval_ID;
	private Date date_approve;
	private String status;
	private String reason;
	private int approveNo;
	private String emailadvisor;
	private TestAnalysisForm testAnalysisForm;
	private Personnel personnel;
	
	public ApprovalTestAnalysisForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApprovalTestAnalysisForm(int approval_ID, Date date_approve, String status, String reason, int approveNo) {
		super();
		this.approval_ID = approval_ID;
		this.date_approve = date_approve;
		this.status = status;
		this.reason = reason;
		this.approveNo = approveNo;
	}

	public int getApproval_ID() {
		return approval_ID;
	}

	public void setApproval_ID(int approval_ID) {
		this.approval_ID = approval_ID;
	}

	public Date getDate_approve() {
		return date_approve;
	}

	public void setDate_approve(Date date_approve) {
		this.date_approve = date_approve;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getApproveNo() {
		return approveNo;
	}

	public void setApproveNo(int approveNo) {
		this.approveNo = approveNo;
	}

	public TestAnalysisForm getTestAnalysisForm() {
		return testAnalysisForm;
	}

	public void setTestAnalysisForm(TestAnalysisForm testAnalysisForm) {
		this.testAnalysisForm = testAnalysisForm;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	public String getEmailadvisor() {
		return emailadvisor;
	}

	public void setEmailadvisor(String emailadvisor) {
		this.emailadvisor = emailadvisor;
	}

	


}
