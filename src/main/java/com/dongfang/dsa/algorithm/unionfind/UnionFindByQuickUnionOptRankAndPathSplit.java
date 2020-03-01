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
 * 		路径分裂：使路径上的每个节点都指向其祖父节点（parent的parent)
 *
 */
public class UnionFindByQuickUnionOptRankAndPathSplit extends UnionFindByQuickUnionOptRank {

    public UnionFindByQuickUnionOptRankAndPathSplit(int capacity) {
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

        while (v != parents[v]) {
            // 让v指向它的祖父节点
            // 将v的parent保留下来，循环一直往下走，直到根节点
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}