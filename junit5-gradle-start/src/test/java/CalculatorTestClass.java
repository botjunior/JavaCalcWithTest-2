import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTestClass {
    @Test
    public void Sum_7_and_5_12Returned(){
        Calculator calculator = new Calculator();
        double d1 = 7, d2 = 5;
        assertEquals(d1 + d2, calculator.Check("7+5"));
    }
    @Test
    public void MathematicalExpression_Returned(){
        Calculator calculator = new Calculator();
        double n1 = -4+3*(144);
        assertEquals(n1, calculator.Check("-4+3*(-12^2)"));
    }
    @Test
    public void MathematicalExpression_166Returned(){
        Calculator calculator = new Calculator();
        double n1 = -5.58;
        assertEquals(n1, calculator.Check(" 1 * (-4) - (-1 + 20 * 4)/50"));
    }
    @Test
    public void MathematicalExpression_16Returned(){
        Calculator calculator = new Calculator();
        double n1 = 16;
        assertEquals(n1, calculator.Check(" -55+71"));
    }
    @Test
    public void MathematicalExpression_5Returned(){
        Calculator calculator = new Calculator();
        double n1 = 5;
        assertEquals(n1, calculator.Check(" (5)"));
    }
    @Test
    public void MathematicalExpression_1and2Returned(){
        Calculator calculator = new Calculator();
        double n1 = 1.2;
        assertEquals(n1, calculator.Check(" (1.2)"));
    }
    @Test
    public void BadSyntax1_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("("));
    }
    @Test
    public void BadSyntax2_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("1.2)"));
    }
    @Test
    public void BadSyntax3_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("(1.2"));
    }
    @Test
    public void BadSyntax4_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax(")()))(("));
    }
    @Test
    public void BadSyntax5_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("-1+"));
    }
    @Test
    public void BadSyntax6_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("*1"));
    }
    @Test
    public void BadSyntax7_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("(*)"));
    }
    @Test
    public void BadSyntax8_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("-(*+)"));
    }
    @Test
    public void BadSyntax9_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("-)"));
    }
    @Test
    public void BadSyntax10_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax("()"));
    }
    @Test
    public void BadSyntax11_FalseReturn(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.CorrectSyntax(null));
    }
    @Test
    public void GoodSyntax1_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("(1.2+4*3)"));
    }
    @Test
    public void GoodSyntax2_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("(1.2+4*3)"));
    }
    @Test
    public void GoodSyntax3_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("(((1 * (-4) - (-1 + 20 * 4)/5.5)))"));
    }
    @Test
    public void GoodSyntax4_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("4*2^3"));
    }
    @Test
    public void GoodSyntax5_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("4+2^3"));
    }
    @Test
    public void GoodSyntax6_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("-4+2"));
    }
    @Test
    public void GoodSyntax7_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("-1*(5)"));
    }
    @Test
    public void GoodSyntax9_TrueReturn(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.CorrectSyntax("5"));
    }



}
