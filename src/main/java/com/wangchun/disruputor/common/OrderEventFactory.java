package com.wangchun.disruputor.common;

import com.lmax.disruptor.EventFactory;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/16 13:31
 *    desc    : 创建实例工厂
 *    version : v1.0
 * </pre>
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
