package com.dongfang.dsa.structure.ch4_tree;

import java.util.Comparator;

@SuppressWarnings("all")
public class AVLTree<E> extends BST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }


    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 平衡，更新高度，一直找parent的过程中更新高度，性能更高
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                break; // 找到第一个不平衡的，恢复平衡后就中止
            }
        }
    }

    @Override
    protected Node buildNode(Object element, Node parent) {
        return new AVLNode(element, parent);
    }


    /*
     * 恢复平衡，高度最低的不平衡节点
     *
     * @param node 高度最低的不平衡节点
     *                    g
     *                 p
     *             n
     */
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                /*
                 *                    g           p
                 *                 p          n      g
                 *             n
                 */
                // 对g进行右旋转
                rotateRight(grand);

            } else { // LR
                /*
                 *                    g               g          n
                 *                 p               n          p      g
                 *                    n         p
                 */
                 // 对p进行左旋转，再对g进行右旋转
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) { // RL
                /*
                 *                    g            g                   n
                 *                       p             n             g    p
                 *                    n                    p
                 */
                // 对p进行右旋转，再对g进行左旋转
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                /*
                 *                    g                      p
                 *                       p                g      n
                 *                          n
                 */
                // 对g进行左旋转
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> node) {

    }

    private void rotateRight(Node<E> node) {

    }

    private boolean isBalanced(Node<E> node) {
        // AVL树中，一定可以强转
        int balanceFactor = ((AVLNode<E>) node).balanceFactor();
        return Math.abs(balanceFactor) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private class AVLNode<E> extends Node<E> {
        int height = 1;

        AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        // 平衡因子，左子树高度减去右子树高度
        int balanceFactor() {
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        void updateHeight() {
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>) right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }


        Node<E> tallerChild() {
            int leftHeight = (left == null) ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = (right == null) ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;

            return isLeftChild() ? left : right;
        }
    }
}
