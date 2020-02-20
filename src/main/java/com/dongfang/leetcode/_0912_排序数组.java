package com.dongfang.leetcode;//给定一个整数数组 nums，将该数组升序排列。
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：[5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：[5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 10000 
// -50000 <= A[i] <= 50000 
// 
//


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
public class _0912_排序数组 {
    public static void main(String[] args) {
        new _0912_排序数组().sortArray(new int[]{3, 2, 4, 5, 6, 7, 9, 8, 0});
    }

    public List<Integer> sortArray(int[] nums) {
        int heapSize = nums.length;
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            siftDown(nums, i, heapSize);
        }

        while (heapSize > 1) {
            swap(nums, 0, heapSize - 1);
            heapSize--;
            siftDown(nums, 0, heapSize);
        }

        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    private void siftDown(int[] nums, int index, int heapSize) {
        int element = nums[index];
        int half = heapSize / 2;

        while (index < half) {
            int childIndex = (index * 2) + 1;
            int child = nums[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;

            // 选出左右子节点最大的那个
            if (rightIndex < heapSize && (nums[rightIndex] - child) > 0) {
                child = nums[childIndex = rightIndex];
            }

            if ((element - child) >= 0) break;

            nums[index] = child;
            // 重新设置index
            index = childIndex;
        }
        nums[index] = element;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
