
/**
 * The point of this class is to evaluate an aritmetic expression in postfix
 * notation.  The real point is to use a Stack.  The class can handle 
 * expressions like 2 3 4 + * and 2 3 4+* which mean the same thing (14).
 * 
 * Automatically converts all integers to double.  This makes division work.  There are
 * a variety of ways that an error can occur.  If an error does occur the method returns
 * Double.POSITIVE_INFINITY and prints an error message.  Otherwise the computed result
 * is returned.  The main method tests for infinity before outputting the result.
 * 
 * The possible errors are:
 * Division by zero.
 * Too many operators
 * Too few operators (Message is:  Error in postfix expression.)
 * Unrecognized number or operator.  (I can't actually tell the difference between these two.  
 * If someone includes "a" in their input stream, how can I know what the user intended it to be?  
 * Hexadecimal?  A variable?  A function?)  
 */
import java.util.*;
public class PostfixEvaluation
{
    
    private String thisToken;
    private Stack<Double> numberStack = new Stack<Double>();

    public double postfixEvaluator(String postfixExpression)
    {
        
         /*  This StringTokenizer constructor sets the delimiters and returns
         *  them as tokens.
         */
        StringTokenizer tokens = new StringTokenizer(postfixExpression," +-*/",true);
        int count = tokens.countTokens();
        Double x1,x2;
        for(int i=0;i<count;i++) // loop over tokens
        {
            String thisToken = tokens.nextToken();
            
            /*  The main purpose of this switch statement is to separate the operators and the numbers.
             */
            switch(thisToken) 
            {
                case " ":  // ignore blanks
                break;
                case "+":  //  If it's an operator, pop two numbers and evaluate the operation.
                case "-":
                case "*":
                case "/":
                
                    if(numberStack.size() <= 1)  //  Happens if not enough elements to pop and perform operation.
                    {
                        System.out.println("Too many operators");
                        System.out.println("Try again please");
                        return Double.POSITIVE_INFINITY; 
                    }
                    x1 = numberStack.pop();  //  pop off two numbers.
                    x2 = numberStack.pop();
                 
                    double value = computeOperator(x2,x1,thisToken);  //  compute the operation.
                   
                    numberStack.push(value);  // push the result
                    break;
                    default:  // if it's not an operator, push a number on the numberStack.
                   
                    try
                    {
                      numberStack.push(Double.parseDouble(thisToken));  //  will fail if not parsable to Double.
                    }
                    catch(NumberFormatException e)
                    {
                       System.out.println(thisToken + " is not a number or a valid operator");
                       System.out.println("Try again please");
                       return Double.POSITIVE_INFINITY;  // returns infinity on failure.
                    }
                break;
            }
           
        }
        double result = numberStack.pop();
       
        if(!numberStack.empty())  //  Stack isn't empty.
        {
            System.out.println("Error in postfix expression.");
            System.out.println("Try again please");
            return Double.POSITIVE_INFINITY;
        }
        return result;  
    }
    private double computeOperator(double x2, double x1, String operator)
    {
       
        switch(operator)
        {
            case "+":
                return x1+x2;
            case "-":
                return x2-x1;
            case "*":
                return x2*x1;
            case "/":
                if(x1 == 0.0)
                {
                    System.out.println("No division by zero permitted");
                    return Double.POSITIVE_INFINITY;
                }
                else return x2/x1;
            default:
                System.out.println("There is something seriously wrong if this is printed");
                return Double.POSITIVE_INFINITY;
        }
    }
}
