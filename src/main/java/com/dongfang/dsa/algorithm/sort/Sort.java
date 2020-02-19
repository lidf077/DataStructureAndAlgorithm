package com.dongfang.dsa.algorithm.sort;

public abstract class Sort<T extends Comparable> {
    protected T[] array;
    protected int compareCount;
    protected int swapCount;

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


    protected int compare(T t1, T t2) {
        compareCount++;
        return t1.compareTo(t2);
    }

    protected void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swapCount++;
    }

}
