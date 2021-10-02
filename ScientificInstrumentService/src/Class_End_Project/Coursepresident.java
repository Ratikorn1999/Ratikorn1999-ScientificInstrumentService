package Class_End_Project;

public class Coursepresident {
	
	private String coursepresidentid;
	private String prefix;
	private String firstname;
	private String lastname;
	private String email;
	private String branch;
	private String faculty;
	private String university;
	private String phonenumber;
	
	public Coursepresident() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Coursepresident(String coursepresidentid, String prefix, String firstname, String lastname, String email,
			String branch, String faculty, String university, String phonenumber) {
		super();
		this.coursepresidentid = coursepresidentid;
		this.prefix = prefix;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.branch = branch;
		this.faculty = faculty;
		this.university = university;
		this.phonenumber = phonenumber;
	}
	
	

	public String getCoursepresidentid() {
		return coursepresidentid;
	}


	public void setCoursepresidentid(String coursepresidentid) {
		this.coursepresidentid = coursepresidentid;
	}


	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	

}
