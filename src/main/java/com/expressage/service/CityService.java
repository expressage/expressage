package com.expressage.service;

import java.util.List;

import com.expressage.pojo.City;

public interface CityService {
	List<City> zm_findCityByFid(Integer fid);
}
