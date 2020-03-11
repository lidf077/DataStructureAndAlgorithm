package com.dongfang.dsa.acm._02__02_searchAndSort;

import org.junit.Test;

@SuppressWarnings("all")
public class BinarySearch {
    /**
     * 二分查找的常见3个问题：
     *      1、寻找一个数
     *      2、寻找左侧边界
     *      3、寻找右侧边界
     *      技巧：不要出现else，所有的情况用if eles写清楚，展现所有细节
     *          int mid = (left + right) >>> 1 java中的无符号右移不会出现溢出
     */

    /**
     * 二、寻找左侧边界的二分搜索
     * 以下是最常见的代码形式，其中的标记是需要注意的细节：
     *
     * int left_bound(int[] nums, int target) {
     *     if (nums.length == 0) return -1;
     *     int left = 0;
     *     int right = nums.length; // 注意
     *
     *     while (left < right) { // 注意
     *         int mid = (left + right) / 2;
     *         if (nums[mid] == target) {
     *             right = mid;
     *         } else if (nums[mid] < target) {
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             right = mid; // 注意
     *         }
     *     }
     *     return left;
     * }
     * 1、为什么 while 中是 < 而不是 <=?
     *
     * 答：用相同的方法分析，因为 right = nums.length 而不是 nums.length - 1。因此每次循环的「搜索区间」是 [left, right) 左闭右开。
     *
     * while(left < right) 终止的条件是 left == right，此时搜索区间 [left, left) 为空，所以可以正确终止。
     *
     * PS：这里先要说一个搜索左右边界和上面这个算法的一个区别，也是很多读者问的：刚才的 right 不是 nums.length - 1 吗，为啥这里非要写成 nums.length 使得「搜索区间」变成左闭右开呢？
     *
     * 因为对于搜索左右侧边界的二分查找，这种写法比较普遍，我就拿这种写法举例了，保证你以后遇到这类代码可以理解。你非要用两端都闭的写法反而更简单，我会在后面写相关的代码，把三种二分搜索都用一种两端都闭的写法统一起来，你耐心往后看就行了。
     *
     * 2、为什么没有返回 -1 的操作？如果 nums 中不存在 target 这个值，怎么办？
     *
     * 答：因为要一步一步来，先理解一下这个「左侧边界」有什么特殊含义：
     *
     *
     *
     * 对于这个数组，算法会返回 1。这个 1 的含义可以这样解读：nums 中小于 2 的元素有 1 个。
     *
     * 比如对于有序数组 nums = [2,3,5,7], target = 1，算法会返回 0，含义是：nums 中小于 1 的元素有 0 个。
     *
     * 再比如说 nums = [2,3,5,7], target = 8，算法会返回 4，含义是：nums 中小于 8 的元素有 4 个。
     *
     * 综上可以看出，函数的返回值（即 left 变量的值）取值区间是闭区间 [0, nums.length]，所以我们简单添加两行代码就能在正确的时候 return -1：
     *
     * while (left < right) {
     *     //...
     * }
     * // target 比所有数都大
     * if (left == nums.length) return -1;
     * // 类似之前算法的处理方式
     * return nums[left] == target ? left : -1;
     * 3、为什么 left = mid + 1，right = mid ？和之前的算法不一样？
     *
     * 答：这个很好解释，因为我们的「搜索区间」是 [left, right) 左闭右开，所以当 nums[mid] 被检测之后，下一步的搜索区间应该去掉 mid 分割成两个区间，即 [left, mid) 或 [mid + 1, right)。
     *
     * 4、为什么该算法能够搜索左侧边界？
     *
     * 答：关键在于对于 nums[mid] == target 这种情况的处理：
     *
     *     if (nums[mid] == target)
     *         right = mid;
     * 可见，找到 target 时不要立即返回，而是缩小「搜索区间」的上界 right，在区间 [left, mid) 中继续搜索，即不断向左收缩，达到锁定左侧边界的目的。
     *
     * 5、为什么返回 left 而不是 right？
     *
     * 答：都是一样的，因为 while 终止的条件是 left == right。
     *
     * 作者：labuladong
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
     */
    private int binarySearchLeftBound(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (target < arr[mid]) {
                right = mid;
            } else if (target == arr[mid]) {
                right = mid; // 向右收缩
            } else if (target > arr[mid]) {
                left = mid + 1;
            }
        }

        /**
         * target 比所有数都大
         * if (left == nums.length) return -1;
         * 类似之前算法的处理方式
         * return nums[left] == target ? left : -1;
         */
        return left;
    }

    @Test
    public void testBinarySearchLeftBound() {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int index = binarySearchLeftBound(arr, 1);
        System.out.println("index = " + index);
    }


    /**
     * 三、寻找右侧边界的二分查找
     * 类似寻找左侧边界的算法，这里也会提供两种写法，还是先写常见的左闭右开的写法，只有两处和搜索左侧边界不同，已标注：
     *
     * int right_bound(int[] nums, int target) {
     *     if (nums.length == 0) return -1;
     *     int left = 0, right = nums.length;
     *
     *     while (left < right) {
     *         int mid = (left + right) / 2;
     *         if (nums[mid] == target) {
     *             left = mid + 1; // 注意
     *         } else if (nums[mid] < target) {
     *             left = mid + 1;
     *         } else if (nums[mid] > target) {
     *             right = mid;
     *         }
     *     }
     *     return left - 1; // 注意
     * }
     * 1、为什么这个算法能够找到右侧边界？
     *
     * 答：类似地，关键点还是这里：
     *
     * if (nums[mid] == target) {
     *     left = mid + 1;
     * 当 nums[mid] == target 时，不要立即返回，而是增大「搜索区间」的下界 left，使得区间不断向右收缩，达到锁定右侧边界的目的。
     *
     * 2、为什么最后返回 left - 1 而不像左侧边界的函数，返回 left？而且我觉得这里既然是搜索右侧边界，应该返回 right 才对。
     *
     * 答：首先，while 循环的终止条件是 left == right，所以 left 和 right 是一样的，你非要体现右侧的特点，返回 right - 1 好了。
     *
     * 至于为什么要减一，这是搜索右侧边界的一个特殊点，关键在这个条件判断：
     *
     * if (nums[mid] == target) {
     *     left = mid + 1;
     *     // 这样想: mid = left - 1
     *
     *
     * 因为我们对 left 的更新必须是 left = mid + 1，就是说 while 循环结束时，nums[left] 一定不等于 target 了，而 nums[left-1] 可能是 target。
     *
     * 至于为什么 left 的更新必须是 left = mid + 1，同左侧边界搜索，就不再赘述。
     *
     * 3、为什么没有返回 -1 的操作？如果 nums 中不存在 target 这个值，怎么办？
     *
     * 答：类似之前的左侧边界搜索，因为 while 的终止条件是 left == right，就是说 left 的取值范围是 [0, nums.length]，所以可以添加两行代码，正确地返回 -1：
     *
     * while (left < right) {
     *     // ...
     * }
     * if (left == 0) return -1;
     * return nums[left-1] == target ? (left-1) : -1;
     *
     * 作者：labuladong
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
     */
    private int binarySearchRightBound(int[] arr, int target) {
        int left = 0, rignt = arr.length;

        while (left < rignt) {
            int mid = (left + rignt) >>> 1;
            if (target < arr[mid]) {
                rignt = mid;
            } else if (target == arr[mid]) {
                left = mid + 1;
            } else if (target > arr[mid]) {
                left = mid + 1;
            }
        }
        /**
         * if (left == 0) return -1;
         * return nums[left-1] == target ? (left-1) : -1;
         */
        return left - 1;
    }

    @Test
    public void binarySearchRightBound() {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int index = binarySearchRightBound(arr, 12);
        System.out.println("index = " + index);
    }

    /**
     * 四、逻辑统一
     * 来梳理一下这些细节差异的因果逻辑：
     *
     * 第一个，最基本的二分查找算法：
     *
     * 因为我们初始化 right = nums.length - 1
     * 所以决定了我们的「搜索区间」是 [left, right]
     * 所以决定了 while (left <= right)
     * 同时也决定了 left = mid+1 和 right = mid-1
     *
     * 因为我们只需找到一个 target 的索引即可
     * 所以当 nums[mid] == target 时可以立即返回
     * 第二个，寻找左侧边界的二分查找：
     *
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     *
     * 因为我们需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧右侧边界以锁定左侧边界
     * 第三个，寻找右侧边界的二分查找：
     *
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     *
     * 因为我们需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧左侧边界以锁定右侧边界
     *
     * 又因为收紧左侧边界时必须 left = mid + 1
     * 所以最后无论返回 left 还是 right，必须减一
     *
     * 作者：labuladong
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}