package com.wangchun.disruputor.higher;

import com.lmax.disruptor.EventHandler;

public class Handler2 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        trade.setName("猪小宝");
        Thread.sleep(2000);
        System.out.println("设置名称H2====="+l +"======"+b);
    }
}
