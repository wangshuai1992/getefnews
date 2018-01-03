package com.wangshuai.efnews.manager;

import com.wangshuai.efnews.dal.dataobject.RequestLogDO;
import com.wangshuai.efnews.dal.mapper.RequestLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-03 09:31
 */
@Service
public class RequestLogManager {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogManager.class);

    @Resource
    private RequestLogMapper requestLogMapper;


    public boolean insert(RequestLogDO requestLogDO) {
        return requestLogMapper.insert(requestLogDO) > 0;
    }
}
