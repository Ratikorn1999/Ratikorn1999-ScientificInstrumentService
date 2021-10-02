package Class_End_Project;



public class TestItem {


	private String testitem_ID;
	private String testitem_Name;
	private String experiment;
	private String min_qty_samples;
	private Double priceType_A;
	private Double priceType_B;
	private Double priceType_C;
	private Double priceType_D;
	private Double nextprice;
	
	private TypeTestItem typetestitem;
	
	
	
	
	public TestItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TestItem(String testitem_ID, String testitem_Name, String experiment, String min_qty_samples,
			Double priceType_A, Double priceType_B, Double priceType_C, Double priceType_D, Double nextprice) {
		super();
		this.testitem_ID = testitem_ID;
		this.testitem_Name = testitem_Name;
		this.experiment = experiment;
		this.min_qty_samples = min_qty_samples;
		this.priceType_A = priceType_A;
		this.priceType_B = priceType_B;
		this.priceType_C = priceType_C;
		this.priceType_D = priceType_D;
		this.nextprice = nextprice;
	}

	public String getTestitem_ID() {
		return testitem_ID;
	}
	public void setTestitem_ID(String testitem_ID) {
		this.testitem_ID = testitem_ID;
	}
	public String getTestitem_Name() {
		return testitem_Name;
	}
	public void setTestitem_Name(String testitem_Name) {
		this.testitem_Name = testitem_Name;
	}
	public String getExperiment() {
		return experiment;
	}
	public void setExperiment(String experiment) {
		this.experiment = experiment;
	}
	public String getMin_qty_samples() {
		return min_qty_samples;
	}
	public void setMin_qty_samples(String min_qty_samples) {
		this.min_qty_samples = min_qty_samples;
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
	public TypeTestItem getTypetestitem() {
		return typetestitem;
	}
	public void setTypetestitem(TypeTestItem typetestitem) {
		this.typetestitem = typetestitem;
	}

	public Double getNextprice() {
		return nextprice;
	}

	public void setNextprice(Double nextprice) {
		this.nextprice = nextprice;
	}
	
	
}
