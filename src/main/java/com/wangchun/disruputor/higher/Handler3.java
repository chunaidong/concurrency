package com.wangchun.disruputor.higher;

import com.lmax.disruptor.EventHandler;

public class Handler3 implements EventHandler<Trade> {
    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {

        System.out.println("H3打印信息：：：ID "+trade.getId()+":::name"+trade.getName()
        +"::::price==="+trade.getPrice() +"======l===="+l+"======="+b);
    }
}
