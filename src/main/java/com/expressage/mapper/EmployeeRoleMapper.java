package com.expressage.mapper;

import com.expressage.pojo.EmployeeRole;

public interface EmployeeRoleMapper {
    int insert(EmployeeRole record);

    int insertSelective(EmployeeRole record);
}