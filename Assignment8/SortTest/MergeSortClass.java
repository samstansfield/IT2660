public class MergeSortClass
{
    int[] outArray;
    Integer[] array;
    int temp;
  

   
    static int[] MergeSort(int n, int array[])
    {
        int[] tempArray = new int[n];
        mergeSortMethod(0,n-1,tempArray,array);
        return array;
    }
    static void mergeSortMethod(int leftIndex, int rightIndex, int tempArray[], int array[])
    {
        int n = rightIndex-leftIndex+1;
        if(n == 1) return;
        int midIndex = (rightIndex + leftIndex)/2;
       
        mergeSortMethod(leftIndex, midIndex, tempArray, array);
        mergeSortMethod(midIndex+1, rightIndex, tempArray, array);
        merge(leftIndex, midIndex+1, rightIndex,tempArray,array);
        return;
    }
    static void merge(int leftIndex, int midIndex, int rightIndex, int tempArray[], int array[])
    {
        int tempPointer = leftIndex;
        int rightEnd = midIndex-1;  //  The book calls this "leftEnd" which makes no sense IMO.  
                                     //  It is the right end of the left sub array.
        int n = rightIndex-leftIndex+1;
        
        while((leftIndex<=rightEnd)&&(midIndex<=rightIndex))
        {
            
            if(array[leftIndex] <= array[midIndex])
            {
                tempArray[tempPointer] = array[leftIndex];
                tempPointer++;
                leftIndex++;
            }
            else
            {
                tempArray[tempPointer] = array[midIndex];
                tempPointer++;
                midIndex++;
            }
           
            
        }
        if(leftIndex <= rightEnd)
        {
            while(leftIndex<=rightEnd)
            {
                
                tempArray[tempPointer] = array[leftIndex];
                leftIndex++;
                tempPointer++;
            }
        }
        else
        {
            while(midIndex<=rightIndex)
            {
                tempArray[tempPointer] = array[midIndex];
                midIndex++;
                tempPointer++;
            }
        }
        
        for(int i = 0;i<n;i++)
        {
            array[rightIndex] = tempArray[rightIndex];
            rightIndex--;
        }
        
    }
}
