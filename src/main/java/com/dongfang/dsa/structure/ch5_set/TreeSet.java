package com.dongfang.dsa.structure.ch5_set;

import com.dongfang.dsa.structure.Visitor;
import com.dongfang.dsa.structure.ch4_tree.RBTree;
import com.sun.source.util.Trees;

import java.util.Comparator;

/**
 * 底层使用红黑树实现集合
 *      元素必须有可比较性
 * @param <E>
 */
public class TreeSet<E> implements Set<E> {
    private RBTree<E> treeSet;

    public TreeSet() {
        this(null);
    }

    // 元素必须有可比较性
    public TreeSet(Comparator<E> comparator) {
        treeSet = new RBTree<>(comparator);
    }

    @Override
    public int size() {
        return treeSet.size();
    }

    @Override
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    @Override
    public void clear() {
        treeSet.clear();
    }

    @Override
    public boolean contains(E element) {
        return treeSet.contains(element);
    }

    @Override
    public void add(E element) {
        treeSet.add(element);
    }

    @Override
    public void remove(E element) {
        treeSet.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        // 前序 后序遍历对树有意义，对集合来说没有意义，因此用中序遍历，从小到大输出
        treeSet.InOrderTraversalByIterator(visitor);
    }
}
