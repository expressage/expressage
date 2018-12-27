package com.expressage.mapper;

import com.expressage.pojo.TransferRelation;

public interface TransferRelationMapper {
    int deleteByPrimaryKey(Integer transferRelationId);

    int insert(TransferRelation record);

    int insertSelective(TransferRelation record);

    TransferRelation selectByPrimaryKey(Integer transferRelationId);

    int updateByPrimaryKeySelective(TransferRelation record);

    int updateByPrimaryKey(TransferRelation record);
}