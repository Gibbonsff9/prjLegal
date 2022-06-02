package pkgLegal;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Project Name: 	Legal
// Programmers:		Salvatore Barucci, Christian Gibbons
// Documentation:	Andrew McLaughlin, Siam Hoque
// Presentation:	Ahsan Rehmatulla, Sudesh Sookwah
// Date Created:	April 16, 2020

public class Legal {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String buffer = "";
	
	int clientsIndex = -1;
	
	Clients [] clients = new Clients[5];
	Lawyers [] lawyers = new Lawyers[3];
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	ArrayList<Cases> cases = new ArrayList<Cases>();
	
	
	Legal() throws IOException
	{
		loadAppointments();
		loadCases();
		loadClients();
		loadLawyers();
		
	} // end of constructor
	
	void mainMenu() throws IOException 
	{
		int mainMenuChoice;
		boolean validInput;

		do {
			System.out.printf( "Main Menu: \n" +
				"\t 1. Schedule an Appointment\n" +
				"\t 2. Pay a Bill\n" +
				"\t 3. Cancel an Appointment\n" +
				"\t 4. View Your Bills\n" +
				"\t 5. Display all Appointments\n" +
				"\t 6. Display Appointments by Lawyer\n" +
				"\t 7. Exit Program\n\n" +
				"Enter your choice (1-7): ");
			
			mainMenuChoice = Integer.parseInt(br.readLine());
			
			System.out.println();

			validInput = false;
			
			// Validate User Input
			if (mainMenuChoice >= 1 && mainMenuChoice <= 7)
				validInput = true;
		
			// If mainMenuChoice not between 1 and 7, error message will print, loop will rerun
			if (!validInput)
			{
				System.out.println("Invalid Choice, please enter a number between 1 and 6.");
			}
			else if (mainMenuChoice == 1)
			{
				// Choice 1 leads to scheduleAppointment method
				scheduleAppointment();
			}
			else if (mainMenuChoice == 2)
			{
				// Choice 2 leads to payBill method
				payBill();
			}
			else if (mainMenuChoice == 3)
			{
				// Choice 3 leads to cancelAppointment method
				cancelAppointment();
			}
			else if (mainMenuChoice == 4)
			{
				// Choice 4 leads to displayBills method
				displayBills();
			}
			else if (mainMenuChoice == 5)
			{
				// Choice 5 leads to displayAllAppointments method
				displayAllAppointments();
			}
			else if (mainMenuChoice == 6)
			{
				// Choice 6 leads to displayLawyerAppointments method
				displayLawyerAppointments();
			}
			
				// Choice 7 leads to program exiting, executing finally statements

			// displayMainMenu can exited so the program will finish only when the mainMenuChoice is 7
		} while (mainMenuChoice != 7 || !validInput);
		

	} // end of mainMenu() 
	
	void displayLawyers()
	{	
		System.out.println("Lawyers: \tPhone Number: \tSpecialty: \tHourly Rate:");
		for (int i = 0; i < 3; i++)
		{	System.out.print((i+1)+"." + lawyers[i].getLawyerName() + "\t" +
							lawyers[i].getPhoneNumber( )+ "\t" +
							lawyers[i].getSpecialty() + "\t$");
						System.out.printf("%.2f \n", lawyers[i].getHourlyRate());
		}
	} //end of displayLawyers()
	
	void scheduleAppointment() throws IOException
	{
		String date = "";
		String time = "";
		int clientChoice = -1;
		System.out.print("Enter the date you wish to make your appointment: ");
		date = br.readLine();
		System.out.print("Enter the time you wish to make your appointment: ");
		time = br.readLine();
		
		displayLawyers();
		
		System.out.println("\nSelect a Lawyer: ");
		clientChoice = Integer.parseInt(br.readLine());
		if (clientChoice >= 1 && clientChoice <= 3) 
		{
		appointments.add(new Appointment(date, time, clients[clientsIndex].getClientID(), lawyers[clientChoice - 1].getLawyerName()));
		saveAppointments();
		System.out.println("Appointment Saved.");
		}
		else
			System.out.println("Invalid Choice");
	
		System.out.println();
		System.out.println("Press ENTER to return to Main Menu");
		buffer = br.readLine();
		
	} // end of scheduleAppointment()
	
	void payBill() throws IOException
	{
		boolean billExists = false;
		int userBillChoice = -1;
		String cardNum = "";
		String expDate = "";
		System.out.println("Pay a Bill \nShowing your Bills:\n");
		
		for (int i = 0; i < cases.size(); i++) 
		{
			if (cases.get(i).getClientID() == clients[clientsIndex].getClientID())
				billExists = true;
		}
		
		if (billExists)
		{
			System.out.println("   Case Number \tClient ID \tTotal Bill \tPayment Status\n   _____________________________________________________________________");
			
			int i = 0, 
				j = 0,
				billIndex = -1,
				counter = 0;
			
			int[] billIndexArray = new int[cases.size()];
			
			while(i < cases.size()) 
			{
				if( cases.get(i).getClientID() == clients[clientsIndex].getClientID())
				{
					j++;
					System.out.print( (j) + ": " + cases.get(i).getCaseNum() + "\t\t" +
									cases.get(i).getClientID() + "\t\t$");
									System.out.printf("%.2f", cases.get(i).getTotalBill());
					System.out.print("    \t" + cases.get(i).getStatus() + "\n");	
					
					billIndexArray[i] = j; 
				}				
				i++;
			}
			
			System.out.print("\nSelect the case you wish to pay for: ");
			userBillChoice = Integer.parseInt(br.readLine());
			
			if ( userBillChoice >= 1 && userBillChoice <= j)
			{
				while(counter < cases.size())
				{
					if(billIndexArray[counter] == userBillChoice)
					{
						billIndex = counter;
						break;
					}
					
					counter++;
				}
				
				if((cases.get(billIndex).getStatus().contentEquals("PAID"))) {				//If status is PAID then process is complete
					System.out.println("Bill is already paid.");
				}
				else {
						System.out.print("Please enter card number: ");
						cardNum = br.readLine();
						
						if (cardNum.contentEquals(clients[clientsIndex].getCardNum())) {
							System.out.print("Please enter expiration date: ");
							expDate = br.readLine();
							
							if(expDate.contentEquals(clients[clientsIndex].getExpDate())) {
							
								cases.get(billIndex).setStatus("PAID");						//If card matches case is marked "PAID"
								saveCases();
								System.out.println("Bill Paid.");			
						}
							else {
								System.out.println("Expiration date does not match card.");	//If expiration date doesn't match.
							}
						
				}
						else {
							System.out.println("Card not on file.");
						}
				}
			}
			else
				System.out.println("Invalid Choice");
		}
		else
			System.out.println("You currently do not have any cases pending.\n");
	
		System.out.println();
		System.out.println("Press ENTER to return to Main Menu");
		buffer = br.readLine();
		
		
	} // end of payBill()
	
	void displayBills() throws IOException
	{
		boolean billExists = false;
		System.out.println("Showing your Bills:\n");
		
		for (int i = 0; i < cases.size(); i++) 
		{
			if (cases.get(i).getClientID() == clients[clientsIndex].getClientID())
				billExists = true;
		}
		
		if (billExists)
		{
			System.out.println("Case Number \tClient ID \tTotal Bill \tPayment Status\n_____________________________________________________________________");
			
			int i = 0;
			
			while(i < cases.size()) 
			{
				if( cases.get(i).getClientID() == clients[clientsIndex].getClientID())
				{
					System.out.print(cases.get(i).getCaseNum() + "\t\t" +
									cases.get(i).getClientID() + "\t\t$");
									System.out.printf("%.2f", cases.get(i).getTotalBill());
					System.out.print("    \t" + cases.get(i).getStatus() + "\n");	
				}				
				i++;
			}
		}
		else
			System.out.println("You currently do not have any cases pending.\n");
		
		System.out.println();
		System.out.println("Press ENTER to return to Main Menu");
		buffer = br.readLine();
	} // end of displayBills()
	
	void cancelAppointment() throws IOException
	{
		boolean appointmentExists = false;
		int userChoice = -1;
		
		System.out.println("Cancel an Appointment\nShowing your appointments:\n");
		
		// Search the appointments arrayList to see if client has an appointment to be canceled
		for (int i = 0; i < appointments.size(); i++)
		{
			if (appointments.get(i).getClientID() == clients[clientsIndex].getClientID())
				appointmentExists = true;
		}
		
		if (appointmentExists)
		{
			int i = 0, 
				j = 0,
				apptIndex = -1,
				counter = 0;
			
			System.out.println("   Client ID \tDate \t\tTime \t\tLawyer\n   _____________________________________________________________");
						
			int[] apptIndexArray = new int[appointments.size()]; // Saves value of index of appointments to any 
																 // instance matching the ClientID of the client currently logged in
			
			while(i < appointments.size()) 
			{
				if( appointments.get(i).getClientID() == clients[clientsIndex].getClientID())
				{
					j++;
					System.out.print( (j) + ": " + appointments.get(i).getClientID() + "\t\t" +
									appointments.get(i).getDate() + "\t" + 
									appointments.get(i).getTime() + " \t" +
									appointments.get(i).getLawyerName() + "\n");	
					
					apptIndexArray[i] = j; 
				}				
				i++;
			}

			System.out.print("\nSelect the appointment you wish to cancel.\nEnter your choice: ");
			userChoice = Integer.parseInt(br.readLine());
			
			if (userChoice >= 1 && userChoice <= j)
			{
				while(counter < appointments.size())
				{
					if (apptIndexArray[counter] == userChoice)
					{
						apptIndex = counter;
						break;
					}
					counter++;
				}

				appointments.remove(apptIndex);
				saveAppointments();
				System.out.println("Appointment Canceled.");
			}
			else
				System.out.println("Invalid Choice");
		
		}
		else
			System.out.print("You currently do not have any appointments.");
		
		System.out.println();
		System.out.println("\nPress ENTER to return to Main Menu ");
		buffer = br.readLine();
		
	} // end of cancelAppointment()
	
	void displayAllAppointments() throws IOException
	{
		System.out.println("Displaying all Appointments:\n");
		
		System.out.println("Client ID \tDate \t\tTime \t\tLawyer\n_____________________________________________________________");
		
		for (int i = 0; i < appointments.size(); i++) 
		{
			System.out.print( appointments.get(i).getClientID() + "\t\t" +
								appointments.get(i).getDate() + "\t" + 
								appointments.get(i).getTime() + " \t" +
								appointments.get(i).getLawyerName() + "\n");
		}
		
		System.out.println("\nPress ENTER to return to Main Menu ");
		buffer = br.readLine();
		
	} // end of displayAllAppointments()
	
	void displayLawyerAppointments() throws IOException
	{
		System.out.println("Displaying Appointments by Lawyer:\n");
		
		displayLawyers();
		
		System.out.print("\nSelect a Lawyer: ");
		int clientChoice = Integer.parseInt(br.readLine());
		System.out.println();
		if (clientChoice >= 1 && clientChoice <= 3) 
		{
	
			System.out.println("Client ID \tDate \t\tTime \t\tLawyer\n_____________________________________________________________");
			
			for (int i = 0; i < appointments.size(); i++) 
			{
				if( lawyers[clientChoice - 1].getLawyerName().equalsIgnoreCase( appointments.get(i).getLawyerName() ) )
				{
					System.out.print( appointments.get(i).getClientID() + "\t\t" +
									appointments.get(i).getDate() + "\t" + 
									appointments.get(i).getTime() + " \t" +
									appointments.get(i).getLawyerName() + "\n");
				}
			}
		}
		else
			System.out.println("Invalid Choice");
		
		System.out.println();
		System.out.println("Press ENTER to return to Main Menu");
		buffer = br.readLine();
		
	} // end of displayLawyerAppointments()
	
	void loadAppointments() throws IOException
	{
		// Reading data from Appointment text file
		FileReader fr = new FileReader("appointment.txt");
		BufferedReader br = new BufferedReader(fr);
		
		// File Variables
		String date = "";
		String time = "";
		int clientID = 0;
		String status = "";
		
		// Variables for each line
		String eachLine = "";
		StringTokenizer st;
		eachLine = br.readLine();
		
		while (eachLine != null)
		{
			st = new StringTokenizer(eachLine,",");
			while (st.hasMoreTokens())
			{
				date = st.nextToken();
				time = st.nextToken();
				clientID = Integer.parseInt(st.nextToken());
				status = st.nextToken();
				
				appointments.add(new Appointment(date,time,clientID,status));
				
				eachLine = br.readLine();
				
			} // end of reading a line
		} // end of reading file
		
		br.close();
		System.out.println("Appointments Loaded");
	
	} // end of loadAppointments()
	
	void loadCases() throws IOException
	{
		// Reading data from Cases text file
		FileReader fr = new FileReader("cases.txt");
		BufferedReader br = new BufferedReader(fr);
		
		// File Variables
		int caseNum = 0;
		int clientID = 0;
		float totalBill = 0.00f;
		String status = "";
		
		// Variables for each line
		String eachLine = "";
		StringTokenizer st;
		eachLine = br.readLine();
		
		while (eachLine != null)
		{
			st = new StringTokenizer(eachLine,",");
			while (st.hasMoreTokens())
			{
				caseNum = Integer.parseInt(st.nextToken());
				clientID = Integer.parseInt(st.nextToken());
				totalBill = Float.parseFloat(st.nextToken());
				status = st.nextToken();
				
				cases.add(new Cases(caseNum,clientID,totalBill,status));
				
				eachLine = br.readLine();
				
			} // end of reading a line
		} // end of reading file
		
		br.close();
		System.out.println("Cases Loaded");
	
	} // end of loadCases()
	
	void loadClients() throws IOException
	{
		// Reading data from Clients text file
		FileReader fr = new FileReader("clients.txt");
		BufferedReader br = new BufferedReader(fr);
		
		// File Variables
		String fullName = "";
		int clientID = 0;
		String username = "";
		String password = "";
		String cardNum = "";
		String expDate = "";
		
		// Counter for array
		int i = 0;
		
		// Variables for each line
		String eachLine = "";
		StringTokenizer st;
		eachLine = br.readLine();
		
		while (eachLine != null)
		{
			st = new StringTokenizer(eachLine,",");
			while (st.hasMoreTokens())
			{
				fullName = st.nextToken();
				clientID = Integer.parseInt(st.nextToken());
				username = st.nextToken();
				password = st.nextToken();
				cardNum = st.nextToken();
				expDate = st.nextToken();
				
				clients[i] = new Clients(fullName,clientID,username,password,cardNum,expDate);
				i++; // go to next instance of array
				
				eachLine = br.readLine();
				
			} // end of reading a line
		} // end of reading file
		
		br.close();
		System.out.println("Clients Loaded");
		
	} // end of loadClients()
		
	void loadLawyers() throws IOException
	{
		// Reading data from Lawyers text file
		FileReader fr = new FileReader("lawyers.txt");
		BufferedReader br = new BufferedReader(fr);
		
		// File Variables
		String lawyerName = "";
		String phoneNum = "";
		String specialty = "";
		float hourlyRate = 0.00f;
		
		// Counter for array
		int i = 0;
		
		// Variables for each line
		String eachLine = "";
		StringTokenizer st;
		eachLine = br.readLine();
		
		while (eachLine != null)
		{
			st = new StringTokenizer(eachLine,",");
			while (st.hasMoreTokens())
			{
				lawyerName = st.nextToken();
				phoneNum = st.nextToken();
				specialty = st.nextToken();
				hourlyRate = Float.parseFloat(st.nextToken());
				
				lawyers[i] = new Lawyers(lawyerName,phoneNum,specialty,hourlyRate);
				i++; // go to next instance of array
				
				eachLine = br.readLine();
			
			} // end of reading a line
		} // end of reading file
		
		br.close();
		System.out.println("Lawyers Loaded");
			
	} // end of loadLawyers()
	
	void saveAppointments() throws IOException
	{
		// FileWriter to OVERWRITE not APPEND the file
		FileWriter fw = new FileWriter("appointment.txt", false);
		BufferedWriter bw = new BufferedWriter(fw);
		
		// Write one sale at a time into text file
		for (int i = 0; i < appointments.size(); i++)
		{
			bw.write(appointments.get(i).getDate() + "," + 
					appointments.get(i).getTime() + "," +
					appointments.get(i).getClientID() + "," +
					appointments.get(i).getLawyerName() + "\n");
		}
		
		bw.close();
	
	} // end of saveAppointments()
	
	void saveCases() throws IOException
	{
		// FileWriter to OVERWRITE not APPEND the file
		FileWriter fw = new FileWriter("cases.txt", false);
		BufferedWriter bw = new BufferedWriter(fw);
		
		// Write one sale at a time into text file
		for (int i = 0; i < cases.size(); i++)
		{
			bw.write(cases.get(i).getCaseNum() + "," + 
					cases.get(i).getClientID() + "," +
					cases.get(i).getTotalBill() + "," +
					cases.get(i).getStatus() + "\n");
		}
		
		bw.close();

	} // end of saveCases()
	
	boolean login() throws IOException
	{
		boolean loginValid = false,
				usernameFound = false;
		String username = "",
				password = "";
		int attempt = 1; // User gets three attemps before program terminates
		
		do
		{
		// Give invalid login message for each failed login after their first attempt
		if(attempt > 1)
			System.out.println("\nInvalid Login, Please try again.\n");
			
		System.out.printf("   ----Login----\n" + (4 - attempt) + " Attempt(s) Remaining\n\n"
				+ "Username: ");
		username = br.readLine();
		
		System.out.print("Password: ");
		password = br.readLine();

		// Search array for username
		for (int i = 0; i < clients.length; i++)
		{
			if (username.equals(clients[i].getUsername()))
			{
				clientsIndex = i; // username found in array, set index to mark which instance it matches
				usernameFound = true;
			}
			
			// Check if password matches on the instance where the username was found
			if (usernameFound)
			{
				if (password.equals(clients[clientsIndex].getPassword()))
					loginValid = true;
			}			
		}
		
		attempt++;
		
		} while(attempt <= 3 && !loginValid);
		// Loop will stop if user has more than 3 failed attempts or
		// enters the correct username and password combination
		
		return loginValid;
	}
	
}
