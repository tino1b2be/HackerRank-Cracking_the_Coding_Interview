import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();
        
        // must be even if balanced
        if (expression.length() % 2 != 0) return false;
        
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c == '{' || c == '(' || c == '[')
                stack.push(c);
            else {
                try {
                    char c1 = stack.pop();
                    if (!((c1 == '(' && c == ')') ||
                            (c1 == '{' && c == '}') ||
                            (c1 == '[' && c == ']'))) {
                        return false;
                    }
                } catch (Exception e){
                    // in case we pop and empty stack
                    return false;
                }
            }
        }
        // must be emptyis balanced
        return stack.empty();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println((isBalanced(expression)) ? "YES" : "NO");
        }
    }
}
