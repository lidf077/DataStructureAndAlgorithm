package com.dongfang.dsa.structure.ch6_map;

/**
 * Map在有些编程语言中也叫做字典，dictionary
 *
 * Map的每一个key都是唯一的
 *
 *      类似Set，Map可以直接利用之前学习的链表，二叉搜索树（AVL树，红黑树）等数据结构来实现
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {
    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V> {
        boolean isStop;

        public abstract boolean visit(K key, V value);
    }
}
