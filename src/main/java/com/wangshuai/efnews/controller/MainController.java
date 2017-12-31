package com.wangshuai.efnews.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangshuai.efnews.manager.NewsDetailManager;
import com.wangshuai.efnews.manager.NewsImageManager;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        for(String detail : newsDetailManager.getDetailList()) {
            System.out.println(detail);
        }

        for(String image : newsImageManager.getImageList()) {
            System.out.println(image);
        }

        JSONArray json1 = (JSONArray)JSONArray.toJSON(newsDetailManager.getDetailList());
        JSONArray json2 = (JSONArray)JSONArray.toJSON(newsImageManager.getImageList());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detail", json1);
        jsonObject.put("image", json2);

        return jsonObject;
    }
}
