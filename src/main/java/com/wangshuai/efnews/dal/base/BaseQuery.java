package com.wangshuai.efnews.dal.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础查询
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 18:33
 */
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 382937777454696093L;

    /**
     * 记录 id
     */
    protected Long id;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 修改时间
     */
    protected Date modifyTime;

    /**
     * 是否删除
     */
    protected Integer isDeleted = 0;

    /**
     * 备注
     */
    protected String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
