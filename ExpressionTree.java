//import ADT.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class ExpressionTree extends LinkedBinaryTree<ExpressionTreeOp>
{
    public ExpressionTree() 
    {
        super();
    }

    public ExpressionTree(ExpressionTreeOp element,
                    ExpressionTree leftSubtree, ExpressionTree rightSubtree) 
    {
        root = new BinaryTreeNode<ExpressionTreeOp>(element, leftSubtree, rightSubtree);
    }
    
    public int evaluate() 
    {
        return evaluateNode(root);
    }

    private int evaluateNode(BinaryTreeNode<ExpressionTreeOp> root) 
    {
        int result, operand1, operand2;
        ExpressionTreeOp element;
        
        if (root == null)
            result = 0;
        else
        {
            element = root.getElement();
            
            if (element.isOperator())
            {
                operand1 = evaluateNode(root.getLeft());
                operand2 = evaluateNode(root.getRight());
                result = computeTerm(element.getOperator(), operand1, operand2);
            }
            else
                result = element.getValue();
        }
        
        return result;
    }

    private static int computeTerm(char operator, int operand1, int operand2)
    {
        int result=0;
        
        if (operator == '+')
            result = operand1 + operand2;    
        else if (operator == '-')
            result = operand1 - operand2;
        else if (operator == '*')
            result = operand1 * operand2;
        else 
            result = operand1 / operand2;

        return result;
    }
    
    public String printTree() 
    {
        List<BinaryTreeNode<ExpressionTreeOp>> nodes = 
            new ArrayList<BinaryTreeNode<ExpressionTreeOp>>();
        List<Integer> levelList = 
            new ArrayList<Integer>();
        BinaryTreeNode<ExpressionTreeOp> current;
        String result = "";
        int printDepth = this.getHeight();
        int possibleNodes = (int)Math.pow(2, printDepth + 1);
        int countNodes = 0;
        
        nodes.add(root);
        Integer currentLevel = 0;
        Integer previousLevel = -1;
        levelList.add(currentLevel);
        
        while (countNodes < possibleNodes) 
        {
            countNodes = countNodes + 1;
            current = nodes.remove(0);
            currentLevel = levelList.remove(0);
            if (currentLevel > previousLevel)
            {
                result = result + "\n\n";
                previousLevel = currentLevel;
                for (int j = 0; j < ((Math.pow(2, (printDepth - currentLevel))) - 1); j++)
                    result = result + " ";
            }
            else
            {
                for (int i = 0; i < ((Math.pow(2, (printDepth - currentLevel + 1)) - 1)) ; i++) 
                {
                    result = result + " ";
                }
            }
            if (current != null)
            {
                result = result + (current.getElement()).toString();
                nodes.add(current.getLeft());
                levelList.add(currentLevel + 1);
                nodes.add(current.getRight());
                levelList.add(currentLevel + 1);
            }
            else {
                nodes.add(null);
                levelList.add(currentLevel + 1);
                nodes.add(null);
                levelList.add(currentLevel + 1);
                result = result + " ";
            }
        }
        
        return result;
    }
    
    // *** SECOND HALF ***
    
    public String toString() 
    {
    	return printTree();
    	
    }

    public String generateCode() 
    {
    	String code = "";
    	
    	String v = getVar();
        code = generateCodeNode(root, v) + "Result in " + v;
        putVar(v);
        
        return code;
    }

    private String generateCodeNode(BinaryTreeNode<ExpressionTreeOp> root, String v) 
    {
        String code, operand1Code, operand2Code;
        ExpressionTreeOp element;
        
        if (root==null)
            code = "";
        else
        {
            element = root.getElement();
            
            if (element.isOperator())
            {
                operand1Code = generateCodeNode(root.getLeft(), v);

                String vr = getVar();       		
                operand2Code = generateCodeNode(root.getRight(), vr);
                
                code = operand1Code + operand2Code + 
                		getOperation(element.getOperator()) + " " + v + ", " + v + ", " +  vr + "\n";
                putVar(vr);
            }
            else
                code = "li " + v + ", " + element.getValue() + "\n";
        }
        
        return code;
    }
        
    private static String getOperation(char operator)
    {
        String result = "";
        
        if (operator == '+')
            result = "add";    
        else if (operator == '-')
            result = "sub";
        else if (operator == '*')
            result = "mul";
        else 
            result = "div";

        return result;
    }
    
    private static Stack<String> vars;
    static {
        vars = new Stack<String>();
        vars.push("$v1");
        vars.push("$v0");
        vars.push("$a3");
        vars.push("$a2");
        vars.push("$a1");
        vars.push("$a0");
        vars.push("$s7");
        vars.push("$s6");
        vars.push("$s5");
        vars.push("$s4");
        vars.push("$s3");
        vars.push("$s2");
        vars.push("$s1");
        vars.push("$s0");
        vars.push("$t9");
        vars.push("$t8");
        vars.push("$t7");
        vars.push("$t6");
        vars.push("$t5");
        vars.push("$t4");
        vars.push("$t3");
        vars.push("$t2");
        vars.push("$t1");
        vars.push("$t0");
  }
   
   private String getVar() {
    	return vars.pop();
    }
    
    private void putVar(String v) {
    	vars.push(v);
    }
  
}