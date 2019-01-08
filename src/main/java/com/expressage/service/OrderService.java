package com.expressage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Order;

public interface OrderService {
	int deleteByPrimaryKey(Integer oid);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> pmlSelectAll(String orderno);
    
    Order pmlSelectByPrimaryOid(Integer oid);    
}
