package com.wangshuai.efnews.manager.log;

import com.alibaba.fastjson.JSONObject;
import com.wangshuai.efnews.common.utils.AreaUtil;
import com.wangshuai.efnews.dal.dataobject.RequestLogDO;
import com.wangshuai.efnews.manager.RequestLogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * 日志队列消费者
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 19:08
 */
@Component
public class WriteLogToDatabaseTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteLogToDatabaseTask.class);

    private volatile boolean isClosed = false;

    /**
     * 待写入的日志队列
     */
    @Resource
    private RequestLogQueue requestLogQueue;

    @Resource
    private RequestLogManager requestLogManager;

    /**
     * 根据IP获取地理位置信息
     */
    @Resource
    private AreaUtil areaUtil;

    /**
     * 线程池
     */
    @Resource
    private Executor executor;

    @PostConstruct
    public void init() {
        executor.execute(() -> {
            while(!isClosed) {
                try {
                    String logJsonStr = requestLogQueue.take();

                    if(StringUtils.isEmpty(logJsonStr)) {
                        LOGGER.error("从队列中取到的日志为空");
                        continue;
                    }

                    RequestLogDO requestLogDO = JSONObject.parseObject(logJsonStr, RequestLogDO.class);

                    if(!StringUtils.isEmpty(requestLogDO)) {
                        String ip = requestLogDO.getIp();
                        String jsonStr = areaUtil.getAreaInfoByIp(ip);
                        LOGGER.info(jsonStr);
                        requestLogDO.setArea(jsonStr);
                    }

                    requestLogManager.insert(requestLogDO);
                } catch (Exception e) {
                    LOGGER.error("request log insert error", e);
                }


            }
        });
    }

    public void close() {
        this.isClosed = true;
    }

}
