package com.itstaredu.ch10;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author sam
 * 2018/11/1
 * 自定义序列化类
 */
public class FlowBean implements Writable {

    private long upFLow;
    private long downFlow;
    private long sumFLow;

    public FlowBean(long upFLow, long downFlow) {
        this.upFLow = upFLow;
        this.downFlow = downFlow;
        this.sumFLow = upFLow + downFlow;
    }


    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFLow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFLow = dataInput.readLong();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.upFLow);
        dataOutput.writeLong(this.downFlow);
        dataOutput.writeLong(this.sumFLow);
    }

    @Override
    public String toString() {
        return upFLow + "\t" + downFlow + "\t" + sumFLow;
    }

    public long getUpFLow() {
        return upFLow;
    }

    public void setUpFLow(long upFLow) {
        this.upFLow = upFLow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFLow() {
        return sumFLow;
    }

    public void setSumFLow(long sumFLow) {
        this.sumFLow = sumFLow;
    }
}
