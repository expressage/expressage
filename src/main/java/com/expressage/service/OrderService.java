package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Order;

public interface OrderService {
	List<Order> zm_selOrder(Integer uid);

	Order zm_getOrderByOid(Integer oid);

	int zm_delOrder(Integer oid);

	int zm_orderRemarks(Integer oid, String remarks);
	
	

	int insert(Order record);

	Order selectByPrimaryKey(Integer oid);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

    List<Order> pmlSelectAll(String orderno);
    
    Order pmlSelectByPrimaryOid(Integer oid);  
}
