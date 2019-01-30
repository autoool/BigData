package com.itstaredu.mapjoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author sam
 * 2018/11/15
 * 商品表加载到内存中，然后数据在map端输出前进行替换
 */
public class CacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {


    //1. 商品表加到内存
   private  HashMap<String, String> pdMap;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        pdMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(context.getCacheFiles()[0].getPath()), "utf-8"));
        String line;
        while (StringUtils.isNotEmpty(line = bufferedReader.readLine())) {
            String[] fileds = line.split(",");
            pdMap.put(fileds[0], fileds[1]);
        }
        System.out.println(pdMap.get("01"));
        bufferedReader.close();
    }


    //2.map传输

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fileds = line.split(",");
        String pid = fileds[1];
        /*
        map里面明明有01 - 苹果  为啥拿不到？
         */
        String pName = pdMap.get(pid);
        line = line + "," + pName;
        context.write(new Text(line), NullWritable.get());
    }
}
