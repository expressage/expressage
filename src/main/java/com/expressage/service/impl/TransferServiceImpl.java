package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.TransferMapper;
import com.expressage.pojo.Transfer;
import com.expressage.service.TransferService;
@Service
public class TransferServiceImpl implements TransferService {
	
	@Autowired
	TransferMapper transferMapper;

	@Override
	public List<Transfer> zkSelTransfer() {
		return transferMapper.zkSelTransfer();
	}

}
