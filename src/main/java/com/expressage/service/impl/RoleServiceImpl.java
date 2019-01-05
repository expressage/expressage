package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.RoleMapper;
import com.expressage.pojo.Role;
import com.expressage.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<Role> zkSelRole() {
		return roleMapper.zkSelRole();
	}

	@Override
	public List<Role> zkSelRoleByEid(Integer eid) {
		return roleMapper.zkSelRoleByEid(eid);
	}
}
