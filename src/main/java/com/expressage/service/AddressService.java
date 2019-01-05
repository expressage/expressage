package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Address;

public interface AddressService {
	List<Address> zm_selAddress(Integer uid);

	Address zm_selAddressByAid(Integer aid);

	int zm_addAddress(Address address);

	int zm_updIsdefault1(Integer aid,Integer uid);
	
	int zm_updIsdefault0(Integer aid);
	
	int zm_updAddress(Address address);

	int zm_delAddress(Integer aid);
	
	int zm_getCount(Integer uid);
}
