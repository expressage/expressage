package com.expressage.service;

import java.util.List;
import java.util.Map;

import com.expressage.pojo.Power;
import com.expressage.pojo.Role;

public interface PowerService {
	
	List<Power> zkSelByUserId(Map<String,Object> map);

	List<Power> zkSelPowerByRid(Integer rid);

	List<Power> zkSelPower();

	int zkAddPower(String pname, String url);

	int zkDelPower(Integer pid);

	Power zkSelPowerByPid(Integer pid);

	int zkUpdPowerByPid(Integer pid, String pname, String url);

	int zkSelPowerCountByPname(String pname);

}
