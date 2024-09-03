package com.ustc.normal;

import java.util.Arrays;

public class LeeCode725 {

    /**
     * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
     * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
     * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
     * 返回一个由上述 k 部分组成的数组。
     *
     * 输入：head = [1,2,3], k = 5
     * 输出：[[1],[2],[3],[],[]]
     * */
    public static void main(String[] args) {
        ListNode head = new ListNode(1,
                        new ListNode(2,
                        new ListNode(3)));
        System.out.println(Arrays.toString(splitListToParts(head, 5)));
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode dummy = head;
        ListNode[] res = new ListNode[k];
        while(head!=null) {
            len++;
            head = head.next;
        }
        int base = len / k; // 每个分段都会分到的base个节点
        int mod = len % k;  // 多余的节点给每个分段多分配1个节点，一共分配mod个分段
        for(int i=0; i<k; i++){
            res[i] = dummy;
            // 当前分段分配的节点数目
            int move = base + (i < mod ? 1 : 0);
            for(int j=0; j<move-1; j++){
                dummy = dummy.next;
            }
            // 将当前分段结尾的节点指向null
            if(dummy==null) break;
            ListNode temp = dummy.next;
            dummy.next = null;
            dummy = temp;
        }

        return res;
    }
}
