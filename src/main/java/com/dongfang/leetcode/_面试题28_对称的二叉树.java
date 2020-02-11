package com.dongfang.leetcode;

public class _面试题28_对称的二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isSymmetric(root.left) && isSymmetric(root.right);
    }
}
