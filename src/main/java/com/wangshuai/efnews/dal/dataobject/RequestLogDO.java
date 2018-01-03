package com.wangshuai.efnews.dal.dataobject;

import com.wangshuai.efnews.dal.base.BaseDO;

/**
 * 访问日志对象
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 18:11
 */
public class RequestLogDO extends BaseDO {

    private static final long serialVersionUID = 9122445951738109289L;

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
     * 访问者地区
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
