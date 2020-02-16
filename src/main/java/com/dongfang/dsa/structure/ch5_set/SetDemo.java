package com.dongfang.dsa.structure.ch5_set;

import org.junit.Test;

public class SetDemo {
    @Test
    public void testListSet() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(1);
        listSet.add(2);
        listSet.add(1);
        listSet.add(3);
        listSet.add(2);
        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println("element = " + element);
                return false;
            }
        });
    }
}
