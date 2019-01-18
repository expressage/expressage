
package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.expressage.mapper.RolePowerMapper;
import com.expressage.service.RolePowerService;
@Service
public class RolePowerServiceImpl implements RolePowerService {

	@Autowired
	RolePowerMapper rolePowerMapper;
	
	@Override
	public int zkDelPowerByRid(Integer rid) {
		return rolePowerMapper.zkDelPowerByRid(rid);
	}

	@Override
	public int zkAddPowerByRid(Integer rid, Integer powers) {
		return rolePowerMapper.zkAddPowerByRid(rid, powers);
	}

	@Override
	public int zkPowerCountByRid(Integer rid) {
		return rolePowerMapper.zkPowerCountByRid(rid);
	}

}
