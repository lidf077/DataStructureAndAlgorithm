package com.dongfang.leetcode;//给定一个二叉树，返回它的 后序 遍历。
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
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


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
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _145_二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        List<Integer> res = new ArrayList<>();
        TreeNode prev = null;

        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if ((top.right == null && top.left == null) || (prev != null && (top == prev.left || top == prev.right))) {
                prev = stack.pop();
                System.out.println("prev.val = " + prev.val);
                res.add(prev.val);
            } else {
                if (top.right != null) stack.push(top.right);
                if (top.left != null) stack.push(top.left);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
