import java.util.Scanner;

/**
 * LeetCode 类似题目：替换数字
 * 
 * 描述：给定一个字符串，将其中的每个数字替换为字符串"number"。
 * 
 * 示例：
 * 输入: "a1b2c3"
 * 输出: "anumberbnumbercnumber"
 * 
 * 输入: "123"
 * 输出: "numbernumbernumber"
 */
public class ReplaceNum {
    
    public static String replaceNumber(String s) {
        int count = 0; // 统计数字的个数
        int sOldSize = s.length();
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                count++;
            }
        }
        // 扩充字符串s的大小，也就是每个空格替换成"number"之后的大小
        char[] newS = new char[s.length() + count * 5];
        int sNewSize = newS.length;
        // 将旧字符串的内容填入新数组（手动循环复制）
        char[] oldChars = s.toCharArray();
        for (int i = 0; i < sOldSize; i++) {
            newS[i] = oldChars[i];
        }
        // 从后先前将空格替换为"number"
        for (int i = sNewSize - 1, j = sOldSize - 1; i>=0; j--, i--) {
            if (!Character.isDigit(newS[j])) {
                newS[i] = newS[j];
            } else {
                newS[i] = 'r';
                newS[i - 1] = 'e';
                newS[i - 2] = 'b';
                newS[i - 3] = 'm';
                newS[i - 4] = 'u';
                newS[i - 5] = 'n';
                i -= 5;
            }
        }
        return new String(newS);
    };
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        System.out.println(replaceNumber(s));
        scanner.close();
    }
}