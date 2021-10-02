package Class_End_Project;

import java.util.List;
import java.util.Vector;

public class Personnel {

	private String personnel_ID;
	private String personnel_Prefix;
	private String personnel_FirstName;
	private String personnel_LastName;
	private String email;
	private String position;
	private String personnel_type;
	private String faculty;
	private String phone;
	private List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm = new Vector<>();
	private List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm = new Vector<>();
	private List<ApprovalChangeForm> lsitApprovalChangeForm = new Vector<>();

	public Personnel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personnel(String personnel_ID, String personnel_Prefix, String personnel_FirstName,
			String personnel_LastName, String email, String position, String personnel_type, String faculty) {
		super();
		this.personnel_ID = personnel_ID;
		this.personnel_Prefix = personnel_Prefix;
		this.personnel_FirstName = personnel_FirstName;
		this.personnel_LastName = personnel_LastName;
		this.email = email;
		this.position = position;
		this.personnel_type = personnel_type;
		this.faculty = faculty;
	}

	public String getPersonnel_ID() {
		return personnel_ID;
	}

	public void setPersonnel_ID(String personnel_ID) {
		this.personnel_ID = personnel_ID;
	}

	public String getPersonnel_Prefix() {
		return personnel_Prefix;
	}

	public void setPersonnel_Prefix(String personnel_Prefix) {
		this.personnel_Prefix = personnel_Prefix;
	}

	public String getPersonnel_FirstName() {
		return personnel_FirstName;
	}

	public void setPersonnel_FirstName(String personnel_FirstName) {
		this.personnel_FirstName = personnel_FirstName;
	}

	public String getPersonnel_LastName() {
		return personnel_LastName;
	}

	public void setPersonnel_LastName(String personnel_LastName) {
		this.personnel_LastName = personnel_LastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPersonnel_type() {
		return personnel_type;
	}

	public void setPersonnel_type(String personnel_type) {
		this.personnel_type = personnel_type;
	}

	public List<ApprovalTestAnalysisForm> getLsitApprovalTestAnalysisForm() {
		return lsitApprovalTestAnalysisForm;
	}

	public void setLsitApprovalTestAnalysisForm(List<ApprovalTestAnalysisForm> lsitApprovalTestAnalysisForm) {
		this.lsitApprovalTestAnalysisForm = lsitApprovalTestAnalysisForm;
	}

	public List<ApprovalOfServiceRequestForm> getLsitApprovalOfServiceRequestForm() {
		return lsitApprovalOfServiceRequestForm;
	}

	public void setLsitApprovalOfServiceRequestForm(
			List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm) {
		this.lsitApprovalOfServiceRequestForm = lsitApprovalOfServiceRequestForm;
	}

	public List<ApprovalChangeForm> getLsitApprovalChangeForm() {
		return lsitApprovalChangeForm;
	}

	public void setLsitApprovalChangeForm(List<ApprovalChangeForm> lsitApprovalChangeForm) {
		this.lsitApprovalChangeForm = lsitApprovalChangeForm;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
