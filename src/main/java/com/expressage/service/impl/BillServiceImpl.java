package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.BillMapper;
import com.expressage.pojo.Bill;
import com.expressage.service.BillService;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillMapper billMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer billId) {
		return billMapper.deleteByPrimaryKey(billId);
	}

	@Override
	public int insert(Bill record) {
		return billMapper.insert(record);
	}

	@Override
	public int insertSelective(Bill record) {
		return billMapper.insertSelective(record);
	}

	@Override
	public Bill selectByPrimaryKey(Integer billId) {
		return billMapper.selectByPrimaryKey(billId);
	}

	@Override
	public int updateByPrimaryKeySelective(Bill record) {
		return billMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Bill record) {
		return billMapper.updateByPrimaryKey(record);
	}

}
