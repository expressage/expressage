package com.expressage.shiro;

import java.util.ArrayList;
import java.util.Collection;
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
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.expressage.pojo.Employee;
import com.expressage.pojo.Power;
import com.expressage.service.EmployeeService;
import com.expressage.service.PowerService;

public class MyShiroRealm extends AuthorizingRealm{
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private PowerService powerService;
	
	@Autowired
	private RedisSessionDAO redisSessionDAO;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("eid", employee.getEid());
		List<Power> powersList = powerService.zkSelByUserId(map);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Power power : powersList) {
			info.addStringPermission(power.getUrl());
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String ename = (String) token.getPrincipal();
		Employee employee = employeeService.zkSelByUsername(ename);
		if(employee==null) throw new UnknownAccountException();
		if(employee.getEnable()=="0") {
			throw new LockedAccountException();
		}
		SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(
				employee,
				employee.getPassword(),
				ByteSource.Util.bytes(ename),
				getName()
		);
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("employeeSession", employee);
		session.setAttribute("employeeSessionId", employee.getEid());
		return authorizationInfo;
	}
	
	public void clearUserAuthByUserId(List<Integer> employeeIds) {
		if (employeeIds==null||employeeIds.size()==0) return;
		Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		for (Session session : sessions) {
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			//PRINCIPALS_SESSION_KEY这个属性记录的是用户名，而AUTHENTICATED_SESSION_KEY属性记录的是用户认证
			if (obj!=null&&obj instanceof SimplePrincipalCollection) {
				SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
				obj = spc.getPrimaryPrincipal();
				if(obj!=null&&obj instanceof Employee) {
					Employee employee = (Employee)obj;
					System.out.println("employee:"+employee);
					if(employee!=null&&employeeIds.contains(employee.getEid())) {
						list.add(spc);
					}
				}
			}
		}
		RealmSecurityManager securityManager =
				(RealmSecurityManager) SecurityUtils.getSecurityManager();
		
		MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
		for (SimplePrincipalCollection simplePrincipalCollection : list) {
			realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
		}
	}

}
