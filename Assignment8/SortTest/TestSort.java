import java.util.*;

/**
 * The TestSort class provides a driver program for testing sorting algorithms.
 * It generates a test array of random integers using the RandomIntegers class.
 * A print array method is provided so that the arrays can be printed 5 integers to a
 * line, thus conserving some space.
 * 
 * @author Edward J. Durkin
 * @version November 2016
 */

public class TestSort
{
    public static void main(String args[])
    {
       //Create test array
       int[] test = new int[20];
       RandomIntegers rtest = new RandomIntegers(20, 100); // Generate 20 in the 0 to 100 range
       test = rtest.getTestArray(); //get those integers
       
       TestSort program = new TestSort();
       
       //Print it out
       System.out.println("Unsorted integers: ");
       printArray(test);
       System.out.println();
       
       //Sort it - Students replace this sort with your sort (bubble, merge, or quick)
       test = HeapSortClass.HeapSort(20,test);
       
       //Print sorted array
       System.out.println("\nSorted integers: ");
       printArray(test);
    }
    
    public static void printArray(int a[])
    {
       for (int i = 0; i< a.length ; i++)
       {
         if(i % 5 == 0)
          System.out.println( ); //keep it 5 to a line
          
          System.out.print(a[i] + " ");
       }
       System.out.println();
     }
}


