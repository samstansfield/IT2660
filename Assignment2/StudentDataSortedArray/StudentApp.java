
/**  This class provides a menu-based driver for the DataStructure class.
 * 
 */
import java.util.*;
public class StudentApp
{
    
    private RandomStudents r;
    public StudentApp()
    {
        r = new RandomStudents();  //  Set up to generate random students.
        return;
    }
    public static void main(String[] args)
    {
         StudentApp tester = new StudentApp();  //  Create an instance of the Student App class.  For the randomizer.
         Scanner keyboard = new Scanner(System.in);
         System.out.println("Input the initial size of the data structure.");
         int initialSize = keyboard.nextInt();
         DataStructure structure = new DataStructure(initialSize);
         System.out.println("How many entries to start?");
         int numEntries =  keyboard.nextInt();
         boolean done = false;
         
         
         for(int i=0;i<numEntries;i++)
         {
             Student one = tester.r.getStudent();  //  get a random student.
             if(!structure.insert(one)) System.out.println("Insert failed");
         }
         structure.showAll();
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
            System.out.println(" 5 to output the sorted list");
            System.out.println();
            System.out.println(" 6 to exit the program");
            System.out.println();
            System.out.println("-----------------------------------------------");
        
            int choice = keyboard.nextInt();
        
            switch (choice)
            {
                case 1:
                Student newStudent = new Student();
                newStudent.input();
                structure.insert(newStudent);
                break;
                case 2:
                System.out.println("Please input a name to search for");
                String choice2 = keyboard.next();
                Student testStudent = new Student();
                testStudent.setName(choice2);
                Student found = structure.fetch(testStudent);
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
                System.out.println("Please input a name to delete");
                choice2 = keyboard.next();
                testStudent = new Student();
                testStudent.setName(choice2);
                boolean test = structure.delete(testStudent);
                if(!test)System.out.println("Not found");
                break;
                case 4:
                System.out.println("Input a student's name to update");
                choice2 = keyboard.next();
                Student oldStudent = new Student();
                oldStudent.setName(choice2);
                testStudent = new Student();
                testStudent.input();
                structure.update(oldStudent,testStudent);
                break;
                case 5:
                structure.showAll();
                break;
                case 6:
                done = true;
                return;
              
            
            }
         }    
    }
}
    

