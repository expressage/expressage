package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.service.EmployeeRoleService;
@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	@Autowired
	EmployeeRoleService employeeRoleService;
	
	@Override
	public int zkAddRoleByEid(Integer eid, int rid) {
		return employeeRoleService.zkAddRoleByEid(eid, rid);
	}

}
