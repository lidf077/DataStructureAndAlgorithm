package com.dongfang.dsa.algorithm.sort;

import java.util.Comparator;

public abstract class Sort<T extends Comparable> {
    protected T[] array;
    protected int compareCount;

    public void sort(T[] array) {
        if (array == null || array.length < 2) return;
        this.array = array;
        sort();
    }

    protected abstract void sort();

    /**
     * return 0  array[i] == array[j]
     * >0 array[i] >  array[j]
     * <0 array[i] <  array[j]
     */
    protected int compare(int i, int j) {
        compareCount++;
        return array[i].compareTo(array[j]);
    }

    protected void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
