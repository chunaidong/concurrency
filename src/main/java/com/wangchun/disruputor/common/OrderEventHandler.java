package com.wangchun.disruputor.common;

import com.lmax.disruptor.EventHandler;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/16 13:33
 *    desc    : 事件监听
 *    version : v1.0
 * </pre>
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        System.out.println("消费者："+orderEvent.getValue());
    }
}
