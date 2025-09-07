/**
 * LeetCode 344. 反转字符串
 * 
 * 描述：编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 
 * 示例：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class ReverseString {
    public static void reverse(char[] s) {
        // 初始化左右指针
        int left = 0;
        int right = s.length - 1;
        
        // 当左指针小于右指针时，交换对应位置的字符
        while (left < right) {
            // 交换左右指针指向的字符
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            // 移动指针
            left++;
            right--;
        }
    }
    
    // 测试方法
    public static void main(String[] args) {
        
        // 示例1测试
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        reverse(s1);
        System.out.println(new String(s1)); // 输出：olleh
        
        // 示例2测试
        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        reverse(s2);
        System.out.println(new String(s2)); // 输出：hannaH
    }
}
    