package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.Visitor;
import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

@SuppressWarnings("all")
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

    /**
     * 如果树为空，返回false
     * 如果树不为空，开始层序遍历二叉树，用队列
     * 如果node.left != null && node.right != null， node.left node.right入队
     * 如果node.left == null && node.right != null，返回false
     * 如果node.left != null && node.right == null
     * 或者 node.left == null && node.right == null，接下来都是叶子节点了
     * 那么后面遍历的节点都应该为叶子节点，才是完全二叉树
     * 否则返回false
     *
     * @return
     */
/*    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> queueHead = queue.poll();
            if (isLeaf && !queueHead.isLeaf()) return false;

            if (queueHead.hasTwoChildren()) {
                queue.offer(queueHead.left);
                queue.offer(queueHead.right);
            } else if (queueHead.left == null && queueHead.right != null) {
                return false;
            } else { // 后面遍历的节点必须是叶子节点
                isLeaf = true;
                if (queueHead.left != null) {
                    queue.offer(queueHead.left);
                }
            }
        }
        return true;
    }*/

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

    /**
     * 前驱节点：中序遍历时的前一个节点
     * 如果是二叉搜索树，前驱节点就是前一个比它小的节点
     * node.left != null
     * predecessor = node.left.right.right.right
     * 终止条件 right 为null
     * <p>
     * node.left = null
     * predecessor = node.parent.parent.parent
     * 终止条件：node在parent的右子树中
     * <p>
     * node.left == null && node.parent = null 那就没有前驱节点
     *
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
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

    /**
     * 后继节点：中序遍历时的后一个节点
     * 如果是二叉搜索树，后继节点是后一个比它大的节点
     * node.right != null
     * successor = node.right.left.left.left...
     * 终止条件：left 为null
     * node.right == null && node.parent != null
     * successor = node.parent.parent.parent...
     * 终止条件：node在parent的左子树中
     * node.right == null && node.parent == null 没有前驱节点
     *
     * @param node
     */
    private Node<E> successor(Node<E> node) {
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
    }
}
