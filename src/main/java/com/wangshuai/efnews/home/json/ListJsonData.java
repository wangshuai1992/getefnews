package com.wangshuai.efnews.home.json;

import java.util.List;

/**
 * @author wangshuai
 */
public class ListJsonData<T> extends BaseJson {

    /**
     * 对象
     */
    private List<T> datalist;
    /**
     * 记录数
     */
    private Integer count;

    /**
     * @return the datalist
     */
    public List<T> getDatalist() {
        return datalist;
    }

    /**
     * @param datalist the datalist to set
     */
    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}
