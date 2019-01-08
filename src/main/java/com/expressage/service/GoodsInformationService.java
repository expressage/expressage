package com.expressage.service;

import java.util.List;

import com.expressage.pojo.GoodsInformation;

public interface GoodsInformationService {
	/**
	 * 货物回执信息查询
	 * @return
	 */
   List<GoodsInformation> findGoods();
   /**
    * 货物回执信息删除
    * @param xid
    * @return
    */
   int deleGoods(int xid);
}
