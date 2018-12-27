package com.expressage.mapper;

import com.expressage.pojo.OutInfo;

public interface OutInfoMapper {
    int deleteByPrimaryKey(Integer oiid);

    int insert(OutInfo record);

    int insertSelective(OutInfo record);

    OutInfo selectByPrimaryKey(Integer oiid);

    int updateByPrimaryKeySelective(OutInfo record);

    int updateByPrimaryKey(OutInfo record);
}