package com.itstaredu.outputformat;

import com.itstaredu.ch14.*;
import com.itstaredu.ch14.funcinput.FuncFileInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

/**
 * @author sam
 * 2018/11/15
 */
public class FileDriver {

    public static void main(String[] args) {
        try {
            //1 获取job信息
            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration);
            //2 获取jar
            job.setJarByClass(FileDriver.class);

            //3 获取自定义mapper reducer 类
            job.setMapperClass(FileMapper.class);
            job.setReducerClass(FileReduce.class);

            //设置自定义读取方式

            //4 设置map输出数据类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            //5 设置reducer最终数据类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            //设置自定义outputFormat
            job.setOutputFormatClass(FuncFileOutputFormat.class);

            //设置combiner


            //设置自定义分区类
//            job.setPartitionerClass(WordCountPartitioner.class);
            //reducetask 必须大于指定分区数
//            job.setNumReduceTasks(4);


//            job.setInputFormatClass(CombineTextInputFormat.class);
//            CombineTextInputFormat.setMaxInputSplitSize(job, 1024 * 4);
//            CombineTextInputFormat.setMinInputSplitSize(job, 1024 * 3);

            //6 设置输入存在的路径与处理后的结果路径

            FileInputFormat.setInputPaths(job, new Path("E:/Personal/BigData/data-source/test/fileinput"));
            FileOutputFormat.setOutputPath(job, new Path("E:/Personal/BigData/data-source/test/outlogs"));

            //7提交任务
            job.waitForCompletion(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
