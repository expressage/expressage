package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Role;

public interface RoleService {
	List<Role> zkSelRole();
    
    List<Role> zkSelRoleByEid(Integer eid); 
    
    int zkAddRole(String rname);
    
    int zkDelRole(Integer rid);

	Role zkSelRoleByRid(Integer rid);

	int zkUpdRole(Integer rid,String name);

	int zkSelRoleCountByRname(String rname);
}
