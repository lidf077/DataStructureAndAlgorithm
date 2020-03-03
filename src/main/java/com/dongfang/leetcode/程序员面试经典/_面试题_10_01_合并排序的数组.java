package com.dongfang.leetcode.程序员面试经典;
//给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
//
// 初始化 A 和 B 的元素数量分别为 m 和 n。 
//
// 示例: 
//
// 输入:
//A = [1,2,3,0,0,0], m = 3
//B = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
public class _面试题_10_01_合并排序的数组 {
    public void merge(int[] A, int m, int[] B, int n) {
        int index = m + n - 1;
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index] = A[i];
                i--;
            } else {
                A[index] = B[j];
                j--;
            }
            index--;
        }
        while (i >= 0) {
            A[index] = A[i];
            index--;
            i--;
        }
        while (j >= 0) {
            A[index] = B[j];
            index--;
            j--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
