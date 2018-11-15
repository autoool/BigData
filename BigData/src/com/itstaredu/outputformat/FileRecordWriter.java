package com.itstaredu.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author sam
 * 2018/11/15
 */
public class FileRecordWriter extends RecordWriter<Text, NullWritable> {

    Configuration configuration = null;
    FSDataOutputStream output = null;
    FSDataOutputStream outputOther = null;

    //1. 定义数据输出路径

    public FileRecordWriter(TaskAttemptContext job) throws IOException {
        //获取配置信息
        configuration = job.getConfiguration();

        //获取文件系统
        FileSystem fileSystem = FileSystem.get(configuration);

        //定义输出路径
        output = fileSystem.create(new Path("e:/output/itstar.logs"));
        outputOther = fileSystem.create(new Path("e:/outputother/itstar.logs"));
    }

    //2. 数据输出
    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        //判断的话根据key进行
        if (key.toString().contains("google")) {
            output.write(key.getBytes());
        } else {
            outputOther.write(key.getBytes());
        }
    }


    //3.关闭资源
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        if (null != output) {
            output.close();
        }
        if (null != outputOther) {
            outputOther.close();
        }
    }
}
