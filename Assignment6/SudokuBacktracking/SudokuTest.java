
/**
 * Driver for the backtracking project.  For test runs I got a few puzzles off of
 * the internet.
 * 
 * This program has to set up test runs and allow for evaluation.  Neither of these tasks is obvious.
 * 
 * The first task requires setting up a way to input a gameboard.
 * The second requires that the gameboard be displayed.  Both of these should be done using methods for
 * the Backtracking class.
 */
public class SudokuTest
{
    Character[][] gameboard = new Character[9][9];
   
    Character testBoard1[][] = {{'5','6','-','-','1','-','7','-','2'},{'-','2','-','3','-','-','1','5','4'},
        {'-','-','3','7','-','-','-','-','6'},{'-','-','-','-','7','5','9','4','-'},{'-','-','-','2','-','6','-','-','-'},
        {'-','5','7','4','9','-','-','-','-'},{'1','-','-','-','-','8','2','-','-'},{'6','8','5','-','-','7','-','1','-'},
        {'3','-','2','-','4','-','-','8','9'}};
    Character testBoard2[][] = {{'5','6','8','-','1','-','7','-','2'},{'-','2','-','3','-','-','1','5','4'},
        {'-','-','3','7','-','-','-','-','6'},{'-','-','-','-','7','5','9','4','-'},{'-','-','-','2','-','6','-','-','-'},
        {'-','5','7','4','9','-','-','-','-'},{'1','-','-','-','-','8','2','-','-'},{'6','8','5','-','-','7','-','1','-'},
        {'3','-','2','-','4','-','-','8','9'}};  //  This one has no solution.
    /**
     * Constructor for objects of class SudokuTest
     */
    public SudokuTest()
    {
        
    }

    public static void main(String[] args)
    {
        SudokuTest program = new SudokuTest();
        Backtracking thisList = new Backtracking();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                program.gameboard[i][j] = program.testBoard1[i][j];
            }
        }
        
        thisList.putBoard(program.gameboard);
        
        thisList.printGameboard();
       
        boolean done = thisList.getNextSquare(1);
        if(done)thisList.printGameboard();
        else System.out.println("No solution found");
        System.out.println();
        thisList = new Backtracking();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                program.gameboard[i][j] = program.testBoard2[i][j];
            }
        }
        
        thisList.putBoard(program.gameboard);
        
        thisList.printGameboard();
       
        done = thisList.getNextSquare(1);
        if(done)thisList.printGameboard();
        else System.out.println("No solution found");
        System.out.println();
        
        /*
         * At this point I want to do a set of tests of the "Backtracking" class.   In the above all 
         * of the methods of that class except for inputBoard and showAll are used.
         */
        thisList = new Backtracking();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                program.gameboard[i][j] = ("-").charAt(0);
            }
            
        }
        program.gameboard = thisList.inputBoard();
        thisList.putBoard(program.gameboard);
        System.out.println();
        thisList.printGameboard();
        System.out.println();
        thisList.getNextSquare(1);
        thisList.printGameboard();
        System.out.println();
        System.out.println("Here is result of the showAll command:");
        thisList.showAll();
        
        
        
    }
}
