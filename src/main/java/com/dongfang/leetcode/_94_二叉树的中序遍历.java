package com.dongfang.leetcode;//给定一个二叉树，返回它的中序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class _94_二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
