package com.geektime.math;

/**
 * @author sam
 * 2018/12/17
 * 求麦粒的个数
 */
public class Lesson3_1 {

    public static long getNumberOfWheat(int grid) {
        long sum = 0;
        long numberOfWheatInGrid = 0;
        numberOfWheatInGrid = 1;
        sum += numberOfWheatInGrid;
        for (int i = 2; i <= grid; i++) {
            numberOfWheatInGrid = numberOfWheatInGrid * 2;
            sum += numberOfWheatInGrid;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(String.format("罕舍王给了多少粒麦子 %d", getNumberOfWheat(63)));
    }
}
