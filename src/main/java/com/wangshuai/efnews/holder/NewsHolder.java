package com.wangshuai.efnews.holder;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-30 00:51
 */
@Component
public class NewsHolder {

    /**
     * redis操作
     */
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * detail_list在redis中的key
     */
    private static final String DETAIL_LIST_KEY = "detail_list";

    /**
     * image_list在redis中的key
     */
    private static final String IMAGE_LIST_KEY = "image_list";

    /**
     * 内存中的临时detail_list
     */
    private List<String> tempDetailList = Collections.synchronizedList(new LinkedList<>());

    /**
     * 内存中的临时image_list
     */
    private List<String> tempImageList = Collections.synchronizedList(new LinkedList<>());

    /**
     * 从redis中获取detail_list
     *
     * @return
     */
    public List<String> getDetailList() {
        String jsonstr = redisTemplate.opsForValue().get(DETAIL_LIST_KEY);
        return JSON.parseArray(jsonstr, String.class);
    }

    /**
     * 从redis中获取image_list
     *
     * @return
     */
    public List<String> getImageList() {
        String jsonstr = redisTemplate.opsForValue().get(IMAGE_LIST_KEY);
        return JSON.parseArray(jsonstr, String.class);
    }

    public List<String> getTempDetailList() {
        return tempDetailList;
    }

    public List<String> getTempImageList() {
        return tempImageList;
    }

    /**
     * 将临时list更新到redis
     */
    public void updateTempDetailListToRedis() {
        String jsonStr = JSON.toJSONString(tempDetailList);
        redisTemplate.opsForValue().set(DETAIL_LIST_KEY, jsonStr);
    }

    /**
     * 将临时list更新到redis
     */
    public void updateTempImageListToRedis() {
        String jsonStr = JSON.toJSONString(tempImageList);
        redisTemplate.opsForValue().set(IMAGE_LIST_KEY, jsonStr);
    }

}
