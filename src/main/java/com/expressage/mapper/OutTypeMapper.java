package com.expressage.mapper;

import com.expressage.pojo.OutType;

public interface OutTypeMapper {
    int deleteByPrimaryKey(Integer otid);

    int insert(OutType record);

    int insertSelective(OutType record);

    OutType selectByPrimaryKey(Integer otid);

    int updateByPrimaryKeySelective(OutType record);

    int updateByPrimaryKey(OutType record);
}