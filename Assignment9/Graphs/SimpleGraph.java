/*
 * This class performs simple operations on a graph.  There is an interesting question
 * here about how to define an edge.  Certainly it has a starting and ending point as well as, 
 * in the example, a milage associated with it (although, we are ignoring that).  The fetchEdge 
 * method is supposed to return an int. 
 * 
 * One option is to redefine an edge as containing a Listing, this could contain the weight of that 
 * edge.  In an unweighted graph the contents of the Listing would be null.  A second option 
 * (which I am going with) is to have the fetchEdge method return a boolean.  True if it exists.  
 * Given a finite set of points all of the possible edges are well-defined.  The only question 
 * is an existence one.  I'm going with the latter since the graph is unweighted.
 * 
 * My main goal here is to be left with a class that I can use to analyze graphs.  I decided to 
 * number the edges.  If there are n points the total number of possible (directed) edges is 
 * n*(n-1).  Similar to a perfect hash situation I'm setting up a simple algorithm which will 
 * take an edge number and return the points or take a pair of points and return the edge number.  
 * In addition, I'm setting up a boolean array with n^2 elements which contains true if the 
 * corresponding edge exists and false if it does not.  The main diagonal is always false.  
 * The rows label the starting points and the columns label the ending points.  
 * The general rule is:  lineNumber = n*startPoint + endPoint.  There is a simple test for diagonal:  
 * On the main diagonal the edge number will always be divisible by n+1.  This matrix is the boolean 
 * form of the adjacency matrix.  The whole point of this is to make looking up an edge doable without 
 * an O(n^2) search.
 * 
 */
import java.util.*;  
public class SimpleGraph // a directed graph
{   
    Listing vertex[];  //the reference to the vertex array
    //  Listing edge[];  // This would be the edge array of Listings.
    int adjacency[][];  // reference to the adjacency matrix array
    int transitiveClosure[][];
    boolean edgeExists[];
    boolean vertexExists[];
    int max, numberOfVertices, numberOfEdges;
    public SimpleGraph(int n)
    {  
        vertex = new Listing[n]; // allocation of the vertex array
        //  edge = new Listing[n*n];  //  The edge array
        adjacency = new int[n][n];
        transitiveClosure = new int[n][n];
        edgeExists = new boolean[n*n];
        vertexExists = new boolean[n];
        max = n;   
        numberOfVertices = 0;
        numberOfEdges = 0;
    }
    public boolean deleteEdge(int edgeNum)
    {
        if(!edgeExists[edgeNum]) return false;
        else
        {
            edgeExists[edgeNum] = false;
            return true;
        }
    }
    public boolean deleteEdge(int start, int end)
    {
        return deleteEdge(max*start+end);
    }
    public boolean deleteVertex(int thisVertex)
    {
        if(!vertexExists[thisVertex]) return false;  //  Vertex not present.
        vertexExists[thisVertex] = false;
        for(int i=0;i<max;i++)  //  delete all the edges that end on thisVertex.
        {
            edgeExists[max*i +thisVertex] = false;
            edgeExists[max*thisVertex+i] = false;
        }
        vertex[thisVertex] = null;
        return true;
    }
    public boolean fetchEdge(int edgeNumber)
    {
        return edgeExists[edgeNumber];
    }
    public boolean fetchEdge(int start, int end)
    {
        return edgeExists[max*start+end];
    }
    public Listing fetchVertex(int thisVertex)
    {
        return vertex[thisVertex];
        
    }
    public boolean insertEdge(int edgeNumber)
    {
        int toVertex = edgeNumber%max;
        int fromVertex = (edgeNumber-toVertex)/max;
        return insertEdge(fromVertex,toVertex);
    }
    public boolean insertEdge(int fromVertex, int toVertex)
    { 
        if(vertex[fromVertex] == null || vertex[toVertex] == null) // non-existent vertex
           return false;
        numberOfEdges++;
        adjacency[fromVertex][toVertex] = 1;
        edgeExists[max*fromVertex+toVertex] = true;
        return true;
    }
    public boolean insertVertex(int vertexNumber, Listing newListing)
    {   
        if (vertexNumber >= max)  //the graph is full
           return false;
        if(vertexExists[vertexNumber]) return false;  //  Happens if vertex already exists.
        vertex[vertexNumber] = newListing.deepCopy(); 
        vertexExists[vertexNumber] = true;
        numberOfVertices++;
        return true;
    }
    public void showAdjacency()
    {
        for(int i=0;i<max;i++)
        {
            for(int j=0;j<max;j++)
            {
                int index = i*max+j;
                if(edgeExists[index])adjacency[i][j] = 1;
                else adjacency[i][j] = 0;
            }
            System.out.println(Arrays.toString(adjacency[i]));
        }
        
    }
    public void showTransitiveClosure()
    {
        for(int i=0;i<max;i++)
        {
            for(int j=0;j<max;j++)
            {
                int index = i*max+j;
                if(edgeExists[index])transitiveClosure[i][j] = 1;
                else transitiveClosure[i][j] = 0;
            }
            
        }
        for(int k=0;k<max;k++)
        {
            for(int j=0;j<max;j++)
            {
                if(transitiveClosure[k][j] == 1)
                {
                    for(int i=0;i<max;i++)
                    {
                        if((transitiveClosure[i][k] == 1) && (i!=j)) transitiveClosure[i][j] = 1;
                    }
                }
            }
            
        }
        for(int i =0; i<max;i++) System.out.println(Arrays.toString(transitiveClosure[i]));
        
    }
    public void showEdges(int vertexNumber) //edges emanating from vertexNumber
    {   
       for(int column=0; column<numberOfVertices; column++)
       {   
           if(edgeExists[vertexNumber*max+column]) // there is an edge
              System.out.println(vertexNumber + "," + column);
       }
    }
    public void showVertex(int vertexNumber)
    {  
        System.out.println(vertex[vertexNumber]);
    }
    public boolean updateEdge(int oldStart, int oldEnd, int newStart, int newEnd)
    {
        if(deleteEdge(oldStart,oldEnd)) return insertEdge(newStart,newEnd);
        else return false;
    }
    public boolean updateVertex(int thisVertex, Listing newListing)
    {
        System.out.println(newListing.toString());
        if(vertexExists[thisVertex])
        {
            vertex[thisVertex] = newListing;
            return true;
        }
        else return false;
    }
    
}// end of SimpleGraph class

