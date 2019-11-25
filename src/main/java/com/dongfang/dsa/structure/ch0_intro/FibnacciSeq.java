package com.dongfang.dsa.structure.ch0_intro;

import com.dongfang.dsa.tools.StopWatch;

public class FibnacciSeq {
    /**
     * first  n-2
     * second n-2
     * 相当于是双指针
     * 0 1 2 3 4 5 6 7...
     * 0 1 1 2 3 5 8 13 ...
     */

    private static long fib1(long n) {
        if (n <= 1) return n;
        return fib1(n - 1) + fib1(n - 2);
    }

    private static long fib2(long n) {
        long first = 0;
        long second = 1;
        long sum;

        for (long i = 0; i < n - 1; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public static void main(String[] args) {
        test002(50);
    }

    public static void test002(long n) {
        Runnable fib1Job = () -> {
            long res = fib1(n);
            System.out.println("递归算法res = " + res);
        };

        StopWatch.watch("递归算法", fib1Job);

        Runnable fib2Job = () -> {
            long res = fib2(n);
            System.out.println("迭代算法res = " + res);
        };

        StopWatch.watch("迭代算法", fib2Job);

    }


    public static void test001() {
        System.out.println("fib1(30) = " + fib1(50));
//        System.out.println("fib1(45) = " + fib1(45));
        System.out.println("fin2(1) = " + fib2(1));
        System.out.println("fin2(3) = " + fib2(3));
        System.out.println("fin2(1000) = " + fib2(10000));

        StopWatch.watchTime("fib2(10000)", new Runnable() {
            @Override
            public void run() {
                fib2(10000);
            }
        });
    }
}
