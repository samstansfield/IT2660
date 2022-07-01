
/**
 * Sam Stansfield, Assignment 3B.  Postfix evaluator.
 */
import java.util.*;
public class test
{
    
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        PostfixEvaluation evaluator =  new PostfixEvaluation();
        boolean done = false;  //  never set true but keeps the "while" loop going until program exits.
        while(!done)
        {
             System.out.println("Please enter an algebraic expression in postfix notation, \"DONE\" to finish");
             String postfixExpression = keyboard.nextLine();
            
             if(postfixExpression.toUpperCase().equals("DONE"))
             {
                 System.exit(0);
             }
             Double result = evaluator.postfixEvaluator(postfixExpression);
             if(!(result.isInfinite()))  //  The evaluator returns infinity if it fails.
             {
                 System.out.println(postfixExpression + " = " + result);  //  If it succeeded, output the result.
                 
             }
        }
    }
}
