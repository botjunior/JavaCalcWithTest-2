import  java.util.*;

public class MainClass {
    public static void main(String[] args) {
        try {
            System.out.println("Start");
            MainClass mainClass = new MainClass();
            mainClass.calc();
            System.out.println();
            System.out.println("End");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void calc() {
        Calculator calculator = new Calculator();
        System.out.println(calculator.Check("1 * 3^2"));
        //System.out.println(calculator.Check("()"));
        System.out.println(calculator.Check(" 1 * (-4) - (-1 + 20 * 4)/5.5"));
        System.out.println(calculator.Check("((13)^2) - 1 * 3"));
        System.out.println(calculator.Check("(13)^2"));
        System.out.println(calculator.Check("13^2"));
    }

}
/*
Start
4.0
-5.58
166.0
169.0
169.0
 */