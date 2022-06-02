package pkgLegal;

public class Appointment {

	private String date; 
	private String time;
	private int clientID;
	private String lawyerName;
	
	//Default Constructor
	public Appointment(){
		
		date = "";
		time = "";
		clientID = 0;
		lawyerName = "";	
	}
	
	//Constructor
	public Appointment(String d, String t, int id, String lN){
		
		date = d;
		time = t;
		clientID = id;
		lawyerName = lN;
	}

	//Setters and Getters
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setStatus(String lawyerName) {
		this.lawyerName = lawyerName;
	}
	
	
}
