package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTreeInfo;

import java.io.PipedReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        /**
         * 添加步骤：
         *      1、找到父节点parent
         *      2、创建新节点node
         *      3、parent.lent = node 或者 parent.right = node
         */
        // 来到这里，添加的不是第一个节点
        Node<E> parent = null;
        Node<E> node = root;
        int compareRes = 0;
        while (node != null) {
            compareRes = compare(element, node.element);
            parent = node;
            if (compareRes > 0) {
                node = node.right;
            } else if (compareRes < 0) {
                node = node.left;
            } else { // compareRes = 0 对象的比较结果一样，但是对象可能不一样
                node.element = element;
                return;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (compareRes > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        // 如果不传比较器，强制认为它是可以比较的
        return ((Comparable<E>) e1).compareTo(e2);
    }


    private void elementNotNullCheck(E element) {
        Objects.requireNonNull(element, "element must not be null");
    }


    public abstract class Visitor<E> {
        boolean isStop;

        abstract boolean visit(E element);
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

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;

        toString(node.left, sb, prefix + "---");
        sb.append(prefix).append(node.element).append("\n");
        toString(node.right, sb, prefix + "---");
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
        return ((Node<E>) node).element;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
