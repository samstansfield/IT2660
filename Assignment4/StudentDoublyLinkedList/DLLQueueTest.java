
/**
 * Main program to test the DLLQueue class.  The reason I did this is to not have to type in strings more than
 * once during testing.  There is an array of Strings for the inital test.  Once that is complete the 
 * user may input a String for testing.
 */
import java.util.*;
public class DLLQueueTest
{
    boolean done = false;
    Scanner keyboard = new Scanner(System.in);
    String inputString;
    String letterString;
    String[] testStrings = {"Madam, I'm Adam","This is not a Palindrome","Able was I ere I saw Elba", 
                "Redrum, sir, is Murder", "Was it a cat I saw?", "In girum imus nocte et consumimur igni"};
                //  We go into a circle by night and are consumed by fire.
    private RandomStudents r;
    DLLQueue<Student> studentQueue;
    public DLLQueueTest() 
    {
        r = new RandomStudents();
    }  // Blank constructor.
    public static void main(String[] args)
    {
        DLLQueueTest program = new DLLQueueTest();  //  Must instantiate the main program.
        /*
         * First build the random students and add them to a queue.
         */
        program.studentQueue = new DLLQueue<Student>();
        System.out.println("First get some random students and enqueue them.");
        for(int i=0;i<5;i++)
        {
             
             Student one = program.r.getStudent(); //  get a random student.
             System.out.println(one.toString());  //  Print them out as we get them then enqueue them..
             if(!program.studentQueue.enqueue(one)) System.out.println("enqueue failed");
        }
        System.out.println("Now do showAll, should show Students in the same order.");
        program.studentQueue.showAll();
        System.out.println("Now dequeue them and print them again.  They should be in the same order");
        for(int i=0;i<5;i++)
        {
             
             
             System.out.println(program.studentQueue.dequeue().toString());  //  Print them out as we get them.
             
        }
        
        System.out.println("Now look at some Palindromes using the DLLQueue");
        //  Switch to Palindromes.
        for(int i=0;i<6;i++)   //  Do the test strings.
        {
            program.tester(program.testStrings[i]);
            
        }
        
        
        
    }
    /*  The following tests the inputString.  It first extracts the letters from the String.
     * Then it converts to upper case and finds the length.  Finally it 
     * creates an instance of the Palindromes class and tests it.
     */
    private void tester(String inputString)
    {
        String letterString = inputString.replaceAll("[^A-Za-z]+","").toUpperCase();
        int size = letterString.length();
        DLLPalindromes thisPalindrome = new DLLPalindromes(size);
        boolean test = thisPalindrome.checkPalindrome(letterString);
           
        System.out.println("The input String is: " + inputString);
        if(test)System.out.println("It is a Palindrome.");
        else System.out.println("It is not a Palindrome.");
    }
}

    

    
