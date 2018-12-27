package com.expressage.mapper;

import com.expressage.pojo.GoodsInformation;

public interface GoodsInformationMapper {
    int deleteByPrimaryKey(Integer xid);

    int insert(GoodsInformation record);

    int insertSelective(GoodsInformation record);

    GoodsInformation selectByPrimaryKey(Integer xid);

    int updateByPrimaryKeySelective(GoodsInformation record);

    int updateByPrimaryKey(GoodsInformation record);
}