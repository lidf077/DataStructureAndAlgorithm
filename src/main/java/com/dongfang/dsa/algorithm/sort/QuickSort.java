package com.dongfang.dsa.algorithm.sort;

/**
 * 快速排序--执行流程
 * 	1、从序列中选择一个轴点元素（pivot）
 * 		假设每次选择0位置的元素为轴点元素
 * 	2、利用pivot将序列分割成2个子序列
 * 		将小于pivot的元素放在pivot前面（右侧）
 * 		将大于pivot的元素放在pivot后面（左侧）
 * 		等于pivot的元素放哪边都可以
 * 	3、对子序列进行1 2 操作，
 * 		直到不能再分割（子序列中只剩下一个元素）
 * 快速排序的本质是逐渐将每一个元素都转成轴点元素
 *
 * 快速排序--时间复杂度
 * 	1、在轴点左右元素数量比较均匀的情况下，同时也是最好的情况
 * 		T(N) = 2*T(N/2) + O(N) = O(NlogN)
 * 	2、如果轴点左右元素数量极度不均匀，最坏情况
 * 		T(N) = T(N-1) + O(N) = O(N^2)
 * 	3、为了降低最坏情况的出现概率，一般采用的做法是
 * 		随机选择轴点元素
 * 	4、最好，平均时间复杂度 O(nlogn)
 * 	5、最坏时间复杂度O(N^2)
 * 	6、由于递归调用的缘故，空间复杂度O(logn)
 * 	7、属于不稳定排序
 * @param <E>
 */
public class QuickSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        quickSort(0, array.length);
    }

    /**
     * 对 [begin, end]范围内的元素进行快速排序
     *
     * @param begin
     * @param end
     */
    private void quickSort(int begin, int end) { // T(n) = 2 * T(n/2) + O(n) = nlogn
        if (end - begin < 2) return; // 元素数量小于2，直接return

        // 确定轴点位置
        int mid = pivotIndex(begin, end);
        // 对子序列进行快速排序，对左右子序列进行快速排序
        quickSort(begin, mid);    // T(n/2)
        quickSort(mid + 1, end);  // T(n/2)
    }

    /**
     * 构造出 [begin, end]范围的轴点元素
     *
     * @param begin
     * @param end
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        // 随机选择一个元素，跟begin 位置的元素交换，每一个索引都有机会
        swap(begin, begin + (int) (Math.random() * (end - begin)));

        // 备份begin 位置的元素
        E pivot = array[begin];
        // end指向最后一个元素
        end--;

        while (begin < end) {
            while (begin < end) {
                // 这里如果使用 <= 会导致分割不均匀，出现最坏时间复杂度
                if (compare(pivot, array[end]) < 0) { // 右边的元素 > pivot
                    end--;
                } else { // 右边的元素 <= 于轴点
                    array[begin] = array[end];
                    begin++;
                    break;
                }
            }

            while (begin < end) {
                if (compare(pivot, array[begin]) > 0) { // 左边元素 < pivot
                    begin++;
                } else { // 左边元素 >= pivot
                    array[end] = array[begin];
                    end--;
                    break;
                }
            }
        }

        // 将轴点元素放入最终的位置
        array[begin] = pivot;

        //  begin == end，返回哪个都行
        return begin;
    }
}
