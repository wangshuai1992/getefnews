package com.wangshuai.efnews.home.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangshuai.efnews.manager.NewsDetailManager;
import com.wangshuai.efnews.manager.NewsImageManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @since 2017-08-27 13:24
 */
@Controller
public class MainController {

    @Resource
    private NewsDetailManager newsDetailManager;

    @Resource
    private NewsImageManager newsImageManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/")
    public String index(Model modelMap) {
        return "main";
    }

    @RequestMapping("/getDetailList")
    @ResponseBody
    public Object getDetailList() {
        return newsDetailManager.getDetailList();
    }

    @RequestMapping("/getImageList")
    @ResponseBody
    public Object getImageList() {
        return newsImageManager.getImageList();
    }

    @RequestMapping("/getTableData")
    @ResponseBody
    public Object getTableData(String language) {
        if(StringUtils.isEmpty(language)) {
            language = "zh_cn";
        }

        JSONArray details = (JSONArray) JSONArray.toJSON(newsDetailManager.getDetailList());
        JSONArray images = (JSONArray) JSONArray.toJSON(newsImageManager.getImageList());

        JSONArray datas = new JSONArray();

        for (Object o : images) {
            JSONObject image = JSON.parseObject((String) o);

            if(!language.equals(image.getString("language"))) {
                continue;
            }

            String date = image.getString("date");
            String imgUrl = image.getString("url");
            JSONObject detail = searchDetailByImageUrl(imgUrl, details);

            String title = detail == null ? "" : detail.getString("title");
            String detailUrl = detail == null ? "" : detail.getString("url");

            JSONObject data = new JSONObject();
            data.put("date", date);
            data.put("imgUrl", imgUrl);
            data.put("title", title);
            data.put("detailUrl", detailUrl);

            datas.add(data);
        }

        return datas;
    }

    private JSONObject searchDetailByImageUrl(String imageUrl, JSONArray details) {
        for (Object o : details) {
            JSONObject detail = JSON.parseObject((String) o);

            String url = detail.getString("imgUrl");

            if(imageUrl.equals(url)) {
                return detail;
            }
        }
        return null;
    }
}
