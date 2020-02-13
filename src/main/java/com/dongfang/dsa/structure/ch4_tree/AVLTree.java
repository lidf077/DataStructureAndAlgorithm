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
    protected void afterRemove(Node<E> node) {
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

    private void rotate(
            Node<E> r, // 子树的根节点
            Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f) {
        // 让d成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }


    /*
     *                    g--|                    |--p--|
     *                    |--p--|                 g--|  n
     *                    c     n                    c
     */
    private void rotateLeft(Node<E> grand) {
        // 更新指向
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /*
     *                 |--g       |--p--|
     *              |--p--|       n  |--g
     *              n     c          c
     */
    private void rotateRight(Node<E> grand) {
        // 更新指向
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 更新父节点
        // parent成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;

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
