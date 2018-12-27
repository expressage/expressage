package com.expressage.mapper;

import com.expressage.pojo.Distribute;

public interface DistributeMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Distribute record);

    int insertSelective(Distribute record);

    Distribute selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Distribute record);

    int updateByPrimaryKey(Distribute record);
}