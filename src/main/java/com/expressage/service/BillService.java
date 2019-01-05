package com.expressage.service;

import com.expressage.pojo.Bill;

public interface BillService {
	  int deleteByPrimaryKey(Integer billId);

	    int insert(Bill record);

	    int insertSelective(Bill record);

	    Bill selectByPrimaryKey(Integer billId);

	    int updateByPrimaryKeySelective(Bill record);

	    int updateByPrimaryKey(Bill record);
	    

}
