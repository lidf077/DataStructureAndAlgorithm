package com.dongfang.dsa.algorithm.unionfind;

public class UnionFindByQuickFind extends UnionFind {
    public UnionFindByQuickFind(int capacity) {
        super(capacity);
    }


    /**
     * 查找V所属的集合（根节点）
     *
     * @param v 必须是数组的索引内
     * @return
     */
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 合并两个集合，将v1的父节点改成v2的父节点
     * 合并v1 v2所在的集合
     *
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }
}
