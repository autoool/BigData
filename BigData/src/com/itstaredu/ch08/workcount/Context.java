package com.itstaredu.ch08.workcount;

import java.util.HashMap;

/**
 * @author sam
 * 2018/10/28
 * 数据传输的类
 * 封装数据
 * 集合
 * <单词，数量>
 */
public class Context {

    private HashMap<Object, Object> contextMap = new HashMap<>();

    public void wrtie(Object key, Object value) {
        contextMap.put(key, value);
    }

    public Object get(Object key) {
        return contextMap.get(key);
    }

    public HashMap<Object, Object> getContextMap() {
        return contextMap;
    }
}
