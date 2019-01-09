package com.expressage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Order;


public interface OrderMapper {
	List<Order> zm_selOrder(@Param("uid")Integer uid);
	
	Order zm_getOrderByOid(@Param("oid")Integer oid);
	
	int zm_delOrder(@Param("oid")Integer oid);
	
	int zm_orderRemarks(@Param("oid")Integer oid,@Param("remarks")String remarks);
	
	
	
	

    int insert(Order order);

    int insertSelective(Order order);

    Order selectByPrimaryKey(@Param("oid")Integer oid);

    int updateByPrimaryKeySelective(Order order);

    int updateByPrimaryKey(Order order);
    
    List<Order> pmlSelectAll(@Param("orderno")String orderno);
    
    Order pmlSelectByPrimaryOid(@Param("oid") Integer oid);    
}