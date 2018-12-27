package com.expressage.mapper;

import com.expressage.pojo.CarType;

public interface CarTypeMapper {
    int deleteByPrimaryKey(Integer ctid);

    int insert(CarType record);

    int insertSelective(CarType record);

    CarType selectByPrimaryKey(Integer ctid);

    int updateByPrimaryKeySelective(CarType record);

    int updateByPrimaryKey(CarType record);
}