package com.dongfang.leetcode;

public class _面试题28_对称的二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;

        if (t1 == null || t2 == null) return false;

        if (t1.val != t2.val) return false;

        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
    }
}
