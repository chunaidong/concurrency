package com.wangchun.concurrency.aotmic;

import com.wangchun.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/14 21:12
 *    desc    : 输入描述
 *    version : v1.0
 * </pre>
 */
@ThreadSafe
public class AotmicInteger {

    private static AtomicInteger aotmicInteger = new AtomicInteger();

    private static Logger log = LoggerFactory.getLogger(AotmicInteger.class);

    //请求总数
    private static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;


    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadTotal);
        for(int i = 0 ; i< clientTotal;i++){

            executorService.execute(()->{

                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){

                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info(aotmicInteger.get()+"");

    }

    public static void add(){

        aotmicInteger.incrementAndGet();

    }


}
