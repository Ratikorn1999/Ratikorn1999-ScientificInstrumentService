package Class_End_Project;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

public class ChangeForm {

	private String changeFormNumber;
	private String type;
	private Date pickupdate;
	private int approveLV;
	private SericeRequestForm sericeRequestForm;
	private List<Schedule> listSchedule = new Vector<>();
	private List<Changelist> changelists;
	private List<ApprovalChangeForm> listApprovalChangeForm ;
	private Timestamp createdate;
	private String change_status;

	public ChangeForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChangeForm(String changeFormNumber, String type, Date pickupdate, int approveLV) {
		super();
		this.changeFormNumber = changeFormNumber;
		this.type = type;
		this.pickupdate = pickupdate;
		this.approveLV = approveLV;
	}

	public String getChangeFormNumber() {
		return changeFormNumber;
	}

	public void setChangeFormNumber(String changeFormNumber) {
		this.changeFormNumber = changeFormNumber;
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

	public int getApproveLV() {
		return approveLV;
	}

	public void setApproveLV(int approveLV) {
		this.approveLV = approveLV;
	}

	public SericeRequestForm getSericeRequestForm() {
		return sericeRequestForm;
	}

	public void setSericeRequestForm(SericeRequestForm sericeRequestForm) {
		this.sericeRequestForm = sericeRequestForm;
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

	public String getChange_status() {
		return change_status;
	}

	public void setChange_status(String change_status) {
		this.change_status = change_status;
	}

	public List<Changelist> getChangelists() {
		return changelists;
	}

	public void setChangelists(List<Changelist> changelists) {
		this.changelists = changelists;
	}

	public List<ApprovalChangeForm> getListApprovalChangeForm() {
		return listApprovalChangeForm;
	}

	public void setListApprovalChangeForm(List<ApprovalChangeForm> listApprovalChangeForm) {
		this.listApprovalChangeForm = listApprovalChangeForm;
	}
	
	
}
