package com.expressage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.expressage.pojo.Power;
@Mapper
public interface PowerMapper {
    int insert(Power record);

    int insertSelective(Power record);
    
    @Select("SELECT * FROM power")
	List<Power> selAll();
	
	@Select("SELECT * FROM `power` p LEFT JOIN role_power  rp ON p.pid = rp.pid LEFT JOIN employee_role er ON er.rid =rp.rid WHERE er.eid=#{eid} GROUP BY p.pid")
	List<Power> selByUserId(Map<String,Object> map);
	
	List<Power> zkSelPowerByRid(Integer rid);

	List<Power> zkSelPower();
}