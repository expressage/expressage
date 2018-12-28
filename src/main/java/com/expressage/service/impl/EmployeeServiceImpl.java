package com.expressage.service.impl;

import org.springframework.stereotype.Service;

import com.expressage.mapper.EmployeeMapper;
import com.expressage.pojo.Employee;
import com.expressage.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	EmployeeMapper employeeMapper;
	
	@Override
	public Employee selByUsername(String username) {
		return employeeMapper.selByUsername(username);
	}

}
