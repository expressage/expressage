package com.expressage.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.expressage.pojo.Employee;
import com.expressage.pojo.Power;
import com.expressage.pojo.Role;
import com.expressage.service.EmployeeService;
import com.expressage.service.PowerService;
import com.expressage.service.RoleService;

public class MyShiroRealm extends AuthorizingRealm{
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private PowerService powerService;
	
	@Autowired
	private RoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("--------------*doGetAuthorizationInfo*--------------");
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("eid", employee.getEid());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Role> roleList = roleService.zkSelRoleByEid(employee.getEid());
		for (Role role : roleList) {
			info.addRole(role.getRname());
		}
		List<Power> powersList = powerService.zkSelByEmplId(map);
		for (Power power : powersList) {
			info.addStringPermission(power.getUrl());
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("--------------*doGetAuthenticationInfo*--------------");
		String account = (String) token.getPrincipal();//从token获得账号
		Employee employee = employeeService.zkSelByUsername(account);
		if(employee==null) throw new UnknownAccountException();
		if(employee.getEnable()=="0"||employee.getEnable().equals("0")) {
			throw new LockedAccountException();
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(account);
		SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(
				employee,
				employee.getPassword(),
				credentialsSalt,
				getName()
		);
		return authorizationInfo;
	}
}
