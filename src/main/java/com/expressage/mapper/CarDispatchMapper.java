package com.expressage.mapper;

import com.expressage.pojo.CarDispatch;

public interface CarDispatchMapper {
    int deleteByPrimaryKey(Integer carDispatchId);

    int insert(CarDispatch record);

    int insertSelective(CarDispatch record);

    CarDispatch selectByPrimaryKey(Integer carDispatchId);

    int updateByPrimaryKeySelective(CarDispatch record);

    int updateByPrimaryKey(CarDispatch record);
}