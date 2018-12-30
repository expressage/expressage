package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Role;

public interface RoleService {
	List<Role> zkSelRole();
    
    List<Role> zkSelRoleByEid(Integer eid); 
}
