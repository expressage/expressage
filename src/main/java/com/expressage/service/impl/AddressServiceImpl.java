package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.AddressMapper;
import com.expressage.pojo.Address;
import com.expressage.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper addressMapper;

	public List<Address> zm_selAddress(Integer uid) {
		return addressMapper.zm_selAddress(uid);
	}

	public Address zm_selAddressByAid(Integer aid) {
		return addressMapper.zm_selAddressByAid(aid);
	}

	public int zm_addAddress(Address address) {
		return addressMapper.zm_addAddress(address);
	}

	public int zm_updIsdefault1(Integer aid, Integer uid) {
		return addressMapper.zm_updIsdefault1(aid, uid);
	}

	public int zm_updIsdefault0(Integer aid) {
		return addressMapper.zm_updIsdefault0(aid);
	}

	public int zm_updAddress(Address address) {
		return addressMapper.zm_updAddress(address);
	}

	public int zm_delAddress(Integer aid) {
		return addressMapper.zm_delAddress(aid);
	}

	public int zm_getCount(Integer uid) {
		return addressMapper.zm_getCount(uid);
	}
}
