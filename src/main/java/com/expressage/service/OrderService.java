package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Order;

public interface OrderService {
	List<Order> zm_selOrder(Integer uid);
	
	
	
	int deleteByPrimaryKey(Integer oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> pmlSelectAll(String orderno);
}
