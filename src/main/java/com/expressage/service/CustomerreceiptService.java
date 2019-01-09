package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Customerreceipt;

public interface CustomerreceiptService {
	/**
	 * 客户回执信息查询
	 * @return
	 */
     List<Customerreceipt>  SelCustomer();
     
     /**
      * 客户回执信息删除
      * @param kid
      * @return
      */
     int delCustomer(int kid);
}
