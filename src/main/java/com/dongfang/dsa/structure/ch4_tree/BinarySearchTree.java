package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.Objects;

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
            } else { // compareRes = 0
                node.element = element
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

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
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
