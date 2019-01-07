package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.EmployeeRoleMapper;
import com.expressage.service.EmployeeRoleService;
@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	@Autowired
	EmployeeRoleMapper employeeRoleMapper;
	
	@Override
	public int zkAddRoleByEid(Integer eid, int rid) {
		return employeeRoleMapper.zkAddRoleByEid(eid, rid);
	}

	@Override
	public int zkDelRoleByEid(Integer eid) {
		return employeeRoleMapper.zkDelRoleByEid(eid);
	}

	@Override
	public int zkEmplCountByRid(Integer rid) {
		return employeeRoleMapper.zkEmplCountByRid(rid);
	}

}
