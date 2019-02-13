package com.geektime.math;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author sam
 * 2019/2/13
 */
public class Lesson7_1 {

    public static HashMap<String, Double> qHorsesTime = new HashMap<String, Double>() {
        {
            put("q1", 1.0);
            put("q2", 2.0);
            put("q3", 3.0);
        }
    };
    public static HashMap<String, Double> tHorsesTime = new HashMap<String, Double>() {
        {
            put("t1", 1.5);
            put("t2", 2.5);
            put("t3", 3.5);
        }
    };

    public static ArrayList<String> q_horses = new ArrayList<String>(Arrays.asList("q1", "q2", "q3"));

    /**
     * @param horses 田忌赛马数据
     * @param result
     */
    public static void permutate(ArrayList<String> horses, ArrayList<String> result) {
        if (horses.size() == 0) {
            System.out.println(result);
            compareHourse(result, q_horses);
            System.out.println();
            return;
        } else {
            //循环总长度数，然后通过递归实现输出全排列
            for (int i = 0; i < horses.size(); i++) {
                //从剩下未出战马匹中选择一个
                System.out.println(horses);
                ArrayList<String> new_result = (ArrayList<String>) (result.clone());
                new_result.add(horses.get(i));
                //将已选择马匹从未选中移除
                ArrayList<String> rest_horses = (ArrayList<String>) (horses.clone());
                rest_horses.remove(i);
//                System.out.println("rest_horses");
//                System.out.println(rest_horses);
                //递归调用
                permutate(rest_horses, new_result);

            }
        }
    }

    public static void compareHourse(ArrayList<String> t, ArrayList<String> q) {
        int tWonCnt = 0;
        for (int i = 0; i < t.size(); i++) {
            if (tHorsesTime.get(t.get(i)) < qHorsesTime.get(q.get(i)))
                tWonCnt++;
        }
        if (tWonCnt > (t.size() / 2))
            System.out.println("田忌获胜");
        else {
            System.out.println("齐王获胜");
        }
    }

    public static void main(String[] args) {

//        ArrayList<String> horses = new ArrayList<String>(Arrays.asList("t1", "t2", "t3"));
//        Lesson7_1.permutate(horses, new ArrayList<String>());
        problem_1();
//        repeatPre();

    }

    public static ArrayList<String> pwdWord = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e"));
    public static ArrayList<String> realPwd = new ArrayList<>(Arrays.asList("a", "d", "d", "d"));
    //[a, c, d, e, b]

    public static void conparePwd(ArrayList<String> sort, ArrayList<String> pwd) {
        if (sort.size() == pwd.size()) {
            boolean result = true;
            for (int i = 0; i < sort.size(); i++) {
                if (!sort.get(i).equals(pwd.get(i))) {
                    result = false;
                }
            }
            if (result) {
                System.out.println("找到密码");
                System.out.println(pwd);
            }
        } else {
            System.out.println("密码位数不对");
        }
    }

    public static void problem_1() {
        calPwdRepeatLength(pwdWord, new ArrayList<String>());
    }

    //密码不包含重复字母
    public static void calPwd(ArrayList<String> param, ArrayList<String> result) {
        if (result.size() == realPwd.size()) {
            conparePwd(result, realPwd);
//            System.out.println(result);
            return;
        } else {
            for (int i = 0; i < param.size(); i++) {
                ArrayList<String> new_result = (ArrayList<String>) (result.clone());
                new_result.add(param.get(i));
                ArrayList<String> new_param = (ArrayList<String>) (param.clone());
                new_param.remove(i);
                calPwd(new_param, new_result);
            }
        }
    }

    //密码包含重复字母  全排列
    public static void calPwdRepeat(ArrayList<String> param, ArrayList<String> result) {
        if (result.size() == pwdWord.size()) {
//            conparePwd(result, realPwd);
            System.out.println(result);
            return;
        } else {
            for (int i = 0; i < pwdWord.size(); i++) {
                ArrayList<String> new_result = (ArrayList<String>) (result.clone());
                new_result.add(param.get(i));
                ArrayList<String> new_param = (ArrayList<String>) (param.clone());
//                new_param.remove(i);
                calPwdRepeat(pwdWord, new_result);
            }
        }
    }

    //输出对应位数的全排列
    public static int sortLength = 4;

    public static void calPwdRepeatLength(ArrayList<String> param, ArrayList<String> result) {
        if (result.size() == sortLength) {
            conparePwd(result, realPwd);
//            System.out.println(result);
            return;
        } else {
            for (int i = 0; i < pwdWord.size(); i++) {
                ArrayList<String> new_result = (ArrayList<String>) (result.clone());
                new_result.add(param.get(i));
                ArrayList<String> new_param = (ArrayList<String>) (param.clone());
//                new_param.remove(i);
                calPwdRepeatLength(pwdWord, new_result);
            }
        }
    }

    public static void perStr(ArrayList<String> buf, String str, int length) {
//        char[] chs = str.toCharArray();
//        if(length == 0){
//            for(int i = buf.size()-1; i>=0; i--){
//                System.out.print(buf.get(i));
//            }
//            System.out.println();
//            return;
//        }
//        for (int i = 0;i<chs.length;i++){
//            buf.get(length)[length-1] = chs[i];
//            per(buf,str,length-1);
//        }
    }

    public static void per(char[] buf, String str, int length) {
        char[] chs = str.toCharArray();
        if (length == 0) {
//            System.out.println("length =0");
//            System.out.println(str);
            for (int i = buf.length - 1; i >= 0; i--) {
                System.out.print(buf[i]);
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < chs.length; i++) {
            buf[length - 1] = chs[i];
            per(buf, str, length - 1);
        }
    }

    //输出全排列
    public static void repeatPre() {
        String str = new String("abc");
        per(new char[str.length()], str, str.length());
    }


}
