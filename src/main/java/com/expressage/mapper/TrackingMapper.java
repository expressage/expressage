package com.expressage.mapper;

import com.expressage.pojo.Tracking;

public interface TrackingMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Tracking record);

    int insertSelective(Tracking record);

    Tracking selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Tracking record);

    int updateByPrimaryKey(Tracking record);
}