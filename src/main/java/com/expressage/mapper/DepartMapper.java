package com.expressage.mapper;

import com.expressage.pojo.Depart;

public interface DepartMapper {
    int deleteByPrimaryKey(Integer departId);

    int insert(Depart record);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(Integer departId);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);
}