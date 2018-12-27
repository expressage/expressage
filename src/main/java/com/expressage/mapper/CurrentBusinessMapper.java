package com.expressage.mapper;

import com.expressage.pojo.CurrentBusiness;

public interface CurrentBusinessMapper {
    int insert(CurrentBusiness record);

    int insertSelective(CurrentBusiness record);
}