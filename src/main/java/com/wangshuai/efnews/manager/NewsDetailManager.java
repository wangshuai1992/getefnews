package com.wangshuai.efnews.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangshuai.efnews.manager.holder.NewsHolder;
import com.wangshuai.efnews.manager.http.HttpPool;
import com.wangshuai.efnews.manager.http.MessageHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2017-12-30 01:22
 */
@Service
public class NewsDetailManager {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDetailManager.class);

    /**
     * 匹配标题的正则表达式
     */
    private Pattern titlePattern = Pattern.compile("<span id=\"news-title\"><a href=\"#\">.*?</a></span>");

    /**
     * 匹配时间的正则表达式
     */
    private Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    /**
     * 匹配图片路径的正则表达式
     */
    private Pattern imagePattern = Pattern.compile("<img src=\".*?\">");

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
    @Value("${efnews.detail.url}")
    private String detailUrl;

    /**
     * start_seq
     */
    @Value("${efnews.detail.startseq}")
    private int startSeq;

    /**
     * end_seq
     */
    @Value("${efnews.detail.endseq}")
    private int endSeq;

    /**
     * mission_per_thread
     */
    @Value("${efnews.detail.missionpertask}")
    private int missionPerTask;

    public List<String> getDetailList() {
        return newsHolder.getDetailList();
    }

    /**
     * 定时从网站获取信息
     */
    @Scheduled(initialDelay = 10000, fixedRate = 30 * 60 * 1000)
    public void getNewsFromSite() {
        MessageHttpClient mhc = httpPool.getMhc();

        List<String> tempDetailList = newsHolder.getTempDetailList();

        int taskCount = (endSeq - startSeq) / missionPerTask + 1;

        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        tempDetailList.clear();
        int index = startSeq;

        while (--taskCount >= 0) {
            int tempStart = index;
            int end = index + missionPerTask;

            executor.execute(() -> {
                LOGGER.info(" ===> detail task started === {} - {}", tempStart, end - 1);
                for (int i = tempStart; i < end; i++) {
                    String url = detailUrl + i;
                    Map<String, String> headers = new HashMap<>();
                    headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");

                    String html = mhc.doGet(url, headers, "utf-8");

                    if (html.contains("无尽的边界") || html.contains("复活") || html.contains("Thank you.")) {
                        JSONObject json = new JSONObject();

                        String title = "";
                        String date = "";
                        String imgUrl = "";

                        Matcher matcher = titlePattern.matcher(html);
                        if (matcher.find()) {
                            title = html.substring(matcher.start(), matcher.end())
                                    .replace("<span id=\"news-title\"><a href=\"#\">", "")
                                    .replace("</a></span>", "");
                        }

                        matcher = datePattern.matcher(html);
                        if (matcher.find()) {
                            date = html.substring(matcher.start(), matcher.end());
                        }

                        matcher = imagePattern.matcher(html);
                        if (matcher.find()) {
                            imgUrl = html.substring(matcher.start(), matcher.end())
                                    .replace("<img src=\"", "")
                                    .replace("\">", "");
                        }

                        json.put("title", title);
                        json.put("date", date.replaceAll("-", ""));
                        json.put("url", url);
                        json.put("imgUrl", imgUrl);

                        String str = json.toJSONString();
                        tempDetailList.add(str);
                        LOGGER.info("detail add ===> {}", str);
                    }
                }
                countDownLatch.countDown();
            });
            index += missionPerTask;
        }

        executor.execute(() -> {

            //等待任务执行结束
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                LOGGER.error("countDownLatch.await interrupted.", e);
            }

            LOGGER.info("所有detail任务执行结束！");

            sortDetailList(tempDetailList);

            newsHolder.updateTempDetailListToRedis();
        });
    }

    /**
     * 对detailList根据日期排序
     *
     * @param list
     */
    private void sortDetailList(List<String> list) {
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
