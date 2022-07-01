import java.util.*;
import java.io.*;

/**
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
public class RandomStudents
{
    // instance variables
    private String studentNames[];
    private int numberOfNames;
    private Random randomInteger;

    /**
     * Constructor for objects of class RandomStudent
     */
    public RandomStudents()
    {
       numberOfNames = 1000;
       studentNames = new String[numberOfNames*2];
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
		studentNames[i] = line.substring(0,space);
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
      return studentNames[index];   
    }
    
    private String getRandomID( )
    {
       //ID is an 8-digit integer value that will be expressed as a String
       int number = randomInteger.nextInt(100000000) + 100000000; //generate a 9-digit number
       String value = Integer.valueOf(number).toString( );
       return "s" + value.substring( 1, value.length( )); //Replace first character with an s       
    }
    
    private double getRandomGPA( )
    {
        //GPA is asssumed to be uniformly distributed between 2.00 and 4.00 expressed as a String
        int gpaInteger = randomInteger.nextInt(200) + 201;
        double gpaDouble = gpaInteger/100.0;
        return gpaDouble;        
    }
    
    public Student getStudent( )
    {
        String name = getRandomName( );
        String id = getRandomID( );
        double gpa = getRandomGPA( );
        return new Student(name, id, gpa);
    }
     
        
        
}

