package com.dongfang.dsa.structure.ch1_list;


/**
 * 复杂度分析，只分析增 删 改 查 的复杂度
 *      最好情况复杂度
 *      最坏情况复杂度
 *      平均情况复杂度
 *           操作                         ArrayList       LinkedList
 *           增add(E element)
 *           删remove(index)
 *           改set(index,element)
 *          查 get(index)                 O(1)             O(N)
 */

/**
 * 只定义提供给外界的方法
 * @param <E>
 */
public interface List<E> {
    static final int ELEMENT_NOT_FOUNT = -1;

    /**
     * 元素的数量
     *
     * @return
     */
    int size();

    /**
     * 清除所有的元素
     */
    void clear();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    boolean contains(E element);

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    void add(E element);

    /**
     * 获取index位置的元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 设置index位置的元素为新值element，返回旧值
     *
     * @param index
     * @param element
     * @return
     */
    E set(int index, E element);

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    void add(int index, E element);

    /**
     * 删除index位置的元素，并返回
     *
     * @param index
     * @return
     */
    E remove(int index);

    void remove(E element);

    /**
     * 查看元素element的索引
     *
     * @param element
     * @return
     */
    int indexOf(E element);
}
