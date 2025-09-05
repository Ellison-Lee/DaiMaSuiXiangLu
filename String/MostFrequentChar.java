import java.util.Scanner;

public class MostFrequentChar {
    public static char findMostFrequent(String s) {
        // 处理空字符串情况
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("输入字符串不能为空");
        }
        
        // 假设输入是ASCII字符，使用数组计数
        int[] count = new int[256]; // 覆盖所有ASCII字符
        
        // 统计每个字符出现的次数
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c]++;
        }
        
        // 找出出现次数最多的字符
        int maxCount = 0;
        char result = ' ';
        
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
                result = (char) i;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String input = scanner.nextLine();
        
        try {
            char mostFrequent = findMostFrequent(input);
            System.out.println("出现次数最多的字符是：" + mostFrequent);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }
}
    