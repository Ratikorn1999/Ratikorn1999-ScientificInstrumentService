package Class_End_Project;

public class Member {

	private String member_ID;
	private String member_Prefix;
	private String member_FirstName;
	private String member_LastName;
	private String email;
	private String typeMember;
	private String position;
	private String phonenumber;
	private String studentID;
	private String degree;
	private String branch;
	private String faculty;
	private String university;
	private String advisor_name;
	private String emailadvisor;
	private String company_name;

	private Login login;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String member_ID, String member_Prefix, String member_FirstName, String member_LastName, String email,
			String typeMember, String position, String phonenumber, String studentID, String degree, String branch,
			String faculty, String university, String advisor_name, String emailadvisor, String company_name) {
		super();
		this.member_ID = member_ID;
		this.member_Prefix = member_Prefix;
		this.member_FirstName = member_FirstName;
		this.member_LastName = member_LastName;
		this.email = email;
		this.typeMember = typeMember;
		this.position = position;
		this.phonenumber = phonenumber;
		this.studentID = studentID;
		this.degree = degree;
		this.branch = branch;
		this.faculty = faculty;
		this.university = university;
		this.advisor_name = advisor_name;
		this.emailadvisor = emailadvisor;
		this.company_name = company_name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getMember_ID() {
		return member_ID;
	}

	public void setMember_ID(String member_ID) {
		this.member_ID = member_ID;
	}

	public String getMember_Prefix() {
		return member_Prefix;
	}

	public void setMember_Prefix(String member_Prefix) {
		this.member_Prefix = member_Prefix;
	}

	public String getMember_FirstName() {
		return member_FirstName;
	}

	public void setMember_FirstName(String member_FirstName) {
		this.member_FirstName = member_FirstName;
	}

	public String getMember_LastName() {
		return member_LastName;
	}

	public void setMember_LastName(String member_LastName) {
		this.member_LastName = member_LastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTypeMember() {
		return typeMember;
	}

	public void setTypeMember(String typeMember) {
		this.typeMember = typeMember;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getAdvisor_name() {
		return advisor_name;
	}

	public void setAdvisor_name(String advisor_name) {
		this.advisor_name = advisor_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getEmailadvisor() {
		return emailadvisor;
	}

	public void setEmailadvisor(String emailadvisor) {
		this.emailadvisor = emailadvisor;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
