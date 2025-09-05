public class RverseStringWithK {
    public class ReverseStringWithK {
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
    
}
