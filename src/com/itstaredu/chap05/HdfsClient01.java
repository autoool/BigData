package com.itstaredu.chap05;

import org.apache.hadoop.conf.Configuration;

/**
 * @author sam
 * 2018/10/23
 */
public class HdfsClient01 {

    public static void main(String[] args) {
        //客户端加载配置文件
        Configuration configuration = new Configuration();
        //置顶配置
        configuration.set("dfs.replication","2");
        //置顶块大小
        configuration.set("dfs.blocksize","64m");

    }
}
