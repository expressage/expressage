package com.expressage.mapper;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.EmployeeRole;

public interface EmployeeRoleMapper {
    int insert(EmployeeRole record);

    int insertSelective(EmployeeRole record);
    
    int zkAddRoleByEid(@Param("eid")Integer eid,@Param("rid")int rid);
    
    int zkDelRoleByEid(Integer eid);
    
    int zkEmplCountByRid(Integer rid);
}