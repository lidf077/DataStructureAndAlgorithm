package com.dongfang.dsa.algorithm.unionfind;

/**
 * 简介：
 *      -- 在一些有N个元素的集合应用问题中，通常是开始时让每个元素构成一个单元素的集合，
 *          然后按一定的顺序将属于同一组的元素所在的集合合并，其间要反复查找一个元素在哪个集合中，
 *          顾名思义就是有 合并集合 和 查找集合 两种操作的关于数据结构的一种算法
 *
 * 性质：
 *       -- 并查集并不支持分割一个集合
 *
 * 算法思想：
 *      -- 用集合中的某个元素来代表这个集合，该元素称为些集合的代表元
 *          一个集合内的所有元素组织万能代表元为根的树形结构
 *          对于每一个元素parent[x]指向x在树形结构上的父亲节点，如果x是根节点，则令parent[x] = x
 *          对于查找操作，假设需要确定x所在的集合，也就是确定集合的代表元，可以沿着parent[x]不断在树形结构中向上移动，
 *          直到到达根节点
 *
 *          判断两个元素是否属于同一个集合，只需要看他们的代表元是否相同即可
 *
 *          查询两个元素之间是否有连接的路，就是查询是否在同一个集合
 *          连接两个元素，就是合并两个元素所在的集合
 *
 * 并查集 Union Find
 *     1、并查集也叫做不相交集合（Disjoint Set）
 *     2、并查集有2个核心操作
 *         1、查找（Find）查找元素所在的集合（这里的集合并不是特指set这种数据结构，是指广义的数据集合）
 *         2、合并（Union）将两个元素所在的集合合并成为一个集合
 *     3、有2种常见的实现思路
 *         Quick Find 查找很快，合并很慢
 *             查找的时间复杂度O(1)
 *             合并的时间复杂度为O(n)
 *         Quick Union
 *             查找 的时间复杂度为O(logn)可以优化
 *             合并的时间复杂度为O(logn)可以优化
 */
public abstract class UnionFind {
    /**
     * 并查集处理整数
     *      0 1 2 3 4 5 6 7  代表0-7的8个村庄的编号
     *      1 1 2 1 5 6 6 6  代表村庄属于哪个集合
     *      6号村庄属于6号集合 0 1 3号村庄属于同一集合
     *      5 6 7号村庄属于同一集合，之间有相连的路
     *
     *      parent[i]是i号村庄所在的集合，也可以看作是i的父节点
     *
     *      是否属于同一个集合，查看根节点
     *
     *      因此，并查集是可以用数组实现的树形结构
     */
    protected int[] parents;

    /**
     * 初始化时，每个元素各自属于一个单元素的集合
     * @param capacity
     */
    public UnionFind(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be >= 1");

        // 初始化，每一个元素是一个单独的集合
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            // 每一个节点的父节点是它自己，单独成一个集合了
            parents[i] = i;
        }
    }

    /**
     * 查找V所属的集合（返回它的根节点）
     *
     * @param v 必须是数组的索引内
     * @return
     */
    public abstract int find(int v);

    /**
     * 其中一方嫁接到另一方
     * 合并v1 v2所在的集合
     *
     * @param v1
     * @param v2
     */
    public abstract void union(int v1, int v2);

    /**
     * 检查v1 v2是否属于同一个集合
     * 根节点相同，就是同一个集合
     * 两个元素之间是否有路相连
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        // 两个元素的根节点一样，有路连接
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) throw new IllegalArgumentException("V is out of bounds");
    }
}