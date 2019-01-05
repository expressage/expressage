package com.expressage.service;

public interface RolePowerService {

	int zkDelPowerByRid(Integer rid);

	int zkAddPowerByRid(Integer rid, Integer powers);
	
	int zkPowerCountByRid(Integer rid);

}
