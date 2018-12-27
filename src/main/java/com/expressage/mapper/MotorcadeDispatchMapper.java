package com.expressage.mapper;

import com.expressage.pojo.MotorcadeDispatch;

public interface MotorcadeDispatchMapper {
    int deleteByPrimaryKey(Integer motorcadeDispatchId);

    int insert(MotorcadeDispatch record);

    int insertSelective(MotorcadeDispatch record);

    MotorcadeDispatch selectByPrimaryKey(Integer motorcadeDispatchId);

    int updateByPrimaryKeySelective(MotorcadeDispatch record);

    int updateByPrimaryKey(MotorcadeDispatch record);
}