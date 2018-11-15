package com.itstaredu.ch14.funcinput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author sam
 * 2018/11/11
 */
public class FuncReaordReader extends RecordReader<NullWritable, BytesWritable> {

    boolean isProcess = false;
    FileSplit fileSplit;
    Configuration configuration;
    BytesWritable value = new BytesWritable();

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        //初始化文件切片
        this.fileSplit = (FileSplit) split;
        //初始化配置信息
        configuration = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!isProcess) {
            // 1 根据切片长度创建缓冲区
            byte[] buf = new byte[(int) fileSplit.getLength()];
            FSDataInputStream fin = null;
            FileSystem fs = null;
            //2. 获取路径
            Path path = fileSplit.getPath();

            //3. 根据路径获取文件系统
            fs = path.getFileSystem(configuration);

            //4. 拿到输出流


            //5. 数据拷贝

            //6. 拷贝缓存到最终输出

        }
        return false;
    }

    @Override
    public NullWritable getCurrentKey() throws IOException, InterruptedException {
        return NullWritable.get();
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
