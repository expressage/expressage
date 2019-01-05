package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.CityMapper;
import com.expressage.pojo.City;
import com.expressage.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;
	
	public List<City> zm_findCityByFid(Integer fid) {
		return cityMapper.zm_findCityByFid(fid);
	}

}
