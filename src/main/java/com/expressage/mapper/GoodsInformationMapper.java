package com.expressage.mapper;

import java.util.List;

import com.expressage.pojo.GoodsInformation;

public interface GoodsInformationMapper {
	/**
	 * 货物回执信息查询
	 * @return
	 */
	List<GoodsInformation> finInformation();
	
	/**
	 * 货物回执信息删除
	 * @param xid
	 * @return
	 */
    int deleteByPrimaryKey(Integer xid);

    int insert(GoodsInformation record);

    int insertSelective(GoodsInformation record);

    GoodsInformation selectByPrimaryKey(Integer xid);

    int updateByPrimaryKeySelective(GoodsInformation record);

    int updateByPrimaryKey(GoodsInformation record);
}