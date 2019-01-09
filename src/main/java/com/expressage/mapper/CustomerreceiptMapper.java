package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.Customerreceipt;

public interface CustomerreceiptMapper {
	/**
	 * 客户回执信息查询
	 * @return
	 */
	List<Customerreceipt> findCustomer();
	/**
	 * 客户回执信息删除
	 * @param kid
	 * @return
	 */
    int deleteByPrimaryKey(Integer kid);

    int insert(Customerreceipt record);

    int insertSelective(Customerreceipt record);

    Customerreceipt selectByPrimaryKey(Integer kid);

    int updateByPrimaryKeySelective(Customerreceipt record);

    int updateByPrimaryKey(Customerreceipt record);
}