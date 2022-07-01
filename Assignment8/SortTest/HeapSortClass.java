public class HeapSortClass
{
    int[] outArray;
    Integer[] array;
    int temp;
  

/*
     * There are three basic steps for the heap sort.  1)  Build the initial heap, 2)  Swap the root 
     * into position.  3)  Reheap downward.  Repeat 2 and 3 until done.  The swap/reheap steps could
     * be done recursively.  It seemed easier to do it with a while loop.  Unlike the LNR traversal,
     * the reheap only moves along one branch of the tree and stops when it gets to the leaf.  This seems
     * simple enough until one realizes that there is a reheap step in (1).  The actual algorithm for 1 is:
     *      1)  Start with the leftmost-highest parent.  
     *      2)  Reheap downward from that parent.
     *      3)  Move (in upward direction) to next parent.
     *      4)  Repeat 2,3 until done.
     */
    static int[] HeapSort(int n, int[] array)
    {
        
        int startPoint = n/2-1;
        for(int i = startPoint;i>=0;i--)
        {
              ReHeap(-1,i,array);
        }  //  End of build initial heap step.
        
        int walled = 0;
        for(int i=n-1;i>=0;i--)  //  Start of swap/reheap step
        {
           swap(0,i,array);
           walled++;
           // System.out.println("swap" + walled);
           //  TestSort.printArray(array);
            // reheap downwards:
           ReHeap(walled,0,array);
           // System.out.println("reheap");
           // TestSort.printArray(array);
        }  // End of outer for loop.  Ready for next swap.
        return array;
    }
    /*
     * Does the ReHeap operation on the tree.  The method reheaps downward starting from "node".  "walled" is -1
     * during build when the reheap stops at the bottom of the array.  During the swap/reheap process walled is the 
     * number of nodes that are "walled off" from the algorithm.  
     */
    static void ReHeap(int walled, int node, int array[])
    {
        boolean done = false;
        int n;
        if(walled < 0) n = array.length;
        else n = array.length - walled;
        while(!done)     //  
        {
            int lcIndex = 2*node+1;
            int rcIndex = 2*node+2;
                
            if(lcIndex >= n) done = true;
            else
            {
                if(rcIndex >= n)
                {
                    // System.out.println("Wrong way");
                    // System.out.println(node  + " " + rcIndex + " "  + done);
                    done = true;  //  at this point this is the last possible node to swap so done.
                    if(array[node] < array[lcIndex])
                    {
                        swap(lcIndex,node,array);
                    }
                }
                else
                {
                    boolean bpl = array[node] >= array[lcIndex];  //  true if parent >= left
                    boolean bpr = array[node] >= array[rcIndex];
                    boolean blr = array[lcIndex] >= array[rcIndex];
                    if(!(bpl && bpr))  //  only swap if the parent is less than a child.
                    { 
                        if(blr)  //  if left is larger swap with that
                        {
                            
                            swap(lcIndex,node,array);
                            node = lcIndex;
                        }
                        else    //  otherwise swap with right.
                        {
                            
                            swap(rcIndex,node,array);
                            node = rcIndex;
                        }
                        done = false;
                    }
                    else done =  true;  //  No swap here so done with reheap..
                }
            }
                
        }   //  End of while.  
        return;
    }
    static void swap(int i, int j, int[] array)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return;
    }
}
