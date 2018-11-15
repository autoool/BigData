package com.itstaredu.ch10;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author sam
 * 2018/11/3
 */
public class FlowBeanCom implements WritableComparable<FlowBean> {

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

    }

    /**
     * 实现排序接口
     * @param o
     * @return
     */
    @Override
    public int compareTo(FlowBean o) {
        return 0;
    }
}
