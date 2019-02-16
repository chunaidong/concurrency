package com.wangchun.concurrency.proconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/16 10:03
 *    desc    : 产品
 *    version : v1.0
 * </pre>
 */
public class Mac {


    private static final int MAX_INT = 10;

    private ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(MAX_INT);

    public void take(){
        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void make(){
        try {
            blockingQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
