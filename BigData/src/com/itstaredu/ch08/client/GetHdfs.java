package com.itstaredu.ch08.client;

import com.itstaredu.ch08.server.ClientNamenodeProtocol;
import com.itstaredu.util.LogUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author sam
 * 2018/10/28
 */
public class GetHdfs {

    public static void main(String[] args) throws IOException {
        /**
         * RPC通信客户端
         * 1 拿到协议
         *
         * 2 发送请求
         * 3 拿到元数据
         */
        ClientNamenodeProtocol proxy = RPC.getProxy(ClientNamenodeProtocol.class, ClientNamenodeProtocol.versionID, new InetSocketAddress("localhost", 7777), new Configuration());
        String metaData = proxy.getMetaData("/newdir");
        LogUtils.log(metaData);
    }
}
