package LoginMJU;

public class Student {

	private String studentID;
	private String nameTh;
	private String surnameTh;
	private String programNameTh;
	private String facultyNameTh;
	private String statusName;
	private String status;
	private String levelName;
	private String idcard;
	private String advisor_name;
	private String emailadvisor;
	private String phonenumber;
	private String email;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String studentID, String nameTh, String surnameTh, String programNameTh, String facultyNameTh,
			String statusName, String status, String levelName, String idcard) {
		super();
		this.studentID = studentID;
		this.nameTh = nameTh;
		this.surnameTh = surnameTh;
		this.programNameTh = programNameTh;
		this.facultyNameTh = facultyNameTh;
		this.statusName = statusName;
		this.status = status;
		this.levelName = levelName;
		this.idcard = idcard;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getNameTh() {
		return nameTh;
	}

	public void setNameTh(String nameTh) {
		this.nameTh = nameTh;
	}

	public String getSurnameTh() {
		return surnameTh;
	}

	public void setSurnameTh(String surnameTh) {
		this.surnameTh = surnameTh;
	}

	public String getProgramNameTh() {
		return programNameTh;
	}

	public void setProgramNameTh(String programNameTh) {
		this.programNameTh = programNameTh;
	}

	public String getFacultyNameTh() {
		return facultyNameTh;
	}

	public void setFacultyNameTh(String facultyNameTh) {
		this.facultyNameTh = facultyNameTh;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getAdvisor_name() {
		return advisor_name;
	}

	public void setAdvisor_name(String advisor_name) {
		this.advisor_name = advisor_name;
	}

	public String getEmailadvisor() {
		return emailadvisor;
	}

	public void setEmailadvisor(String emailadvisor) {
		this.emailadvisor = emailadvisor;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
