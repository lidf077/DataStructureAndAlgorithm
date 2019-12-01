package com.dongfang.dsa.structure.ch1_list;

@SuppressWarnings("unchecked")
public class ArrayList<E> {
    // 元素的数量，已经存在的元素的index为【0, size-1]
    private int size;

    // 存放所有的元素
    private E[] elements;

    // 对象数组，其中存放的是对象的地址值，也就是引用值，数组中存放的是对象的地址值
    // Object对象可以指向任何对象的引用，因此new Object[] 数组中可以存放任何对象的引用
    // 存放地址可以节省空间

    // 不指定初始长度时的默认长度
    private static final int DEFAULT_CAPACITY = 10;

    // ArrayList中找不到元素
    private static final int ELEMENT_NOT_FOUNT = -1;

    public ArrayList(int capacity) {
        // capacity 可能为负数，因此要对这个异常边界进行处理
        //capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        capacity = Math.max(capacity, DEFAULT_CAPACITY);

        // 所有类都继承java.lang.Object
        //elements = new E[capacity];
        elements = (E[]) new Object[capacity];
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
        // 如果不设为null，对象还是存在于内存中
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
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
    public boolean contains(E element) {
        // 如果元素的索引存在，则包含
        return indexOf(element) != ELEMENT_NOT_FOUNT;
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(E element) {
/*        elements[size] = element;
        size++;*/

        // 先使用size，再++
        // elements[size++] = element;
        // 直接将元素添加到最后面
        add(size, element);
    }

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
/*        if (index < 0 || index >= size) {
            // 客户端有错误，就抛出异常，让客户端知道
            // 如果直接返回一个0，不好，有错误的地方，就抛出异常，利于定位问题
            // 给出友善的提示信息
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }*/
        rangeCheck(index);

        return elements[index];
    }

    /**
     * 设置index位置的元素为新值element，返回旧值
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        // index = size时，在末尾插入，因此改成index > size
/*        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }*/

        rangeCheckForAdd(index);
        ensureCapacity(index + 1);

        // [size - 1, index] -> [size, index + 1] 移动区间
//        for (int i = size - 1; i >= index; i--) {
//            elements[i + 1] = elements[i];
//        }
        // 只要能减少运算的地方，就优化，从后往前挪
        for (int i = size; i > index; i++) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 删除index位置的元素，并返回
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);
        // [index + 1, size - 1] -> [index, size -2]
        // 把这个区间的元素 移动到前面的区间
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
//        size--;
//        elements[size] = null;
        elements[--size] = null;
        return old;
    }

    public void remove(E element) {
        remove(indexOf(element));
    }

    /**
     * 查看元素element的索引
     *
     * @param element
     * @return
     */
    public int indexOf(E element) {
        // 如果是null，找到第一个null，返回位置就好
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            // 不为null，element调用方法肯定不会空指针异常
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
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

    // [0, size-1]区间的检查
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    // [0, size]的检查
    private void rangeCheckForAdd(int index) {
        // 插入时允许在末尾插入
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    }

    /**
     * 保证要有capacity的容量
     *
     * @param capacity
     */
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
}
