package com.ustc.normal;

public class LCR021 {
    /**
     * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * */
    public static void main(String[] args) {
        // head = [1], n = 1
        ListNode head = new ListNode(1);
        ListNode.printListNode(removeNthFromEnd(head, 1));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy, fast = dummy;
        while(n-- > 0) fast = fast.next;

        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }
}
