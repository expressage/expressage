package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.expressage.mapper.EmployeeRoleMapper;
import com.expressage.service.EmployeeRoleService;
@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	@Autowired
	EmployeeRoleMapper employeeRoleMapper;
	
	@Override
	@Cacheable(key="'roleMe'",value="zk")
	public int zkAddRoleByEid(Integer eid, int rid) {
		return employeeRoleMapper.zkAddRoleByEid(eid, rid);
	}

	@Override
	@CacheEvict(key="'roleMe'",value="zk")
	public int zkDelRoleByEid(Integer eid) {
		return employeeRoleMapper.zkDelRoleByEid(eid);
	}

	@Override
	@CachePut(key="'roleMe'",value="zk")
	public int zkEmplCountByRid(Integer rid) {
		return employeeRoleMapper.zkEmplCountByRid(rid);
	}

}
