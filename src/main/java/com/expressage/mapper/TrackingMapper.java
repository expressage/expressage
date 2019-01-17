package com.expressage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Tracking;

public interface TrackingMapper {
	int zm_addTrack(Tracking tracking);

	List<Tracking> zm_selTrackByOid(@Param("oid") Integer oid);

	List<Tracking> zm_selTrackByOrderno(@Param("orderno") String orderno);

	int deleteByPrimaryKey(Integer tid);

	int insert(Tracking record);

	int insertSelective(Tracking record);

	Tracking selectByPrimaryKey(Integer tid);

	int updateByPrimaryKeySelective(Tracking record);

	int updateByPrimaryKey(Tracking record);
}