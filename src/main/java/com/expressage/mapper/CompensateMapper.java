package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.Compensate;

public interface CompensateMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Compensate record);

    int insertSelective(Compensate record);

    Compensate selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Compensate record);

    int updateByPrimaryKey(Compensate record);
    
    List<Compensate>pmlSelectWage();
    
    
}