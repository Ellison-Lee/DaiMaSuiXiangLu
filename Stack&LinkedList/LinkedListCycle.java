import java.util.Objects;

/**
 * LeetCode 141. 环形链表
 * 
 * 描述：给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
 * 
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * 
 * 示例：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 */

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class LinkedListCycle {
    // 判断链表是否有环的方法
    public static boolean hasCycle(ListNode head) {
        // 边界条件：空链表或只有一个节点且无环
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return false;
        }
        
        // 初始化快慢指针
        ListNode slow = head;       // 慢指针，每次走1步
        ListNode fast = head.next;  // 快指针，每次走2步
        
        // 当快慢指针不相遇时继续遍历
        while (!Objects.equals(slow, fast)) {
            // 快指针到达链表末尾，说明无环
            if (Objects.isNull(fast) || Objects.isNull(fast.next)) {
                return false;
            }
            
            slow = slow.next;       // 慢指针前进一步
            fast = fast.next.next;  // 快指针前进两步
        }
        
        // 快慢指针相遇，说明有环
        return true;
    }

    // 测试示例
    public static void main(String[] args) {
        
        // 测试用例1：有环链表
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;  // 形成环：node4 -> node1
        System.out.println("测试用例1（有环）：" + hasCycle(node1));  // 输出 true
        
        // 测试用例2：无环链表
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        node5.next = node6;
        node6.next = null;
        System.out.println("测试用例2（无环）：" + hasCycle(node5));  // 输出 false
        
        // 测试用例3：空链表
        System.out.println("测试用例3（空链表）：" + hasCycle(null));  // 输出 false
    }
}
    