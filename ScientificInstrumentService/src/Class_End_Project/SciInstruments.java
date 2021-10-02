package Class_End_Project;

import java.util.List;
import java.util.Vector;

public class SciInstruments {

	private String sciInstruments_ID;
	private String sciInstruments_Name;
	private String status;
	private String detail;
	private Double priceType_A;
	private Double priceType_B;
	private Double priceType_C;
	private Double priceType_D;
	private Double priceFlatFee;
	
	private Staff staff;
	private Coursepresident coursepresident;
	private SciInstrumentsType sciInstrumentsType;
	private List<Schedule> lsitSchedule = new Vector<>();

	public SciInstruments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SciInstruments(String sciInstruments_ID, String sciInstruments_Name, String status, String detail,
			Double priceType_A, Double priceType_B, Double priceType_C, Double priceType_D, Double priceFlatFee) {
		super();
		this.sciInstruments_ID = sciInstruments_ID;
		this.sciInstruments_Name = sciInstruments_Name;
		this.status = status;
		this.detail = detail;
		this.priceType_A = priceType_A;
		this.priceType_B = priceType_B;
		this.priceType_C = priceType_C;
		this.priceType_D = priceType_D;
		this.priceFlatFee = priceFlatFee;
	}

	public Double getPriceFlatFee() {
		return priceFlatFee;
	}

	public void setPriceFlatFee(Double priceFlatFee) {
		this.priceFlatFee = priceFlatFee;
	}

	public String getSciInstruments_ID() {
		return sciInstruments_ID;
	}

	public void setSciInstruments_ID(String sciInstruments_ID) {
		this.sciInstruments_ID = sciInstruments_ID;
	}

	public String getSciInstruments_Name() {
		return sciInstruments_Name;
	}

	public void setSciInstruments_Name(String sciInstruments_Name) {
		this.sciInstruments_Name = sciInstruments_Name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPriceType_A() {
		return priceType_A;
	}

	public void setPriceType_A(Double priceType_A) {
		this.priceType_A = priceType_A;
	}

	public Double getPriceType_B() {
		return priceType_B;
	}

	public void setPriceType_B(Double priceType_B) {
		this.priceType_B = priceType_B;
	}

	public Double getPriceType_C() {
		return priceType_C;
	}

	public void setPriceType_C(Double priceType_C) {
		this.priceType_C = priceType_C;
	}

	public Double getPriceType_D() {
		return priceType_D;
	}

	public void setPriceType_D(Double priceType_D) {
		this.priceType_D = priceType_D;
	}

	public SciInstrumentsType getSciInstrumentsType() {
		return sciInstrumentsType;
	}

	public void setSciInstrumentsType(SciInstrumentsType sciInstrumentsType) {
		this.sciInstrumentsType = sciInstrumentsType;
	}

	public List<Schedule> getLsitSchedule() {
		return lsitSchedule;
	}

	public void setLsitSchedule(List<Schedule> lsitSchedule) {
		this.lsitSchedule = lsitSchedule;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Coursepresident getCoursepresident() {
		return coursepresident;
	}

	public void setCoursepresident(Coursepresident coursepresident) {
		this.coursepresident = coursepresident;
	}

	
	
}
