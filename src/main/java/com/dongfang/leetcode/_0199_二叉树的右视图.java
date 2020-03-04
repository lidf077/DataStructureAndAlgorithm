package com.dongfang.leetcode;//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
// 示例: 
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// 
// Related Topics 树 深度优先搜索 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class _0199_二叉树的右视图 {
    public List<Integer> rightSideViewByDfs(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        TreeNode node = root;
        // 直接往右走会遗漏 g下面这种情况
        /**
         *         1
         *        /
         *       2
         */
        while (node != null) {
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    public List<Integer> rightSideViewByBfs(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = queue.size();
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelSize--;

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (levelSize == 0) {
                res.add(node.val);
                levelSize = queue.size(); // 使用队列大小来记录一层的节点个数，是很好的方法
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
