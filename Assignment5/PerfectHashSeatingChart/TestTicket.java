
/**
 * This tests the methods of the Ticket class.  It does not, in general, test all of the constructors.  It does the 
 * non-static methods first and then the static methods.
 */
import java.util.*;
public class TestTicket
{
    
    
    private RandomTickets r;
   
    
    public TestTicket()
    {
        r = new RandomTickets();  //  Set up to generate random tickets
        return;
    }
    public static void main(String[] args)
    {
         TestTicket tester = new TestTicket();  //  Create an instance of the TestTicket class.  For the randomizer.
         Scanner keyboard = new Scanner(System.in);
         
         System.out.println("Create some random Tickets.  Print them out.");
         for(int i=0;i<5;i++)
         {
             Ticket one = tester.r.getTicket();
             
             System.out.println("toString " + one.toString());
             System.out.println("seatString " + one.seatString());
             one.seatString();
         }
         System.out.println("Now test the input method");
         Ticket thisTicket = new Ticket();
         thisTicket.input();
         System.out.println("Here's a few of the non-static methods");
         System.out.println("ToString " + thisTicket.toString());
         System.out.println("getTicketNumber " + thisTicket.getTicketNumber());
         System.out.println("getPurchaser " + thisTicket.getPurchaser());
         System.out.println("getCode " + thisTicket.getCode());
         System.out.println("That is all the non-static methods");
         System.out.println("Now switch to the static methods");
         for(int i=0;i<5;i++)
         {
             System.out.println("Please enter a four digit number");
             int code = keyboard.nextInt();
             System.out.println(code + " " + Ticket.deCode(code) + " " + Ticket.enCode(Ticket.deCode(code)));
             
         }
         System.out.println("ParseTicket ");
         for(int i = 0; i<4;i++)
         {
             System.out.println(Ticket.parseTicket(thisTicket.ticketNumber)[i]);
             
         }
         System.out.println("seatInfo " + Ticket.seatInfo(thisTicket.ticketNumber));
         System.out.println("encode(ticketNumber) " + Ticket.enCode(thisTicket.ticketNumber));
        
            
    }
}
