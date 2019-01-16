package com.expressage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.expressage.mapper.PowerMapper;
import com.expressage.pojo.Power;
import com.expressage.service.PowerService;
@Service
public class PowerServiceImpl implements PowerService {

	@Autowired
	PowerMapper powerMapper;

	@Override
	@Cacheable(key="'powerMe'",value="zk")
	public List<Power> zkSelByEmplId(Map<String, Object> map) {
		return powerMapper.zkSelByEmplId(map);
	}

	@Override
	public List<Power> zkSelPowerByRid(Integer rid) {
		return powerMapper.zkSelPowerByRid(rid);
	}

	@Override
	@Cacheable(key="'power'",value="zk")
	public List<Power> zkSelPower() {
		return powerMapper.zkSelPower();
	}

	@Override
	@Cacheable(key="'power'",value="zk")
	public int zkAddPower(String pname, String url) {
		return powerMapper.zkAddPower(pname, url);
	}

	@Override
	@CacheEvict(key="'power'",value="zk")
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
	@CachePut(key="'power'",value="zk")
	public int zkUpdPowerByPid(Integer pid, String pname, String url) {
		return powerMapper.zkUpdPowerByPid(pid, pname, url);
	}

}
