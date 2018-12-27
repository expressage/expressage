package com.expressage.mapper;

import com.expressage.pojo.Ware;

public interface WareMapper {
    int deleteByPrimaryKey(Integer wareId);

    int insert(Ware record);

    int insertSelective(Ware record);

    Ware selectByPrimaryKey(Integer wareId);

    int updateByPrimaryKeySelective(Ware record);

    int updateByPrimaryKey(Ware record);
}