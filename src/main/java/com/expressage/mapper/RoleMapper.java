package com.expressage.mapper;

import com.expressage.pojo.Role;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);
}