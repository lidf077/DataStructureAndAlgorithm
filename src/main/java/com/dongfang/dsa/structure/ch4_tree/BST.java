package com.dongfang.dsa.structure.ch4_tree;

import java.awt.geom.AffineTransform;
import java.util.Comparator;

/**
 * 复杂度分析:
 *      增删改查的次数与树的高度有关，操作次数最坏为树的高度
 *      o(h) = o(logN)
 *
 *      但是，如果是从小到大添加，就会形成一个链表，树的高度就和元素的个数相同
 *          二叉搜索树退化成链表 n=100万，最你高度20
 *
 *          删除节点时，也可能导致二叉搜索树退化成链表
 * @param <E>
 */
@SuppressWarnings("all")
public class BST<E> extends BinaryTree {
    private Comparator<E> comparator;
    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }


    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个节点
        if (root == null) {
            root = buildNode(element, null);
            afterAdd(root);
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
                // 也可以添加上，形成链表
                node.element = element;
                return;
            }
        }

        // 看看插入到父节点的哪个位置
        Node<E> newNode = buildNode(element, parent);
        if (compareRes > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        // 新添加节点之后的处理
        afterAdd(newNode);
        size++;
    }

    /**
     * 添加新结点之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {}

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }

        // 如果不传比较器，强制认为它是可以比较的
        return ((Comparable<E>) e1).compareTo(e2);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        if (node.hasTwoChildren()) { // 度为2的节点
            Node<E> successor = successor(node); // 找到此节点的后继节点
            // 用后继节点的值，覆盖度为2的节点的值
            node.element = successor.element;
            // 删除后继节点，让node指向后继节点，后面只删除node节点就行，后面删除node节点的代码可以统一
            node = successor;
        }

        // 统一删除node节点，node的度必然是1或者0
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // 度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left right的指向
            if (node.parent == null) { // node是度为1的节点，并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else /*if (node == node.parent.right)*/ {
                node.parent.right = replacement;
            }

        } else if (node.parent == null) { // node为叶子节点并且是根节点
            root = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) { // 左叶子
                node.parent.left = null;
            } else /*if (node == node.parent.right)*/ { // 右叶子
                node.parent.right = null;
            }
        }

        size--;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int compareRes = compare(element, node.element);
            if (compareRes == 0) return node;
            if (compareRes > 0) node = node.right;
            else node = node.left;
        }
        return null;
    }
}
