package com.dongfang.leetcode.swordtooffer;//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
//versal/ 
// Related Topics 树 广度优先搜索


//leetcode submit region begin(Prohibit modification and deletion)

import com.dongfang.leetcode.TreeNode;

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
public class _面试题32_II_从上到下打印二叉树_II {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> levelRes = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelRes.add(node.val);
            levelSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);

            if (levelSize == 0) {
                levelSize = queue.size();
                res.add(levelRes);
                levelRes = new ArrayList<>();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
