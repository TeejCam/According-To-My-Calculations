package org.example;
import java.util.Stack;

//this class just converts infix to postfix

public class ShuntingYard {

    /*public static void main(String[] args) {
        String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        System.out.printf("infix:   %s%n", infix);
        System.out.printf("postfix: %s%n", infixToPostfix(infix));
    }*/

    public static String infixToPostfix(String infix) {
        /* To find out the precedence, we take the index of the
           token in the ops string and divide by 2 (rounding down). 
           This will give us: 0, 0, 1, 1, 2 */
        final String ops = "-+/*^";

        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);
          
                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            } 
            else if (c == '(') {
                s.push(-2); // -2 stands for '('
            } 
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }

    public static boolean isNumber(String str)
    {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static double evaluatePostfix(String expression)
    {
        Stack<Double> stack = new Stack<>();

        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if(isNumber(token)){
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch(token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                    case "^":
                        stack.push(Math.pow(a, b));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }
            }
        }
        return stack.pop();
    }

    
    public static double evaluate(String infixExp)
    {
        String postfixExp = infixToPostfix(infixExp);
        return evaluatePostfix(postfixExp);
    }
    
}