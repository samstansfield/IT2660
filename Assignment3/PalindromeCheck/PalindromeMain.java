
/**
 * Main program to test the Palindromes class.  It has a separate method to call the checkPalindrome 
 * method of the Palindromes class.  The reason I did this is to not have to type in strings more than
 * once during testing.  There is an array of Strings for the inital test.  Once that it complete the 
 * user may input a String for testing.
 */
import java.util.*;
public class PalindromeMain
{
    boolean done = false;
    Scanner keyboard = new Scanner(System.in);
    String inputString;
    String letterString;
    String[] testStrings = {"Madam, I'm Adam","This is not a Palindrome","Able was I ere I saw Elba", 
                "Redrum, sir, is Murder", "Was it a cat I saw?", "In girum imus nocte et consumimur igni"};
                //  We go into a circle by night and are consumed by fire.
    
    public PalindromeMain() {};  // Blank constructor.
    public static void main(String[] args)
    {
        PalindromeMain program = new PalindromeMain();  //  Must instantiate the main program.
        for(int i=0;i<6;i++)   //  Do the test strings.
        {
            program.tester(program.testStrings[i]);
            
        }
        while(!program.done)  //  now accept input.
        {
            System.out.println("Please enter a string to test for Palindrome, DONE to exit.");
            program.inputString = program.keyboard.next();
            if(program.inputString.toUpperCase().equals("DONE"))
            {
                System.out.println("Thanks for playing");
                System.exit(0);
            }
            program.tester(program.inputString);
        }
        
        
    }
    /*  The following tests the inputString.  It first extracts the letters from the String.
     * Then it converts to upper case and finds the lengteh.  Finally it 
     * creates an instance of the Palindromes class and tests it.
     */
    private void tester(String inputString)
    {
        String letterString = inputString.replaceAll("[^A-Za-z]+","").toUpperCase();
        int size = letterString.length();
        Palindromes thisPalindrome = new Palindromes(size);
        boolean test = thisPalindrome.checkPalindrome(letterString);
           
        System.out.println("The input String is: " + inputString);
        if(test)System.out.println("It is a Palindrome.");
        else System.out.println("It is not a Palindrome.");
    }
}

    

    
