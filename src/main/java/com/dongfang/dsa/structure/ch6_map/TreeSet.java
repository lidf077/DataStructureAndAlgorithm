package com.dongfang.dsa.structure.ch6_map;

import com.dongfang.dsa.structure.Visitor;
import com.dongfang.dsa.structure.ch5_set.Set;

/**
 * 将map的value去掉，只要key，就是一个set
 * key是不重复的
 * <p>
 * map的所有key组合在一起，就是一个set, map的key就是set
 * <p>
 * 因此set可以间接利用map来作为内部实现
 *
 * @param <E>
 */
public class TreeSet<E> implements Set<E> {
    private Map<E, Object> treeSet;

    public TreeSet() {
        treeSet = new TreeMap<>();
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
        return treeSet.containsKey(element);
    }

    @Override
    public void add(E element) {
        // value是什么不重要，key能加进去就可以了
        treeSet.put(element, null);
    }

    @Override
    public void remove(E element) {
        treeSet.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        treeSet.traversal(new Map.Visitor<E, Object>() {
            @Override
            public boolean visit(E key, Object value) {
                return visitor.visit(key);
            }
        });
    }
}
