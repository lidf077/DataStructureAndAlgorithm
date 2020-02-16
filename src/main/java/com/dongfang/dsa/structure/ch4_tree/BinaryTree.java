package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.Visitor;
import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTreeInfo;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("all")
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
    }


    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node<E> node) {
        //
        if (node == null) return;
        System.out.println(node.element);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void preOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrderTraversal(root, visitor);
    }

    public void preOrderTraversalByIterator(Visitor<E> visitor) {
        if (visitor == null) return;
        preOrderTraversalByIterator(root, visitor);
    }

    /**
     * 迭代前序遍历三步走：
     * 节点不为空
     *      1、访问节点
     *      2、将右子节点入栈
     *      3、往左走
     * 节点为空：
     *      弹出栈顶元素，循环上面的操作
     * @param node
     * @param visitor
     */
    private void preOrderTraversalByIterator(Node<E> node, Visitor<E> visitor) {
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                // 访问node结点
                if (visitor.visit(node.element)) return;

                // 将右子节点入栈
                if (node.right != null) stack.push(node.right);

                // 左边不为空就一直向左走
                node = node.left;
            } else if (stack.isEmpty()){ // 到过左边的叶子节点，栈为空
                return; // 栈为空，遍历完了
            } else {
                node = stack.pop();
            }
        }
    }

    private void preOrderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.isStop) return;
        visitor.isStop = visitor.visit(node.element);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<E> node) {
        if (node == null) return;
        inOrderTraversal(node.left);
        System.out.println(node.element);
        inOrderTraversal(node.right);
    }


    public void InOrderTraversalByIterator(Visitor<E> visitor) {
        if (visitor == null) return;
        InOrderTraversalByIterator(root, visitor);
    }

    /**
     * 如果节点为不为空：
     *      1、先将节点入栈，
     *      2、向左走
     * 如果栈为空：
     *      遍历结束
     * 如果节点为空：
     *      1、遍历到了最左边的叶子
     *      2、node = 右子树
     *      3、对node进行循环操作
     * @param node
     * @param visitor
     */
    private void InOrderTraversalByIterator(Node<E> node, Visitor<E> visitor) {
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return;
            } else {
                node = stack.pop();
                // 访问
                if (visitor.visit(node.element)) return;
                // 让右节点进行中序遍历，右节点进行这个循环操作
                node = node.right;
            }
        }
    }

    public void inOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inOrderTraversal(root, visitor);
    }

    private void inOrderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.isStop) return;
        inOrderTraversal(node.left);
        if (visitor.isStop) return;
        visitor.isStop = visitor.visit(node.element);
        inOrderTraversal(node.right);
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postOrderTraversal(root, visitor);
    }

    private void postOrderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;

        postOrderTraversal(node.left, visitor);
        postOrderTraversal(node.right, visitor);

        if (visitor.isStop) return;
        visitor.isStop = visitor.visit(node.element);
    }

    private void postOrderTraversal(Node<E> node) {
        if (node == null) return;

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element);
    }



    public void PostOrderTraversalByIterator(Visitor<E> visitor) {
        if (visitor == null) return;
        PostOrderTraversalByIterator(root, visitor);
    }

    private void PostOrderTraversalByIterator(Node<E> node, Visitor<E> visitor) {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);

        // 记录上一次弹出访问的节点
        Node<E> prev = null;

        while (!stack.isEmpty()) {
            Node<E> top = stack.peek();
            if (top.isLeaf() || (prev != null && top == prev.parent)) {
                prev = stack.pop();
                // 访问节点
                if (visitor.visit(prev.element)) return;
            } else {
                if (top.right != null) stack.push(top.right);
                if (top.left != null) stack.push(top.left);
            }
        }
    }

    public void levelOrderTraversal() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> queueHead = queue.poll();
            System.out.println(queueHead.element);
            if (queueHead.left != null) {
                queue.offer(queueHead.left);
            }
            if (queueHead.right != null) {
                queue.offer(queueHead.right);
            }
        }
    }

    // 要做什么，交给visitor
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> queueHead = queue.poll();
            // 增强的迭代器
            boolean isStop = visitor.visit(queueHead.element);
            if (isStop) return;
            if (queueHead.left != null) {
                queue.offer(queueHead.left);
            }
            if (queueHead.right != null) {
                queue.offer(queueHead.right);
            }
        }
    }

    public int height() {
        return height(root);
    }

    // 一个节点的高度为左右子树的最高+1
    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int heightByIterator() {
        return heightByIterator(root);
    }

    private int heightByIterator(Node<E> node) {
        if (node == null) return 0;

        int height = 0;
        int levelSize = 1; // 存储每一层的元素数量
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> queueHead = queue.poll();
            levelSize--; // 每取出一个，这一层的元素数量减1

            // 访问完一层，height++
            // 怎么知道访问完了一层，如果知道每一层的节点个数，从队列中取出这样的数量后就知道访问完了一层
            // 队头元素访问完了，队列中有多少个，访问完一层后，下一层中的节点个数就是队列中元素的个数，队列的长度

            if (queueHead.left != null) {
                queue.offer(queueHead.left);
            }

            if (queueHead.right != null) {
                queue.offer(queueHead.right);
            }

            if (levelSize == 0) { // 意味着即将访问下一层
                levelSize = queue.size();
                height++;
            }
        }

        return height;
    }


    // 先写层序遍历
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean isLeaf = false;
        while (!isEmpty()) {
            Node<E> node = queue.poll();

            if (isLeaf && !node.isLeaf()) return false;

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {// 左边空，右边不为空
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else { // node.right == null，后面的都得是叶子
                isLeaf = true;
            }
        }

        return true;
    }


    protected void elementNotNullCheck(E element) {
        Objects.requireNonNull(element, "element must not be null");
    }

    protected Node<E> predecessor(Node<E> node) {
        Node<E> p = node.left;

        // 前驱节点在左子树中，left.right.right.right.right
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从祖父节点，祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    protected Node<E> successor(Node<E> node) {
        Node<E> p = node.right;

        // 前驱节点在左子树中，right.left.left.left.left
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从祖父节点，祖父节点中寻找后继节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    protected Node<E> buildNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return /*((Node<E>) node).element*/ node;
    }


    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        boolean hasTwoChildren() {
            return left != null && right != null;
        }

        boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        Node<E> sibling() {
            if (isLeftChild()) return parent.right;

            if (isRightChild()) return parent.left;

            return null;
        }
    }

}
