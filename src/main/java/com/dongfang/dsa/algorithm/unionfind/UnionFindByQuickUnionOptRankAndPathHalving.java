package com.dongfang.dsa.algorithm.unionfind;

/**
 * 基于树的高度优化后，树会相对平衡一些
 *      但是随着union的次数增多，树的高度会越来越高
 *      导致find操作变慢，尤其是底层节点，因为find是不断向上找的
 *
 *		还有2种更优的做法，不但能降低树高，实现成本也比路径压缩低
 * 			路径分裂
 * 			路径减半
 *
 * 		路径分裂、路径减半的效率差不多，但都比路径压缩要好
 *
 * 		路径减半：使路径上每隔一个节点就指向其祖父节点 Path halving
 *
 * 			使用路径压缩，分裂或减半 + 基于ranke或者size的优化
 * 		可以确保每个操作的均摊时间复杂度 < 5
 *
 */
public class UnionFindByQuickUnionOptRankAndPathHalving extends UnionFindByQuickUnionOptRank {

    public UnionFindByQuickUnionOptRankAndPathHalving(int capacity) {
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

        // 每隔一个节点，指向它的祖父节点
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            // v的父节点已经更改为祖父节点
            v = parents[v];
        }
        return v;
    }
}