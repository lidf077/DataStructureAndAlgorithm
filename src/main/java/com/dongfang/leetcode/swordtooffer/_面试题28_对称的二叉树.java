package com.dongfang.leetcode.swordtooffer;

import com.dongfang.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _面试题28_对称的二叉树 {
    public boolean isSymmetric(TreeNode root) {
/*        if (root == null) return true;
        return isSymmetric(root.left, root.right);*/

        if (root == null || (root.left == null && root.right == null)) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) continue;

            if (left == null || right == null) return false;

            if (left.val != right.val) return false;

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    private boolean isSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;

        if (t1 == null || t2 == null) return false;

        if (t1.val != t2.val) return false;

        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
    }
}
