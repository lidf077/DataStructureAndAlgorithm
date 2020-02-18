package com.dongfang.dsa.structure.ch7_heap;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTreeInfo;

import java.util.Comparator;

/*
 * 完全二叉堆
 *      二叉堆的逻辑结构就是一棵完全二叉树，所以也叫完全二叉堆
 *      有完全二叉树的性质，二叉堆的底层物理结构一般使用数组实现即可
 *
 *      索引i的规律，n是元素数量
 *          1、如果i = 0，它是根节点
 *          2、如果 i > 0 ，它的父节点索引为floor((i - 1) / 2)
 *          3、如果 2i + 1 <= n - 1 它的左子节点索引为 2i + 1
 *          4、如果 2i + 1 > n - 1，它无左子节点
 *          5、如果 2i + 2 <= n - 1，它的右子节点索引为2i + 2
 *          6、如果 2i + 2 > n - 1，它无右子节点
 * @param <E>
 */

@SuppressWarnings("all")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /*
     * 循环指行以下操作
     *          1、如果node > 父节点，与父节点交换位置
     *          2、如果node <= 父节点，或者没有父节点，退出循环
     *          这个过程叫做上滤 sift up
     *          时间复杂度 O(logn)
     *
     * @param element
     */
    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
        siftUp(size - 1); // 让最后一个位置的元素上滤
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /*
     * 删除堆顶元素
     *      1、用最后一个节点覆盖根节点
     *      2、删除最后一个节点
     *      3、循环执行以下操作
     *          1、如果node < 子节点，与最大的子节点交换位置
     *          2、如果node >= 子节点，或者node没有子节点，退出循环
     *      这个过程叫做下滤，sift down，时间复杂度O(logn)
     *      交换位置的操作可以像添加一样优化
     * @return
     */
    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;

        siftDown(0);
        return root;
    }

    /**
     * 删除堆顶元素的同时插入一个新元素
     *
     * @param element 插入的新元素
     * @return 返回原来的堆项
     */
    @Override
    public E replace(E element) {
/* 2logn
       E root = remove();
        add(element);
        return root;*/

        // 将新添加的元素替换掉堆顶，再sift down
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }


    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // 第一个叶子节点的索引 == 非叶子节点的数量 floor(n/2)
        // index < 第一个叶子节点的索引
        // 必须保证index位置是非叶子节点
        while (index < half) {
            // index的节点有2种情况
            // 1.只有左子节点
            // 2.同时有左右子节点

            // 默认为左子节点跟它进行比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点中最大的值，不超出数组边界并且右边大
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                childIndex = rightIndex;
                child = elements[rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // 将子节点存放在index位置
            elements[index] = child;
            // 重新设置index
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 让index位置的元素上滤
     *
     * @param index
     */
    private void siftUp(int index) {
/*        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 2; // 向下取整
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) return;
            // element 大于parent节点，交换
            swap(index, parentIndex);
            // index指向父节点
            index = parentIndex;
        }*/

        // 将新添加的节点备份，确定了最终位置才摆放上去
        // 时间复杂度从 3logn -->logn + 1
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 2; // 向下取整
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;

            // 将父元素存储在index位置
            elements[index] = parent;

            index = parentIndex;
        }

        // index就是要放的位置
        elements[index] = element;
    }

    private void swap(int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element must not be null");
        }
    }


    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
