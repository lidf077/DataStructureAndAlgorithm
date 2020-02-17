package com.dongfang.leetcode;//给定一个二叉树，原地将它展开为链表。
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import javafx.scene.effect.Effect;

import java.awt.event.PaintEvent;
import java.io.PrintWriter;
import java.time.Period;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _114_二叉树展开为链表 {
    public void flatten(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode prev = root;

        while (true) {
            if (node != null) {
                if (node != root) {
                    prev.left = null;
                    prev.right = node;
                    prev = prev.right;
                }
                if (node.right != null) stack.push(node.right);
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                node = stack.pop();
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
