public class ReverseLinkedList{
    static class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    //反转链表的方法
    public static ListNode reverseList(ListNode head){
        ListNode pre = null; //上一个节点
        ListNode curr = head; //当前节点

        while(curr != null){
            ListNode nextTemp = curr.next;//保存下一个节点
            curr.next = pre; //反转当前节点的指针
            pre = curr;//节点后移
            curr = nextTemp;//节点后移
        }

        return pre;
    }

    //打印链表
    public static void printList(ListNode head){
        ListNode curr = head;
        while(curr!=null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println();
    }

    //链末添加节点
    public static ListNode addNode(ListNode head, int val){
        ListNode newNode = new ListNode(val);
        
        if(head == null){
            return newNode;
        }
        else{
            ListNode curr = head;
            while(curr.next!=null){
                curr = curr.next;
            }
            curr.next = newNode;
            return head;
        }
    }

    public static void main(String[] args){
        ListNode head = null;
        head = addNode(head,1);
        head = addNode(head,2);
        head = addNode(head,3);
        head = addNode(head,4);
        head = addNode(head,5);
        head = addNode(head,6);

        System.out.println("原链表");
        printList(head);

        head = reverseList(head);
        System.out.println("反转后链表：");
        printList(head);
    }
}