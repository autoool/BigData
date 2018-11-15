package com.itstaredu.ch12;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author sam
 * 2018/11/3
 */
public class OrderBean implements WritableComparable<OrderBean> {

    private int orderId;
    private double price;

    public OrderBean() {
        super();
    }

    public OrderBean(int orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(orderId);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readInt();
        this.price = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return orderId + "    " + price;
    }

    @Override
    public int compareTo(OrderBean o) {
        if (orderId > o.orderId) {
            return 1;
        } else if (orderId < o.orderId) {
            return -1;
        } else {
            return price > o.price ? -1 : 1;
        }
    }
}
