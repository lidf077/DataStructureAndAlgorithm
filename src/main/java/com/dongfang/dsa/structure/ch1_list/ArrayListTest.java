package com.dongfang.dsa.structure.ch1_list;

import org.junit.jupiter.api.Test;

public class ArrayListTest {
    @Test
    public void testIndexOutOfBound() {
        ArrayList arrayList = new ArrayList(100);
        // java.lang.IndexOutOfBoundsException: index: -4, size: 0
        int res = arrayList.get(-4);
    }

    @Test
    public void testArrayListAdd() {
        ArrayList list = new ArrayList();
        list.add(33);
        list.add(99);
        System.out.println("list = " + list);
    }
}
