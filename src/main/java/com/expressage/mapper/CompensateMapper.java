package com.expressage.mapper;

import com.expressage.pojo.Compensate;

public interface CompensateMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Compensate record);

    int insertSelective(Compensate record);

    Compensate selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Compensate record);

    int updateByPrimaryKey(Compensate record);
}