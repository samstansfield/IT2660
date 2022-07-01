
/**
 * The Student class describes the nodes of the DataStructure.  
 */
import java.util.Scanner;
public class Student
{
    
    private String name;
    private String id;
    private double gpa;

    /**
     * There are three constructors.  The first is the default (blank) 
     * constructor.  
     */
    public Student()
    {
        name = "";
        id = "";
        gpa = 0.0;
        return;
    }
    public Student(String name1, String id1, double gpa1) //  Constructor for general student.
    {
        name = name1;
        id = id1;
        gpa = gpa1;
        return;

    }
    public Student(Student a)    //  Copy constructor
    {
        name = a.name;
        id = a.id;
        gpa = a.gpa;
        return;
    }
    /*  There are 6 other methods:  */

    public String toString()   // Output a string for a Student object.
    {
        return (this.name + this.id + this.gpa);
    }
    public void input()  //  input a new Student from the run-stream
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter sample student data.");
        System.out.println("First the name:");
        this.name = keyboard.next();
        System.out.println("Now the id");
        this.id = keyboard.next();
        System.out.println("Finally, the gpa");
        this.gpa = keyboard.nextDouble();
       
    }
    public void set(String name, String id, double gpa)  //  Set the fields of an existing Student
    {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
        return;
    }
    public String getName()  //  return the value of the "name" field
    {
        return this.name;  
    }
    public String getId()    //  return the valud of the "id" field.
    {
        return this.id;
    }
    public double getGPA()  // return the value of the "gpa" field.
    {
        return this.gpa;
    }
    public boolean equals(Object otherObject) //  Objects are equal if IDs are equal.  
    {
        if(otherObject == null) 
            return false;
        else if(this.getClass() != otherObject.getClass()) 
            return false;
        else
            {   
               
                Student two = (Student)otherObject;
                return (this.id == two.id);
            }
    }
    public Student deepCopy()  //  Generate a deepcopy (clone) of a Student.
    {
        return new Student(this);
    }
    
    
} 