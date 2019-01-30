package com.itstaredu.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author sam
 * 2018/11/18
 */
public class TestCompress {

    public static void main(String[] args) {
        Compress("E:\\Personal\\BigData\\data-source\\test\\accountserver.txt","org.apache.hadoop.io.compress.DefaultCodec");

    }

    private static void Compress(String filename, String className) {
        try {
            //获取输入流
            FileInputStream fileInputStream = new FileInputStream(new File(filename));
            Class claName = Class.forName(className);
            CompressionCodec codeC = (CompressionCodec) ReflectionUtils.newInstance(claName, new Configuration());

            //创建输出流
            FileOutputStream fos = new FileOutputStream(new File(filename + codeC.getDefaultExtension()));
            //创建压缩输出流
            CompressionOutputStream cos = codeC.createOutputStream(fos);

            //copy流
            IOUtils.copyBytes(fileInputStream, cos, 1024 * 1024 * 2, false);

            //关闭资源
            fileInputStream.close();
            cos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
