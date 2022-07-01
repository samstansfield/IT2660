/*
 * This is the DataStructure class for the first assignment.  It sets up an 
 * array of "Student" data.   
 */

public class DataStructure
{
    private int next = 0;  // always points to the next empty space in the data array.
    private Student[] data;  //  the array of data 
    private int size;      //  contains the size of the array.
    
    public DataStructure() //  Default constructor for blank student. 
    {
         data = new Student[100];
         size = 100;
         return;
    }
    public DataStructure(int size)   //  general constructor
    {
         data = new Student[size];
         this.size = size;
         return;
    }
   
    public void add(Student x)  //  method to add a student
    {
        this.data[next] = x;
        next++;
        return;
    }
    public void showAll()  //  method to output the array
    {
        if(this.next <= 0)System.out.println("No data in structure");
        else {
            for(int i=0;i<=this.next-1;i++) {
                System.out.println(this.data[i].toString());
            }      
        }
        return;
    }
    /*  The following method is not in the brief for this problem.  It seemed the easiest way to 
     * enforce encapsulation and read the size of the array.  It's used in the test of the 
     * constructors but would be generally useful.
     */
    public int getSize()
    {
        return size;
    }
}