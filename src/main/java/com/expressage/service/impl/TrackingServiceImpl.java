package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.TrackingMapper;
import com.expressage.pojo.Tracking;
import com.expressage.service.TrackingService;
@Service
public class TrackingServiceImpl implements TrackingService{
	@Autowired
	private TrackingMapper trackingMapper;
	
	public int zm_addTrack(Tracking tracking) {
		return trackingMapper.zm_addTrack(tracking);
	}
	public List<Tracking> zm_selTrackByOid(Integer oid) {
		return trackingMapper.zm_selTrackByOid(oid);
	}

	public List<Tracking> zm_selTrackByOrderno(String orderno) {
		return trackingMapper.zm_selTrackByOrderno(orderno);
	}

}
