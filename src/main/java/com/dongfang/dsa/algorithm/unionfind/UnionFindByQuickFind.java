package com.dongfang.dsa.algorithm.unionfind;

public class UnionFindByQuickFind extends UnionFind {
    public UnionFindByQuickFind(int capacity) {
        super(capacity);
    }


    /**
     *父节点就是根节点
     * @param v 必须是数组的索引内
     * @return
     */
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 每个节点，向上找一次就找到父节点
     * 将 v1的父节点改成v2的父节点，因为之前并查集中会有元素与v1属于同一个集合
     *
     *  将 v1 所在集合的所有元素的父节点嫁接到 v2 的父节点上
     * 树的高度最高是2
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        // 找到v1 v2的父节点
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        // 遍历并查集，发现有元素的父节点与v1相同的，都改成v2的父节点
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }
}