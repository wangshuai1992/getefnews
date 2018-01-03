package com.wangshuai.efnews.dal.mapper;

import com.wangshuai.efnews.dal.dataobject.RequestLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-02 18:10
 */
@Mapper
public interface RequestLogMapper {

    int insert(RequestLogDO requestLogDO);

}
