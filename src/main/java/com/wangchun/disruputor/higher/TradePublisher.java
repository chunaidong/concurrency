package com.wangchun.disruputor.higher;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

public class TradePublisher implements Runnable {

    private Disruptor<Trade> disruptor;
    private CountDownLatch countDownLatch;

    public TradePublisher(Disruptor<Trade> disruptor, CountDownLatch countDownLatch) {
       this.disruptor = disruptor;
       this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        disruptor.publishEvent(new TradeEvent());
        countDownLatch.countDown();
    }
}

class  TradeEvent implements EventTranslator<Trade>{
    @Override
    public void translateTo(Trade trade, long l) {
        trade.setPrice(22.22);
    }
}

