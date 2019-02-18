package com.wangchun.disruputor.common;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/16 13:50
 *    desc    : 主程序
 *    version : v1.0
 * </pre>
 */
public class DisruptorMain {

    public static void main(String[] args) {

        //准备工作
        OrderEventFactory eventFactory = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;
        /**
         * 1.eventFactory:消息(Event)对象工厂
         * 2.ringBufferSize:容器的长度
         * 3.executors：线程池（建议使用自定义线程池）
         * 4.ProducerType：单生成者 还是 多生产者
         * 5.waitStrategy：等待策略
         */
        //1.实例化disruptor对象
        Disruptor disruptor =new  Disruptor(eventFactory,
                                            ringBufferSize,
                                            DaemonThreadFactory.INSTANCE,
                                            ProducerType.SINGLE,
                                            new BlockingWaitStrategy());
        //2.添加消费者的监听（构建disruptor与消费者的一个关联关系）
        disruptor.handleEventsWith(new OrderEventHandler());
        //3.启动
        disruptor.start();
        //4.获取实际存储数据的容器：RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer data = ByteBuffer.allocate(8);

        for(long i = 0 ; i < 100 ; i++){
            data.putLong(0,i);
            producer.sendData(data);
        }

        disruptor.shutdown();
    }

}
