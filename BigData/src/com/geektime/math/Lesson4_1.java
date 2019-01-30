package com.geektime.math;

/**
 * @author sam
 * 2018/12/17
 * 牛顿迭代法求麦粒问题
 */
public class Lesson4_1 {


    public static void main(String[] args) {
        int grid = 63;
        long start, end = 0;
        start = System.currentTimeMillis();
        System.out.println(String.format("麦粒：%d", Lesson3_1.getNumberOfWheat(grid)));
        end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d", (end - start)));

        start = System.currentTimeMillis();
        System.out.println(String.format("麦粒：%d", (long) (Math.pow(2, grid) - 1)));
        end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d", (end - start)));

    }
}
