package com.dongfang.dsa.algorithm.sort;

public abstract class Sort<E extends Comparable<E>> {
    protected E[] array;
    protected int compareCount;
    protected int swapCount;

    public void sort(E[] array) {
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


    protected int compare(E e1, E e2) {
        compareCount++;
        return e1.compareTo(e2);
    }

    protected void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swapCount++;
    }

}
