package com.expressage.mapper;

import com.expressage.pojo.GoodsReceipt;

public interface GoodsReceiptMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(GoodsReceipt record);

    int insertSelective(GoodsReceipt record);

    GoodsReceipt selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(GoodsReceipt record);

    int updateByPrimaryKey(GoodsReceipt record);
}