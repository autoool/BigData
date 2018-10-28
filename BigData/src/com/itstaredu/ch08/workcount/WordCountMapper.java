package com.itstaredu.ch08.workcount;

/**
 * @author sam
 * 2018/10/28
 */
public class WordCountMapper implements Mapper {

    @Override
    public void map(String line, Context context) {
        /**
         * 1 拿到数据进行切分
         * 2 拿到单词，相同key  value++1
         */
        String[] words = line.split(" ");
        for (String word : words) {
            Object value = context.get(word);
            if (null == value) {
                context.wrtie(word, 1);
            } else {
                int count = (int) value;
                ++count;
                context.wrtie(word, count);
            }
        }
    }
}
