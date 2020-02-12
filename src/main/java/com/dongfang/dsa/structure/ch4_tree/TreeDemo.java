package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTrees;
import org.junit.Test;

import java.util.Comparator;

public class TreeDemo {
    private static class Person {
        private int age;
        private double height;
    }

    @Test
    public void testComparator() {
        BST<Person> bst = new BST<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });

        // 为每个bst定制了比较器
        BST<Person> bst1 = new BST<>((o1, o2) -> (int) (o1.height - o2.height));

        BST<Integer> bst2 = new BST<>();
    }

    @Test
    public void testAdd() {
        int[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BST<Integer> bst = new BST<>();
        for (int datum : data) {
            bst.add(datum);
        }
        BinaryTrees.println(bst);
    }

    @Test
    public void testTraversal() {
        int[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1, 0};
        BST<Integer> bst = new BST<>();
        for (int datum : data) {
            bst.add(datum);
        }

        BinaryTrees.println(bst);
/*        bst.levelOrderTraversal(new Visitor<Integer>() {
            boolean visit(Integer element) {
                return false;
            }
        });*/
        System.out.println(bst.toString());
    }

    @Test
    public void testRemoveFromBst() {
        int[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BST<Integer> bst = new BST<>();
        for (int datum : data) {
            bst.add(datum);
        }

        BinaryTrees.println(bst);
        bst.remove(7);
        BinaryTrees.println(bst);
        System.out.println("bst.contains(11) = " + bst.contains(11));
        System.out.println("bst.contains(7) = " + bst.contains(7));
    }
}
