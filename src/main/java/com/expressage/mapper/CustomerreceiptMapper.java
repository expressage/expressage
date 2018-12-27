package com.expressage.mapper;

import com.expressage.pojo.Customerreceipt;

public interface CustomerreceiptMapper {
    int deleteByPrimaryKey(Integer kid);

    int insert(Customerreceipt record);

    int insertSelective(Customerreceipt record);

    Customerreceipt selectByPrimaryKey(Integer kid);

    int updateByPrimaryKeySelective(Customerreceipt record);

    int updateByPrimaryKey(Customerreceipt record);
}