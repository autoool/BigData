package com.itstaredu.flink;

/**
 * @author sam
 * 2019/2/14
 */
public class WordWithCount {

    public String word;
    public long count;

    public WordWithCount() {
    }

    public WordWithCount(String word, long count) {
        this.count = count;
        this.word = word;
    }


    @Override
    public String toString() {
        return word + " " + count;
    }
}
