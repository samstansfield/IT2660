
/**
 * Write a description of class BubbleSort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BubbleSortArray
{
     int[] array;

    /**
     * Constructor for objects of class BubbleSort
     */
    public BubbleSortArray(int n, int[] unsortedArray)
    {
        int[] array = new int[n];
        for(int i = 0;i<n;i++)this.array[i] = unsortedArray[i];
        
    }
    static int[] BubbleSort(int n, int[] array)
    {
        int itemSorted = 0;
        boolean flip;
        int b;
        int temp;
        int itemsSorted = 0;
        int pass = 0;
        int compareCount = 0;
        int swapCount = 0;
        int totalSwap = 0;
        int totalComp = 0;
        System.out.println();
        System.out.println("Pass " + "Compares " + "Swaps");
        do
        {
            flip = false;
            for(int t = n-2;t>=itemsSorted;t--)
            {
                b = t+1;
                compareCount++;
                if(array[b] < array[t])
                {
                    swapCount++;
                    flip = true;
                    temp = array[t];
                    array[t] = array[b];
                    array[b] = temp;
                }
            }
            itemsSorted++;
            pass++;
            System.out.println(pass + "     " + compareCount + "        " + swapCount);
            totalSwap = totalSwap + swapCount;
            totalComp = totalComp + compareCount;
            compareCount = 0;
            swapCount = 0;
        }
        while((flip == true) && (itemsSorted != n-1));
        System.out.println("-------------------");
        System.out.println("Totals " + totalComp + "      " + totalSwap);
        return array;
    }
    
}
