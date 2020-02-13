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

    @Test
    public void testAvlTree() {
        int[] data = {90, 36, 46, 94, 47, 45, 71, 48, 43, 64, 9, 44, 21, 70, 30, 40, 56, 27, 1, 77};
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int datum : data) {
            avlTree.add(datum);
        }
        BinaryTrees.println(avlTree);

        /**
         *            ┌───90───┐
         *            │        │
         *   ┌───────36──────┐ 94
         *   │               │
         * ┌─9─┐           ┌─46─┐
         * │   │           │    │
         * 1   21─┐     ┌─45    47─┐
         *        │     │          │
         *      ┌─30 ┌─43─┐      ┌─71─┐
         *      │    │    │      │    │
         *     27   40    44    48─┐  77
         *                         │
         *                       ┌─64─┐
         *                       │    │
         *                      56    70
         */

        /**
         *            ┌─────────46────────┐
         *            │                   │
         *      ┌────30───┐          ┌────71───┐
         *      │         │          │         │
         *   ┌─21─┐   ┌───43──┐   ┌─48─┐     ┌─90─┐
         *   │    │   │       │   │    │     │    │
         * ┌─9    27 36─┐   ┌─45 47  ┌─64─┐ 77    94
         * │            │   │        │    │
         * 1            40 44       56    70
         */
    }
}
