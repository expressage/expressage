package com.expressage.mapper;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.RolePower;

public interface RolePowerMapper {
    int insert(RolePower record);

    int insertSelective(RolePower record);
    
    int zkDelPowerByRid(Integer rid);

	int zkAddPowerByRid(@Param("rid")Integer rid,@Param("powers") Integer powers);
	
	int zkPowerCountByRid(Integer rid);
}