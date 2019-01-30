package com.geektime.math;

/**
 * @author sam
 * 2018/12/17
 */
public class Lesson4_2 {
    public static class Result {
        public long wheatNum = 0;
        public long wheatTotalNum = 0;
    }

    public static boolean prove(int k, Result result) {
        //证明n=1时，命题是否成立
        if (k == 1) {
            if ((Math.pow(2, 1) - 1) == 1) {
                result.wheatNum = 1;
                result.wheatTotalNum = 1;
                return true;
            } else {
                return false;
            }
            //如果n=(k-1)时命题成立，证明n=k时命题是否成立
        } else {
            boolean proveOfPreviousOne = prove(k - 1, result);
            result.wheatNum *= 2;
            result.wheatTotalNum += result.wheatNum;
            boolean proveOfCurrentOne = false;
            if (result.wheatTotalNum == (Math.pow(2, k) - 1)) {
                proveOfCurrentOne = true;
            }
            if (proveOfPreviousOne && proveOfCurrentOne) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        int grid = 63;
        Result result = new Result();
        System.out.println(prove(grid,result));
    }
}
