package com.dongfang.leetcode;//给定一个 N 叉树，返回其节点值的前序遍历。
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
public class _589_N叉树的前序遍历 {
    public List<Integer> preorder(Node root) {
        if (root == null) return Collections.emptyList();

        Stack<Node> stack = new Stack<>();
        Node node = root;
        List<Integer> res = new ArrayList<>();
        while (true) {
            List<Node> children = node.children;
            if (children != null && !children.isEmpty()) {
                res.add(node.val);

                for (int i = 1; i < children.size(); i++) {
                    stack.push(children.get(i));
                }
                node = children.get(0);
            } else if (stack.isEmpty()) {
                break;
            } else {
                node = stack.pop();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
