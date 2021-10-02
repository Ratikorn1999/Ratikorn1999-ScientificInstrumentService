package Class_End_Project;


public class UseTestItem {
	
	private String usetestitemID;
	private String sampleName;
	private String code;
	private String weight;
	private int qty;
	private String articleNo;
	private String number_Operating;
	private String storage;
	private double maintenancefee ;
	private TestItem testItem;

	public UseTestItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UseTestItem(String usetestitemID, String sampleName, String code, String weight, int qty, String articleNo,
			 String number_Operating, String storage, double maintenancefee) {
		super();
		this.usetestitemID = usetestitemID;
		this.sampleName = sampleName;
		this.code = code;
		this.weight = weight;
		this.qty = qty;
		this.articleNo = articleNo;
		this.number_Operating = number_Operating;
		this.storage = storage;
		this.maintenancefee = maintenancefee;
	}

	public String getUsetestitemID() {
		return usetestitemID;
	}

	public void setUsetestitemID(String usetestitemID) {
		this.usetestitemID = usetestitemID;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}

	

	public String getNumber_Operating() {
		return number_Operating;
	}

	public void setNumber_Operating(String number_Operating) {
		this.number_Operating = number_Operating;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public double getMaintenancefee() {
		return maintenancefee;
	}

	public void setMaintenancefee(double maintenancefee) {
		this.maintenancefee = maintenancefee;
	}

	
	public TestItem getTestItem() {
		return testItem;
	}

	public void setTestItem(TestItem testItem) {
		this.testItem = testItem;
	}

	



	
	
	

}
