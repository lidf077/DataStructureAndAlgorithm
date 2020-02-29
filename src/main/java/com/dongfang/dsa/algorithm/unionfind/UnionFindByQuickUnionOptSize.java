package com.dongfang.dsa.algorithm.unionfind;

import java.util.Arrays;

/**
 * 在union的过程中，可能会出现树不平衡的情况，甚至退化成链表
 *      有2种常见的优化方案
 *          1、基于size的优化，元素少的树，嫁接到元素多的树
 *          2、基于rank的优化，矮的树 嫁接到 高的树
 */
public class UnionFindByQuickUnionOptSize extends UnionFind {
    // 以i为根节点的那棵树，有多少根节点
    private int[] sizes;
    public UnionFindByQuickUnionOptSize(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

    /**
     * 通过parent链条不断向上找
     *
     * 返回所在集合的根节点
     * @param v 必须是数组的索引内
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        // 这值不等于父节点，一直沿着树往上找
        // 循环退出时，v = 父节点
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 将 v1 的根节点，嫁接到 v2 的根节点上，
     *    v1 的根节点 成为 v2 的子节点
     *
     * 将v1的根节点的父节点，改成v2的根节点
     * @param v1
     * @param v2
     */
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;

        if (sizes[p1] < sizes[p2]) { // p1的元素数量比p2少
            parents[p1] = p2;
            // 嫁接后，元素数量要做相应修改
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}