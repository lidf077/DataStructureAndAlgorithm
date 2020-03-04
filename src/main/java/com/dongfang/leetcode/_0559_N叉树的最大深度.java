package com.dongfang.leetcode;//给定一个 N 叉树，找到其最大深度。
//
// 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 我们应返回其最大深度，3。 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总不会超过 5000。 
// Related Topics 树 深度优先搜索 广度优先搜索


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
public class _0559_N叉树的最大深度 {
    public int maxDepth(Node root) {
        if (root == null) return 0;

        int height = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = queue.size();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            levelSize--;
            List<Node> children = node.children;
            if (children != null) {
                children.forEach(queue::offer);
            }

            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }

        return height;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
