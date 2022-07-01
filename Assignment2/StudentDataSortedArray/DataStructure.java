/**
 * This is version 3.  
 * 
 * This class uses arrays to implement a data structure.  
 * 
 * It has methods for the four main processes:  fetch, delete, insert and update.
 * It also provides a sorted output of the main array.
 * 
 * To me the use of a pointer table for a situation like this is second nature.  The resources
 * involved in constantly making deep copys as the main array is rearranged is a poor choice
 * of methodology.  When the structure is sorted it is the pointer table that is rearranged.  
 * 
 * The main complication of doing it this what is what to do about garbage collection?  
 * Since I want to avoid moving cells around in the main array what I am doing is to push the
 * address of the (now blank) cell onto a stack.  Then insert will always check the stack 
 * first before inserting at the bottom of the array.  It doesn't matter where a particular item
 * is in the array.  (The pointer table and the stack were never part of the problems I had
 * getting this to work.  I still want to know how to get a binary seach to consistently give
 * the correct node for insert. I tried logic, I tried trial and error and I tried searching
 * the internet.  All the attempts partially worked.)
 * 
 * For long term use an additional method is recommended.  If the stack gets full then the
 * main array should be garbage collected.  This would only be needed if there are many 
 * more deletes than inserts.  It seems that the size of a structure would be more likely to
 * increase instead of decrease, it's hard to imagine when the "purge()" method would be used.
 * 
 */
import java.util.*;
public class DataStructure
{
    
    private Student[] data;
    private int[] pointerTable;  
    private int nextNode = 0;
    private int nextPointer = 0;
    private Stack<Integer> pointerStack;  //  For garbage collection on delete.
    private int initialSize;
    
    private Student blankStudent = new Student();  //  For placing in main array on delete.
    /**
     * Constructor for objects of class DataStructure
     */
    public DataStructure()  // default size = 100
    {
        data = new Student[100];
        pointerTable = new int[100];
        pointerStack = new Stack();
        this.initialSize = 100;
        return;
    }
    public DataStructure(int initialSize)
    {
        data = new Student[initialSize];
        pointerTable = new int[initialSize];
        pointerStack = new Stack();
        this.initialSize = initialSize;
        return;
    }
    /* 
     * delete finds the node.  Pushes the address in the data array and moves the nodes in the 
     * pointer table up.  It replaces the deleted student with a blank.  Returns false if the 
     * node to be deleted is not found.
     */
    public boolean delete(Student targetKey)
    {
        int pointerAddress = this.search(targetKey,false);
        if(pointerAddress < 0) return false;  //  Happens if node not found.
        this.data[pointerTable[pointerAddress]] = blankStudent.deepCopy();  //  blank out the data
        
        
        pointerStack.push(pointerTable[pointerAddress]);  //  push the data address
        nextPointer--;
        for(int i = pointerAddress;i < nextPointer;i++)
        {
            pointerTable[i] = pointerTable[i+1];
        }
        
       
        return true;
    }
    /* This method is for debugging and testing purposes.  It displays the contents of 
     * data, pointerTable and pointerStack. 
     */
    public void displayTables()
    {
        System.out.println("nextNode = " + nextNode);
        System.out.println("nextPointer = " + nextPointer);
        System.out.println("The data table is:");
        for (int i=0;i<nextNode;i++)
        {
            System.out.println(data[i].toString());
        }
        System.out.println("The pointerTable is:");
        for (int i=0;i<nextPointer;i++)
        {
            System.out.println(pointerTable[i]);
        }
        System.out.println("The pointerStack is:");
        for (int i=0;i<pointerStack.size();i++)
        {
            System.out.println(pointerStack.get(i));
        }
    }
    /*  
     * This method fetches a particular node by pointer address.  Used for debugging.  
     */
    public Student fetch(int i)
    {
        return data[pointerTable[i]].deepCopy();
    }
    public Student fetch(Student targetKey)
    {
        int pointerAddress = this.search(targetKey, false);
        if(pointerAddress < 0)return (Student)null;  // not found
        return this.data[pointerTable[pointerAddress]].deepCopy();
    }
    /**
     * Returns the current number of nodes in the DataStructure. It actually returns 
     * the size of the pointer table.  There could be empty spaces in the data table
     * which we don't want to count.
     */
    public int getSize()
    {
        return nextPointer;   
    }
    /*  The StackEmptyError is part of the interface associated with the Stack class.  It is a dummy. 
     * It will never actually be thrown.  I check for the stack being empty before doing a "pop" operation.
     * This is the only method that performs the "pop" operation.  
     */
    public boolean insert(Student newStudent) 
    {
        int address;
       
        
        if(pointerStack.empty()) {  //  Check if the pointerStack is empty.
            
             if(nextNode >= initialSize) return false;  // fails when structure is full.
             data[nextNode] = newStudent.deepCopy();
            
             address = nextNode;
             nextNode++;
        }
        else {
           
             address = pointerStack.pop();
             data[address] = newStudent.deepCopy();
        }
        int pointerAddress = this.search(newStudent,true);  //  The location of the new pointer.
        pointerTable[pointerAddress] = address;  
        nextPointer++;  //  update the size of the pointer table.
        return true;  
    }
    /*  The following method does the search. This is used by all four main methods to find the 
     * appropriate spot in the data array.  It always returns the appropriate index for the location in the
     * pointer table.  For the delete, fetch and update methods it will return -1 if the target is not found, 
     * otherwise the return value is the address in the pointer table.  For the insert method it returns the 
     * address in the pointer table where the new node will go.  The value of the boolean variable callingMethod
     * is true for insert and false otherwise.  It never fails for insert.  Using the arrayList means the table
     * is never full.  The only way for insert to fail is on a memory full or other system error.
     * 
     * One of the main reasons for doing it this way is to allow for the possibility of using a different
     * search technique.  All that would have to be done in that case is to rewrite this method.
     * 
     * In the final version this doesn't do much.  On insert it simply returns the nextNode value.  For the rest
     * it returns the pointer when found and -1 when not found.
     */
     private int search(Student targetKey, boolean callingMethod)  
    {
        if(callingMethod)return nextPointer;  //  Return the nextPointer on insert.
       
        for(int i=0;i<nextPointer;i++)  //  Otherwise do a sequential search.
        {
            int test = targetKey.compareTo(this.data[pointerTable[i]]);
            
            if(test == 0) return i;
        }
        return -1;  //  only gets here if not found.
    }
    /*  Displays the contents of the DataStructure, displays the data in sorted 
     * order and skips the blanks where elements have been deleted.  It does a "bubble"
     * sort of the data and rearranges the pointer table to accomplish this.  The data
     * table itself is not rearranged.  Note that once sorted, the pointerTable
     * remains partially sorted even after more  operations.
     */
    public void showAll()
    {
            int  max = this.getSize();
            
            this.sort();
            for(int i=0;i<max;i++)
            {
                System.out.println(data[pointerTable[i]].toString());
            }
            return;
    }
    /*  Does a bubble sort of the data by sorting the pointerTable.
     *  Note that every time the showAll method is called it leaves behind a sorted
     *  pointer table.  
     */
    private void sort()
    {
        int max = this.getSize();
        boolean swapped = false;
        int itemsSorted = 0;
        do
        {
            swapped = false;
            for(int i=1;i<=max;i++)
            {
                for(int j = 0;j<max-i;j++)
                {
                    int test = data[pointerTable[i]].compareTo(data[pointerTable[i-1]]);
                    if( test <  0) //  if needed, swap the pointers.
                    {
                        int temp = pointerTable[i-1];
                        pointerTable[i-1] = pointerTable[i];
                        pointerTable[i] = temp;
                        swapped = true;
                    }
                }
                itemsSorted++;
            }
        }
        while (swapped);
        return;
    }
    /*   The easiest way to do update is delete followed by insert.  Note that the delete will push an 
     * address and then insert will pop the same address.
     */
    
    public boolean update(Student targetKey, Student newStudent)
    {
            Scanner keyboard =  new Scanner(System.in);
            boolean test = delete(targetKey);
            if (!test)  //  delete could fail by not found.  
            {
                System.out.println("Delete failed, should updated student be inserted (default is y)? (y/n)");
                String response = keyboard.next();
                if(response.toUpperCase() == "N")return false;  //  update fails in this case.
            }
            insert(newStudent);
            return true;
    }
}


    
    
    
    
    
    
    
        
    
    
    

