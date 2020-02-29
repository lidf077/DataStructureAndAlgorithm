package com.dongfang.dsa.algorithm.unionfind;

import java.util.Arrays;

/**
 * 基于size的优化也有缺点，也会导致树的高度不平衡，因为是基于节点数目优化的
 * rank是基于树的高度做优化，树会更加平衡
 */
public class UnionFindByQuickUnionOptRank extends UnionFindByQuickUnion {
    private int[] ranks;
    public UnionFindByQuickUnionOptRank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
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
        if (ranks[p1] < ranks[p2]) { // 基于树高做优化，矮的嫁接到高的
            parents[p1] = p2;
            // 高度没有变化，所以不更新高度
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            // 两棵树的高度一样，谁嫁接谁都可以
            parents[p1] = p2;
            ranks[p2]++; // 要对被嫁接的树的高度进行更新
        }
    }
}