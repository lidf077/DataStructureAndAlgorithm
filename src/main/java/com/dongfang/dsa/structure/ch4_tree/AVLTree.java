package com.dongfang.dsa.structure.ch4_tree;

import java.util.Comparator;

/**
 * 总结
 *      添加：
 *          可能会导致所有祖先节点都失衡
 *          只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡，仅需要O(1)次调整
 *
 *      删除：
 *          只可能会导致父节点或者祖先节点失衡，只可能导致一个节点失衡
 *          让父节点恢复平衡后，可能会导致更高层的祖先节点失衡，最多需要O(logN)次调整
 *      平均时间复杂度：
 *          搜索：O(logN)
 *          添加：O(logN) 仅需要 O(1) 次旋转操作
 *          删除：O(logN) 最多需要 O(logN) 次旋转操作
 * @param <E>
 */
@SuppressWarnings("all")
public class AVLTree<E> extends BBST<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
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
                reBalance(node);
                break; // 找到第一个不平衡的，恢复平衡后就中止，整棵树就平衡了，没必要向上访问
            }
        }
    }


    /**
     * 删除导致的失衡
     * 只可能会导致父节点失衡
     * 除父节点以外的其他节点，都不可能失衡
    * */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 平衡，更新高度，一直找parent的过程中更新高度，性能更高
                updateHeight(node);
            } else {
                // 恢复平衡
                reBalance(node);
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

    private void reBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { // RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);
        // 先更新子树高度，再更新根节点高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        // 父类的所有逻辑做完后，更新高度
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
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
