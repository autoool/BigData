package com.itstaredu.ch12;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author sam
 * 2018/11/3
 */
public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        String[] filed = line.split("\t");

        Integer orderId = Integer.parseInt(filed[0]);
        Double price = Double.parseDouble(filed[2]);
        OrderBean orderBean = new OrderBean(orderId, price);

        context.write(orderBean, NullWritable.get());

    }
}
