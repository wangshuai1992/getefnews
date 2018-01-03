package com.wangshuai.efnews.common.utils;

import com.alibaba.fastjson.JSON;
import com.wangshuai.efnews.manager.http.HttpPool;
import com.wangshuai.efnews.manager.http.MessageHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据IP获取地区信息
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-03 10:43
 */
@Component
public class AreaUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaUtil.class);

    /**
     * 地区服务接口地址
     */
    private static final String API_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    /**
     * redis缓存前缀
     */
    private static final String REDIS_KEY_PREFIX = "IP_INFO:";

    /**
     * 上次接口请求时间
     */
    private long lastReqTime = 0L;

    /**
     * 缓存过期时间(10天)
     */
    private static final long EXPIRE_TIME = 10 * 24 * 60 * 60 * 1000;

    /**
     * 缓存数据，同一IP不重复查询地理信息
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * http操作
     */
    @Resource
    private HttpPool httpPool;

    public String getAreaInfoByIp(String ip) {
        try {
            String redisKey = REDIS_KEY_PREFIX + ip;
            if (redisTemplate.hasKey(redisKey)) {
                return redisTemplate.opsForValue().get(redisKey);
            }

            MessageHttpClient mhc = httpPool.getMhc();

            Map<String, String> headers = new HashMap<>();
            headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");

//            long now = System.currentTimeMillis();
//            if(now - lastReqTime < 5000) {
//                //两次接口请求最少间隔5秒
//                Thread.sleep(5000);
//            }
            long begin = System.currentTimeMillis();

            String resp = unicode2String(mhc.doPost(API_URL + ip, null, headers, "utf-8"));
            lastReqTime = System.currentTimeMillis();

            long end = System.currentTimeMillis();
            LOGGER.info("地理信息接口请求时间 ======>  {}", (end - begin));

            String result = JSON.parseObject(resp).getString("data");
            redisTemplate.opsForValue().setIfAbsent(redisKey, result);
            redisTemplate.expire(redisKey, EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return result;
        } catch (Exception e) {
            LOGGER.error("根据IP查询地理位置信息出错", e);
            return "";
        }
    }

    /**
     * unicode 转字符串
     */
    private String unicode2String(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

}
