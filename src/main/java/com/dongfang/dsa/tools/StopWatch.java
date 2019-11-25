package com.dongfang.dsa.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StopWatch {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    // time complexity 时间复杂度    程序指令的执行次数，执行时间
    public static void watchTime(String jobName, Runnable job) {
        executor.execute(() -> {
            long start = System.currentTimeMillis();
            job.run();
            long end = System.currentTimeMillis();
            System.out.println(jobName + " execution time is " + (end - start) + " milliseconds");
        });
    }

    // space complexity 空间复杂度 程序执行时所占用的内存空间，不算输入和输出
    public static void watchMemory(String jobName, Runnable job) {
        executor.execute(() -> {
            long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            job.run();
            long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long actualMemUsed = afterUsedMem - beforeUsedMem;
            System.out.println(jobName + " execution memory is " + actualMemUsed + " bytes");
        });
    }

    public static void watch(String jobName, Runnable job) {
        executor.execute(() -> {
            System.out.println();
            System.err.println(jobName + "*********************************************");
            System.err.println(jobName + "*********************************************");
            System.out.println(jobName + "开始运行");

            long start = System.currentTimeMillis();
            long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

            job.run();
            long end = System.currentTimeMillis();
            System.out.println(jobName + " 运行时间为 " + (end - start) + " milliseconds");
            System.err.println(jobName + "--------------------------------------------------");

            long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long actualMemUsed = afterUsedMem - beforeUsedMem;
            System.out.println(jobName + " 耗费内存为 " + actualMemUsed + " bytes");

            System.out.println(jobName + "结束运行");
            System.err.println(jobName + "*********************************************");
            System.err.println(jobName + "*********************************************");
            System.out.println();
        });
    }
}
