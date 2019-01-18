package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	@Override
	public int zkAddRole(String rname) {
		return roleMapper.zkAddRole(rname);
	}

	@Override
	public int zkDelRole(Integer rid) {
		return roleMapper.zkDelRole(rid);
	}

	@Override
	public Role zkSelRoleByRid(Integer rid) {
		return roleMapper.zkSelRoleByRid(rid);
	}

	@Override
	public int zkUpdRole(Integer rid, String name) {
		return roleMapper.zkUpdRole(rid, name);
	}

	@Override
	public int zkSelRoleCountByRname(String rname) {
		return roleMapper.zkSelRoleCountByRname(rname);
	}
}
