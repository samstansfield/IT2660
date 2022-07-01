
/**
 * Write a description of class BinaryTree here.
 *
 * This class implements a binary tree as an array.  I decided to include delete and update 
 * methods.  I created an inner class within the BinaryTree that is a kind of wrapper class for Student.
 * I did not want to alter the Student class.  
 * 
 * I can see that this could be a useful structure sometimes but the issues with it make it 
 * mostly useless IMO.
 * 
 * I added a "findNode" so that I didn't have to write separate searches.  
 * "showAll" outputs the tree in alphabetical order.  "printArray" outputs the main array in index order.    
 * 
 * Delete is especially simple since all one has to do to delete it set the "deleted" flag.
 * Insert is a little more complex.  Here's why:  To save on having too many empty nodes in the tree,
 * on insert, if a deleted node is there with the search key then use that slot. 
 * 
 * This class does not address the issue of duplicate keys, it does not forbid them.  It is better to 
 * use the StudentNumber as a key since it is unique.  However, that means producing the sorted list 
 * could be difficult.
 */
public class BinaryTreeArray
{
    StudentNode[] structure;
    int size;
    /**
     * Constructor for objects of class BinaryTree
     */
    public BinaryTreeArray()
    {
        this.size = 100;
        this.structure = new StudentNode[100];
    }
    
    public BinaryTreeArray(int n)
    {
        this.size =n;
        this.structure = new StudentNode[this.size];
    }
    public BinaryTreeArray(int n, int weight)
    {
        this.size =n*weight;
        this.structure = new StudentNode[this.size];
    }
    public boolean delete(String thisKey)
    {
        int test = findNode(thisKey,false);
        if(test < 0) return false;
        else
        {
            structure[test].setDeleted(true);  //  this is all there is to delete a node
            return true;
        }
    }
    public Student fetch(String thisKey)
    {
        int address = findNode(thisKey, false);
        if(address < 0) 
        {
            System.out.println("Not found");
            return null;
        }
        else
        {
            return structure[address].getStudent().deepCopy();
        }
        
    }
    /*
     * The this method generally returns the address of the node.  The boolean variable isInsert is true
     * for insert but false for fetch or delete.  
     * 
     * It moves through the tree following the rules for an array rep. of a tree.  There are several
     * different ways it can stop.  
     * 
     * The conditions are:  1)  The current node is null
     *                      2)  Out of bounds.  Always exits with return value -1
     *                      3)  Key is found.
     *                      
     * If this is insert then for null return the address.  For found keep going and 
     * find a suitable null array element.
     * For fetch and delete return -1 if not found or address if found.
     */
    public int findNode(String thisKey, boolean isInsert)
    {
        
        int index = 0;
        while(structure[index] != null)
        {
            int test = Integer.signum(structure[index].getStudent().compareTo(thisKey));  // test is -1,0, or +1
            switch(test)
            {
                case 1:
                    index = 2*index+1;
                    break;
                case -1:
                    index =  2*index+2;
                    break;
                case 0:
                    if(!isInsert) return index;
                    if(structure[index].getDeleted()) return index;
                    index = 2*index+1;
                    break;
            }
            if(index >= this.size) return -1;
        }
        if(isInsert)return index;  //  gets here if thisStudent is null
        else return -1;
        
    }
    public boolean insert(Student thisStudent)
    {
        int test = findNode(thisStudent.getKey(),true);
       
        if(test < 0)
        {
            System.out.println("Out of bounds");
            return false;
        }
        else
        {
            if(this.structure[test] == null)
            {
                StudentNode newNode = new StudentNode(thisStudent.deepCopy());
                this.structure[test] = newNode;
            }
            else
            {
                this.structure[test].setDeleted(false);
                this.structure[test].setStudent(thisStudent.deepCopy());
                
            }
            
            
            return true;
        }
    }
    public void printArray()
    {
        for(int i = 0;i<size;i++)
        {
            if(structure[i] != null)System.out.println(structure[i].getStudent().toString());
        }
    }
    public void printTree(int i)  //  recursive method to output tree in sorted order.
    {
        if((i>=size)||(structure[i] == null))return;  //  If out-of-bounds or empty node then return.
        
        printTree(2*i+1);  //  print the left tree
        if(!structure[i].getDeleted())System.out.println(structure[i].getStudent().toString());  //  print the current node
        printTree(2*i+2);  //  print the right tree.
        return;
    }
    public void showAll()
    {
        printTree(0);
    }
    public boolean update(String thisKey, Student newStudent)
    {
        boolean test = delete(thisKey);
        if(!test) return false;   //  delete failed  (not found)
        else
        {
            return insert(newStudent);
        }
    }
    
    

    class StudentNode
    {
        Student thisStudent;
        boolean deleted = false;
        StudentNode()
        {
        }
        StudentNode(Student thisStudent)
        {
            this.thisStudent = thisStudent;
            
        }
        Student getStudent()
        {
            return this.thisStudent;
        }
        boolean getDeleted()
        {
            return this.deleted;
        }
        void setStudent(Student thisStudent)
        {
            this.thisStudent = thisStudent;
            return;
        }
        void setDeleted(boolean input)
        {
            this.deleted = input;
            return;
        }
    }
}
