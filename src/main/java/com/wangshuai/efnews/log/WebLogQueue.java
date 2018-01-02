package com.wangshuai.efnews.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 访问日志记录队列
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-01 19:32
 */
public class WebLogQueue {

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>(MAX_SIZE);

    private static final int MAX_SIZE = 50000;

    /**
     * 单例
     */
    private static WebLogQueue instance = new WebLogQueue();

    private WebLogQueue() {

    }

    public static WebLogQueue getInstance() {
        return instance;
    }

    /**
     * 非阻塞式添加 如果满了 返回false
     *
     * @param logJsonStr
     * @return
     */
    public boolean offer(String logJsonStr) {
        return queue.offer(logJsonStr);
    }

    /**
     * 阻塞式消费
     *
     * @return
     * @throws InterruptedException
     */
    public String take() throws InterruptedException {
        return queue.take();
    }


}
