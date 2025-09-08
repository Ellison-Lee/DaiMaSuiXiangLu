public class QuickSort {
    // 快速排序主方法
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }
    
    // 递归排序方法
    private static void sort(int[] arr, int left, int right) {
        // 递归终止条件：左右指针相遇或交叉
        if (left >= right) {
            return;
        }
        
        // 选择中间元素作为基准值
        int pivot = arr[(left + right) / 2];
        // 分区操作，返回分区后基准值的位置
        int index = partition(arr, left, right, pivot);
        // 递归排序左半部分
        sort(arr, left, index - 1);
        // 递归排序右半部分
        sort(arr, index, right);
    }
    
    // 分区操作：将小于基准值的元素放左边，大于基准值的元素放右边
    private static int partition(int[] arr, int left, int right, int pivot) {
        // 左右指针相遇时退出循环
        while (left <= right) {
            // 从左向右找到第一个大于等于基准值的元素
            while (arr[left] < pivot) {
                left++;
            }
            // 从右向左找到第一个小于等于基准值的元素
            while (arr[right] > pivot) {
                right--;
            }
            
            // 交换左右指针指向的元素
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        // 返回左指针位置作为下一次分区的边界
        return left;
    }
    
    // 交换数组中两个位置的元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // 测试方法
    public static void main(String[] args) {
        int[] arr1 = {4,3,2,1};
        System.out.println("排序前：");
        for (int num : arr1) {
            System.out.print(num + " ");
        }
        
        quickSort(arr1);
        
        System.out.println("\n排序后：");
        for (int num : arr1) {
            System.out.print(num + " ");
        }
    }
}
