
/**
 * Write a description of class DataStructure here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class DataStructure
{
    /*  I am sure that this needs to be more generic but I suspect that is the next step.  
     * 
     */
    private ArrayList<Student> data;

    /**
     * Constructor for objects of class DataStructure
     */
    public DataStructure()
    {
        data = new ArrayList<Student>();
        return;
    }

    /**
     * Returns the current number of nodes in the DataStructure.  
     */
    public int getSize()
    {
        return data.size();   // size() is a method from the ArrayList class.
    }
    /*  Inserts a Student into the DataStructure.  Always returns true.  It's not clear if it could fail unless there
     * are severe memory issues.
     */
    public boolean insert(Student a)
    {
        data.add(a);  //  add(a) is a method from the ArrayList class.
        return true;
    } 
    /*  Displays the contents of the DataStructure, get(i) is a method of the ArrayList class, it returns the Object 
       contained at index i. */
    public void showAll()
    {
        int x = this.getSize();
        for(int i=0;i<x;i++)
        {
            System.out.println(data.get(i).toString());
        }
        return;
    }
    /*  Not in the brief but looks up the Student at index i.  This seemed the simplest way to respect encapsulation and 
     * have the test program test the new Student methods.  
     */
    public Student lookUp(int i)
    {
        return data.get(i);
    }
    
}
