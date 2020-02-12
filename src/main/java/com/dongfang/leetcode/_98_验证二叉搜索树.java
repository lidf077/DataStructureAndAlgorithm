package com.dongfang.leetcode;//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class _98_验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        long prev = Long.MIN_VALUE;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            if (prev >= node.val) return false;
            prev = node.val;
            node = node.right;
        }
        return true;
    }


    public boolean isValidBSTByLevelTraversal(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;

            if (left != null) {
                queue.offer(left);
                while (left.right != null) {
                    left = left.right;
                }
            }

            if (right != null) {
                queue.offer(right);
                while (right.left != null) {
                    right = right.left;
                }
            }

            if (left != null && right != null) {
                if (!(left.val < node.val && node.val < right.val)) return false;
            }

            if (left != null && right == null) {
                if (!(left.val < node.val)) return false;
            }

            if (left == null && right != null) {
                if (!(node.val < right.val)) return false;
            }
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
