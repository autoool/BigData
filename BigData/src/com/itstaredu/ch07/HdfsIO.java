package com.itstaredu.ch07;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author sam
 * 2018/10/26
 */
public class HdfsIO {

    FileSystem fileSystem;
    Configuration configuration;

    @Before
    public void before() throws URISyntaxException, IOException, InterruptedException {
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://192.168.243.129:9000/"), configuration, "root");
    }

    @After
    public void after() throws IOException {
        fileSystem.close();
    }

    @Test
    public void copyFileToHDFS() throws IOException {
        FileInputStream in = new FileInputStream(new File("E:/Personal/BigData/data-source/test/account.txt"));
        FSDataOutputStream out = fileSystem.create(new Path("/account.txt"));
        IOUtils.copyBytes(in, out, configuration);

        IOUtils.closeStream(in);
        IOUtils.closeStream(out);

    }

    @Test
    public void getFileFromHDFS() throws IOException {
        FSDataInputStream in = fileSystem.open(new Path("/account.txt"));
        FileOutputStream out = new FileOutputStream(new File("E:/Personal/BigData/data-source/test/accountserver.txt"));
        IOUtils.copyBytes(in, out, configuration);

        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }


}
