package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.pojo.Role;
import com.expressage.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleService roleService;

	@Override
	public List<Role> zkSelRole() {
		return roleService.zkSelRole();
	}

	@Override
	public List<Role> zkSelRoleByEid(Integer eid) {
		return roleService.zkSelRoleByEid(eid);
	}
}
