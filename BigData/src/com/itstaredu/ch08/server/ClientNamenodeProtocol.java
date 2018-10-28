package com.itstaredu.ch08.server;

/**
 * @author sam
 * 2018/10/28
 */
public interface ClientNamenodeProtocol {

    /**
     * 1 定义协议id
     * 2 定义方法获取元数据方式
     */

    public static final long versionID = 1L;

    public String getMetaData(String path);

}
