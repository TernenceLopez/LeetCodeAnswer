package com.ustc.simple;

public class LeeCode21 {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(2,a1);
        ListNode a3 = new ListNode(1,a2);

        ListNode b1 = new ListNode(4);
        ListNode b2 = new ListNode(3,b1);
        ListNode b3 = new ListNode(1,b2);

        mergeTwoLists(a3, b3);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // List2插入到List1中
        ListNode l1 = list1, l2 = list2;
        while(l2 != null){
            ListNode l2Next = l2;
            ListNode l1Next = l1.next;
            while(l2Next.val >= l1.val && l2Next.val < l1.next.val){
                l2Next = l2Next.next;
            }
            // 插入
            l1.next = l2;
            while(l2.next != l2Next){
                l2 = l2.next;
            }
            l2.next = l1Next;
            // 重新赋值
            l2 = l2Next;
            l1 = l1Next;
        }

        return list1;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
