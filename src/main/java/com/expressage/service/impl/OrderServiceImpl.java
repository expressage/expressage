package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.OrderMapper;
import com.expressage.pojo.Order;
import com.expressage.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	

	public List<Order> zm_selOrder(Integer uid) {
		return orderMapper.zm_selOrder(uid);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer oid) {
		return orderMapper.deleteByPrimaryKey(oid);
	}

	@Override
	public int insert(Order record) {
		return orderMapper.insert(record);
	}

	@Override
	public int insertSelective(Order record) {
		return orderMapper.insertSelective(record);
	}

	@Override
	public Order selectByPrimaryKey(Integer oid) {
		return orderMapper.selectByPrimaryKey(oid);
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Order record) {
		return orderMapper.updateByPrimaryKey(record);
	}

	
	@Override
	public List<Order> pmlSelectAll(String orderno) {
		return orderMapper.pmlSelectAll(orderno);
	}
	
}
