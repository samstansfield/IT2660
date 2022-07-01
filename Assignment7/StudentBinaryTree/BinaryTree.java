
/**
 * Write a description of class BinaryTreeWithTraversal here.
 *
 */
public class BinaryTree
{
    TreeNode root;
    /**
     * Constructor for objects of class BinaryTree
     */
    public BinaryTree()
    {
        root = null;
    }

    public class TreeNodeWrapper
    {
        TreeNode treeRef = null;
        public TreeNodeWrapper()
        {
        }
        public TreeNode get()
        {
            return treeRef;
        }
        public void set(TreeNode thisNode)
        {
            treeRef = thisNode;
        }
    }
    private class TreeNode
    {
        private Student thisStudent;
        private TreeNode lc;
        private TreeNode rc;
        public TreeNode()
        {
        }
    }
    public boolean delete(String thisKey)
    {
        boolean found;
        TreeNodeWrapper parent = new TreeNodeWrapper();
        TreeNodeWrapper child = new TreeNodeWrapper();
        TreeNode largest;
        TreeNode nextLargest;
        found = findNode(thisKey,parent,child);
        if(!found) return false;  
        else
        {
            if((child.get().lc == null) && (child.get().rc == null))  // case 1, no children
            {
                if(child.get() == root)root = null;
                else
                {
                    if(parent.get().lc == child.get()) parent.get().lc = null;
                    else parent.get().rc = null;
                }
            }
            else if((child.get().lc == null) || (child.get().rc == null))  // case 2, one child.
            {
                if(child.get() == root) // check for root
                {
                    if(parent.get().lc == child.get()) parent.get().lc = null;
                    else parent.get().rc = null;
                }
                
                else if(parent.get().lc == child.get())
                {
                    if(child.get().lc != null) parent.get().lc = child.get().lc;
                    else parent.get().lc = child.get().rc;
                }
                else
                {
                    if(child.get().lc != null) parent.get().rc = child.get().lc;
                    else parent.get().rc = child.get().rc;
                }
            }
            else                                                        //  Case 3, two children
            {
                nextLargest = child.get().lc;
                largest = nextLargest.rc;
                if(largest != null)
                {
                    while(largest.rc != null)
                    {
                        nextLargest = largest;
                        largest = largest.rc;
                    }
                    child.get().thisStudent = largest.thisStudent;
                    nextLargest.rc = largest.lc;
                }
                else
                {
                    nextLargest.rc = child.get().rc;
                    if(parent.get().lc == child.get()) parent.get().lc = nextLargest;
                    else parent.get().rc = nextLargest;
                }
            }
            return true;
        }
    }
    public Student fetch(String thisKey)
    {
        boolean found;
        TreeNodeWrapper parent = new TreeNodeWrapper();
        TreeNodeWrapper child = new TreeNodeWrapper();
        found = findNode(thisKey, parent, child);
        if(found) return child.get().thisStudent.deepCopy();
        else return null;
    }
    private boolean findNode(String targetKey, TreeNodeWrapper parent, TreeNodeWrapper child)
    {
        parent.set(root);
        child.set(root);
        if(root == null) return true;
        while(child.get() != null)
        {
            if(child.get().thisStudent.getKey().toUpperCase().compareTo(targetKey.toUpperCase()) == 0) return true;  
            else
            {
                parent.set(child.get());
                if(targetKey.toUpperCase().compareTo(child.get().thisStudent.getKey().toUpperCase()) < 0)
                    child.set(child.get().lc);
                else
                    child.set(child.get().rc);
            }
        }
        return false;
    }
    public boolean insert(Student newStudent)
    {
        TreeNodeWrapper parent = new TreeNodeWrapper();
        TreeNodeWrapper child = new TreeNodeWrapper();
        TreeNode newNode = new TreeNode();
        if(newNode == null) return false;  //  tree is full
        newNode.thisStudent = newStudent.deepCopy();
        newNode.lc = null;
        newNode.rc = null;
        if(root == null)root = newNode;  // tree is empty
        else
        {
            findNode(newStudent.getKey(),parent,child);
            if(newStudent.getKey().toUpperCase().compareTo(parent.get().thisStudent.getKey().toUpperCase()) < 0) parent.get().lc =newNode;
            else parent.get().rc =newNode;
        }
        return true;
    }
    public boolean update(String targetKey, Student newStudent)
    {
        if(!delete(targetKey))return false;
        else if(!insert(newStudent)) return false;
        return true;
    }
    /*
     * The following method is the only one that wasn't given to us.  It will actually traverse the tree 
     * in either direction.  if direction is true then the result will be a sorted list.  If if is false the 
     * result is a reverse sorted list.  This is done recursively.  It prints the output as it goes.
     */
    public void traversal(TreeNode root, boolean direction)
    {
        if(direction)
        {
            if(root.lc != null) traversal(root.lc,true);
            System.out.println(root.thisStudent.toString());
            if(root.rc != null) traversal(root.rc,true);
        }
        else
        {
            if(root.rc != null) traversal(root.rc,false);
            System.out.println(root.thisStudent.toString());
            if(root.lc != null) traversal(root.lc,false);
        }
    }
    public void showAll()
    {
        this.traversal(this.root,false);
    }
    public void showSorted()
    {
        this.traversal(this.root,true);
    }
}
