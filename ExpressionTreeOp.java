public class ExpressionTreeOp 
{
    private int termType;
    private char operator;
    private int value;

    public ExpressionTreeOp(int type, char op, int val) 
    {
        termType = type;
        operator = op;
        value = val;
    }

    public boolean isOperator() 
    {
        return (termType == 1);
    }
    
    public char getOperator() 
    {
        return operator;
    }

    public int getValue() 
    {
        return value;
    }
    
    public String toString()
    {
        if (termType == 1) 
            return operator + "";
        else
            return value + "";
    }
}
    

