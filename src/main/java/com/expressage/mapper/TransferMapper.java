package com.expressage.mapper;

import com.expressage.pojo.Transfer;

public interface TransferMapper {
    int deleteByPrimaryKey(Integer transferId);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(Integer transferId);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);
}