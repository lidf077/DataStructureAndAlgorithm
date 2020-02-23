package com.dongfang.dsa.algorithm.sort;

/**
 * 桶排序：按照个位，十位，百位，千位将数分别放在桶中，再放回原数组中进行排序
 *      空间复杂度是O(kn+k)，时间复杂度是O(dn)
 *      d是最大值的位置，k是进制
 *
 *      执行流程：
 *          1、创建一定数量的桶（比如用数组，链表作为桶）
 *          2、按照一定的规则 （不同类型的数据，规则 不同），将序列中的元素均匀地分配到对应的桶中
 *          3、分别对每个桶进行单独排序
 *          4、将所有非空桶的元素合并成有序序列
 */
public class BucketSort extends Sort<Integer> {
    @Override
    protected void sort() {
        bucketSort();
    }

    private void bucketSort() {
        int max = array[0];
        for (Integer element : array) {
            if (element > max) max = element;
        }

        // 桶数组
        int[][] buckets = new int[10][array.length];
        // 每个桶的元素数量
        int[] bucketSize = new int[buckets.length];
        for (int divider = 1; divider <= max; divider *= 10) {
            for (int i = 0; i < array.length; i++) {
                int no = array[i] / divider % 10;
                buckets[no][bucketSize[no]++] = array[i];
            }
            int index = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < bucketSize[i]; j++) {
                    array[index++] = buckets[i][j];
                }
                bucketSize[i] = 0;
            }
        }
    }
}
