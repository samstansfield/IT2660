
/**
 * By Sam Stansfield.  This class builds a perfect hash structure for the Green Community Theatre tickets.
 * 
 */
import java.util.*;
public class PerfectHash
{
    Ticket[] ticketList =new Ticket[10000]; 
    Scanner keyboard = new Scanner(System.in);
    /**
     * Constructor for objects of class PerfectHash
     */
    public PerfectHash()
    {
        
    }
    boolean insert(Ticket thisTicket)
    {
        int pk = Ticket.enCode(thisTicket);
        if(thisTicket.purchaser == null) return false;  //  insert will fail if no purchaser in Ticket.
        else
        {
        
        ticketList[pk] = thisTicket.deepCopy();
        
        return true;
        }
    }
    
    Ticket fetch(String ticketNumber)
    {
        int pk = Ticket.enCode(ticketNumber);
        return this.fetch(pk);
    }
    
    Ticket fetch(int code)
    {
        if(ticketList[code] == null)
        {
            return null;
        }
        else
        {
            return this.ticketList[code].deepCopy();
        }
    }
    boolean delete(Ticket thisTicket)
    {
        return delete(thisTicket.ticketNumber);
    }
    
    boolean delete(String thisTicket)
    {
        int pk = Ticket.enCode(thisTicket);
        if(this.ticketList[pk] == null)
        {
            return false;
        }
        else
        {
            this.ticketList[pk] = null;
            return true;
        }
    }
    /*
     * The following two methods look very similar.  The difference is in the signatures.  There are also two delete
     * methods with different signatures and they are used in the two different update methods.
     */
    boolean update(Ticket oldTicket, Ticket newInfo)  
    {
        this.delete(oldTicket);
        return this.insert(newInfo);
    }
    boolean update(String oldTicket, Ticket newInfo)
    {
        this.delete(oldTicket);
        return this.insert(newInfo);
    }
    
    /*
     * The following method outputs the list to the standard output.  
     * 
     */
    public void showAll()
    {
        for(int i = 0;i<10000;i++)
        {
            if(ticketList[i] != null)System.out.println(ticketList[i].toString());
        }
        return;
    }
    
}
