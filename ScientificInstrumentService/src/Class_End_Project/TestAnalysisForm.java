package Class_End_Project;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import LoginMJU.Student;

public class TestAnalysisForm {

	private String testAnalysisFormNumber;
	private String type;
	private Date pickupdate;
	private String language;
	private String client_name;
	private String address;
	private String fax;
	private String report_Address;
	private String billing_Address;
	private String packing_style;
	private String additional_details;
	private String receive_Analysis_Result;
	private String resultsOfTheAnalysis;
	private int approveLV;
	private Timestamp createdate;
	private String testing_status;
	private String approvestatus;

	private Member member;
	private Student student;
	private Personnel personnel;
	private List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm = new Vector<>();
	private Payment payment;
	private List<UseTestItem> listUseTestItem = new Vector<>();

	public TestAnalysisForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestAnalysisForm(String testAnalysisFormNumber, String type, Date pickupdate, String language,
			String client_name, String address, String report_Address, String billing_Address, String packing_style,
			String additional_details, String receive_Analysis_Result, String resultsOfTheAnalysis, int approveLV) {
		super();
		this.testAnalysisFormNumber = testAnalysisFormNumber;
		this.type = type;
		this.pickupdate = pickupdate;
		this.language = language;
		this.client_name = client_name;
		this.address = address;
		this.report_Address = report_Address;
		this.billing_Address = billing_Address;
		this.packing_style = packing_style;
		this.additional_details = additional_details;
		this.receive_Analysis_Result = receive_Analysis_Result;
		this.resultsOfTheAnalysis = resultsOfTheAnalysis;
		this.approveLV = approveLV;
	}

	public String getTestAnalysisFormNumber() {
		return testAnalysisFormNumber;
	}

	public void setTestAnalysisFormNumber(String testAnalysisFormNumber) {
		this.testAnalysisFormNumber = testAnalysisFormNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPickupdate() {
		return pickupdate;
	}

	public void setPickupdate(Date pickupdate) {
		this.pickupdate = pickupdate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReport_Address() {
		return report_Address;
	}

	public void setReport_Address(String report_Address) {
		this.report_Address = report_Address;
	}

	public String getBilling_Address() {
		return billing_Address;
	}

	public void setBilling_Address(String billing_Address) {
		this.billing_Address = billing_Address;
	}

	public String getPacking_style() {
		return packing_style;
	}

	public void setPacking_style(String packing_style) {
		this.packing_style = packing_style;
	}

	public String getAdditional_details() {
		return additional_details;
	}

	public void setAdditional_details(String additional_details) {
		this.additional_details = additional_details;
	}

	public String getReceive_Analysis_Result() {
		return receive_Analysis_Result;
	}

	public void setReceive_Analysis_Result(String receive_Analysis_Result) {
		this.receive_Analysis_Result = receive_Analysis_Result;
	}

	public String getResultsOfTheAnalysis() {
		return resultsOfTheAnalysis;
	}

	public void setResultsOfTheAnalysis(String resultsOfTheAnalysis) {
		this.resultsOfTheAnalysis = resultsOfTheAnalysis;
	}

	public int getApproveLV() {
		return approveLV;
	}

	public void setApproveLV(int approveLV) {
		this.approveLV = approveLV;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<ApprovalTestAnalysisForm> getLsitApprovalTestAnalysisForm() {
		return lsitApprovalTestAnalysisForm;
	}

	public void setLsitApprovalTestAnalysisForm(List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm) {
		this.lsitApprovalTestAnalysisForm = lsitApprovalTestAnalysisForm;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getTesting_status() {
		return testing_status;
	}

	public void setTesting_status(String testing_status) {
		this.testing_status = testing_status;
	}

	public List<UseTestItem> getListUseTestItem() {
		return listUseTestItem;
	}

	public void setListUseTestItem(List<UseTestItem> listUseTestItem) {
		this.listUseTestItem = listUseTestItem;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getApprovestatus() {
		return approvestatus;
	}

	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	
}
