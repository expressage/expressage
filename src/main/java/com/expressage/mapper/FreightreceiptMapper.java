package com.expressage.mapper;

import com.expressage.pojo.Freightreceipt;

public interface FreightreceiptMapper {
    int deleteByPrimaryKey(Integer hid);

    int insert(Freightreceipt record);

    int insertSelective(Freightreceipt record);

    Freightreceipt selectByPrimaryKey(Integer hid);

    int updateByPrimaryKeySelective(Freightreceipt record);

    int updateByPrimaryKey(Freightreceipt record);
}