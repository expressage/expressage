package com.expressage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.expressage.mapper.EmployeeMapper;
import com.expressage.pojo.Employee;
import com.expressage.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Override
	@Cacheable(key="'emplMe'",value="zk")
	public Employee zkSelByUsername(String username) {
		return employeeMapper.zkSelByUsername(username);
	}

	@Override
	@Cacheable(key="'empl'",value="zk")
	public List<Employee> zkSelAll(Integer eid, String name, String enable,Integer tid, Integer num, Integer size) {
		return employeeMapper.zkSelAll(eid, name, enable, tid, num,size);
	}

	@Override
	public int zkCount(Integer eid, String name, String enable,Integer tid) {
		return employeeMapper.zkCount(eid, name, enable,tid);
	}

	@Override
	@Cacheable(key="'empl'",value="zk")
	public int zkInsert(Employee employee) {
		return employeeMapper.zkInsert(employee);
	}

	@Override
	public Employee zkSelectByKey(Integer eid) {
		return employeeMapper.zkSelectByKey(eid);
	}

	@Override
	@CachePut(key="'empl'",value="zk")
	public int zkUpdByKey(Employee employee) {
		return employeeMapper.zkUpdByKey(employee);
	}

	@Override
	public int zkSelEmployeeByAccount(String account) {
		return employeeMapper.zkSelEmployeeByAccount(account);
	}

	@Override
	@CachePut@Cacheable(key="'empl'",value="zk")
	public int zkProhibitEmpl(Integer eid) {
		return employeeMapper.zkProhibitEmpl(eid);
	}

	@Override
	public int zkUpdPassword(String password, Integer eid) {
		return employeeMapper.zkUpdPassword(password, eid);
	}

}
