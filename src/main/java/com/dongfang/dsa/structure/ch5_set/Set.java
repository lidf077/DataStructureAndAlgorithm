package com.dongfang.dsa.structure.ch5_set;

import com.dongfang.dsa.structure.Visitor;

/**
 * 集合的特点：
 *      不能存放重复元素
 *      常用于去重
 *          存放新增ip，统计新增ip量
 *          存放词汇，统计词汇量
 *
 *      思考：
 *          集合的内部实现能否直接利用以前学过的数据结构
 *              动态数组
 *              链表
 *              二叉搜索树（AVL树，红黑树）
 * @param <E>
 */
public interface Set<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);


}
