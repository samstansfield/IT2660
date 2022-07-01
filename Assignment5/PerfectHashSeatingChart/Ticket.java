
/**
 * Write a description of class Ticket here.
 *
 * There are three different ways to identify a ticket.  One is by the ticketNumber, one is by the unique address
 * in the data Structure  and one is via the four quantities that identify the specific ticket:  date, section
 * row and seat.  The program does not store these quantities, they are all determined from the ticketNumber.
 * 
 * 
 */
import java.util.*;
public class Ticket
{
    String ticketNumber;
    String purchaser;
    Scanner keyboard = new Scanner(System.in);
    /**
     * Constructors for objects of class Ticket
     */
    public Ticket()
    {
        
    }
    public Ticket(String ticketNumber, String purchaser)
    {
        this.ticketNumber = ticketNumber;
        this.purchaser = purchaser;
    }
    public Ticket(int week, int section, char row, int seat)
    {
        this.ticketNumber = Ticket.getTicketNumber(week,section,row,seat);
        
    }
    public Ticket(Ticket oldTicket)  // copy constructor
    {
        this.ticketNumber = oldTicket.ticketNumber;
        this.purchaser = oldTicket.purchaser;
    }
    public Ticket(int week, int section, char row, int seat, String purchaser)
    {
         this.ticketNumber = Ticket.getTicketNumber(week,section,row,seat);
         this.purchaser = purchaser;
    }
    
    public Ticket(int code, String purchaser)
    {
        this.ticketNumber = Ticket.deCode(code);
        this.purchaser = purchaser;
    }
    public void setTicketNumber(String ticketNumber)
    {
        this.ticketNumber = ticketNumber;
        return;
    }
    public void setPurchaser(String purchaser)
    {
        this.purchaser = purchaser;
        return;
    }
    public String getTicketNumber()
    {
        return this.ticketNumber;
    }
    
    
    public String getPurchaser()
    {
        return this.purchaser;
    }
    public int getCode()
    {
        return enCode(this);
    }
    public Ticket deepCopy()
    {
        Ticket tempTicket = new Ticket();
        tempTicket.setTicketNumber(this.ticketNumber);
        tempTicket.setPurchaser(this.purchaser);
        return tempTicket;
    }
    void input()  //  insert ticket info from the console.
    {
        System.out.println("Please input the customer name. ");
        String name = keyboard.next();
        System.out.println("Please input the Week Number. ");
        int week = keyboard.nextInt();
        System.out.println("Please input the section number. ");
        int section = keyboard.nextInt();
        System.out.println("Please input the row. ");
        Character row = keyboard.next().toUpperCase().charAt(0);
        System.out.println("Please input the seat number. ");
        int seat = keyboard.nextInt();
        this.setTicketNumber(getTicketNumber(week,section,row,seat));
        this.setPurchaser(name);
        return;
     
    }
    public String toString()
    {
        return (this.ticketNumber + " " + this.purchaser);
    }
    
    public String seatString()  //  Returns a string with purchaser and seat info.
    {
        String outString = this.toString() + " " + seatInfo(this.ticketNumber);
        return outString;
    }
    /*
     * Start with the encoding algorithm.  There are 10000 slots in the data structure.  
     * 10 days, numbered 1-10.  Four sections numbered 1-4.  Each section has rows A-Y
     * (u+0041 to u+0059).  Finally there are ten seats per row numbered 1-10.
     * 
     * The code is a number 0000-9999.  The first digit tells the day.  The next two tell
     * the section and row, the last tells the seat.
     * 
     * The ticket number is of the form:  DN-SC-SN where DN is a two digit week number (0-10), S
     * is a number 1-4 that determines the section, C is a character which determines the row and SN
     * is the seat number 1-10;
     * 
     * There are three different ways to represent a ticket.  First by the actual string 
     * ticketNumber, the second is via the access code.  The third is via the four variables, day,
     * section, row and seat.  It is important to have methods that translate these in all possible ways. 
     * For a total of six possible translation methods.  deCode takes the code and returns the ticket number.
     * enCode has several different signatures.  It always returns the access code.  ticketInfo takes a
     * ticketNumber and returns a string with the four properties.  Note that changing the seat information 
     * changes the ticketNumber and requires a new ticket.
     * 
     */
    public static int enCode(Ticket thisTicket) //  Returns the integer access code.
    {
        String number = thisTicket.ticketNumber;
        return enCode(number);
    }
   
    public static int enCode(String ticketNumber)
    {
        String[] tokens = Ticket.parseTicket(ticketNumber);
        int week = Integer.parseInt(tokens[0]);
        int section = Integer.parseInt(tokens[1]);
        String row = tokens[2];
        int seat = Integer.parseInt(tokens[3]);
        return Ticket.enCode(week,section,row.charAt(0),seat);
    }
    public static int enCode(int week, int section, char row, int seat)
    {
        return 1000*(week-1)+10*(25*(section-1)+row-65)+(seat-1);
    }
    public static String deCode(int code)  //  Takes the integer access code and returns a ticketNumber.
    {
         Integer a,b,c,d;
         d = code%(10);
         Integer seat = d+1;
         int temp = (code-d)/10;
         c = temp%10;
         temp = (temp-c)/10;
         b = temp%10;
         a = (temp-b)/10;
         Integer week = a+1;
         temp = b*10+c;
         Integer rowint = temp%25;
         Integer section = (temp-rowint)/25 +1;
         String outString = week.toString() + "-" + section.toString() 
                           + Character.toString(rowint+65) + "-" + seat.toString();
         
         
         
         return outString;
        
    }
    /*  The following method takes a ticketNumber and separates it into four substrings.  It returns an array
     * of Strings.  It is used by both the seatInfo and encode(String) methods.  It's annoying that it's tricky
     * to convert between Character and String.
     */
    public static String[] parseTicket(String ticketNumber)
    {
        String[] outStrings = new String[4];
        StringTokenizer tokens = new StringTokenizer(ticketNumber,"-");
        if(tokens.countTokens() != 3)
        {
            System.out.println("Error in ticket number " + ticketNumber);
            System.exit(0);
            
        }
        else
        {
            String thisToken = tokens.nextToken();
            outStrings[0] = thisToken;
            thisToken = tokens.nextToken();
            outStrings[1] = Character.toString(thisToken.charAt(0));
            outStrings[2] = Character.toString(thisToken.charAt(1));
            outStrings[3] = tokens.nextToken();
            
        }   
        return outStrings;
    }
    public static String seatInfo(String ticketNumber)  //  Generates a string[] with the seat info.
    {
        String outString = "";
        String[] outStrings = Ticket.parseTicket(ticketNumber);
        outString = outString + "Day " + outStrings[0] + " Section " + outStrings[1] + 
                " Row " + outStrings[2] + " Seat " + outStrings[3];
        return outString;
    }
    public static String getTicketNumber(int week, int section, char row, int seat)
    {
        return (String.valueOf(week) + "-" + String.valueOf(section) + row + "-" + String.valueOf(seat));
    }
}
