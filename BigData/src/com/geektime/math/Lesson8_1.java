package com.geektime.math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author sam
 * 2019/2/13
 */
public class Lesson8_1 {

    //队伍比赛  三个元素中 2 个元素的所有组合
    public static void main(String[] args) {
        ArrayList<String> teams = new ArrayList<>(Arrays.asList("t1", "t2", "t3"));
        combine(teams, new ArrayList<String>(), 2);
    }

    public static void combine(ArrayList<String> params, ArrayList<String> result, int m) {
        if (result.size() == m) {
            System.out.println(result);
            return;
        } else {
            for (int i = 0; i < params.size(); i++) {

                ArrayList<String> new_result = (ArrayList<String>) (result.clone());
                new_result.add(params.get(i));
                ArrayList<String> new_params = new ArrayList<>(params.subList(i + 1, params.size()));

                combine(new_params, new_result, m);
            }
        }
    }

    //抽奖系统 100人 一等奖1  二等奖3 三等奖10  其实就是选出14个人所有情况 14个人的所有排序
    public static void problem() {
        ArrayList<String> people = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            people.add("" + i);
        }
    }

    public static void combinePro() {

    }
}
