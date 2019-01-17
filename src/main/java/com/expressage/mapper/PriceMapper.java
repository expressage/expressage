package com.expressage.mapper;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Price;

public interface PriceMapper {
	Price zm_selPriceByAddress(@Param("address1") String address1, @Param("address2") String address2);
}