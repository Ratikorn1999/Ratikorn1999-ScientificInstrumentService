package Class_End_Project;

import java.sql.Timestamp;


public class Schedule {
	
	private String schedule_ID;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private String time;
	private double maintenancefee ;
	private double additionalcost ; 
	private ChangeForm changeForm;
	private SericeRequestForm sericeRequestForm;
	private SciInstruments sciInstruments;
	
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(String schedule_ID, Timestamp startDateTime, Timestamp endDateTime) {
		super();
		this.schedule_ID = schedule_ID;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	public String getSchedule_ID() {
		return schedule_ID;
	}
	public void setSchedule_ID(String schedule_ID) {
		this.schedule_ID = schedule_ID;
	}
	public Timestamp getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Timestamp getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	
	
}
