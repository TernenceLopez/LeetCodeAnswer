package com.ustc.normal;

import java.util.ArrayList;
import java.util.List;

public class LeeCode109 {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        // -10,-3,0,5,9
        ListNode node = new ListNode(-10,
                new ListNode(-3,
                        new ListNode(0,
                                new ListNode(5,
                                        new ListNode(9)))));
        System.out.println(sortedListToBST(node));
    }

    public static TreeNode sortedListToBST(ListNode head) {
        List<Integer> arr = new ArrayList<>();
        while(head != null){
            arr.add(head.val);
            head = head.next;
        }
        return sortedArrayToBST(arr, 0, arr.size() - 1);
    }

    /**
     * arr[left, right] 能够构成的平衡二叉树的根节点
     * */
    public static TreeNode sortedArrayToBST(List<Integer> arr, int left, int right) {
        if(left > right) return null;
        int rootIndex = (left + right) / 2;
        return new TreeNode(arr.get(rootIndex),
                sortedArrayToBST(arr, left, rootIndex - 1),
                sortedArrayToBST(arr, rootIndex + 1, right));
    }
}
