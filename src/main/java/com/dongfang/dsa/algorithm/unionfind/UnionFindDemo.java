package com.dongfang.dsa.algorithm.unionfind;

import org.junit.Test;

public class UnionFindDemo {
    @Test
    public void testQuickFind() {
        UnionFind quickFind = new UnionFindByQuickFind(12);
        quickFind.union(0, 1);
        quickFind.union(0, 3);
        quickFind.union(0, 4);
        quickFind.union(2, 3);
        quickFind.union(2, 5);

        quickFind.union(6, 7);

        quickFind.union(8, 10);
        quickFind.union(9, 10);
        quickFind.union(9, 11);

        System.out.println("quickFind.isSame(0, 6) = " + quickFind.isSame(0, 6));
        System.out.println("quickFind.isSame(0, 5) = " + quickFind.isSame(0, 5));

        quickFind.union(4, 6);
        System.out.println("quickFind.isSame(2, 7) = " + quickFind.isSame(2, 7));

        System.out.println("quickFind.find(7) = " + quickFind.find(7));
    }

    @Test
    public void testQuickUnion() {
        UnionFind quickUnion = new UnionFindByQuickUnion(12);
        quickUnion.union(0, 1);
        quickUnion.union(0, 3);
        quickUnion.union(0, 4);
        quickUnion.union(2, 3);
        quickUnion.union(2, 5);

        quickUnion.union(6, 7);

        quickUnion.union(8, 10);
        quickUnion.union(9, 10);
        quickUnion.union(9, 11);

        System.out.println("quickUnion.isSame(0, 6) = " + quickUnion.isSame(0, 6));
        System.out.println("quickUnion.isSame(0, 5) = " + quickUnion.isSame(0, 5));

        quickUnion.union(4, 6);
        System.out.println("quickUnion.isSame(2, 7) = " + quickUnion.isSame(2, 7));

        System.out.println("quickUnion.find(7) = " + quickUnion.find(7));
    }

    @Test
    public void testQuickUnionOptSize() {
        UnionFind quickUnionOptSize = new UnionFindByQuickUnionOptSize(12);
        quickUnionOptSize.union(0, 1);
        quickUnionOptSize.union(0, 3);
        quickUnionOptSize.union(0, 4);
        quickUnionOptSize.union(2, 3);
        quickUnionOptSize.union(2, 5);

        quickUnionOptSize.union(6, 7);

        quickUnionOptSize.union(8, 10);
        quickUnionOptSize.union(9, 10);
        quickUnionOptSize.union(9, 11);

        System.out.println("quickUnionOptSize.isSame(0, 6) = " + quickUnionOptSize.isSame(0, 6));
        System.out.println("quickUnionOptSize.isSame(0, 5) = " + quickUnionOptSize.isSame(0, 5));

        quickUnionOptSize.union(4, 6);
        System.out.println("quickUnionOptSize.isSame(2, 7) = " + quickUnionOptSize.isSame(2, 7));

        System.out.println("quickUnionOptSize.find(7) = " + quickUnionOptSize.find(7));
    }

    @Test
    public void testPathCompressUnionFind() {
        UnionFind quickUnionOptRankAndPathCompression = new UnionFindByQuickUnionOptRankAndPathHalving(12);
        quickUnionOptRankAndPathCompression.union(0, 1);
        quickUnionOptRankAndPathCompression.union(0, 3);
        quickUnionOptRankAndPathCompression.union(0, 4);
        quickUnionOptRankAndPathCompression.union(2, 3);
        quickUnionOptRankAndPathCompression.union(2, 5);

        quickUnionOptRankAndPathCompression.union(6, 7);

        quickUnionOptRankAndPathCompression.union(8, 10);
        quickUnionOptRankAndPathCompression.union(9, 10);
        quickUnionOptRankAndPathCompression.union(9, 11);

        System.out.println("quickUnionOptRankAndPathCompression.isSame(0, 6) = " + quickUnionOptRankAndPathCompression.isSame(0, 6));
        System.out.println("quickUnionOptRankAndPathCompression.isSame(0, 5) = " + quickUnionOptRankAndPathCompression.isSame(0, 5));

        quickUnionOptRankAndPathCompression.union(4, 6);
        System.out.println("quickUnionOptRankAndPathCompression.isSame(2, 7) = " + quickUnionOptRankAndPathCompression.isSame(2, 7));

        System.out.println("quickUnionOptRankAndPathCompression.find(7) = " + quickUnionOptRankAndPathCompression.find(7));
    }
}