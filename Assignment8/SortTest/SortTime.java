import java.util.*;

/**
 * A program that measures the time for sorting integers.
 * 
 * @author  Edward Durkin
 * @version Spring 2020
 */
public class SortTime
{   
    public static void main(String args[])
    {
       int size = 1000000;
       long startTime, endTime;
       
       //Create test array
       int[] test = new int[size];
       int[] inArray = new int[size];
       int[] outArray = new int[size];
       System.out.println("The size is: " + size);
       
       RandomIntegers rtest = new RandomIntegers(size, size); //Generate integers
       test = rtest.getTestArray(); //get those integers in array
       System.arraycopy(test,0,inArray,0,size);
       startTime = System.currentTimeMillis();
       outArray = BubbleSort.BubbleSort(size,inArray);  //Sort it - Students replace this with your sort (bubble, merge, or quick)
       endTime = System.currentTimeMillis();
       System.out.println("\nBubble Sort");
       System.out.println("Total time to sort = " + (endTime - startTime) + " milliseconds");
       
       //Verify array is sorted
       System.out.println("Array of integers sorted: " + verify(outArray));
       
       System.arraycopy(test,0,inArray,0,size);
       startTime = System.currentTimeMillis();
       outArray = MergeSortClass.MergeSort(size,inArray);  //Sort it - Students replace this with your sort (bubble, merge, or quick)
       endTime = System.currentTimeMillis();
       System.out.println("\nMerge Sort");
       System.out.println("Total time to sort = " + (endTime - startTime) + " milliseconds");
       
       //Verify array is sorted
       System.out.println("Array of integers sorted: " + verify(outArray));
       System.arraycopy(test,0,inArray,0,size);
       startTime = System.currentTimeMillis();
       outArray = HeapSortClass.HeapSort(size,inArray);  //Sort it - Students replace this with your sort (bubble, merge, or quick)
       endTime = System.currentTimeMillis();
       System.out.println("\nHeap Sort");
       System.out.println("Total time to sort = " + (endTime - startTime) + " milliseconds");
       
       //Verify array is sorted
       System.out.println("Array of integers sorted: " + verify(outArray));
       
    }
    
    // A method that verifies that an array is sorted in ascending order
    public static boolean verify(int[] array)
    { 
       boolean inOrder = true; 
       int i = 0; 
       while (inOrder && i < array.length - 1)
       { 
         inOrder = array[i] <= array[i + 1]; 
         i++; 
       } 
       return inOrder;
    }
}



