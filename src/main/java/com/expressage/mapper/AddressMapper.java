package com.expressage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.Address;

public interface AddressMapper {
	List<Address> zm_selAddress(@Param("uid") Integer uid);

	Address zm_selAddressByAid(@Param("aid") Integer aid);

	int zm_addAddress(Address address);

	int zm_updIsdefault1(@Param("aid") Integer aid, @Param("uid") Integer uid);
	
	int zm_updIsdefault0(@Param("aid") Integer aid);

	int zm_updAddress(Address address);
	
	int zm_delAddress(@Param("aid") Integer aid);
	
	int zm_getCount(@Param("uid") Integer uid);
}