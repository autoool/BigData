package com.itstaredu.ch08.server;

/**
 * @author sam
 * 2018/10/28
 */
public class MyNameNode implements ClientNamenodeProtocol {



    @Override
    public String getMetaData(String path) {
        return path+"  blek01";
    }
}
