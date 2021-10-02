package Class_End_Project;

import java.sql.Date;
import java.util.List;

public class FlatFeeForm extends SericeRequestForm {

	private String type_course_Name;
	private String course_id;
	private String course_Name;
	private String major;
	private String title;
	private String research_project_Name;
	private String funding_source;
	private String projectleader;
	private List<Rentalschedule> listrentalschedule;

	public FlatFeeForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlatFeeForm(String sericeRequestFormNumber, String type, Date pickupdate, String type_Work_Name,
			String subject, String usage_characteristics_Name, int period_of_use, Double sumPrice,
			Double additionalcost, String booking_status, int approveLV, String type_course_Name, String course_id,
			String course_Name, String major, String title, String research_project_Name, String funding_source,
			String projectleader) {
		super(sericeRequestFormNumber, type, pickupdate, type_Work_Name, subject, usage_characteristics_Name,
				period_of_use, sumPrice, additionalcost, booking_status, approveLV);
		this.type_course_Name = type_course_Name;
		this.course_id = course_id;
		this.course_Name = course_Name;
		this.major = major;
		this.title = title;
		this.research_project_Name = research_project_Name;
		this.funding_source = funding_source;
		this.projectleader = projectleader;
	}

	public String getType_course_Name() {
		return type_course_Name;
	}

	public void setType_course_Name(String type_course_Name) {
		this.type_course_Name = type_course_Name;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_Name() {
		return course_Name;
	}

	public void setCourse_Name(String course_Name) {
		this.course_Name = course_Name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getResearch_project_Name() {
		return research_project_Name;
	}

	public void setResearch_project_Name(String research_project_Name) {
		this.research_project_Name = research_project_Name;
	}

	public String getFunding_source() {
		return funding_source;
	}

	public void setFunding_source(String funding_source) {
		this.funding_source = funding_source;
	}

	public String getProjectleader() {
		return projectleader;
	}

	public void setProjectleader(String projectleader) {
		this.projectleader = projectleader;
	}

	public List<Rentalschedule> getListrentalschedule() {
		return listrentalschedule;
	}

	public void setListrentalschedule(List<Rentalschedule> listrentalschedule) {
		this.listrentalschedule = listrentalschedule;
	}

}
