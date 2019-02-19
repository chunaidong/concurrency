package com.wangchun.disruputor.muilty;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //1.传教ringbuffer容器
        RingBuffer<Order> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                new EventFactory<Order>() {
                    @Override
                    public Order newInstance() {
                        return new Order();
                    }
                },1024*1024,new YieldingWaitStrategy());
        //2.创建Barrier屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        //3.构建多消费者
        Consumer[]consumers = new  Consumer[10];
        for(int i =0;i<consumers.length;i++){
            consumers[i] = new Consumer("C"+i);
        }
        //4.构建多消费者工作池
        WorkerPool<Order> workerPool = new WorkerPool<Order>(
                ringBuffer,
                sequenceBarrier,
                new EventExceptionHandler(),
                consumers
        );
        //5.设置多个消费者的sequence序号 用于单独统计消费进度，并且设置到ringbuffer中
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        //6.启动workpool
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        workerPool.start(executorService);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i = 0;i<100;i++){
            Producer producer = new Producer(ringBuffer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                     try {
                          countDownLatch.await();
                     }catch (Exception e){
                         e.printStackTrace();
                     }
                     for(int j = 0 ;j<100 ;j++){
                         producer.sendData(UUID.randomUUID().toString());
                     }
                }
            }).start();
        }

        Thread.sleep(2000);
        System.out.println("----------线程创建完毕，开始生成数据---------");
        countDownLatch.countDown();
        Thread.sleep(10000);
        System.out.println("第三个消费者处理的任务总数：" +consumers[2].getCount());
        executorService.shutdown();
    }

    static class EventExceptionHandler implements ExceptionHandler<Order>{

        @Override
        public void handleEventException(Throwable throwable, long l, Order order) {

        }

        @Override
        public void handleOnStartException(Throwable throwable) {

        }

        @Override
        public void handleOnShutdownException(Throwable throwable) {

        }
    }

}
