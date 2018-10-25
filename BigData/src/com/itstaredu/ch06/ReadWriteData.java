package com.itstaredu.ch06;

import com.itstaredu.util.LogUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;

public class ReadWriteData {

    FileSystem fsystem;

    @Before
    public void before() {
        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", "hdfs://192.168.243.129:9000");
//        configuration.set("fs.replication", "2");
//        configuration.set("fs.blocksize", "64m");
        // configuration.set("fs.hdfs.impl",
        // "org.apache.hadoop.hdfs.DistributedFileSystem");
        try {
            fsystem = FileSystem.get(new URI("hdfs://192.168.243.129:9000"), configuration, "root");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    @After
    public void After() throws Exception {
        fsystem.close();
    }

    @Test
    public void testReadData() throws IOException {
        FSDataInputStream in = fsystem.open(new Path("/a.txt"));
        byte[] buf = new byte[1024];
        in.read(buf);
        System.out.println(new String(buf));
        in.close();
    }

    @Test
    public void testReadData02() throws Exception {
        FSDataInputStream in = fsystem.open(new Path("/a.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
        String line = null;
        String result = "";
        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println(result);
        br.close();
        in.close();
    }

    /**
     * 读取指定偏移量
     *
     * @throws Exception
     */
    @Test
    public void readOffset() throws Exception {
        FSDataInputStream in = fsystem.open(new Path("/a.txt"));
        in.seek(14);
        byte[] buf = new byte[4];
        in.read(buf);
        LogUtils.log(new String(buf));
        in.close();
    }

    @Test
    public void testWriteData() throws IOException {
        FSDataOutputStream out = fsystem.create(new Path("/sales.txt"), true);
        FileInputStream fileInputStream = new FileInputStream("E:/Personal/BigData/data-source/test/sales.txt");
        byte[] buf = new byte[1024];
        int read = 0;
        while ((read = fileInputStream.read(buf)) != -1) {
            out.write(buf, 0, read);
        }
        fileInputStream.close();
        out.close();
        IOUtils.closeStream(out);
    }

}
