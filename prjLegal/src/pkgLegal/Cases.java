package pkgLegal;

public class Cases {
	
	private int caseNum;
	private int clientID;
	private float totalBill; // Lawyers rate * amount of hours + tax
	private String status; // paid or pending payment
	
	//Default Constructor
	public Cases(){
		
		caseNum = 0;
		clientID = 0;
		totalBill = 0f;
		status = "";
	}
	
	//Constructor
	public Cases(int cN, int cI, float tB, String s){
		
		caseNum = cN;
		clientID = cI;
		totalBill = tB;
		status = s;
	}
	
	//Setters and Getters 
	public int getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(int caseNum) {
		this.caseNum = caseNum;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public float getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(float totalBill) {
		this.totalBill = totalBill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
