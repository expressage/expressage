package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.DistributeMapper;
import com.expressage.pojo.Distribute;
import com.expressage.service.DistributeService;

@Service
public class DistributeServiceImpl implements DistributeService {

	@Autowired
	  private DistributeMapper distributeMapper;
	
	@Override
	public List<Distribute> pmlSelectDistribute() {
		return distributeMapper.pmlSelectDistribute();
	}

	@Override
	public int pmlInsertDistribute(Distribute distribute) {
		return distributeMapper.pmlInsertDistribute(distribute);
	}

	@Override
	public int pmlUpdateStatus(Integer did, String status) {
		return distributeMapper.pmlUpdateStatus(did, status);
	}

	@Override
	public Distribute pmlSelectKeyDid(Integer did) {
		return distributeMapper.pmlSelectKeyDid(did);
	}
	
	

}
