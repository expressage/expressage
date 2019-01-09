package com.expressage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.expressage.mapper.GoodsInformationMapper;
import com.expressage.pojo.GoodsInformation;
import com.expressage.service.GoodsInformationService;
@Service
public class GoodsInformationServiceImpl implements GoodsInformationService {
	@Resource
	GoodsInformationMapper mapper;
	@Override
	public List<GoodsInformation> findGoods() {
		// TODO Auto-generated method stub
		return mapper.finInformation();
	}

	@Override
	public int deleGoods(int xid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(xid);
	}
}
