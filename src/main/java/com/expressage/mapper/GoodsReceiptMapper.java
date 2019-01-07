package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.GoodsInformation;
import com.expressage.pojo.GoodsReceipt;

public interface GoodsReceiptMapper {
	/**
	 * 运货差错查询
	 * @return
	 */
	List<GoodsReceipt> findSele();
	/**
	 * 运货差错删除
	 * @param cid
	 * @return
	 */
    int deleteByPrimaryKey(Integer cid);

    int insert(GoodsReceipt record);

    int insertSelective(GoodsReceipt record);

    GoodsReceipt selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(GoodsReceipt record);

    int updateByPrimaryKey(GoodsReceipt record);
}