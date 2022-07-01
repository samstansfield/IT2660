public class BubbleSort
{
    int[] outArray;
    Integer[] array;
    int temp;
       

    static int[] BubbleSort(int n, int[] array)
    {
        int itemSorted = 0;
        boolean flip;
        int b;
        
        int itemsSorted = 0;
        
        do
        {
            flip = false;
            for(int t = n-2;t>=itemsSorted;t--)
            {
                b = t+1;
               
                if(array[b] < array[t])
                {
                    
                    flip = true;
                    swap(b,t,array);
                }
            }
            itemsSorted++;
            
        }
        while((flip == true) && (itemsSorted != n-1));
        return array;
    }
    static void swap(int i, int j, int[] array)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return;
    }
    
}
