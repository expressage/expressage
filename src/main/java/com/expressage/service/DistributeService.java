package com.expressage.service;

import java.util.List;
import com.expressage.pojo.Distribute;

public interface DistributeService {
	    List<Distribute> pmlSelectDistribute();
	    
	    int pmlInsertDistribute(Distribute distribute);
	    
	    int pmlUpdateStatus(Integer did ,String status);
	    
	    Distribute pmlSelectKeyDid(Integer did);
}
