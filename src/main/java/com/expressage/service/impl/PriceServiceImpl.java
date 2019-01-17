package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.PriceMapper;
import com.expressage.pojo.Price;
import com.expressage.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceMapper priceMapper;
	
	public Price zm_selPriceByAddress(String address1, String address2) {
		return priceMapper.zm_selPriceByAddress(address1, address2);
	}

}
