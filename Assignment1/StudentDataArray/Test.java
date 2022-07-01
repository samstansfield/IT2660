
/**
 * This is the test class for the Student and DataStructure classes.   
 * There are two constructors and six methods in the Student class as well as 
 * two constructors and two other methods in the DataStructure class.  
 */
import java.util.Scanner;
public class Test
{
    public static void main(String args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter sample student data.");
        System.out.println("First the name:");
        String name = keyboard.next();
        System.out.println("Now the id");
        String id = keyboard.next();
        System.out.println("Finally the gpa");
        double gpa = keyboard.nextDouble();
        Student student1 = new Student();  // student1 is blank
        Student student2 = new Student(name,id,gpa);   //  student2 uses the inputted data.
        System.out.println("Check the toString method for a blank student");
        System.out.println(student1.toString());
        System.out.println("Check the toString method for the test student");
        System.out.println(student2.toString());
        System.out.println("Now create a third blank student and use the input method");
        Student student3 = new Student();
        student3.input();  // use the input method to fill the instance variables for student3
        System.out.println("Student 3 is:");
        System.out.println(student3.toString());
        Student student4 = new Student();
        System.out.println("Now check the set method");
        System.out.println(student4.toString());
        student4.set("Student4","number4",2.5);
        System.out.println(student4.toString());
        System.out.println("Now check the get methods");
        System.out.println(student2.getName());
        System.out.println(student2.getId());
        System.out.println(student2.getGPA());
        
        System.out.println("Using the four students from above create and output the list");
        System.out.println("use the default constructor");
        DataStructure data1 = new DataStructure();
        data1.add(student1);
        data1.add(student2);
        data1.add(student3);
        data1.add(student4);
        data1.showAll();
        System.out.println("The size of the array is:" + data1.getSize());
        System.out.println("use the general constructor");
        DataStructure data2 = new DataStructure(50);
        data2.add(student1);
        data2.add(student2);
        data2.add(student3);
        data2.add(student4);
        data2.showAll();
        System.out.println("The size of the array is:" + data2.getSize());
    }
}
