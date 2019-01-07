package com.expressage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.expressage.mapper.FreightreceiptMapper;
import com.expressage.pojo.Freightreceipt;
import com.expressage.service.FreightreceiptService;
@Service
public class FreightreceiptServiceImpl implements FreightreceiptService {
	@Resource
	FreightreceiptMapper mapper;
	/**
	 * 查询
	 */
	@Override
	public List<Freightreceipt> findFreig() {
		// TODO Auto-generated method stub
		return mapper.finfreightreceipt();
	}
    /**
     * 删除
     */
	@Override
	public int delFreig(int hid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(hid);
	}
}
