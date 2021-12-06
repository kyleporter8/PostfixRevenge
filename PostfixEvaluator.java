import java.util.*;
import java.io.*;

public class PostfixEvaluator    
{
    private String expression;
    private Stack<ExpressionTree> treeStack;
    
    public PostfixEvaluator()
    {
        treeStack = new Stack<ExpressionTree>();
    }

    private ExpressionTree getOperand(Stack<ExpressionTree> treeStack)
    {
        ExpressionTree temp;
        temp = treeStack.pop();
        
        return temp;
    }
    
    public ExpressionTree parse(String expression)
    {
        ExpressionTree operand1, operand2;
        char operator;
        String tempToken;

        Scanner parser = new Scanner(expression);
        
        while (parser.hasNext()) 
        {
            tempToken = parser.next();
            operator = tempToken.charAt(0);
            
            if ((operator == '+') || (operator == '-') || (operator == '*') || 
                 (operator == '/'))
            {
                operand1 = getOperand(treeStack);
                operand2 = getOperand(treeStack);
                treeStack.push(new ExpressionTree 
                                  (new ExpressionTreeOp(1,operator,0), operand2, operand1));
            }
            else
            {
                treeStack.push(new ExpressionTree(new ExpressionTreeOp
                                    (2,' ',Integer.parseInt(tempToken)), null, null));
            }
            
        }
        return treeStack.peek();        
    }
    
    public String getTree()
    {
        return (treeStack.peek()).printTree();
    }
}
