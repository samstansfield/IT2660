
/**
 * The TreeSort is the only one I'm not using a static method for.  
 */
public class TreeSortClass
{
    int[] outArray;
    Integer[] array;
    int temp;
    TreeNode root;
    
    /**
     * I'm not building a blank constructor.  This is specifically for sorting an array of n elements.
     */
    public TreeSortClass(int n)
    {
        Integer[] array = new Integer[n];
        outArray = new int[n];
        root =  new TreeNode();
    }

    
    
    
   
    /*
     * 
     */
    public int[] TreeSort(int n, int[] array)
    {
        TreeSortClass program = new TreeSortClass(n);  //  Needs to be instantiated so that the root of the tree is accessible.
        BuildTree(0,program.root,array);
        LNRTraversal(0,program.root,program.outArray);
        return program.outArray;
    }
    void BuildTree(int size, TreeNode root, int[] array)  //  i is the index in the input array
    {
        TreeNode newNode;
        for(int i = 0;i<size;i++)
        {
            newNode = treeFindNode(array[i],root);
            newNode.setKey(array[i]);
        }
        
        
    }
    void LNRTraversal(int i, TreeNode root, int[] outArray) //  i is the index in the output array
    {
        if(root == null) return;
        LNRTraversal(i,root.lc,outArray);
        outArray[i] = root.key;
        i++;
        LNRTraversal(i,root.rc,outArray);
        return;
    }
    /*
     * This method finds the location for the new node.  It returns a blank TreeNode at the correct place in the tree.  
     * It is recursive.
     */
    private TreeNode treeFindNode(int targetKey, TreeNode root)
    {
        if(root == null)
        {
            TreeNode newNode = new TreeNode();
            return newNode;
        }
        else if(root.compareTo(targetKey) < 0)
        {
            return treeFindNode(targetKey,root.rc);
        }
        else
        {
            return treeFindNode(targetKey,root.lc);
        }
    }
    
    public class TreeNode
    {
        private int key;
        private TreeNode lc;
        private TreeNode rc;
        public TreeNode()
        {
        }
        public void setKey(int newKey)
        {
            this.key = newKey;
            return;
        }
        public int compareTo(int thisKey)
        {
            /*
             * This is not a standard compareTo method.  It does not test for equality.  
             * Returns -1 for < and +1 for >=.  It is ONLY used for building the tree
             * where duplicates just keep moving down.
             */
            boolean test = (this.key<thisKey);
            int returnValue;
            if(test)
            {
                return -1;
            }
            else
            {
                return 1;
            }
            
            
        }
    }
    
}


