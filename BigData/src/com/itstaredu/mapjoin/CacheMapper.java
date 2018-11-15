package com.itstaredu.mapjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author sam
 * 2018/11/15
 * 商品表加载到内存中，然后数据在map端输出前进行替换
 */
public class CacheMapper extends Mapper<LongWritable,Text,Text,NullWritable>{

    //1. 商品表加到内存

    //2.map传输

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
    }
}
