package Class_End_Project;

import java.sql.Date;

public class Rentalschedule {
	private String rentalscheduleid;
	private Date startDate;
	private Date endDate;
	private String term;
	private double maintenancefee;
	private double additionalcost;
	private ChangeForm changeForm;
	private SericeRequestForm sericeRequestForm;
	private SciInstruments sciInstruments;

	public Rentalschedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rentalschedule(String rentalscheduleid, Date startDate, Date endDate, String term, double maintenancefee) {
		super();
		this.rentalscheduleid = rentalscheduleid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.term = term;
		this.maintenancefee = maintenancefee;
		
	}

	public String getRentalscheduleid() {
		return rentalscheduleid;
	}

	public void setRentalscheduleid(String rentalscheduleid) {
		this.rentalscheduleid = rentalscheduleid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public double getMaintenancefee() {
		return maintenancefee;
	}

	public void setMaintenancefee(double maintenancefee) {
		this.maintenancefee = maintenancefee;
	}

	public double getAdditionalcost() {
		return additionalcost;
	}

	public void setAdditionalcost(double additionalcost) {
		this.additionalcost = additionalcost;
	}

	public ChangeForm getChangeForm() {
		return changeForm;
	}

	public void setChangeForm(ChangeForm changeForm) {
		this.changeForm = changeForm;
	}

	public SericeRequestForm getSericeRequestForm() {
		return sericeRequestForm;
	}

	public void setSericeRequestForm(SericeRequestForm sericeRequestForm) {
		this.sericeRequestForm = sericeRequestForm;
	}

	public SciInstruments getSciInstruments() {
		return sciInstruments;
	}

	public void setSciInstruments(SciInstruments sciInstruments) {
		this.sciInstruments = sciInstruments;
	}

}
