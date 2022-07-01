/*      This class is essentially the one in the book with a modification to the insert method to
 * keep the list sorted.  I decided to not mess with swapping the links.  Instead, I am preserving 
 * the chain of links and swapping the data within the nodes when I sort.
 * 
 * Sorting happens on insert so that the list remains sorted.  The new node is placed first in the 
 * list and then the data are moved to the proper spot in the list.  
 * 
 */
public class SinglyLinkedList
{   
    private Node header;  // list header
    public SinglyLinkedList()
    {   header = new Node();  // dummy node
        header.thisStudent = null;
        header.next = null;
    }
    public boolean insert(Student newStudent)
    {   
        Node n = new Node();
        Node q;
        boolean done = false;
        Student tempStudent;
        if(n == null) // out of memory
           return false;
        else
        {  
           n.next = header.next;
           header.next = n;
           n.thisStudent = newStudent.deepCopy();
           Node p = header.next;
           while ((p != null) && !done) //bubble up the new data to the correct spot in the list.  
           {   
               q = p.next;
               //  If test>0 then swap otherwise done. 
               if(q == null) done = true;
               else
               {
                   int test = p.thisStudent.compareTo(q.thisStudent);  
                   if(test>0)
                   {  //  Swap the data using deepCopy
                       tempStudent = p.thisStudent.deepCopy();
                       p.thisStudent = q.thisStudent.deepCopy();
                       q.thisStudent = tempStudent.deepCopy();
                   }
                   else
                   {
                       done = true;
                   }
               }
               p = p.next;
           }
           
           return true;
        }
    }
    public Student fetch(String targetKey)
    {  
        Node p = header.next;
        while (p != null  &&  !(p.thisStudent.compareTo(targetKey) == 0))
        {  
            p = p.next;
        }
        if(p != null) return p.thisStudent.deepCopy();
        else return null;
    }
    public boolean delete(String targetKey)
    {  
        Node q = header;
        Node p = header.next;
        while (p != null && !(p.thisStudent.compareTo(targetKey) == 0))
        {  
            q = p;
            p = p.next;
        }
        if(p != null)
        {   
           q.next = p.next;
           return true;
        }
        else
           return false;
    }
    public boolean update(String targetKey, Student newStudent)
    {  
         if(delete(targetKey) == false)return false;
         else if(insert(newStudent) == false) return false;
         return true;
    }
    public void showAll()
  
    { 
       
        Node p = header.next;
        while (p != null) //continue to traverse the list
        {   
            System.out.println(p.thisStudent.toString( ));
            p = p.next;
        }
    }
   
    public class Node
    {  
        private Student thisStudent;
        private Node next;
        public Node()
        {
        }
     }// end of inner class Node
}//end SinglyLinkedList outer class

