package com.dongfang.dsa.algorithm.unionfind;

/**
 * 基于树的高度优化后，树会相对平衡一些
 *      但是随着union的次数增多，树的高度会越来越高
 *      导致find操作变慢，尤其是底层节点，因为find是不断向上找的
 *
 *      路径压缩
 *          1、在find的时候，使路径上的所有节点都指向根节点，从而降低树的高度
 *          2、树的高度很小了
 *
 *
 *          基于rank的优化，
 *          再做路径压缩
 *
 */
public class UnionFindByQuickUnionOptRankAndPathCompression extends UnionFindByQuickUnionOptRank {

    public UnionFindByQuickUnionOptRankAndPathCompression(int capacity) {
        super(capacity);


    }

    /**
     * 路径上每一个节点的父节点
     * @param v 必须是数组的索引内
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        while (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}