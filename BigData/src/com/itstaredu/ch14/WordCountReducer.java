package com.itstaredu.ch14;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author sam
 * 2018/10/30
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        //1. 统计单词出现次数
        int sum = 0;

        //2.累加求和
        for (IntWritable value : values) {
            sum += value.get();
        }

        //结果输出
        context.write(key, new IntWritable(sum));
    }
}
