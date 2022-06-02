package pkgLegal;

public class Lawyers {

	private String lawyerName;
	private String phoneNumber;
	private String specialty; //Primary focus in law
	private float hourlyRate;
	
	
	//Default Constructor
	public Lawyers(){
		
		lawyerName = "";
		phoneNumber = "";
		specialty = "";
		hourlyRate = 0f;
	}

	//Constructor
	public Lawyers(String lN, String pN, String sp, float hR) {
	
		lawyerName = lN;
		phoneNumber = pN;
		specialty = sp;
		hourlyRate = hR;
	}

	//Setters and Getters 
	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public float getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
	
	
}
