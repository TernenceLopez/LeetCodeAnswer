package com.ustc.normal;

import java.util.List;

/**
 * 面试题 02.05. 链表求和
 * */
public class LeeCode1367 {
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
    
    /*
    给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
    如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
    一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
    * */
    public static void main(String[] args) {
        // list: [2,2,1]
        // tree: [2,null,2,null,2,null,1]
        System.out.println(isSubPath(
                new ListNode(2,
                        new ListNode(2,
                                new ListNode(1))),
                new TreeNode(2,
                        null,
                        new TreeNode(2,
                                null,
                                new TreeNode(1)))
        ));
    }

    /**
     * 当前root节点对应的tree中是否包含了head链表
     * */
    public static boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true; // 链表已经遍历完，匹配成功
        if (root == null) return false; // 树已经遍历完但链表还没结束，匹配失败

        // 处理匹配链表与当前节点的路径
        if (head.val == root.val) {
            // 一旦发现链表头节点和root节点值相同，尝试匹配剩余路径，在matchPath中递归
            if (matchPath(head, root)) {
                return true;
            }
        }

        // 如果链表头节点的值和root节点值不相同，则递归寻找与head头节点值相同的root节点，找到之后继续调用matchPath匹配剩余路径
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    // 检查从当前节点开始的路径是否匹配链表
    private static boolean matchPath(ListNode head, TreeNode root) {
        if (head == null) return true; // 链表遍历完，匹配成功
        if (root == null) return false; // 树节点遍历完，匹配失败
        if (head.val != root.val) return false; // 当前值不匹配，匹配失败

        // 继续沿着当前路径向下递归
        return matchPath(head.next, root.left) || matchPath(head.next, root.right);
    }
}
