package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Freightreceipt;

public interface FreightreceiptService {
	/**
	 * 查询货运回执单
	 * @return
	 */
   List<Freightreceipt> findFreig();
   
   /**
    * 删除回执单
    * @param freig
    * @return
    */
   int delFreig(int hid);
}
