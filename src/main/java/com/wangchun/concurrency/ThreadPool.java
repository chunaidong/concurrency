package com.wangchun.concurrency;

import java.util.concurrent.*;

public class ThreadPool {


    public static void main(String[] args) {
        /**
         *1. corePoolSize 分为计算机密集型(CPU)=（CPU核数+1)||CPU核数 *2 和IO密集型(网络读写) CPU核数/（1-阻塞系数(0.8-0.9)）
         *2.maximumPoolSize 线程最大数据CPU核数 *2
         *3.空闲线程被回收时间
         *4.单位
         * 5.阻塞队列
         * 6.线程工程
         * 7.拒绝策略   在最大线程任务用完之后，如果再新来线程，处理的逻辑
         */
        ExecutorService executorService = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() + 1,
                Runtime.getRuntime().availableProcessors() * 2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("***业务线程池");
                        if (thread.isDaemon()) {
                            thread.setDaemon(false);
                        }
                        //取消优先级
                        if (Thread.NORM_PRIORITY != thread.getPriority()) {
                            thread.setPriority(Thread.NORM_PRIORITY);
                        }
                        return thread;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("主要做一些异常处理或者补救");
                    }
                }
        );


    }

}
