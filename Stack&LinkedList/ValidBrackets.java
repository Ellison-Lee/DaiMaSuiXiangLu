import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 20. 有效的括号
 * 
 * 描述：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * 3. 每个右括号都有一个对应的相同类型的左括号。
 * 
 * 示例：
 * 输入：s = "()"
 * 输出：true
 * 
 * 输入：s = "()[]{}"
 * 输出：true
 * 
 * 输入：s = "(]"
 * 输出：false
 */
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
