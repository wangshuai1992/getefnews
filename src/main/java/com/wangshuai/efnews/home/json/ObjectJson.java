package com.wangshuai.efnews.home.json;

/**
 * @author wangshuai
 */
public class ObjectJson<T> extends BaseJson {

    private T data;

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

}
