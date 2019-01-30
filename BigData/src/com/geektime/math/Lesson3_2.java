package com.geektime.math;

/**
 * @author sam
 * 2018/12/17
 * 迭代法求平方根
 */
public class Lesson3_2 {

    /**
     * f(1)=1  f(2)=f(1)*2  f(3)=f(2)*2
     * f(n+1)=f(n)*2
     * 求f(n) 到f(1)的和
     */
    public static int fnSum(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fnSum(n - 1) * 2 + fnSum(n - 1);
    }


    public static int fN(int n) {
        return fnSum(n - 1) * 2;
    }

    /**
     * 计算大于1的正整数的平方根
     *
     * @param n              待求的数，
     * @param deltaThreshold 误差阀值
     * @param maxTry         二分查找的最大次数
     * @return 平方根的解
     */
    public static double getSqureRoot(int n, double deltaThreshold, int maxTry) {
        if (n <= 1) {
            return -1.0;
        }
        double min = 1.0, max = (double) n;
        for (int i = 0; i < maxTry; i++) {
            double middle = (max + min) / 2;
            double square = middle * middle;
            double delta = Math.abs((square / n) - 1);
            if (delta <= deltaThreshold) {
                return middle;
            } else {
                if (square > n) {
                    max = middle;
                } else {
                    min = middle;
                }
            }
        }
        return -2.0;
    }

    public static void main(String[] args) {
        int number = 10;
        double squareRoot = getSqureRoot(number, 0.000001, 10000);
        if (squareRoot == -1.0) {
            System.out.println("请输入大于1的整数");
        } else if (squareRoot == -2.0) {
            System.out.println("未找到解");
        } else {
            System.out.println(String.format("%d 的平方根是%f", number, squareRoot));
        }
    }
}
