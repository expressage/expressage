package com.expressage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.expressage.mapper.CustomerreceiptMapper;
import com.expressage.pojo.Customerreceipt;
import com.expressage.service.CustomerreceiptService;
@Service
public class CustomerreceiptServiceImpl implements CustomerreceiptService {
	@Resource
	CustomerreceiptMapper mapper;
	@Override
	public List<Customerreceipt> SelCustomer() {
		// TODO Auto-generated method stub
		return mapper.findCustomer();
	}

	@Override
	public int delCustomer(int kid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(kid);
	}
}
