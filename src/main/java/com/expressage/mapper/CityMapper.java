package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.City;

public interface CityMapper {
	List<City> zm_findCityByFid(Integer fid);
	
	

	int deleteByPrimaryKey(Integer cid);

	int insert(City record);

	int insertSelective(City record);

	City selectByPrimaryKey(Integer cid);

	int updateByPrimaryKeySelective(City record);

	int updateByPrimaryKey(City record);
}