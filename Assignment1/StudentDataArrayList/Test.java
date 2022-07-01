
 
public class Test
{
    /*  Test class for round 2 of the DataStructure assignment.  This accesses the class "RandomStudents" to 
     * generate random instances for testing the new version of the DataStructure class.  The main goals of 
     * the test are in the new version of the DataStructure uses the ArrayList class instead an Array.  There 
     * are several methods which are part of the new class.  These will be tested first, starting with the 
     * constructor.  Then the two new methods for the Student class will be tested.
     */
    private int x;
    private RandomStudents r;
    public Test()
    {
        r = new RandomStudents();  //  Get a random student.
        return;
    }
    
    public static void main(String args)
    {
       
        Test tester = new Test();  //  Create an instance of the Test class.
        DataStructure data = new DataStructure();   //  Construct the DataStructure.
        for(int i=0;i<5;i++)   // add five random students to the DataStructure.
        {
            Student one = tester.r.getStudent();
            data.insert(one);
        }
        System.out.println("The number of entries is " + data.getSize());
        System.out.println("The list of entries is:");
        data.showAll();
        System.out.println("Now test the new methods of the Student class");
        System.out.println("First is the deep copy, take the student in position 0 in the array and output the copy");
        System.out.println("Original is:" + data.lookUp(0).toString());
        System.out.println("Copy is:" + data.lookUp(0).deepCopy().toString());
        System.out.println("Test the equals.  First test an object against itself");
        System.out.println(data.lookUp(0).equals(data.lookUp(0)));
        System.out.println("Now for different entries");
        System.out.println(data.lookUp(0).equals(data.lookUp(1)));
        System.out.println("Now test it against a non-Student object");
        System.out.println(data.lookUp(0).equals(5));
        
    
    }
}
