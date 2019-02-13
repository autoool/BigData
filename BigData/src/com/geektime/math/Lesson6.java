package com.geektime.math;

/**
 * @author sam
 * 2019/2/13
 */
public class Lesson6 {

    public static void bitOperator(Integer[] input) {
        Integer tempResult = 0;
        Integer index = 0;
        if (input.length > 2) {
            tempResult = input[0] ^ input[1];
            for (index = 2; index < input.length; index++) {
                System.out.println(input[index]);
                tempResult = tempResult ^ input[index];
            }
        }
        Integer secondTemp = 0;
        for (index = 0; index < input.length; index++) {
            secondTemp = tempResult ^ input[index];
        }

        System.out.println("result:" + secondTemp);

    }

    public static void main(String[] args) {
        Integer[] integers = {1,3,3,4,5,6,7,8};
        bitOperator(integers);
    }
}
