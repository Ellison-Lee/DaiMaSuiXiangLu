import java.util.ArrayDeque;
import java.util.Deque;

public class ValidBrackets {
    public static boolean isValid(String s){
        Deque<Character> stack  = new ArrayDeque<>();
        for(char c:s.toCharArray()){
            if(c=='(') stack.push(')');
            else if (c=='{') stack.push('}');
            else if (c=='[') stack.push(']');
            else if (stack.isEmpty() || c!=stack.pop()) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
    String s  = "[()]";
    System.out.println(isValid(s));
    }
}
