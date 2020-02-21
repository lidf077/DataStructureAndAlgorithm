package com.dongfang.dsa.algorithm.unionfind;

/**
 * 并查集 Union Find
 * 	1、并查集也叫做不相交集合（Disjoint Set）
 * 	2、并查集有2个核心操作
 * 		1、查找（Find）查找元素所在的集合（这里的集合并不是特指set这种数据结构，是指广义的数据集合）
 * 		2、合并（Union）将两个元素所在的集合合并成为一个集合
 * 	3、有2种常见的实现思路
 * 		Quick Find 查找很快，合并很慢
 * 			查找的时间复杂度O(1)
 * 			合并的时间复杂度为O(n)
 * 		Quick Union
 * 			查找 的时间复杂度为O(nlogn)可以优化
 * 			合并的时间复杂度为O(nlong)可以优化
 */
public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be >= 1");

        // 初始化，每一个元素是一个单独的集合
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            // 每一个节点的父节点是它自己
            parents[i] = i;
        }
    }

    public abstract int find(int v);

    public abstract void union(int v1, int v2);

    /**
     * 检查v1 v2是否属于同一个集合
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) throw new IllegalArgumentException("V is out of bounds");
    }
}
