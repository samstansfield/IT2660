
/**
 * Write a description of class TicketApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class TicketApp
{
    int choice;
    String choice2;
    private RandomTickets r;
    PerfectHash structure = new PerfectHash();
    
    public TicketApp()
    {
        r = new RandomTickets();  //  Set up to generate random students.
        return;
    }
    public static void main(String[] args)
    {
         TicketApp tester = new TicketApp();  //  Create an instance of the Student App class.  For the randomizer.
         Scanner keyboard = new Scanner(System.in);
         boolean done = false;
         String testTicket;
         
         System.out.println("How many random entries to start?");
         int numEntries =  keyboard.nextInt();
         
         
         for(int i=0;i<numEntries;i++)
         {
             
             Ticket one = tester.r.getTicket(); //  get a random ticket.
             if(!tester.structure.insert(one)) System.out.println("Insert failed");
         }
         
         while(!done)
         {
            System.out.println();
            System.out.println("Please enter one of the following:");
            System.out.println("-----------------------------------------------");
            System.out.println();
            System.out.println(" 1 to insert new ticket information");
            System.out.println();
            System.out.println(" 2 to fetch a ticket by ticket number");
            System.out.println();
            System.out.println(" 3 to fetch a ticket by seat info");
            System.out.println();
            System.out.println(" 4 to delete a ticket");
            System.out.println();
            System.out.println(" 5 to update a ticket");
            System.out.println();
            System.out.println(" 6 to print the list of sold tickets");
            System.out.println();
            System.out.println(" 7 to exit the program");
            System.out.println("-----------------------------------------------");
        
            tester.choice = keyboard.nextInt();
        
            switch (tester.choice)
            {
                case 1:
                Ticket newTicket = new Ticket();
                newTicket.input();
                tester.structure.insert(newTicket);
                break;
                case 2:
                System.out.println("Please input a Ticket Number to search for");
                testTicket = keyboard.next();
                
                Ticket found = tester.structure.fetch(testTicket);
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
                Ticket lookupTicket = new Ticket();
                lookupTicket.input();
                found = tester.structure.fetch(lookupTicket.ticketNumber);
                try  //  fetch returns null when the key is not found.  Will throw a NullPointerException.
                {
                    System.out.println(found.toString());
                }
                catch(NullPointerException e)
                {
                    System.out.println("The key was not found");
                }
                break;
                case 4:
                System.out.println("Please input a Ticket Number to delete");
                testTicket = keyboard.next();
                
                boolean test = tester.structure.delete(testTicket);
                if(!test)System.out.println("Not found");
                break;
                case 5:
                System.out.println("Input a Ticket Number to update");
                tester.choice2 = keyboard.next();
                String oldTicket = tester.choice2;
                newTicket = new Ticket();
                newTicket.input();
                
                tester.structure.update(oldTicket,newTicket);
                break;
                case 6:
                tester.structure.showAll();
                break;
                case 7:
                done = true;
                return;
              
            
            }
         }    
    }
}
