import java.util.*;
import java.io.*;

/**
 * This is a modification of the RandomStudent class for testing the StudentApps.  This
 * class produces a random name and ticket combination.
 * 
 * RandomStudents class generates Student objects for test purposes.
 * Each name is a String taken randomly from the 1,000 most common boy names and 
 * 1,000 most common girl names.
 * The ID is an 8-digit random number stored as a String with the character 's' prefixed.
 * The GPA is a double assumed to be uniformly distributed between 2.00 and 4.00.
 * 
 * To use the class a RandomStudents object is created.
 * The getStudent( ) method can then return randomly created Student objects.
 * 
 *
 * @author Edward Durkin
 * @version Version 1.0
 */
public class RandomTickets
{
    // instance variables
    private String guestNames[];
    private int numberOfNames;
    private Random randomInteger;

    /**
     * Constructor for objects of class RandomStudent
     */
    public RandomTickets()
    {
       numberOfNames = 1000;
       guestNames = new String[numberOfNames*2];
       readNamesFromFile("boynames.txt", 0, numberOfNames);
       readNamesFromFile("girlnames.txt", numberOfNames, numberOfNames*2);
       
       long seed = System.currentTimeMillis(); //Get current time as a long.
       randomInteger = new Random(seed);//Use seed to generate random numbers.
    }    
    
    private void readNamesFromFile( String fileName, int start, int end)
    {
       Scanner inputStream = null;

       try
       {
           inputStream =
              new Scanner(new FileInputStream(fileName));
           
           for (int i=start; i< end; i++)
	   {
		String line = inputStream.nextLine();
		//Parse out first name, ignore the number
		int space = line.indexOf(" ",0);
		guestNames[i] = line.substring(0,space);
           }    
	   inputStream.close();
       }
       catch(FileNotFoundException e)
       {
           System.out.println("File: " + fileName + " not found");
           System.out.println("or could not be opened.");
           System.exit(0);
       }
       catch (IOException e)
       {
           System.out.println("Error reading from file:" + fileName);
	   System.exit(0);
       }
              
    }
    
    private String getRandomName( )
    {
      int index = randomInteger.nextInt(numberOfNames*2);
      return guestNames[index];   
    }
    
    private int getRandomTicket( )
    {
       //ID is a 4 -digit integer value that will be expressed as a String
       int number = randomInteger.nextInt(1000) + 1000; //generate a 4-digit number
       
       return number;    
    }
    
    
    
    public Ticket getTicket( )
    {
        String name = getRandomName( );
        int code = getRandomTicket();
        return new Ticket(code,name);
    }
     
        
        
}

