package com.dongfang.dsa.algorithm.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GenericUnionFind<V> {
    private Map<V, Node<V>> nodes = new HashMap<>();

    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<>(v));
    }


    /**
     * 返回根节点，节点对象不对外开放，所以返回节点的值
     */
    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) return;

        if (Objects.equals(p1.value, p2.value)) return;

        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank++;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    /**
     * 找到 v 的根节点
     *
     * @param v
     * @return
     */
    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) return null;
        // 使用路径减半方法，每隔一个节点，指向它的祖父节点
        // 比较节点上的对象是不是相等
        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }


    private static class Node<V> {
        V value;
        Node<V> parent = this; // 初始时，parent 指向自己
        int rank = 1; // 以些节点为根的树的高度

        public Node(V value) {
            this.value = value;
        }
    }
}
