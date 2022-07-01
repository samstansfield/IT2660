
/**  This class provides a menu-based driver for the DataStructure class.
 * 
 */
import java.util.*;
public class StudentApp
{
    int choice;
    String choice2;
    private RandomStudents r;
    BinaryTree structure = new BinaryTree();
    
    public StudentApp()
    {
        r = new RandomStudents();  //  Set up to generate random students.
        return;
    }
    public static void main(String[] args)
    {
         StudentApp tester = new StudentApp();  //  Create an instance of the Student App class.  For the randomizer.
         Scanner keyboard = new Scanner(System.in);
         
         
         System.out.println("How many random entries to start?");
         int numEntries =  keyboard.nextInt();
         boolean done = false;
         
         
         for(int i=0;i<numEntries;i++)
         {
             
             Student one = tester.r.getStudent(); //  get a random student.
             if(!tester.structure.insert(one)) System.out.println("Insert failed");
         }
         
         while(!done)
         {
            System.out.println();
            System.out.println("Please enter one of the following:");
            System.out.println("-----------------------------------------------");
            System.out.println();
            System.out.println(" 1 to insert a new student's information");
            System.out.println();
            System.out.println(" 2 to fetch and output a student's information");
            System.out.println();
            System.out.println(" 3 to delete a student's information");
            System.out.println();
            System.out.println(" 4 to update a student's information");
            System.out.println();
            System.out.println(" 5 to output the list in sorted order");
            System.out.println();
            System.out.println(" 6 to output the list in reverse sorted order");
            System.out.println();
            System.out.println(" 7 to exit the program");
            System.out.println();
            System.out.println("-----------------------------------------------");
        
            tester.choice = keyboard.nextInt();
        
            switch (tester.choice)
            {
                case 1:
                Student newStudent = new Student();
                newStudent.input();
                tester.structure.insert(newStudent);
                break;
                case 2:
                System.out.println("Please input a Student Name to search for");
                String testStudent = keyboard.next();
                
                Student found = tester.structure.fetch(testStudent);
                try  //  fetch returns null when the key is not found.  Will throw a NullPointerException.
                {
                    System.out.println(found.toString());
                }
                catch(NullPointerException e)
                {
                    System.out.println("The key was not found");
                }
                break;
                case 3:
                System.out.println("Please input a Student Name to delete");
                testStudent = keyboard.next();
                
                boolean test = tester.structure.delete(testStudent);
                if(!test)System.out.println("Not found");
                break;
                case 4:
                System.out.println("Input a Student Name of a record to update");
                tester.choice2 = keyboard.next();
                String oldStudent = tester.choice2;
                newStudent = new Student();
                newStudent.input();
                
                if(!tester.structure.update(oldStudent,newStudent)) System.out.println("Update failed");
                break;
                
                case 5:
                tester.structure.showSorted();
                break;
                case 6:
                tester.structure.showAll();
                break;
                case 7:
                done = true;
                return;
              
            
            }
         }    
    }
}
    

