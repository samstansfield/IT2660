
/**
 *  To solve a Sudoku puzzle by backtracking.
 *  There are 81 squares arranged in nine rows, nine columns and nine boxes.  
 *  Each square lies in one of each.  Each digit occurs once in each row, column 
 *  and box.  Some of the squares start out with digits.  The goal is to fill all 
 *  the empty squares subject to the above rules.  The program will stop after it 
 *  finds a solution.  
 *  
 *  General idea:  move through the squares.  Each row, column and box has nine
 *  boolean variables that are true when a digit is present.  False if it isn't.
 *  The "done" condition could be when all 243 boolean variables are true. An easier 
 *  "done" condition is when all the squares are filled subject to the rules.
 *  (There should be exactly one choice when the last square is reached.  There cannot
 *  be more than that but there may be zero, in which case it needs more processing.)
 *  There are actually a grand total 972 booleans when we count the addional nine 
 *  associated with each node that tell us which digits to attempt.  If there is  more than
 *  one soluttion this program will stop after the first one it finds.  If there is no
 *  solution the "getNextSquare" method will return "false" to the main program.
 *  
 *  Most squares will be visited more than once.  Some of the squares near the beginning may be visited 
 *  hundreds of times.
 *  
 *  Backtracking is a way to do this recursively and try all the possibilities.  
 *  A chain of possibilities ends when a square is encountered that has no
 *  possible choice left.  Then it backtracks to find the most recent square with an 
 *  untried choice.  Eventually, the last square will be reached.  
 *  The last square is handled separately.  If we can fill in a digit we are done, otherwise
 *  backtrack to find another path.  The squares are always handled in the same order.
 *  
 *  It will take thousands of attemps overall to reach the end.  Probably not predictable in advance as 
 *  the number would depend on the given nodes and the order that squares are visited in.
 *  
 *  One thing that could be tried to speed it up would be, during preprocessing, to 
 *  look at each empty square and determine a list of what order to fill them in.  I would look
 *  at perhaps the ten with the lowest number of choices to start with.  Do them in order of fewest 
 *  choices to most, then scan the rest of the list.  This could be done with the backtracking algorithm 
 *  modified with a "next" method.  (If there is a square with only one choice it makes sense to do it first.)
 *  
 *  The arrays "row", "column" and "box" are 9x9 boolean arrays.  There is one of each of these as part 
 *  of each node.  The first index labels the row, column or box for that node.  The second index tells us the 
 *  character (digit).  The "node" class is an inner class.  Its constructor sets up these arrays.
 *  
 *  The "node" class is an inner class.  
 */
import java.util.*;
public class Backtracking
{
    private node[] board = new node[81];
    private boolean[][] row = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][] box = new boolean[9][9];
    private Character[] digits = {'1','2','3','4','5','6','7','8','9'};
    private Character[][] gameboard = new Character[9][9];
    Scanner keyboard = new Scanner(System.in);
    private int count = 0;
    /**
     * Constructor for objects of class Backtracking.  Initialize all the nodes.
     */
    public Backtracking()
    {
        for(int i=1;i<=81;i++)
        {
            board[i-1] = new node(i);  //  invokes the node constructor.  This fills in empty nodes in each square.
                                        //  The important thing is getting the boolean variables for each square.
        }                               //  These tell the getNextSquare method which row, column and box each 
                                        // square is in.
        
    }
    //  returns a gameboard array from the main array.
    public Character[][] getBoard()
    {
        Character[][] gameboard =  new Character[9][9];
        for(int i = 0;i<81;i++)
        {
            int x = i%9;
            int y = (i-x)/9;
            gameboard[x][y] = board[i].thisDigit;
        }
        return gameboard;
    }
    public boolean getNextSquare(int i)
    {
        node thisNode = board[i-1];
        boolean[] testNode = new boolean[9];
        /*
         *  This sets up the booleans for that square.  If true, don't try it.
         *  This is a subtle bit of business.  It is really based on the squares that came before this one.
         *  It's not affected by anything that happens in any square encountered after this one until this one 
         *  has been returned to.  If this one fails and we backtrack then the data that lead to this computation
         *  will be different the next time this is performed.
         *  
         *  TestNode is actually an array associated with every node.  It's not
         *  part of the node definition though.  It is created dynamically every time the getNextSquare method 
         *  is invoked.  It is a recursive method so there are many invocations.
         */
       
        count++;
        for(int j =1; j<=9;j++)  
        {
            
            testNode[j-1] = (row[thisNode.rowNum-1][j-1] || column[thisNode.colNum-1][j-1]
                                    || box[thisNode.boxNum-1][j-1]);
            
        }
        
        if(i==81)  //  last square;
        {
            if(thisNode.given)return true;  //  If it was given then return true.  (done)
            for(int j=1;j<=9;j++)  // check the digits.  
            {
                
                if(!testNode[j-1])
                {
                    thisNode.thisDigit = digits[j-1];  //  write in the last digit, if there is one.
                    this.row[thisNode.rowNum-1][j-1] = true;
                    this.column[thisNode.colNum-1][j-1] = true;
                    this.box[thisNode.boxNum-1][j-1] = true;
                    return true;  // report success (done)
                }
            }
            return false;  // nothing to put there.  Backtrack.
        }
        //  If this is a given square  then pass through it when entered or when returning.
        if(thisNode.given)return getNextSquare(i+1);  //  have to check this after check for last square.
        //  Following is the main loop where the recursion occurs.
        for(int j = 1; j<=9;j++)  //  loop on digits for this square
        {
           if(testNode[j-1])continue; // skip digits that are already present.
           else
           {
               thisNode.thisDigit = digits[j-1];   // Set a digit.
               this.row[thisNode.rowNum-1][j-1] = true;
               this.column[thisNode.colNum-1][j-1] = true;
               this.box[thisNode.boxNum-1][j-1] = true;
               boolean nextNode = getNextSquare(i+1);  //  Get the next node.
               if(nextNode)return true;  //  If it succeeeded we're done, pass it on.
               else
               {
                   thisNode.thisDigit = null;  //  Otherwise erase the digit and go to the next choice.
                   this.row[thisNode.rowNum-1][j-1] = false;  
                   this.column[thisNode.colNum-1][j-1] = false;
                   this.box[thisNode.boxNum-1][j-1] = false;
               }
           }
           
        }
        return false;  //  ran out of choices.  Backtrack to previous square.
    }
    /*
     * A board is stored in two ways.  One is as a one-dimensional array of nodes.  This is used 
     * for the backtracking method.  This is the primary data structure.
     * 
     * It is also stored as a 9x9 array of Character.  This is used to ouput the gameboard.  This
     * is first generated by the process of inputting a new game.  It leaves blank (" ") characters
     * in empty squares.  It can also be built from the array of nodes.
     * 
     * The printBoard method fills it in and then displays it.
     * showAll displays the array of nodes.
     */
    public Character[][] inputBoard()
    {
        Character[][] gameboard = new Character[9][9];
        System.out.println("Please input the initial rows, one at a time when prompted.");
        System.out.println("They should be entered as nine-character Strings with hyphens for the blank squares.");
        System.out.println("This program does not validate the data");
        for(int i=1;i<=9;i++)
        {
            System.out.println("Enter the " + i + "th row now.");
            String nextRow = keyboard.next();
            
            for(int j=0;j<9;j++)
            {
                
                gameboard[i-1][j] = nextRow.charAt(j);
            }
            
        }
        
        
        
        return gameboard;
    }
    /*
     * The important thing about this method is the need to not just print out the gameboard array.
     * For legibility reasons it's important to put some spaces in and lines defining the boxes.
     */
    public void printGameboard()
    {
        Character[][] gameboard = new Character[9][9];
        gameboard = this.getBoard();
        String columnSpacer = " I ";
        
        String row = " ----------------------";
        System.out.println();
        System.out.println(row);
        
        for(int i=0;i<3;i++)
        {
            for(int l=0;l<3;l++)
            {
                
                for(int j=0;j<3;j++)
                {
                    System.out.print(columnSpacer);
                    for(int k=0;k<3;k++)
                    {
               
                        if(gameboard[3*j+k][3*i+l] == null)System.out.print(" ");
                        else System.out.print(gameboard[3*j+k][3*i+l]);
                    }
                   
                }
                System.out.print(columnSpacer);
                System.out.println();
            
                
                
            }
            System.out.println(row);
        }
    }
    public void putBoard(Character[][] gameboard)
    {
        //  Take a gameboard and put in the search array
        //  loop on elements
        //  for each non-blank square put a "given" square into the main array.
        
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                int index = 9*i+j;
                
                if(gameboard[i][j] != ("-").charAt(0))
                {
                    
                    this.board[index].thisDigit = gameboard[i][j];
                    this.board[index].given = true;
                    int digitNum = Integer.parseInt(String.valueOf(this.board[index].thisDigit));
                    row[this.board[index].rowNum-1][digitNum-1] = true;
                    column[this.board[index].colNum-1][digitNum-1] = true;
                    box[this.board[index].boxNum-1][digitNum-1] = true;
                }
                
            }
        }
    }
    public void showAll()
    {
        for(int i=0;i<81;i++)
        {
            
            System.out.println(this.board[i].toString());
        }
    }
    
    
    

    class node
    {
        /*
         * For a given node the row, column and box are fixed.  The constructor sets 
         * that up.
         */
        private Character thisDigit = null;
        private Integer rowNum, colNum, boxNum;
        private boolean given = false;
        private node(int count)  // 1<=count<=81
        {
            int temp = count%9;
            if(temp==0)temp = 9;
            this.rowNum = temp;
            this.colNum = (count-temp)/9 +1 ;
            switch(count)
            {
                case 1:
                case 2:
                case 3:
                case 10:
                case 11:
                case 12:
                case 19:
                case 20:
                case 21:
                this.boxNum = 1;
                break;
                //
                case 4:
                case 5:
                case 6:
                case 13:
                case 14:
                case 15:
                case 22:
                case 23:
                case 24:
                this.boxNum = 2;
                break;
                //
                case 7:
                case 8:
                case 9:
                case 16:
                case 17:
                case 18:
                case 25:
                case 26:
                case 27:
                this.boxNum = 3;
                break;
                //
                case 28:
                case 29:
                case 30:
                case 37:
                case 38:
                case 39:
                case 46:
                case 47:
                case 48:
                this.boxNum = 4;
                break;
                //
                case 31:
                case 32:
                case 33:
                case 40:
                case 41:
                case 42:
                case 49:
                case 50:
                case 51:
                this.boxNum = 5;
                break;
                //
                case 34:
                case 35:
                case 36:
                case 43:
                case 44:
                case 45:
                case 52:
                case 53:
                case 54:
                this.boxNum = 6;
                break;
                //
                case 55:
                case 56:
                case 57:
                case 64:
                case 65:
                case 66:
                case 73:
                case 74:
                case 75:
                this.boxNum = 7;
                break;
                //
                case 58:
                case 59:
                case 60:
                case 67:
                case 68:
                case 69:
                case 76:
                case 77:
                case 78:
                this.boxNum = 8;
                break;
                //
                case 61:
                case 62:
                case 63:
                case 70:
                case 71:
                case 72:
                case 79:
                case 80:
                case 81:
                this.boxNum = 9;
                break;
                //
            }
            
        }
        public String toString()
            {
                String result = this.rowNum.toString() + " " +  this.colNum.toString() + " " + this.boxNum.toString() + " ";
                result =result + this.given + " " + this.thisDigit;
                return result;
            }
    }
}
