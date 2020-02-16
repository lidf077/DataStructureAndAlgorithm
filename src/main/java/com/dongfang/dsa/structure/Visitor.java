package com.dongfang.dsa.structure;

/**
 * 动态数组和链表和索引，可以直接通过for循环遍历
 *
 * @param <E>
 */
public abstract class Visitor<E> {
    public boolean isStop;

    public abstract boolean visit(E element);
}