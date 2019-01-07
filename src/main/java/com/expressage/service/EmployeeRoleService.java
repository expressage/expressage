package com.expressage.service;

public interface EmployeeRoleService {
	 int zkAddRoleByEid(Integer eid,int rid);
	 int zkDelRoleByEid(Integer eid);
	 int zkEmplCountByRid(Integer rid);
}
