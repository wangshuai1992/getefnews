package com.wangshuai.efnews.dal.base;

import java.util.Collections;
import java.util.List;

/**
 * 分页查询
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 18:36
 */
public class PageQuery<T> extends BaseQuery {

    private static final long serialVersionUID = -4419019893917406718L;

    /**
     * 执行所有查询语句后返回的结果集
     */
    protected List<T> dataList;

    /**
     * 默认每页显示的记录数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 每页最多显示的记录数
     */
    public static final Integer MAX_PAGE_SIZE = 200;

    /**
     * 一页大小
     */
    protected Integer rows = new Integer(10);
    /**
     * 起始位置
     */
    protected Integer startPos = new Integer(0);
    /**
     * 总记录数
     */
    protected Integer totalRecord = new Integer(0);
    /**
     * 当前页数，从 1开始，1代表第一页
     */
    protected Integer page = 1;
    /**
     * 总页数
     */
    protected Integer totalPage = new Integer(0);

    /**
     * 获取一页的记录数
     *
     * @return
     */
    public Integer getPageSize() {
        if (rows < 1) {
            rows = DEFAULT_PAGE_SIZE;
        }
        return rows;
    }

    /**
     * 得到当前查询的第几页
     *
     * @return
     */
    public Integer getPageIndex() {
        if (page < 1) {
            page = 1;
        }
        return page;
    }

    /**
     * 写入pageIndex
     *
     * @param pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.page = pageNum;
    }

    /**
     * 写入pageSize
     *
     * @param numPerPage
     */
    public void setNumPerPage(Integer numPerPage) {
        this.rows = numPerPage;
    }

    /**
     * 设置一页的记录数
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.rows = pageSize;
    }

    /**
     * 是否有下页
     *
     * @return
     */
    public Boolean hasNextPage() {
        return page < getTotalPage();
    }

    /**
     * 设置当前页面
     *
     * @param pageIndex
     */
    public void setPageIndex(Integer pageIndex) {
        this.page = pageIndex;
    }

    /**
     * @return
     * @Title: getEndPos
     * @Description: 获取当前页的最后一行
     */
    public Integer getEndPos() {
        if (getPageIndex() * getPageSize() < getTotalRecord()) {
            return getPageIndex() * getPageSize();
        } else {
            return getTotalRecord();
        }
    }

    /**
     * @return the dataList
     */
    public List<T> getDataList() {
        if (dataList == null) {
            return Collections.<T>emptyList();
        } else {
            return dataList;
        }
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * @return the rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return the startPos
     */
    public Integer getStartPos() {
        return (getPageIndex() - 1) * getPageSize();
    }

    /**
     * @param startPos the startPos to set
     */
    public void setStartPos(Integer startPos) {
        this.startPos = startPos;
    }

    /**
     * @return the totalRecord
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * @param totalRecord the totalRecord to set
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalPage = (totalRecord + getPageSize() - 1) / getPageSize();
        this.totalRecord = totalRecord;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the totalPage
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}
