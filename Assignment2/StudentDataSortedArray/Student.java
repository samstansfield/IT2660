
/**
 * The Student class describes the nodes of the DataStructure.  
 */
import java.util.Scanner;
public class Student implements Comparable<Student>
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
    /*  The compareTo method is required by the Comparable interface.  It returns names in alphabetical order
     *  it does not test for duplicates.  Probably better to sort by student id.  Or a two part search, first
     *  alphabetical then by student id.
     */
    
    public int compareTo(Student target)  
    {
        int test = this.name.toUpperCase().compareTo(target.name.toUpperCase());
        return test;
    }
    public Student deepCopy()  //  Generate a deepcopy (clone) of a Student.
    {
        return new Student(this);
    }
    
     public boolean equals(Object a)  //  Objects are equal if IDs are equal.  Throws an error if a is not a Student.
    {    Student two = (Student)a;
        return (this.id == two.id);
    }
    public double getGPA()  // return the value of the "gpa" field.
    {
        return this.gpa;
    }
    public String getId()    //  return the valud of the "id" field.
    {
        return this.id;
    }
    public String getName()  //  return the value of the "name" field
    {
        return this.name;  
    }
    
    public Student input()  //  input a new Student from the run-stream
    {
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter student data.");
        System.out.println("First the name:");
        this.name = keyboard.next();
        System.out.println("Now the id");
        this.id = keyboard.next();
        System.out.println("Finally, the gpa");
        this.gpa = keyboard.nextDouble();
        return this;
    }
    public void set(String name, String id, double gpa)  //  Set the fields of an existing Student
    {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
        return;
    }
     public void setName(String name)  // used for searching.  Useful if only the name is known.
    {
        this.name = name;
        return;
    }
    public String toString()   // Output a string for a Student object.
    {
        return (this.name + " " + this.id + " " + this.gpa);
    }
    
    
} 