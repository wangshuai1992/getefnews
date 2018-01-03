package com.wangshuai.efnews.dal.query;

import com.wangshuai.efnews.dal.base.PageQuery;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 18:40
 */
public class RequestLogQuery<T> extends PageQuery<T> {

    private static final long serialVersionUID = -8517330741715768120L;

    /**
     * 访问URL
     */
    private String url;

    /**
     * http方法（GET POST 等）
     */
    private String httpMethod;

    /**
     * 访问者IP
     */
    private String ip;

    /**
     * 地区
     */
    private String area;

    /**
     * 访问方法
     */
    private String classMethod;

    /**
     * 传入参数
     */
    private String args;

    /**
     * 请求返回
     */
    private String response;

    /**
     * 请求消耗时间
     */
    private String spendTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
