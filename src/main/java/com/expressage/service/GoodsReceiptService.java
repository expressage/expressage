package com.expressage.service;

import java.util.List;

import com.expressage.pojo.GoodsReceipt;

public interface GoodsReceiptService {
	/**
	 * 运货差错单查询
	 * @return
	 */
   List<GoodsReceipt> findReceipt();
   /**
    * 运货差错单删除
    * @param cid
    * @return
    */
   int deleReceipt(Integer cid);
}
