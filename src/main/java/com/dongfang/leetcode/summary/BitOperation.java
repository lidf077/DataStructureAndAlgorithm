package com.dongfang.leetcode.summary;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

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

    /**
     * 题1:找出唯一成对的数
     * 1-1000这1000个数放在含有1001个元素的数组中，只有唯一的一个元素值重复，
     * 其它均只出现一次。每个数组元素只能访问一次，设计一个算法，
     * 将它找出来;不用辅助存储空间，能否设计一个算法实现?
     */
    @Test
    public void testPairNumbers() {
        int N = 1100;
        int[] arr = new int[N];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = i + 1;
        }
        arr[N - 1] = new Random().nextInt(N - 1) + 1;
        int index = new Random().nextInt(N);
        int t;
        t = arr[N - 1];
        arr[N - 1] = arr[index];
        arr[index] = t;
        System.out.println(Arrays.toString(arr));
        int x = 0;
        for (int i = 0; i < N - 1; i++) {
            x = x ^ (i + 1);
        }
        for (int i = 0; i < N; i++) {
            x ^= arr[i];
        }
//		x^x=0,出现两次的消去，剩下出现三次的
        System.out.println(x);
//        辅助空间方法
        int[] h = new int[N];
        for (int i = 0; i < N; i++) {
            h[arr[i]]++;
        }
        for (int i = 0; i < N; i++) {
            if (h[i] == 2) {
                System.out.println(i);
            }
        }
    }

    /**
     * 题5:将整数的二进制奇偶位互换
     * 1&保留，0&置零，0^保留
     */
    @Test
    public void testSwapOddAndEven() {
        int a = 6;
        //结果为9

        int ou = a & 0xaaaaaaaa;//和1010 1010 ...做与运算得到偶位
        int ji = a & 0x55555555;//和0101 0101 ...做与运算得到奇位
        int res = (ou >> 1) ^ (ji << 1);

        System.out.println("res = " + res);
    }

    /**
     * 题7:出现k次与出现1次
     * 数组中只有-一个数出现了1次，其他的数都出现了k次，请输出只出现了1次的数。
     * 2 个相同的2 进制数做不进位加法，结果为0
     * 10个相同的10进制数做不进位加法，结果为0
     * k 个相同的k 进制数做不进位加法，结果为0
     */
    @Test
    public void testNRadix() {
        int[] arr = {2, 2, 2, 9, 7, 7, 7, 3, 3, 3, 6, 6, 6, 0, 0, 0};
        int len = arr.length;
        char[][] kRadix = new char[len][];
        int k = 3;

        int maxLen = 0;
        //转成k进制字符数组
        //对于每个数字
        for (int i = 0; i < len; i++) {
            //求每个数字的三进制字符串并翻转，然后转为字符数组
            kRadix[i] = new StringBuilder(Integer.toString(arr[i], k)).reverse().toString().toCharArray();
            if (kRadix[i].length > maxLen)
                maxLen = kRadix[i].length;
        }
        //不进位加法
        int[] resArr = new int[maxLen];
        for (int i = 0; i < len; i++) {
            //  不进位加法
            for (int j = 0; j < maxLen; j++) {
                if (j >= kRadix[i].length)
                    resArr[j] += 0;
                else
                    resArr[j] += (kRadix[i][j] - '0');
            }
        }
        int res = 0;
        for (int i = 0; i < maxLen; i++) {
            res += (resArr[i] % k) * (int) (Math.pow(k, i));// 8%3=2,
        }
        System.out.println(res);
    }

    @Test
    public void testRadix() {
        String res = Integer.toString(77, 3);
        System.out.println("res = " + res);
    }
}