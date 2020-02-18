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
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
    private E[] elements;
    private int size;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 循环指行以下操作
     * 1、如果node > 父节点，与父节点交换位置
     * 2、如果node <= 父节点，或者没有父节点，退出循环
     * 这个过程叫做上滤 sift up
     * 时间复杂度 O(logn)
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

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E replace(E element) {
        return null;
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


    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
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
