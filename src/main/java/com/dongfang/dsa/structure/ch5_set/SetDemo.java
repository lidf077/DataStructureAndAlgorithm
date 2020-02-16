package com.dongfang.dsa.structure.ch5_set;

import com.dongfang.dsa.structure.Visitor;
import org.junit.Test;

import java.text.NumberFormat;

public class SetDemo {
    @Test
    public void testListSet() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(1);
        listSet.add(2);
        listSet.add(1);
        listSet.add(3);
        listSet.add(2);
        listSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println("element = " + element);
                return false;
            }
        });
    }

    @Test
    public void testTreeSet() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println("element = " + element);
                return false;
            }
        });
    }
    @Test
    public void testTreeSetPerformance() {
        Set<Integer> treeSet = new TreeSet<>();
        int num = 1_000_000;
        for (int i = 0; i < num; i++) {
            treeSet.add(i);
        }

        for (int i = 0; i < num; i++) {
            treeSet.remove(i);
        }

    }
}
