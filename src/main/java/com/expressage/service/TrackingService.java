package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Tracking;

public interface TrackingService {
	int zm_addTrack(Tracking tracking);
	
	List<Tracking> zm_selTrackByOid(Integer oid);

	List<Tracking> zm_selTrackByOrderno(String orderno);
}
