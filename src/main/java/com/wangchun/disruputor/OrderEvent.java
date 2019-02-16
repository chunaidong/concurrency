package com.wangchun.disruputor;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/16 13:26
 *    desc    : 被消费对象
 *    version : v1.0
 * </pre>
 */
public class OrderEvent {


    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
