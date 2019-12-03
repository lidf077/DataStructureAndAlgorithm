package com.dongfang.dsa.structure.ch1_list;

/**
 * abstract不用实现接口中的所有方法
 * 抽象类无法实例化
 * 不对外公开，只是抽取公共代码
 * @param <E>
 */
public abstract class AbstractList<E> implements List<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUNT;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    // [0, size-1]区间的检查
    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    // [0, size]的检查
    protected void rangeCheckForAdd(int index) {
        // 插入时允许在末尾插入
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    }

}
