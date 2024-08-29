package com.ustc.normal;

public class LeeCode2 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode result = addTwoNumbers(
                new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(9)))),
                new ListNode(5, new ListNode(6, new ListNode(9))));
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // l1 -> l2
        int incre = 0;
        ListNode dummy = new ListNode(-1, l2);
        ListNode prev = l2;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val + incre;
            l2.val = sum % 10;
            incre = sum / 10;

            prev = l2;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(l1==null && l2==null){
            if(incre!=0) prev.next = new ListNode(incre);
            return dummy.next;
        }else if(l1==null){
            while(l2 != null){
                int sum = l2.val + incre;
                l2.val = sum % 10;
                incre = sum / 10;

                prev = l2;
                l2 = l2.next;
            }
            if(incre!=0) prev.next = new ListNode(incre);
            return dummy.next;
        }else{
            while(l1 != null){
                int sum = l1.val + incre;
                l1.val = sum % 10;
                incre = sum / 10;

                prev.next = l1;
                prev = l1;
                l1 = l1.next;
            }
            if(incre!=0) prev.next = new ListNode(incre);
            return dummy.next;
        }
    }
}
