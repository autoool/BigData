package com.itstaredu.ch09;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author sam
 * 2018/10/30
 */
public class WordcountDriver {

    public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException{
        //1 获取job信息
        Configuration configuration = new Configuration();
        Job job = Job.getInstance();
        //2 获取jar
        job.setJarByClass(WordcountDriver.class);

        //3 获取自定义mapper reducer 类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //4 设置map输出数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //5 设置reducer最终数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //6 设置输入存在的路径与处理后的结果路径

        FileInputFormat.setInputPaths(job,new Path("E:/Personal/BigData/data-source/test/words.txt"));
        FileOutputFormat.setOutputPath(job,new  Path("E:/Personal/BigData/data-source/test/out1"));

        //7提交任务
        job.waitForCompletion(true);
    }
}
