package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.Tracking;

public interface TrackingMapper {
	List<Tracking> zm_selTrackByOid(Integer oid);
	
	
	
    int deleteByPrimaryKey(Integer tid);

    int insert(Tracking record);

    int insertSelective(Tracking record);

    Tracking selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Tracking record);

    int updateByPrimaryKey(Tracking record);
}