package com.wangshuai.efnews.holder;

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
public class NewsHolder {

    private static List<String> detailList = new LinkedList<>();

    private static List<String> tempDetailList = Collections.synchronizedList(new LinkedList<>());

    private static List<String> imageList = new LinkedList<>();

    private static List<String> tempImageList = Collections.synchronizedList(new LinkedList<>());

    private NewsHolder() {

    }

    public static List<String> getDetailList() {
        return detailList;
    }

    public static List<String> getImageList() {
        return imageList;
    }

    public static List<String> getTempDetailList() {
        return tempDetailList;
    }

    public static List<String> getTempImageList() {
        return tempImageList;
    }

}
