package com.dongfang.dsa.structure.ch4_tree;

import com.dongfang.dsa.structure.ch4_tree.printer.BinaryTrees;
import org.junit.Test;

import java.awt.image.DataBufferUShort;
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
    /**
     * datum = 90
     * 90
     * -----------------------------------------------------
     * datum = 36
     *  ┌─90
     *  │
     * 36
     * -----------------------------------------------------
     * datum = 46
     *  ┌─46─┐
     *  │    │
     * 36    90
     * -----------------------------------------------------
     * datum = 94
     *  ┌─46─┐
     *  │    │
     * 36    90─┐
     *          │
     *          94
     * -----------------------------------------------------
     * datum = 47
     *  ┌─46─┐
     *  │    │
     * 36  ┌─90─┐
     *     │    │
     *    47    94
     * -----------------------------------------------------
     * datum = 45
     *  ┌───46──┐
     *  │       │
     * 36─┐   ┌─90─┐
     *    │   │    │
     *    45 47    94
     * -----------------------------------------------------
     * datum = 71
     *  ┌───46──┐
     *  │       │
     * 36─┐   ┌─90─┐
     *    │   │    │
     *    45 47─┐  94
     *          │
     *          71
     * -----------------------------------------------------
     * datum = 48
     *  ┌────46───┐
     *  │         │
     * 36─┐     ┌─90─┐
     *    │     │    │
     *    45 ┌─48─┐  94
     *       │    │
     *      47    71
     * -----------------------------------------------------
     * datum = 43
     *     ┌────46───┐
     *     │         │
     *  ┌─43─┐     ┌─90─┐
     *  │    │     │    │
     * 36    45 ┌─48─┐  94
     *          │    │
     *         47    71
     * -----------------------------------------------------
     * datum = 64
     *     ┌────46───┐
     *     │         │
     *  ┌─43─┐     ┌─71─┐
     *  │    │     │    │
     * 36    45 ┌─48─┐  90─┐
     *          │    │     │
     *         47    64    94
     * -----------------------------------------------------
     * datum = 9
     *       ┌────46───┐
     *       │         │
     *    ┌─43─┐     ┌─71─┐
     *    │    │     │    │
     * ┌─36    45 ┌─48─┐  90─┐
     * │          │    │     │
     * 9         47    64    94
     * -----------------------------------------------------
     * datum = 44
     *       ┌────46───┐
     *       │         │
     *    ┌─43─┐     ┌─71─┐
     *    │    │     │    │
     * ┌─36  ┌─45 ┌─48─┐  90─┐
     * │     │    │    │     │
     * 9    44   47    64    94
     * -----------------------------------------------------
     * datum = 21
     *         ┌────46────┐
     *         │          │
     *    ┌───43──┐     ┌─71─┐
     *    │       │     │    │
     * ┌─21─┐   ┌─45 ┌─48─┐  90─┐
     * │    │   │    │    │     │
     * 9    36 44   47    64    94
     * -----------------------------------------------------
     * datum = 70
     *         ┌────46────┐
     *         │          │
     *    ┌───43──┐     ┌─71─┐
     *    │       │     │    │
     * ┌─21─┐   ┌─45 ┌─48─┐  90─┐
     * │    │   │    │    │     │
     * 9    36 44   47    64─┐  94
     *                       │
     *                       70
     * -----------------------------------------------------
     * datum = 30
     *         ┌────46────┐
     *         │          │
     *    ┌───43──┐     ┌─71─┐
     *    │       │     │    │
     * ┌─21─┐   ┌─45 ┌─48─┐  90─┐
     * │    │   │    │    │     │
     * 9  ┌─36 44   47    64─┐  94
     *    │                  │
     *   30                  70
     * -----------------------------------------------------
     * datum = 40
     *          ┌─────46────┐
     *          │           │
     *    ┌────43───┐     ┌─71─┐
     *    │         │     │    │
     * ┌─21─┐     ┌─45 ┌─48─┐  90─┐
     * │    │     │    │    │     │
     * 9  ┌─36─┐ 44   47    64─┐  94
     *    │    │               │
     *   30    40              70
     * -----------------------------------------------------
     * datum = 56
     *          ┌─────46────┐
     *          │           │
     *    ┌────43───┐     ┌─71─┐
     *    │         │     │    │
     * ┌─21─┐     ┌─45 ┌─48─┐  90─┐
     * │    │     │    │    │     │
     * 9  ┌─36─┐ 44   47  ┌─64─┐  94
     *    │    │          │    │
     *   30    40        56    70
     * -----------------------------------------------------
     * datum = 27
     *             ┌─────46────┐
     *             │           │
     *       ┌────43───┐     ┌─71─┐
     *       │         │     │    │
     *    ┌─30─┐     ┌─45 ┌─48─┐  90─┐
     *    │    │     │    │    │     │
     * ┌─21─┐  36─┐ 44   47  ┌─64─┐  94
     * │    │     │          │    │
     * 9    27    40        56    70
     * -----------------------------------------------------
     * datum = 1
     *            ┌───────46───────┐
     *            │                │
     *      ┌────30───┐          ┌─71─┐
     *      │         │          │    │
     *   ┌─21─┐   ┌───43──┐   ┌─48─┐  90─┐
     *   │    │   │       │   │    │     │
     * ┌─9    27 36─┐   ┌─45 47  ┌─64─┐  94
     * │            │   │        │    │
     * 1            40 44       56    70
     * -----------------------------------------------------
     * datum = 77
     *            ┌─────────46────────┐
     *            │                   │
     *      ┌────30───┐          ┌────71───┐
     *      │         │          │         │
     *   ┌─21─┐   ┌───43──┐   ┌─48─┐     ┌─90─┐
     *   │    │   │       │   │    │     │    │
     * ┌─9    27 36─┐   ┌─45 47  ┌─64─┐ 77    94
     * │            │   │        │    │
     * 1            40 44       56    70
     * -----------------------------------------------------
     */
    @Test
    public void testAvlTree() {
        // 1, 9, 21, 27, 30, 36, 40, 43, 44, 45, 46, 47, 48, 56, 64, 70, 71, 77, 90, 94
        int[] data = {90, 36, 46, 94, 47, 45, 71, 48, 43, 64, 9, 44, 21, 70, 30, 40, 56, 27, 1, 77};
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int datum : data) {
            avlTree.add(datum);
        }
        BinaryTrees.println(avlTree);
//        avlTree.remove(64);
//        avlTree.remove(1);
//        avlTree.remove(9);
//        avlTree.remove(71);
//        avlTree.remove(46);
//        avlTree.remove(27);
        for (int datum : data) {
            avlTree.remove(datum);
            BinaryTrees.println(avlTree);
            System.out.println("---------------------------------------------");
        }
        BinaryTrees.println(avlTree);

    }

    /**
     * add time is 1.699
     * remove time is 1.795
     */
    @Test
    public void testAvlTreePerformance() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        int num = 10_000_000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            avlTree.add(i);
        }
        long mid = System.currentTimeMillis();
        System.out.println("add time is " + (mid - start) / 1000.);
//        BinaryTrees.println(avlTree);
        for (int i = 0; i < num; i++) {
            avlTree.remove(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("remove time is " + (end - mid) / 1000.);
    }

    @Test
    public void testBSTPerformance() {
        BST<Integer> bst = new BST<>();

        int num = 1_000_000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            bst.add(i);
        }
        long mid = System.currentTimeMillis();
        System.out.println("add time is " + (mid - start) / 1000);
//        BinaryTrees.println(avlTree);
        for (int i = 0; i < num; i++) {
            bst.remove(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("time is " + (end - start) / 1000.);
    }

    @Test
    public void testRedBlackTree() {
        RBTree<Integer> rbTree = new RBTree<>();
//        int[] data = {89, 85, 75, 99, 10, 55, 3, 92, 35, 91, 68, 12, 60};
//        for (int datum : data) {
//            rbTree.add(datum);
//            BinaryTrees.println(rbTree);
//            System.out.println("------------------------------------");
//        }


        for (int i = 0; i <= 20; i++) {
            rbTree.add(i);
        }
        BinaryTrees.println(rbTree);
        for (int i = 20; i >= 0; i++) {
            rbTree.remove(i);
            System.out.println("----------------------------------");
            BinaryTrees.println(rbTree);
        }


    }

    /**
     * add time is 2.148
     * remove time is 0.749
     */
    @Test
    public void testRedBlackTreePerformance() {
        RBTree<Integer> rbTree = new RBTree<>();
        int num = 10_000_000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            rbTree.add(i);
        }
        long mid = System.currentTimeMillis();
        System.out.println("add time is " + (mid - start) / 1000.);
        for (int i = 0; i < num; i++) {
            rbTree.remove(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("remove time is " + (end - mid) / 1000.);
    }
}
