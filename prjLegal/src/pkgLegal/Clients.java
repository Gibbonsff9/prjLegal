package pkgLegal;

public class Clients {

	private String fullName;
	private int clientID;
	private String username;
	private String password;
	private String cardNum;
	private String expDate;
	
	//Default Constructor
	public Clients(){
		
		fullName = "";
		clientID = 0;
		username = "";
		password = "";
		cardNum = "";
		expDate = "";
	}
	
	//Constructor
	public Clients(String fN, int cID, String un, String pw, String cN, String eD){
		
		fullName = fN;
		clientID = cID;
		username = un;
		password = pw;
		cardNum = cN;
		expDate = eD;
	}

	//Setters and Getters
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getExpDate() {
		return expDate;
	}
	
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	
	
}
