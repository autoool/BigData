package com.geektime.math;

import java.util.ArrayList;

/**
 * @author sam
 * 2018/12/25
 * 1) 1 2 5 10 几个数字，求和为10 共有几种组合
 * <p>
 * 2) 一个整数可以分解为多个整数的乘积，1 只能出现一次。
 */
public class Lesson5_1 {

    public static long[] rewards = {1, 2, 5, 10};
    public static int count = 0;
    private static long totalReward = 10;
    private static ArrayList<Long> results= new ArrayList<>();

    public static void get(long totalReward, ArrayList<Long> result) {
        if (totalReward == 0) {
            System.out.println(result);
        } else if (totalReward < 0) {
            return;
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Long> newResult = (ArrayList<Long>) (result.clone());
                newResult.add(rewards[i]);
                get(totalReward - rewards[i], newResult);
            }
        }

    }

    public static void getProblem1(long totalReward, ArrayList<Long> result) {
        if (totalReward == 0) {
            count++;
            System.out.println(result);
            return;
        } else if (totalReward < 0) {
            return;
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Long> newResult = (ArrayList<Long>) (result.clone());
                newResult.add(rewards[i]);
                getProblem1(totalReward - rewards[i], newResult);
            }
        }
    }

    public static boolean isExist(int result, ArrayList<Integer> array) {
        if (array.contains(result)) {
            return true;
        } else {
            return false;
        }
    }

    public static void getMultiplication(int multiResult, int multi, ArrayList<Integer> result) {
        if (multi == 1) {
            System.out.println(result);
            return;
        } else {
            for (int i = 1; i < multi; i++) {
                ArrayList<Integer> newResult = (ArrayList<Integer>) (result.clone());
                if (newResult.contains(1)) {
                    if (i == 1) {
                        continue;
                    }
                }
                if (multi % i == 0) {
                    int tempresult = multi / i;
                    newResult.add(i);
                    //输入一组结果
                    getMultiplication(multiResult, tempresult, newResult);
                }


            }
        }
    }

    public static void main(String[] args) {
        long totalReward = 10;
//        getProblem1(totalReward, new ArrayList<Long>());
//        System.out.println(count);
//        int multiResult = 8;
//        int multi = 8;
//        getMultiplication(multiResult, multi, new ArrayList<Integer>());
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(1);
//        System.out.println(arrayList.contains(1));
        get(totalReward,new ArrayList<Long>());
    }
}
