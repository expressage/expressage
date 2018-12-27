package com.expressage.mapper;

import com.expressage.pojo.Motorcade;

public interface MotorcadeMapper {
    int deleteByPrimaryKey(Integer motorcadeId);

    int insert(Motorcade record);

    int insertSelective(Motorcade record);

    Motorcade selectByPrimaryKey(Integer motorcadeId);

    int updateByPrimaryKeySelective(Motorcade record);

    int updateByPrimaryKey(Motorcade record);
}