
/**
 *  This class will verify that a given string is a Palindrome (reads the same backwards and forwards).
 *  
 */
import java.util.*;
public class Palindromes
{
    int size;
    char[] charArray;
    Stack<Character> stack = new Stack<Character>();
    LinkedList<Character> queue = new LinkedList<Character>();
    /**
     * Preprocessing will be done before the Palindrome class is constructed.  
     */
    public Palindromes(int size)
    {
        charArray = new char[size];
        this.size = size;
    }

    public boolean checkPalindrome(String inputString)
    {
        this.charArray = inputString.toCharArray();
        for(int i =0;i<size;i++)
        {
            char temp = this.charArray[i];  //  Get one letter at a time.
            
            this.stack.push(temp);     //  push it on the stack
            this.queue.addLast(temp);  // add it to the back of the queue.
        }
        for(int i=0;i<size;i++)
        {
            Character temp1 = this.queue.remove();  //  take a character from the front of the queue.
            Character temp2 = this.stack.pop();     //  pop a character from the stack.
            
            if(!(temp1.equals(temp2)))return false;  //  It's not a palindrome if the letters aren't equal.
        }
        return true;  //  If we get to this point then all letters are accounted for.
    }
}
