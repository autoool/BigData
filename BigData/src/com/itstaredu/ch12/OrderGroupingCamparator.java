package com.itstaredu.ch12;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author sam
 * 2018/11/3
 */
public class OrderGroupingCamparator extends WritableComparator {

    protected OrderGroupingCamparator() {
        super(OrderBean.class, true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean first = (OrderBean) a;
        OrderBean second = (OrderBean) b;
        if (first.getOrderId() > second.getOrderId()) {
            return 1;
        } else if (first.getOrderId() < second.getOrderId()) {
            return -1;
        } else {
            return 0;
        }

    }
}
