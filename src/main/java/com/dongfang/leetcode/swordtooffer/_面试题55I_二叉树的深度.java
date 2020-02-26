package com.dongfang.leetcode.swordtooffer;

import com.dongfang.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _面试题55I_二叉树的深度 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize = 1;
        int height = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
}
