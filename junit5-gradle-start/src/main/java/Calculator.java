import java.util.ArrayList;
import java.util.List;

public class Calculator{
    public AlgoritmCalculate algoritmCalculate;
    public Calculator(){
        algoritmCalculate = new PolskaAlgoritm();
    }
    public Calculator(String s){
        algoritmCalculate = new PolskaAlgoritm();
        this.Check(s);
    }

    public Double Check(String s){
        if(s != null && algoritmCalculate != null && CorrectSyntax(s))
            return algoritmCalculate.StringCalculateReader(s);
        else{
            if(!CorrectSyntax(s)){
                throw new NullPointerException("Invalid syntax");
            }
            if(algoritmCalculate == null){
                throw new NullPointerException("AlgoritmCalculate is null");
            }
            if(s == null){
                throw new NullPointerException("Input string is null");
            }
            return null;
        }
    }
    private  boolean Operands(char s){
        return s == '+' || s == '-' || s == '*' || s == '/' || s == '^';
    }
    private boolean StringZnakCheck(char s){
        return s == '('||s==')'||s == '+' || s == '-' || s == '*' || s == '/' || s =='^'||s=='.' || s == ' ';
    }

    public   boolean CorrectSyntax(String str){
        if(str == null){
            return false;
        }
        str.replaceAll("\\s+","");
        StringBuilder stringBuilder = new StringBuilder(str);
        int openParenthesis = 0, closeParenthesis = 0;
        if (str.length() == 0){
            return  false;
        }
        if(Operands(str.charAt(0))  && str.charAt(0) != '+' && str.charAt(0) != '-'){
            return false;
        }
        for(int i = 0; i < stringBuilder.length();i++){
            char symbol = stringBuilder.charAt(i);
            if (!(symbol >='0' && symbol <= '9'  || StringZnakCheck(symbol))){
                return false;
            }
            if(closeParenthesis > openParenthesis){
                return  false;
            }
            if(i+1 == stringBuilder.length() && Operands(symbol)){
                return  false;
            }
            if(Operands(symbol) && (Operands(stringBuilder.charAt(i+1)) || stringBuilder.charAt(i+1) == ')')){
                return  false;
            }
            if(symbol == '('){
                openParenthesis+=1;
            }
            else if(symbol ==')'){
                closeParenthesis+=1;
            }
        }
        return  !(openParenthesis != closeParenthesis || openParenthesis+closeParenthesis == stringBuilder.length());
    }
}

interface AlgoritmCalculate{
    double StringCalculateReader(String mathString);
}

class PolskaAlgoritm implements AlgoritmCalculate {
    private List<String> inputString;
    private List<String> stack;
    private List<String> outputString;
    private double result = 0;
    public double getResult(){
        return result;
    }
    public PolskaAlgoritm(){
        stack = new ArrayList<>();
        outputString = new ArrayList<>();
        inputString = new ArrayList<>();
    }
    public PolskaAlgoritm(String s){
        stack = new ArrayList<>();
        StringCalculateReader(s);
    }
    private boolean StringZnakCheck(char s){
        return s == '('||s==')'||s == '+' || s == '-' || s == '*' || s == '/' || s =='^';
    }
    private void clearAtr(){
        stack.clear();
        outputString.clear();
        inputString.clear();
    }
    @Override
    public double StringCalculateReader(String mathString) {
        if(stack.size() > 0 || outputString.size() > 0 || inputString.size() > 0){
            clearAtr();
        }
        for(int i = 0; i < mathString.length();i++){
            if(StringZnakCheck(mathString.charAt(i))){
                inputString.add(String.valueOf(mathString.charAt(i)));
            }
            if(mathString.charAt(i) >= '0' && mathString.charAt(i) <= '9'){
                StringBuilder copynum = new StringBuilder();
                while(i < mathString.length() && !StringZnakCheck(mathString.charAt(i))){
                    copynum.append(mathString.charAt(i));
                    i++;
                }
                i--;
                inputString.add(copynum.toString());
            }

        }
        PolskaCompele();
        PolskaDeCompele();
        return result;
    }
    private void PolskaDeCompele(){
        for(int i = 0; i < outputString.size();i++){
            StringBuilder str = new StringBuilder(outputString.get(i));
            //outputReader();
            if(StringZnakCheck(str.charAt(0)) && str.length() == 1){
                // System.out.println("Start de compele " + i + " " + str.charAt(0));
                double num1, num2;
                num1 = Double.parseDouble(outputString.get(i-1));
                num2 = Double.parseDouble(outputString.get(i-2));
                outputString.remove(i--);
                outputString.remove(i--);
                switch (str.charAt(0)){
                    case '*':
                        outputString.set(i,String.valueOf(num2 * num1));
                        break;
                    case '^':
                        outputString.set(i,String.valueOf(Math.pow(num2,num1)));
                        break;
                    case '+':
                        outputString.set(i,String.valueOf(num2 + num1));
                        break;
                    case '-':
                        outputString.set(i,String.valueOf(num2 - num1));
                        break;
                    case '/':
                        outputString.set(i,String.valueOf(num2 / num1));
                        break;
                }
            }
        }
        result = Double.parseDouble(outputString.get(0));
    }
    private boolean FindClosingPerenthes(String str){
        if(str.charAt(0) == ')'){
            while(stack.get(stack.size()-1).charAt(0)!='('){
                //System.out.println("Remove from stack " + stack.get(stack.size()-1));
                outputString.add(stack.get(stack.size()-1));
                stack.remove(stack.size()-1);
            }
            //System.out.println("Remove from stack " + stack.get(stack.size()-1));
            stack.remove(stack.size()-1);
            return true;
        }
        return false;
    }
    private  boolean Operands(char s){
        return s == '+' || s == '-' || s == '*' || s == '/' || s == '^';
    }
    public void PolskaCompele(){
        boolean negativeNumber = false;
        for(int i = 0; i < inputString.size();i++){
            StringBuilder str = new StringBuilder(inputString.get(i));
            char testSymbol = str.charAt(0);
            if(FindClosingPerenthes(str.toString())){ continue; }

            if(testSymbol == '-' && (i == 0 || inputString.get(i-1).charAt(0)=='(')){
                negativeNumber = true;
                continue;
            }
            else if(testSymbol == '-' && Operands(inputString.get(i+1).charAt(0))){
                throw new NullPointerException("Invalid syntax");
            }
            if(!StringZnakCheck(testSymbol)){
                if(negativeNumber == true){
                    str.insert(0,"-");
                }
                outputString.add(str.toString());
                negativeNumber = false;
                continue;
            }
            if(stack.size() > 0 && (testSymbol == '+' || testSymbol == '-')){
                while (stack.size() > 0 && (stack.get(stack.size()-1).charAt(0) == '*' || stack.get(stack.size()-1).charAt(0) == '/')){
                    outputString.add(stack.get(stack.size()-1));
                    stack.remove(stack.size()-1);
                }
            }
            if(StringZnakCheck(testSymbol)){
                stack.add(str.toString());
                continue;
            }
            System.out.println();
        }
        while (!stack.isEmpty()) {
            outputString.add(stack.get(stack.size() - 1));
            stack.remove(stack.size()-1);
        }
    }
}



