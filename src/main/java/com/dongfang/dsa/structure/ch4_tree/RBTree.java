package com.dongfang.dsa.structure.ch4_tree;

import java.util.Comparator;

@SuppressWarnings("all")
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;


    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }


    // 添加完一个新的节点后，修复一下红黑树的性质，保证它依然是一棵红黑树
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        // 添加的是根根节点，直接染成黑色，返回，或者是上溢到根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) return;

        // 叔父 节点
        Node<E> uncle = parent.sibling();
        // 祖父节点，所有的操作都要将gran染成红色，因此统一操作
        Node<E> grand = red(parent.parent);

        // 叔父节点是红色
        if (isRed(uncle)) {
            // 父节点和叔父节点染成黑色，B树节点上溢
            black(parent);
            black(uncle);

            // 把祖父节点当作是新添加的节点
            afterAdd(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // L L
                black(parent);
            } else { // L R
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // R L
                black(node);
                rotateRight(parent);
            } else { // R R
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 如果删除的节点是红色，直接返回
        if (isRed(node)) return;
        // 用以取代node的子节点是红色，将替代的子节点染成黑色就能保持红黑树的性质
        if (isRed(replacement)) {
            black(replacement);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是根节点，直接返回  root == node
        if (parent == null) return;

        // 删除的是黑色叶子节点
        Node<E> sibling = node.sibling();

    }

    @Override
    protected Node buildNode(Object element, Node parent) {
        return new RBNode<>(element, parent);
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

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R->";
            }
            return str + element.toString();
        }
    }
}
