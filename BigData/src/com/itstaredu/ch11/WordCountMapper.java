package com.itstaredu.ch11;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author sam
 * 2018/10/30
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1 读取数据
        String line = value.toString();
        //2 切割
        String[] strs = line.split(" ");


        //3 循环到下一个阶段
        for (String str : strs) {
            context.write(new Text(str), new IntWritable(1));
        }
    }
}
