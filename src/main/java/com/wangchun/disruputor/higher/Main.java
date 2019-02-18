package com.wangchun.disruputor.higher;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);


        //1.创建Disruptor
        Disruptor<Trade> disruptor = new Disruptor<Trade>(
                new EventFactory<Trade>() {
                    @Override
                    public Trade newInstance() {
                        return new Trade();
                    }
                }, 1024 * 1024,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy()
        );

        //2.Disruptor关联消费者
        //串联
        // disruptor.handleEventsWith(new Handler1()).handleEventsWith(new Handler2())
          //       .handleEventsWith(new Handler3());
         //并联
        disruptor.handleEventsWith(new Handler1(),new Handler2(),new Handler3());
        //3.启动disruptor
        disruptor.start();

        //创建提供者
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executorService.submit(new TradePublisher(disruptor,countDownLatch));


        countDownLatch.await();
        disruptor.shutdown();
        executorService.shutdown();

    }


}
