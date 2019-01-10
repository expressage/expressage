package com.expressage.service;

import java.util.List;

import com.expressage.pojo.Employee;

public interface EmployeeService {
	List<Employee> zkSelAll(Integer eid,String name,String enable,Integer tid,Integer num,Integer size);
	 
	Employee zkSelByUsername(String username);
	 
	int zkCount(Integer eid,String name,String enable,Integer tid);
	
	int zkInsert(Employee employee);
	
	Employee zkSelectByKey(Integer eid);
	
	int zkUpdByKey(Employee employee);
	
	int zkSelEmployeeByAccount(String account);

	int zkProhibitEmpl(Integer eid);

	int zkUpdPassword(String password, Integer eid);
}
