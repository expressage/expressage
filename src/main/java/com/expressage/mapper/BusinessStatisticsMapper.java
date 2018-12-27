package com.expressage.mapper;

import com.expressage.pojo.BusinessStatistics;

public interface BusinessStatisticsMapper {
    int insert(BusinessStatistics record);

    int insertSelective(BusinessStatistics record);
}