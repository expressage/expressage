package com.expressage.mapper;

import com.expressage.pojo.Power;

public interface PowerMapper {
    int insert(Power record);

    int insertSelective(Power record);
}