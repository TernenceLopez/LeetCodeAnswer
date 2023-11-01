package com.ustc.simple;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class LeeCode101 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        que.addFirst(root.left); // 左子树头节点入队列
        que.addFirst(root.right); // 右子树头节点入队列

        while(!que.isEmpty()){
            TreeNode leftNode = que.pollLast(); // 左子树的节点(出队列顺序和入队列顺序一致)
            TreeNode rightNode = que.pollLast(); // 右子树的节点

            if(leftNode==null && rightNode==null)
                // return true;
                continue; // 继续遍历左右子树的节点
            if(leftNode==null && rightNode!=null)
                return false;
            if(leftNode!=null && rightNode==null)
                return false;
            if(leftNode.val != rightNode.val)
                return false;

            que.addFirst(leftNode.left); // 加入左节点左孩子
            que.addFirst(rightNode.right); // 加入右节点右孩子

            que.addFirst(leftNode.right);
            que.addFirst(rightNode.left);
        }

        return true;
    }
}
