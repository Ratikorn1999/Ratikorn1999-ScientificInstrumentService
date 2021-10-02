package Class_End_Project;

public class Staff {
	private String staffid;
	private String prefix;
	private String firstname;
	private String lastname;
	private String email;
	private String phonenumber;

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(String staffid, String prefix, String firstname, String lastname, String email, String phonenumber) {
		super();
		this.staffid = staffid;
		this.prefix = prefix;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

}
