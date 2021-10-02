package LoginMJU;

public class PersonnelMJU {
	private String positionCode;
	private String titlePosition;
	private String firstName;
	private String lastName;
	private String personnelType;
	private String positionType;
	private String position;
	private String faculty;
	private String e_mail;
	public PersonnelMJU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PersonnelMJU(String positionCode, String titlePosition, String firstName, String lastName,
			String personnelType, String positionType, String position, String faculty, String e_mail) {
		super();
		this.positionCode = positionCode;
		this.titlePosition = titlePosition;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personnelType = personnelType;
		this.positionType = positionType;
		this.position = position;
		this.faculty = faculty;
		this.e_mail = e_mail;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getTitlePosition() {
		return titlePosition;
	}
	public void setTitlePosition(String titlePosition) {
		this.titlePosition = titlePosition;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPersonnelType() {
		return personnelType;
	}
	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	
}
