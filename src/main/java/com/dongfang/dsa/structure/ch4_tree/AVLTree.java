package com.dongfang.dsa.structure.ch4_tree;

import java.util.Comparator;

public class AVLTree<E> extends BST {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }
}
