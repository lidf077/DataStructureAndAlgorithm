package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTrees;
import com.dongfang.dsa.structure.ch4_tree.BinarySearchTree.Visitor;
import org.junit.Test;

import java.util.Comparator;

public class TreeDemo {
    private static class Person {
        private int age;
        private double height;
    }

    @Test
    public void testComparator() {
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });

        // 为每个bst定制了比较器
        BinarySearchTree<Person> bst1 = new BinarySearchTree<>((o1, o2) -> (int) (o1.height - o2.height));

        BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
    }

    @Test
    public void testAdd() {
        int[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int datum : data) {
            bst.add(datum);
        }
        BinaryTrees.println(bst);
    }

    @Test
    public void testTraversal() {
        int[] data = {7, 4, 9, 2, 5, 8, 11, 3, 12, 1, 0};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
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
}
