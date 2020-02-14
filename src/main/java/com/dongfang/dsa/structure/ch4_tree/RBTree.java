package com.dongfang.dsa.structure.ch4_tree;

import java.util.Comparator;

public class RBTree<E> extends BST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }


    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根根节点，直接染成黑色，返回
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父 节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = parent.parent;

        // 叔父节点是红色
        if (isRed(uncle)) {
            // 父节点和叔父节点染成黑色
            black(parent);
            black(uncle);

            // 把祖父节点当作是新添加的节点
            red(grand);
            afterAdd(grand);
            return;
        }

        // 叔父节点不是红色

    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private class RBNode<E> extends Node<E> {
        // 新添加的节点，默认为红色，这样能让红黑树的性质尽快满足，红色节点的子节点都是null，为黑色
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
