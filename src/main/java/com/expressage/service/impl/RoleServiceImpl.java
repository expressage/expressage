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
	@Cacheable(key="'role'",value="zk")
	public List<Role> zkSelRole() {
		return roleMapper.zkSelRole();
	}

	@Override
	@Cacheable(key="'roleMe'",value="zk")
	public List<Role> zkSelRoleByEid(Integer eid) {
		return roleMapper.zkSelRoleByEid(eid);
	}

	@Override
	@Cacheable(key="'role'",value="zk")
	public int zkAddRole(String rname) {
		return roleMapper.zkAddRole(rname);
	}

	@Override
	@CacheEvict(key="'role'",value="zk")
	public int zkDelRole(Integer rid) {
		return roleMapper.zkDelRole(rid);
	}

	@Override
	public Role zkSelRoleByRid(Integer rid) {
		return roleMapper.zkSelRoleByRid(rid);
	}

	@Override
	@CachePut(key="'role'",value="zk")
	public int zkUpdRole(Integer rid, String name) {
		return roleMapper.zkUpdRole(rid, name);
	}

	@Override
	public int zkSelRoleCountByRname(String rname) {
		return roleMapper.zkSelRoleCountByRname(rname);
	}
}
