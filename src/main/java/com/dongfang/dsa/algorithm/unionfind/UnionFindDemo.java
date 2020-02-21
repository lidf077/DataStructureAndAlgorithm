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
}
