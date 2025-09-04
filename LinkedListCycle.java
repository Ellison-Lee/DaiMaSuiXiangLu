import java.util.Objects;

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
    public boolean hasCycle(ListNode head) {
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
        LinkedListCycle detector = new LinkedListCycle();
        
        // 测试用例1：有环链表
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;  // 形成环：node4 -> node2
        System.out.println("测试用例1（有环）：" + detector.hasCycle(node1));  // 输出 true
        
        // 测试用例2：无环链表
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        node5.next = node6;
        node6.next = null;
        System.out.println("测试用例2（无环）：" + detector.hasCycle(node5));  // 输出 false
        
        // 测试用例3：空链表
        System.out.println("测试用例3（空链表）：" + detector.hasCycle(null));  // 输出 false
    }
}
    