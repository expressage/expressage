package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.ProductTypeMapper;
import com.expressage.pojo.ProductType;
import com.expressage.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeMapper productTypeMapper;

	public List<ProductType> zm_getProductType() {
		return productTypeMapper.zm_getProductType();
	}

}
