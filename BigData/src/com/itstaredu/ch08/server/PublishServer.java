package com.itstaredu.ch08.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @author sam
 * 2018/10/28
 */
public class PublishServer {

    public static void main(String[] args) throws IOException {
        /*
        1. 构建rpc框架
        2. 绑定地址
        3. 绑定端口号
        4.绑定协议
        5.调用协议实现类
        6.创建服务
         */
        RPC.Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost");
        builder.setPort(7777);
        builder.setProtocol(ClientNamenodeProtocol.class);
        builder.setInstance(new MyNameNode());
        RPC.Server server = builder.build();
        server.start();
    }
}
