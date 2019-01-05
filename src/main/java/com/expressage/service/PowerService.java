package com.expressage.service;

import java.util.List;
import java.util.Map;

import com.expressage.pojo.Power;

public interface PowerService {
	List<Power> selAll();
	
	List<Power> selByUserId(Map<String,Object> map);

	List<Power> zkSelPowerByRid(Integer rid);

	List<Power> zkSelPower();
}
