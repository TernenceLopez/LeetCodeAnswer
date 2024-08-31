package com.ustc.normal;

public class LCR077 {
    public static void main(String[] args) {
        // [4,2,1,3]
        ListNode res = sortList(new ListNode(4,
                new ListNode(2,
                        new ListNode(1,
                                new ListNode(3)))));
        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }
    }
    public static ListNode sortList(ListNode head) {
        return sortRegion(head, null);
    }

    // 排序[start, end)区间的元素，end就算不为null也不参与排序
    public static ListNode sortRegion(ListNode start, ListNode end){
        if(start==null) return null;
        if(start.next==end){
            // 返回这一个元素，next指向null，方便merge方法确定list的边界
            start.next = null;
            return start;
        }

        // mid指向[start, end)中间点
        ListNode slow = start, fast = start;
        while(fast.next!=end){
            slow = slow.next;
            fast = fast.next;
            if(fast.next!=end) fast = fast.next;
        }
        // 排序[start, end)区间, 一直拆分区间，直到区间只有1个或0个元素
        ListNode list1 = sortRegion(start, slow);
        ListNode list2 = sortRegion(slow, end);
        // 将两个链表合并
        ListNode sorted = merge(list1, list2);

        return sorted;
    }

    // list1和list2都是排序好的字符串, 将list1和list2合并在一起
    public static ListNode merge(ListNode list1, ListNode list2){
        ListNode dummy = new ListNode(0);
        // 临时变量，避免直接修改list1, list2, dummy
        ListNode temp = dummy, temp1 = list1, temp2 = list2;

        while(temp1 != null && temp2 !=null){
            if(temp1.val <= temp2.val){
                temp.next = temp1;
                temp1 = temp1.next;
            }else{
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if(temp1 != null) temp.next = temp1;
        if(temp2 != null) temp.next = temp2;

        return dummy.next;
    }
}
