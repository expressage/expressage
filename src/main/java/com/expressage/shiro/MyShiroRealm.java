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
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
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
	private RedisSessionDAO redisSessionDAO;
	
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
		
		//将token转换成UsernamePasswordToken
		 //UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        
		String account = (String) token.getPrincipal();//从token获得账号
		Employee employee = employeeService.zkSelByUsername(account);
		if(employee==null) throw new UnknownAccountException();
		if(employee.getEnable()=="0") {
			throw new LockedAccountException();
		}
		//盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(account);
		//String md5 = new Md5Hash(upToken.getPassword(),upToken.getPrincipal()).toHex();
		//Object md = new SimpleHash("MD5",upToken.getPassword(),credentialsSalt,1024);
		SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(
				employee,
				employee.getPassword(),
				credentialsSalt,
				getName()
		);
		
		return authorizationInfo;
	}
	
	public void clearUserAuthByUserId(List<Integer> employeeIds) {
		System.out.println("--------------*clearUserAuthByUserId*--------------");
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
