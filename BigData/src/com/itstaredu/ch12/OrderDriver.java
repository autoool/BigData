package com.itstaredu.ch12;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author sam
 * 2018/11/3
 */
public class OrderDriver {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance();
        job.setJarByClass(OrderDriver.class);

        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(OrderGroupingCamparator.class);

        job.setPartitionerClass(OrderPartitioner.class);
        job.setNumReduceTasks(3);
        //reducetask个数


        FileInputFormat.setInputPaths(job, new Path("E:/Personal/BigData/data-source/test/order/orders.txt"));
        FileOutputFormat.setOutputPath(job, new Path("E:/Personal/BigData/data-source/test/order/out"));

        job.waitForCompletion(true);


    }

}
