import java.util.Scanner;

/**
 * LeetCode 151. 反转字符串中的单词
 * 
 * 描述：给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
 * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 
 * 示例：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 
 * 输入：s = "  hello world  "
 * 输出："world hello"
 */
public class ReverseWords {
    public static String reverseWords(String s) {
        // 1. 去除字符串前后的空格，并将中间多余的空格缩减为一个
        StringBuilder trimmed = new StringBuilder();
        boolean spaceFound = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                // 遇到非空格字符，直接添加
                trimmed.append(c);
                spaceFound = false;
            } else if (!spaceFound) {
                // 遇到第一个空格，添加并标记已找到空格
                trimmed.append(' ');
                spaceFound = true;
            }
            // 连续的空格会被忽略
        }
        
        // 去除可能添加的最后一个空格
        if (trimmed.length() > 0 && trimmed.charAt(trimmed.length() - 1) == ' ') {
            trimmed.deleteCharAt(trimmed.length() - 1);
        }
        
        // 如果字符串为空，直接返回
        if (trimmed.length() == 0) {
            return "";
        }
        
        // 2. 翻转整个字符串
        reverse(trimmed, 0, trimmed.length() - 1);
        
        // 3. 逐个翻转每个单词
        int start = 0;
        for (int end = 0; end < trimmed.length(); end++) {
            if (trimmed.charAt(end) == ' ') {
                reverse(trimmed, start, end - 1);
                start = end + 1;
            }
        }
        // 翻转最后一个单词
        reverse(trimmed, start, trimmed.length() - 1);
        
        return trimmed.toString();
    }
    
    // 翻转字符串中从start到end的字符
    private static void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();  // 使用nextLine()以读取包含空格的完整输入
        System.out.println(reverseWords(s));
        scanner.close();
    }
}
