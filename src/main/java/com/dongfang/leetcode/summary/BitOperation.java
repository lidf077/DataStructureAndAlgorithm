package com.dongfang.leetcode.summary;

import org.junit.Test;

public class BitOperation {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    @Test
    public void testBitOperation() {
        System.out.println("Integer.toBinaryString(-22) = " + Integer.toBinaryString(-22));
        System.out.println("Integer.toBinaryString(22) = " + Integer.toBinaryString(22));
        System.out.println("Integer.toBinaryString(~22) = " + Integer.toBinaryString(~22));
        System.out.println("(22 ^ 0) = " + (22 ^ 0));
        System.out.println("(8 >> 1) = " + (9 >> 1));
        System.out.println("Integer.toBinaryString(9) = " + Integer.toBinaryString(9));
        int num = 99;
        System.out.println("Integer.toBinaryString(num) = " + Integer.toBinaryString(num));

        System.out.println("22 ^ 22 = " + (22 ^ 22));
    }

    @Test
    public void testOddOrEven() {
        int num = 99;
        if (num / 2 == 0) {
            // 偶数
        } else {
            // 奇数
        }
        // 偶数的最低位是0
        // 奇数的最低位是1

        //1100011
        //0000001 与1做与运算，相当于只是取最低位与1做与运算，其他位都被屏蔽了
        if ((num & 1) == 0) {
            System.out.println(num + "是偶数");
        } else {
            System.out.println(num + "是奇数");
        }
    }

    @Test
    public void testBinaryNumber() {
        int num = 86;
        int k = 5;
        System.out.println("Integer.toBinaryString(86) = " + Integer.toBinaryString(num));
        // 将1左移k-1位，与num做与运算，然后再右移k-1位
        // 1010110
        // 0000001
        // 0001000 将1左移4位
        // 0000000 与运算
        // 0000000 右移4位
        int res = (num & (1 << (k - 1))) >> (k - 1);
        System.out.println("res = " + res);
        // 方法2，将num 右移 k-1位，再与1做与运算
        // 1010110 右移4位，将要判断的那一位移到末尾
        // 0000101
        // 0000001 与1做与运算
        // 0000001
        res = (num >> 4) & 1;
        System.out.println("res = " + res);
    }

    /**
     * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     * <p>
     * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += ((n >> i) & 1);
        }
        return count;
    }

    /**
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     * 示例：
     * <p>
     * 输入: numbers = [1,2]
     * 输出: [2,1]
     *
     * @param numbers
     * @return
     */
    public int[] swapNumbers(int[] numbers) {
        int a = numbers[0];
        int b = numbers[1];
        a = a ^ b;
        // b = a ^ b ^ b = a;
        b = a ^ b;
        // a = a ^ b ^ a
        a = a ^ b;
        return new int[]{a, b};
    }

    @Test
    public void testAbsoluteValue() {
        int num = -56;
        int res = (num ^ (num >> 31)) + (num >>> 31);
        System.out.println("res = " + res);
    }

    /**
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     * <p>
     * 示例1:
     * <p>
     * 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
     * 输出：2
     * 示例2:
     * <p>
     * 输入：A = 1，B = 2
     * 输出：2
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-integer-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @param B
     * @return
     */
    public int convertInteger(int A, int B) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += ((A >> i) & 1) == ((B >> i) & 1) ? 0 : 1;
        }
        return count;
    }
}