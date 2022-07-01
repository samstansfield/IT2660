
/**
 * This program uses a Java HashMap class to organize a set of Student nodes from before.  
 */
import java.util.*;
public class DataStructure
{
    
    HashMap<String, Student> structure;
    
    /**
     * Constructor for objects of class DataStructure
     */
    public DataStructure()
    {
        structure = new HashMap<String, Student>();
    }
    public DataStructure(int size)
    {
        structure = new HashMap<String, Student>(size);
    }
    public boolean delete(String key)
    {
        
        Student result = structure.remove(key);
        if(result == null)return false;
        else return true;
    }
    public Student fetch(String key)
    {
        Student result = structure.get(key);
        return result;
    }
    public Student fetch(Student thisStudent)  // returns null if not found;
    {
        return fetch(thisStudent.getKey());
    }
    public boolean insert(Student newStudent)
    {
        structure.put(newStudent.getKey(),newStudent);
        return true;
    }
    
    public boolean update(String key, Student newStudent)
    {
        
        structure.put(key,newStudent);
        return true;
        
    }
    
    /*
     * In my reading I came across the use of the colon to represent iteration.  The for loop loops over the 
     * *values* in the HashMap (not the keys).  This became necesary when I realized that the HashMap doesn't 
     * implement "Iterator" so we can't use an iterator.  (I think so, there seems to be contradictory information
     * on this point.  I didn't actually attempt to use one.)
     */
    public void showAll()
    {
        for(Student node : structure.values()) System.out.println(node.toString());
        return;
    }
    
    
    
    
}
