package com.wangshuai.efnews.home.aspect;

import com.alibaba.fastjson.JSONObject;
import com.wangshuai.efnews.manager.log.RequestLogQueue;
import com.wangshuai.efnews.common.utils.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 访问统计切面
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-01 17:17
 */
@Aspect
@Component
public class RequestLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    private ThreadLocal<JSONObject> logJson = new ThreadLocal<>();

    @Resource
    private RequestLogQueue requestLogQueue;

    @Pointcut("execution(public * com.wangshuai.efnews.home.controller.*.*(..))")
    public void requestLog() {

    }

    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        String url = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String ip = RequestUtil.getIpAddr(request);
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        JSONObject json = new JSONObject();
        json.put("url", url);
        json.put("httpMethod", httpMethod);
        json.put("ip", ip);
        json.put("classMethod", classMethod);
        json.put("args", args);

        logJson.set(json);

        LOGGER.info("=======访问日志begin=======");
        LOGGER.info("URL : {}", url);
        LOGGER.info("HTTP_METHOD : {}", httpMethod);
        LOGGER.info("IP : {}", ip);
        LOGGER.info("CLASS_METHOD : {}", classMethod);
        LOGGER.info("ARGS : {}", args);
        LOGGER.info("=======访问日志end=======");

    }

    @AfterReturning(returning = "ret", pointcut = "requestLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        String response = ret.toString();
        String spendTime = String.valueOf(System.currentTimeMillis() - startTime.get());

        JSONObject json = logJson.get();
        json.put("response", response);
        json.put("spendTime", spendTime);

        requestLogQueue.offer(json.toJSONString());

        LOGGER.info("=======返回日志begin=======");
        if (response.length() > 40) {
            response = "**check**in**database**";
        }
        LOGGER.info("RESPONSE : {}", response);
        LOGGER.info("SPEND TIME : {}", spendTime);
        LOGGER.info("=======返回日志end=======");
    }


}

