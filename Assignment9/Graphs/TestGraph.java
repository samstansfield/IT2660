
/**
 * Write a description of class TestGraph here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TestGraph
{
    
    
    public static void main(String[] args)
    {   
        SimpleGraph flyUS = new SimpleGraph(10);
        Listing v0 = new Listing("Philadelphia");
        Listing v1 = new Listing("New York");
        Listing v2 = new Listing("Boston");
        Listing v3 = new Listing("Los Angeles");
        Listing v4 = new Listing("Houston");
        // add the hub cities to the graph as vertices
        flyUS.insertVertex(0, v0);
        flyUS.insertVertex(1, v1);
        flyUS.insertVertex(2, v2);
        flyUS.insertVertex(3, v3);
        flyUS.insertVertex(4, v4);
        // add the routes to the graph as edges
        flyUS.insertEdge(0,1);
        flyUS.insertEdge(0,3);
        flyUS.insertEdge(1,2);
        flyUS.insertEdge(1,3);
        flyUS.insertEdge(2,1);
        flyUS.insertEdge(3,4);
        flyUS.insertEdge(4,0);
        flyUS.insertEdge(4,3);
        // output the hubs and the routes stored in the graph
        for(int i=0; i<5; i++)
        {   
            System.out.print("hub " + i + "\'s ");
            flyUS.showVertex(i);
            System.out.println("its routes are: ");
            flyUS.showEdges(i);
        }//end the output loop
        System.out.println("The Adjacency matrix");
        flyUS.showAdjacency();
        System.out.println("The Transitive Closure Matrix");
        flyUS.showTransitiveClosure();
        System.out.println("Insert a node for Cleveland");
        Listing v5 = new Listing("Cleveland");
        flyUS.insertVertex(5,v5);
        System.out.println("Add new routes using edge numbers, Cleveland->Boston(52) and Houston->Cleveland(45)");
        flyUS.insertEdge(52);
        flyUS.insertEdge(45);
        System.out.println("The Adjacency matrix");
        flyUS.showAdjacency();
        Listing newListing = flyUS.fetchVertex(5);
        System.out.println(newListing.toString());
        System.out.println(flyUS.fetchEdge(1,3));
        System.out.println(flyUS.fetchEdge(13));
        System.out.println(flyUS.fetchEdge(2,5));
        System.out.println(flyUS.fetchEdge(25));
        System.out.println("Delete vertex 3");
        flyUS.deleteVertex(3);
        System.out.println("The Adjacency matrix");
        flyUS.showAdjacency();
        System.out.println("Delete edges 21 and 40 by different methods");
        flyUS.deleteEdge(21);
        flyUS.deleteEdge(4,0);
        System.out.println("The Adjacency matrix");
        flyUS.showAdjacency();
        System.out.println("Update Boston to Hartford");
        newListing = new Listing("Hartford");
        flyUS.updateVertex(2,newListing);
        System.out.println("The Transitive Closure Matrix");
        flyUS.showTransitiveClosure();
        
    } //end the method main
}
