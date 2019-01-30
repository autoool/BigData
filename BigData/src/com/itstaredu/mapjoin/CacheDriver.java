package com.itstaredu.mapjoin;

import com.itstaredu.outputformat.FileMapper;
import com.itstaredu.outputformat.FileReduce;
import com.itstaredu.outputformat.FuncFileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

/**
 * @author sam
 * 2018/11/15
 */
public class CacheDriver {

    public static void main(String[] args) {
        try {
            //1 获取job信息
            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration);

            //指定压缩方式
//            configuration.setBoolean("mapreduce.map.output.compress", true);
            configuration.setClass("maprecude.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);

            //2 获取jar
            job.setJarByClass(CacheDriver.class);

            //3 获取自定义mapper reducer 类
            job.setMapperClass(CacheMapper.class);
//            job.setReducerClass(FileReduce.class);

            //设置自定义读取方式

            //4 设置map输出数据类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            //5 设置reducer最终数据类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(NullWritable.class);

            //开启reducer的输出压缩
//            FileOutputFormat.setCompressOutput(job,true);
//            FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

            //设置自定义outputFormat
//            job.setOutputFormatClass(FuncFileOutputFormat.class);

            //设置combiner


            //设置自定义分区类
//            job.setPartitionerClass(WordCountPartitioner.class);
            //reducetask 必须大于指定分区数
//            job.setNumReduceTasks(4);


//            job.setInputFormatClass(CombineTextInputFormat.class);
//            CombineTextInputFormat.setMaxInputSplitSize(job, 1024 * 4);
//            CombineTextInputFormat.setMinInputSplitSize(job, 1024 * 3);

            //6 设置输入存在的路径与处理后的结果路径

            FileInputFormat.setInputPaths(job, new Path("E:/Personal/BigData/data-source/test/order/1118"));
            FileOutputFormat.setOutputPath(job, new Path("E:/Personal/BigData/data-source/test/orderoutput"));

            //加载缓存商品数据
            job.addCacheFile(new URI("file:///E:/Personal/BigData/data-source/test/order/product.txt"));

            //设置reducetask 数量
            job.setNumReduceTasks(0);

            //7提交任务
            job.waitForCompletion(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
