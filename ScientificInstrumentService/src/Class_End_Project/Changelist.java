package Class_End_Project;


public class Changelist {
	private String changelistID;
	private Schedule historyschedule;
	private Schedule newschedule;
	private String detail;
	

	public Changelist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Changelist(String changelistID, String detail) {
		super();
		this.changelistID = changelistID;
		this.detail = detail;
	
	}

	public String getChangelistID() {
		return changelistID;
	}

	public void setChangelistID(String changelistID) {
		this.changelistID = changelistID;
	}

	public Schedule getHistoryschedule() {
		return historyschedule;
	}

	public void setHistoryschedule(Schedule historyschedule) {
		this.historyschedule = historyschedule;
	}

	public Schedule getNewschedule() {
		return newschedule;
	}

	public void setNewschedule(Schedule newschedule) {
		this.newschedule = newschedule;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


}
