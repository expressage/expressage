package com.expressage.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.expressage.pojo.Employee;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * AccessControlFilter访问控制过滤器: 访问控制过滤器是检查当前用户是否能执行访问的controller action的初步授权模式。
   *    这种授权模式基于用户名，客户IP地址和访问类型。 访问控制过滤器，适用于简单的验证。
 * ------isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
 * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
 * 
 * @author Administrator
 *
 */
public class KickoutSessionControlFilter extends AccessControlFilter {

	private String kickoutUrl; 
	private boolean kickoutAfter = false; 
	private int maxSession = 1; 
	private SessionManager sessionManager;
	private Cache<String, Deque<Serializable>> cache;

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cache = cacheManager.getCache("shiro_redis_cache");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (!subject.isAuthenticated() && !subject.isRemembered()) {
			return true;
		}

		Session session = subject.getSession();
		Employee employee = (Employee) subject.getPrincipal();
		String account = employee.getAccount();
		Serializable sessionId = session.getId();

		Deque<Serializable> deque = cache.get(account);

		if (deque == null) {
			deque = new LinkedList<Serializable>();
		}

		if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
			deque.push(sessionId);
			cache.put(account, deque);
		}

		while (deque.size() > maxSession) {
			Serializable kickoutSessionId = null;
			if (kickoutAfter) { 
				kickoutSessionId = deque.removeFirst();
				cache.put(account, deque);
			} else { 
				kickoutSessionId = deque.removeLast();
				cache.put(account, deque);
			}

			try {
				Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
				if (kickoutSession != null) {
					kickoutSession.setAttribute("kickout", true);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		if (session.getAttribute("kickout") != null) {
			try {
				subject.logout();
			} catch (Exception e) { 
				System.out.println(e);
			}
			saveRequest(request);

			/*Map<String, String> resultMap = new HashMap<String, String>();
			if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
				resultMap.put("user_status", "300");
				resultMap.put("message", "您已经在其他地方登录，请重新登录！");
				out(response, resultMap);
			} else {
				// 重定向
				WebUtils.issueRedirect(request, response, kickoutUrl);
			}*/
			return false;
		}
		return true;
	}

	/*private void out(ServletResponse hresponse, Map<String, String> resultMap) throws IOException {
		try {
			hresponse.setCharacterEncoding("UTF-8");
			PrintWriter out = hresponse.getWriter();
			out.println(JSON.toJSONString(resultMap));
			out.flush();
			out.close();
		} catch (Exception e) {
			System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
		}
	}*/

}
