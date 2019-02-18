package com.wangchun.disruputor.higher;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

public class Handler1 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
          trade.setId(UUID.randomUUID().toString());
          Thread.sleep(1000);
         System.out.println("" +
                "设置IDH1====="+l+"======="+b);
    }
}
