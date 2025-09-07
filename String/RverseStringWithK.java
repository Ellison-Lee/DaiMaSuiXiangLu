/**
 * LeetCode 541. 反转字符串 II
 * 
 * 描述：给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * - 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * - 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 
 * 示例：
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 */
public class RverseStringWithK {
    public static String reverseStr(String s, int k) {
        // 将字符串转换为字符数组以便修改
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        // 每次处理2k个字符
        for (int i = 0; i < n; i += 2 * k) {
            // 计算需要反转的结束位置
            // 如果剩余字符大于等于k个，则反转前k个
            // 否则反转剩余的所有字符
            int end = Math.min(i + k - 1, n - 1);
            // 反转从i到end的字符
            reverse(arr, i, end);
        }
        
        return new String(arr);
    }
    
    // 辅助方法：反转字符数组中从start到end的字符
    public static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    
    public static void main(String[] args) {
        
        // 测试示例
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseStr(s, k));  // 输出: "bacdfeg"
        
        // 其他测试用例
        System.out.println(reverseStr("abcd", 2));  // 输出: "bacd"
        System.out.println(reverseStr("abcdefghij", 3));  // 输出: "cbadefihgj"
    }
}
