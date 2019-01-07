package com.expressage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.expressage.pojo.Power;
@Mapper
public interface PowerMapper {
    int insert(Power record);

    int insertSelective(Power record);
	
	List<Power> zkSelByUserId(Map<String,Object> map);
	
	List<Power> zkSelPowerByRid(Integer rid);

	List<Power> zkSelPower();
	
	int zkAddPower(@Param("pname")String pname,@Param("url") String url);
	
	int zkDelPower(Integer pid);
	
	Power zkSelPowerByPid(Integer pid);
	
	int zkUpdPowerByPid(@Param("pid")Integer pid,@Param("pname")String pname,@Param("url") String url);
	
	int zkSelPowerCountByPname(String pname);
}