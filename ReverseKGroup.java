/**
 * k个一组反转链表
 * 例如：
 * 输入: 1->2->3->4->5, k=2
 * 输出: 2->1->4->3->5
 */

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 创建哑节点作为结果链表的起始点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // 记录上一组的尾节点，初始为哑节点
        ListNode prevGroupTail = dummy;
        
        while (true) {
            // 找到当前组的尾节点
            ListNode currentGroupTail = findKthNode(prevGroupTail, k);
            // 如果不足k个节点，结束循环
            if (currentGroupTail == null) {
                break;
            }
            
            // 记录下一组的头节点
            ListNode nextGroupHead = currentGroupTail.next;
            
            // 反转当前组，并获取反转后的头节点
            ListNode[] reversed = reverseList(prevGroupTail.next, currentGroupTail);
            ListNode newGroupHead = reversed[0];
            ListNode newGroupTail = reversed[1];
            
            // 将反转后的组与前后连接
            prevGroupTail.next = newGroupHead;
            newGroupTail.next = nextGroupHead;
            
            // 更新上一组尾节点为当前组的尾节点
            prevGroupTail = newGroupTail;
        }
        
        return dummy.next;
    }
    
    // 查找从start节点开始的第k个节点
    private ListNode findKthNode(ListNode start, int k) {
        ListNode current = start;
        for (int i = 0; i < k; i++) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        return current;
    }
    
    // 反转从head到tail的链表，返回反转后的头和尾
    private ListNode[] reverseList(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode current = head;
        ListNode tailNext = tail.next;  // 保存尾节点的下一个节点
        
        // 反转直到到达原尾节点
        while (current != tailNext) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        // 返回反转后的头节点(原尾节点)和尾节点(原头节点)
        return new ListNode[]{tail, head};
    }
    
    // 测试方法
    public static void main(String[] args) {
        // 创建测试链表: 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        ReverseKGroup solution = new ReverseKGroup();
        ListNode result = solution.reverseKGroup(head, 2);
        
        // 打印结果
        printList(result);  // 应输出: 2->1->4->3->5->null
    }
    
    // 打印链表
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + "->");
            current = current.next;
        }
        System.out.println("null");
    }
}
    