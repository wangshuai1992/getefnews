package com.wangshuai.efnews.manager.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 访问日志记录队列
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-01 19:32
 */
@Component
public class RequestLogQueue {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogQueue.class);

    /**
     * 日志队列最大深度
     */
    private static final int MAX_SIZE = 50000;

    /**
     * 队列在redis中的key值
     */
    private static final String WEB_LOG_KEY = "web_log_queue";

    /**
     * redis操作
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 非阻塞式添加 如果满了 返回false
     *
     * @param logJsonStr
     * @return
     */
    public boolean offer(String logJsonStr) {
        if(redisTemplate.opsForList().size(WEB_LOG_KEY) >= MAX_SIZE) {
            LOGGER.error("日志队列已满， 插入失败");
            return false;
        }
        return redisTemplate.opsForList().leftPush(WEB_LOG_KEY, logJsonStr) > 0;
    }

    /**
     * 阻塞式消费
     *
     * @return
     */
    public String take() {
        return redisTemplate.opsForList().rightPop(WEB_LOG_KEY, 0, TimeUnit.SECONDS);
    }


}
