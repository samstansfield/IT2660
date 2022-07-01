import java.util.*;

/**
 * The RandomInteger class generates an array of random integers of a specified size
 * Duplicate integers are possible.
 * 
 * @author Edward J. Durkin
 * @version November 28, 2016
 */
public class RandomIntegers
{
    private Random randomInteger;
    private int testArray[];
    
    /**
     * Constructor for the RandomIntegers class
     * 
     * @param n     Integer that provides the size of the testArray
     * @param bound Integer that provides the upper bound (exclusive) of the range
     */
    public RandomIntegers(int n, int bound)
    {
       long seed = System.currentTimeMillis(); //Get current time as a long.
       randomInteger = new Random(seed);//Use seed to generate random numbers.
       
       testArray = new int[n];
       for(int i = 0; i < n; i++)
         testArray[i] = randomInteger.nextInt(bound);
    }
    
    /**
     *  Method to return the test array of integers.
     */
    public int[] getTestArray()
    {
        return testArray;
    }
}
