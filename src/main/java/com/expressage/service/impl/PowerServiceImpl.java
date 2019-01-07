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
	public List<Power> zkSelByUserId(Map<String, Object> map) {
		return powerMapper.zkSelByUserId(map);
	}

	@Override
	public List<Power> zkSelPowerByRid(Integer rid) {
		return powerMapper.zkSelPowerByRid(rid);
	}

	@Override
	public List<Power> zkSelPower() {
		return powerMapper.zkSelPower();
	}

	@Override
	public int zkAddPower(String pname, String url) {
		return powerMapper.zkAddPower(pname, url);
	}

	@Override
	public int zkDelPower(Integer pid) {
		return powerMapper.zkDelPower(pid);
	}

	@Override
	public Power zkSelPowerByPid(Integer pid) {
		return powerMapper.zkSelPowerByPid(pid);
	}

	@Override
	public int zkSelPowerCountByPname(String pname) {
		return powerMapper.zkSelPowerCountByPname(pname);
	}

	@Override
	public int zkUpdPowerByPid(Integer pid, String pname, String url) {
		return powerMapper.zkUpdPowerByPid(pid, pname, url);
	}

}
