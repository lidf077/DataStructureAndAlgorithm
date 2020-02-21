package com.dongfang.dsa.algorithm.sort;

/**
 *
 * @param <E>
 *
 *     归并排序：
 *     1、1945年由冯诺伊曼首次提出
 *     2、执行流程：
 *         1、不断地将当前序列平均分割成2个子序列
 *             直到不能再分割，序列中只剩下一下元素
 *         2、不断地将2个子序列合并成一个有序序列
 *             直到最终只剩下一个有序序列
 */
@SuppressWarnings("all")
public class MergeSort<E extends Comparable<E>> extends Sort<E> {
    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        mergeSort(0, array.length);
    }

    /**
     * 对 [begin, end)范围内的数据进行归并排序
     *
     * @param begin
     * @param end
     * 时间复杂度，使用递推表达式求复杂度
     *      T(N/2) + T(N/2) + O(N) = O(NlogN)
     *      总是平均分割子序列，所以最好最坏，平均时间复杂度 O(NlogN)
     *      空间复杂度为 O(N/2 + logN) = O(N)
     *      N/2 是用于临时存放左侧数组，logN是因为递归调用
     */
    private void mergeSort(int begin, int end) {
        // 归并到一个元素 直到最终只剩下一个有序序列
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        mergeSort(begin, mid); // [begin, mid)
        mergeSort(mid, end);   // [mid, end)
        merge(begin, mid, end);
    }

    /**
     * 合并哪两个，头，尾，切割点
     * 将 [begin, mid) [mid, end) 两个范围内的序列合并成一个有序序列
     * 要merge的两个序列是挨在一起的，在同一个数组中，这个性质很重要，
     *
     * @param begin
     * @param mid
     * @param end   i index
     *              e end
     *              l left
     *              r right
     *              a array
     *              合并的时间复杂度为O(N)，对每一个元素遍历 ，看在哪个位置
     */
    private void merge(int begin, int mid, int end) {
        // 左边数组，基于leftArray
        int li = 0;
        int le = mid - begin;

        // 右边数组，基于array
        int ri = mid;
        int re = end;
        int ai = begin; // array的索引

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        while (li < le) { // 左边还没有结束
            // 改成 <= 0会失去稳定性
            if (ri < re && compare(array[ri], leftArray[li]) < 0) {
                array[ai] = array[ri]; // 拷贝右边数组到array
                ai++;
                ri++;
            } else { // ri < re不成立，代表右边结束了
                array[ai] = leftArray[li]; // 拷贝左边数组到array
                ai++;
                li++;
            }
        }
    }
}

