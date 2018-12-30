package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
    
    List<Role> zkSelRole();
    
    List<Role> zkSelRoleByEid(Integer eid); 
}