package com.wangshuai.efnews.dal.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础DO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 18:15
 */
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -2996580813877921996L;

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Integer isDeleted;

    private String remark;

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
