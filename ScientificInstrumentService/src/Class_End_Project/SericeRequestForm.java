package Class_End_Project;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import LoginMJU.Student;

public class SericeRequestForm {

	private String sericeRequestFormNumber;
	private String type;
	private Date pickupdate;
	private String type_Work_Name;
	private String subject;
	private String usage_characteristics_Name;
	private int period_of_use;
	private Double sumPrice;
	private Double additionalcost;
	private String booking_status;
	private int approveLV;
	private Timestamp createdate;

	private Member member;
	private Student student;
	private Personnel personnel;

	private Payment payment;
	private List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm = new Vector<>();
	private ChangeForm changeForm;
	private List<Schedule> listSchedule = new Vector<>();

	public SericeRequestForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SericeRequestForm(String sericeRequestFormNumber, String type, Date pickupdate, String type_Work_Name,
			String subject, String usage_characteristics_Name, int period_of_use, Double sumPrice,
			Double additionalcost, String booking_status, int approveLV) {
		super();
		this.sericeRequestFormNumber = sericeRequestFormNumber;
		this.type = type;
		this.pickupdate = pickupdate;
		this.type_Work_Name = type_Work_Name;
		this.subject = subject;
		this.usage_characteristics_Name = usage_characteristics_Name;
		this.period_of_use = period_of_use;
		this.sumPrice = sumPrice;
		this.additionalcost = additionalcost;
		this.booking_status = booking_status;
		this.approveLV = approveLV;
	}

	public String getSericeRequestFormNumber() {
		return sericeRequestFormNumber;
	}

	public void setSericeRequestFormNumber(String sericeRequestFormNumber) {
		this.sericeRequestFormNumber = sericeRequestFormNumber;
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

	public String getType_Work_Name() {
		return type_Work_Name;
	}

	public void setType_Work_Name(String type_Work_Name) {
		this.type_Work_Name = type_Work_Name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUsage_characteristics_Name() {
		return usage_characteristics_Name;
	}

	public void setUsage_characteristics_Name(String usage_characteristics_Name) {
		this.usage_characteristics_Name = usage_characteristics_Name;
	}

	public int getPeriod_of_use() {
		return period_of_use;
	}

	public void setPeriod_of_use(int period_of_use) {
		this.period_of_use = period_of_use;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Double getAdditionalcost() {
		return additionalcost;
	}

	public void setAdditionalcost(Double additionalcost) {
		this.additionalcost = additionalcost;
	}

	public String getBooking_status() {
		return booking_status;
	}

	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<ApprovalOfServiceRequestForm> getLsitApprovalOfServiceRequestForm() {
		return lsitApprovalOfServiceRequestForm;
	}

	public void setLsitApprovalOfServiceRequestForm(
			List<ApprovalOfServiceRequestForm> lsitApprovalOfServiceRequestForm) {
		this.lsitApprovalOfServiceRequestForm = lsitApprovalOfServiceRequestForm;
	}

	public ChangeForm getChangeForm() {
		return changeForm;
	}

	public void setChangeForm(ChangeForm changeForm) {
		this.changeForm = changeForm;
	}

	public List<Schedule> getListSchedule() {
		return listSchedule;
	}

	public void setListSchedule(List<Schedule> listSchedule) {
		this.listSchedule = listSchedule;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}

	
}
