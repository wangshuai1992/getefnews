package com.wangshuai.efnews.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangshuai.efnews.manager.holder.NewsHolder;
import com.wangshuai.efnews.manager.http.HttpPool;
import com.wangshuai.efnews.manager.http.MessageHttpClient;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-30 16:15
 */
@Service
public class NewsImageManager {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsImageManager.class);

    /**
     * http操作
     */
    @Resource
    private HttpPool httpPool;

    /**
     * 线程池
     */
    @Resource
    private Executor executor;

    /**
     * 列表存储操作
     */
    @Resource
    private NewsHolder newsHolder;

    /**
     * URL
     */
    @Value("${efnews.image.url}")
    private String imageUrl;

    /**
     * 查看image的天数
     */
    @Value("${efnews.image.daycount}")
    private int dayCount;

    public List<String> getImageList() {
        return newsHolder.getImageList();
    }

    /**
     * 定时获取图片信息
     * news_20180101_01_1.jpg
     */
    @Scheduled(initialDelay = 10000, fixedRate = 30 * 60 * 1000)
    public void getImagesFromSite() {
        MessageHttpClient mhc = httpPool.getMhc();

        List<String> tempImageList = newsHolder.getTempImageList();

        CountDownLatch countDownLatch = new CountDownLatch(dayCount * 2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        tempImageList.clear();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -10);

        for (int day = 0; day < dayCount; day++) {
            Date date = calendar.getTime();
            String dateStr = sdf.format(date);

            //中文
            executor.execute(() -> {
                LOGGER.info(" ===> image task started === dateStr : {}", dateStr);

                try {
                    for (int index = 1; index < 10; index++) {
                        StringBuilder imageFileName = new StringBuilder();
                        imageFileName.append("news_").append(dateStr).append("_0").append(index).append("_1.jpg");

                        String url = imageUrl + imageFileName;
                        Map<String, String> headers = new HashMap<>();
                        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");

                        try {
                            Header[] respHeaders = mhc.doGetResponseHeader(url, headers, "utf-8");

                            boolean hasImage = false;
                            for (Header h : respHeaders) {
                                if ("Content-Type".equals(h.getName()) && "image/jpeg".equals(h.getValue())) {
                                    hasImage = true;

                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("fileName", imageFileName);
                                    jsonObject.put("url", url);
                                    jsonObject.put("date", dateStr);
                                    jsonObject.put("language", "zh_cn");

                                    tempImageList.add(jsonObject.toJSONString());
                                    LOGGER.info("image add ===> {}", jsonObject.toJSONString());
                                }
                            }

                            if (!hasImage) {
                                //结束递增文件名序号的循环
                                break;
                            }
                        } catch (Exception e) {
                            LOGGER.error("image http请求出错 === dateStr : " + dateStr, e);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("image task 执行出错", e);
                } finally {
                    countDownLatch.countDown();
                }

            });

            //英语
            executor.execute(() -> {
                LOGGER.info(" ===> image task started === dateStr : {}", dateStr);

                try{
                    for (int index = 1; index < 10; index++) {
                        StringBuilder imageFileName = new StringBuilder();
                        imageFileName.append("news_").append(dateStr).append("_0").append(index).append(".jpg");

                        String url = imageUrl + imageFileName;
                        Map<String, String> headers = new HashMap<>();
                        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");

                        try {
                            Header[] respHeaders = mhc.doGetResponseHeader(url, headers, "utf-8");

                            boolean hasImage = false;
                            for (Header h : respHeaders) {
                                if ("Content-Type".equals(h.getName()) && "image/jpeg".equals(h.getValue())) {
                                    hasImage = true;

                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("fileName", imageFileName);
                                    jsonObject.put("url", url);
                                    jsonObject.put("date", dateStr);
                                    jsonObject.put("language", "en_us");

                                    tempImageList.add(jsonObject.toJSONString());
                                    LOGGER.info("image add ===> {}", jsonObject.toJSONString());
                                }
                            }

                            if (!hasImage) {
                                //结束递增文件名序号的循环
                                break;
                            }
                        } catch (Exception e) {
                            LOGGER.error("image http请求出错 === dateStr : " + dateStr, e);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("image task 执行出错", e);
                } finally {
                    countDownLatch.countDown();
                }
            });

            calendar.add(Calendar.DATE, 1);
        }

        executor.execute(() -> {

            //等待任务执行结束
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                LOGGER.error("countDownLatch.await interrupted.", e);
            }

            LOGGER.info("所有image任务执行结束！");

            sortImageList(tempImageList);

            newsHolder.updateTempImageListToRedis();
            tempImageList.clear();
        });
    }

    /**
     * 对imageList根据日期排序
     *
     * @param list
     */
    private void sortImageList(List<String> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        list.sort((o1, o2) -> {
            JSONObject j1 = JSON.parseObject(o1);
            JSONObject j2 = JSON.parseObject(o2);

            String dateStr1 = j1.getString("date");
            String dateStr2 = j2.getString("date");

            Date date1 = new Date(0);
            Date date2 = new Date(0);
            try {
                date1 = sdf.parse(dateStr1);
                date2 = sdf.parse(dateStr2);
            } catch (ParseException e) {
                LOGGER.error("日期转换失败", e);
            }

            return date1.compareTo(date2);
        });

    }

}
