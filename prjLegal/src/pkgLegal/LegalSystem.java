package pkgLegal;

import java.io.*;

public class LegalSystem {

	public static void main(String[] args) throws IOException 
	{
		
		Legal group = null;
		boolean loginValid = false;
				
		try
		{
			group = new Legal();
			System.out.println();
				
			loginValid = group.login(); 
			
			if(loginValid)
			{
				System.out.println("\nLogin Successfull.\n");
				group.mainMenu();
			}
			else
				System.out.println("\nExceeded Allowed Login Attempts, the Program has been terminated\n");

		}
		catch (FileNotFoundException e)
		{	
			System.out.println("Error. File Not Found");
			System.exit(100);
		}
		catch (IOException e)
		{	
			System.out.println("Error Loading data");
			System.exit(101);
		}
		catch (NumberFormatException e)
		{	
			System.out.println("Error. Invalid number format");
			System.exit(102);
		}
		finally 
		{
			group.saveAppointments();
			group.saveCases();
			
			System.out.println("Thank you for using Legal System!");
			System.out.println("Goodbye.");
		}

	}

}
