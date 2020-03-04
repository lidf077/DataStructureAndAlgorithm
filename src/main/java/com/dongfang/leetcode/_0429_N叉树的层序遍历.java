package com.dongfang.leetcode;//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索


import java.util.ArrayList;
import java.util.Collections;
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
public class _0429_N叉树的层序遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return Collections.emptyList();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = queue.size();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> levelResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            levelSize--;
            levelResult.add(node.val);
            List<Node> children = node.children;
            if (children != null) {
                children.forEach(queue::offer);
            }

            if (levelSize == 0) {
                result.add(levelResult);
                levelSize = queue.size();
                levelResult = new ArrayList<>();
            }
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
