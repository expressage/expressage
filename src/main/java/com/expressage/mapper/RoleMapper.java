package com.expressage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
    
    List<Role> zkSelRole();
    
    List<Role> zkSelRoleByEid(Integer eid); 
    
    int zkAddRole(String rname);
    
    int zkDelRole(Integer rid);
    
    Role zkSelRoleByRid(Integer rid);

	int zkUpdRole(@Param("rid")Integer rid,@Param("rname")String name);

	int zkSelRoleCountByRname(String rname);
}