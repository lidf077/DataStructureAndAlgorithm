package com.dongfang.dsa.structure.ch1_list;

public class ArrayList {
    // 元素的数量
    private int size;

    // 存放所有的元素
    private int[] elements;

    // 不指定初始长度时的默认长度
    private static final int DEFAULT_CAPACITY = 10;

    // ArrayList中找不到元素
    private static final int ELEMENT_NOT_FOUNT = -1;

    public ArrayList(int capacity) {
        // capacity 可能为负数，因此要对这个异常边界进行处理
        //capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = new int[capacity];
    }

    public ArrayList() {
        // 构造函数相互调用，使用this(arg)
        this(DEFAULT_CAPACITY);
    }


    /**
     * 元素的数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 清除所有的元素
     */
    public void clear() {
        // 清空的原则由自己把握，可以对elements进行缩小
        size = 0;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(int element) {
        // 如果元素的索引存在，则包含
        return indexOf(element) != ELEMENT_NOT_FOUNT;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(int element) {
/*        elements[size] = element;
        size++;*/

        // 先使用size，再++
        elements[size++] = element;
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            // 客户端有错误，就抛出异常，让客户端知道
            // 如果直接返回一个0，不好，有错误的地方，就抛出异常，利于定位问题
            // 给出友善的提示信息
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return elements[index];
    }

    /**
     * 设置index位置的元素为新值element，返回旧值
     *
     * @param index
     * @param element
     * @return
     */
    public int set(int index, int element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }

        int old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 删除index位置的元素，并返回
     *
     * @param index
     * @return
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        // [index + 1, size - 1] -> [index, size -2]
        // 把这个区间的元素 移动到后面的区间
        int old = elements[index];
        for (int i = index + 1; i <= size - 1; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return old;
    }

    /**
     * 查看元素element的索引
     *
     * @param element
     * @return
     */
    public int indexOf(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) return i;
        }
        // 找不到就返回-1
        return ELEMENT_NOT_FOUNT;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            res.append(elements[i]);
            if (i != (size - 1)) res.append(", ");
        }
        res.append("]");
        return res.toString();
    }
}
