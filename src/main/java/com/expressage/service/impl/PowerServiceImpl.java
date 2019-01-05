package com.expressage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.PowerMapper;
import com.expressage.pojo.Power;
import com.expressage.service.PowerService;
@Service
public class PowerServiceImpl implements PowerService {

	@Autowired
	PowerMapper powerMapper;
	
	@Override
	public List<Power> selAll() {
		return powerMapper.selAll();
	}

	@Override
	public List<Power> selByUserId(Map<String, Object> map) {
		return powerMapper.selByUserId(map);
	}

	@Override
	public List<Power> zkSelPowerByRid(Integer rid) {
		return powerMapper.zkSelPowerByRid(rid);
	}

	@Override
	public List<Power> zkSelPower() {
		return powerMapper.zkSelPower();
	}

}
