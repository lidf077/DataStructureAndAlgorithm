package com.dongfang.leetcode;//您需要在二叉树的每一行中找到最大的值。
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
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
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class _0515_在每个树行中找最大值 {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = queue.size();
        List<Integer> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelSize--;
            if (node.val > max) max = node.val;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);

            if (levelSize == 0) {
                levelSize = queue.size();
                result.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
