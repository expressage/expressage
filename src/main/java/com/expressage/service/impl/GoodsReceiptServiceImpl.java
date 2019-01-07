package com.expressage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.expressage.mapper.GoodsReceiptMapper;
import com.expressage.pojo.GoodsReceipt;
import com.expressage.service.GoodsReceiptService;
@Service
public class GoodsReceiptServiceImpl implements GoodsReceiptService {
	@Resource
	GoodsReceiptMapper mapper;
	@Override
	public List<GoodsReceipt> findReceipt() {
		// TODO Auto-generated method stub
		return mapper.findSele();
	}

	@Override
	public int deleReceipt(Integer cid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(cid);
	}
}
