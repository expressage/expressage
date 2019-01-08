package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.Freightreceipt;

public interface FreightreceiptMapper {
	/**
	 * 查询
	 * @return
	 */
	List<Freightreceipt> finfreightreceipt();
	/**
	 * 删除
	 * @param hid
	 * @return
	 */
    int deleteByPrimaryKey(Integer hid);

    int insert(Freightreceipt record);

    int insertSelective(Freightreceipt record);

    Freightreceipt selectByPrimaryKey(Integer hid);

    int updateByPrimaryKeySelective(Freightreceipt record);

    int updateByPrimaryKey(Freightreceipt record);
}