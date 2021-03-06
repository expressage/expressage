/**
 * 
 */
package com.expressage.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.expressage.pojo.Power;
import com.expressage.service.PowerService;
import com.expressage.shiro.KickoutSessionControlFilter;
import com.expressage.shiro.MyShiroRealm;

import io.netty.util.internal.StringUtil;

/**
 * @author Administrator
 *
 */
@Configuration
@EnableCaching
public class ShiroConfig {
	
	@Autowired
    private PowerService powerService;
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	/**
     * lifecycleBeanPostProcessor是负责生命周期的 , 初始化和销毁的类(Shiro生命周期处理器)
     * (可选)
     */
	@Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		System.out.println("--------------*getLifecycleBeanPostProcessor*--------------");
        return new LifecycleBeanPostProcessor();
    }
	
	 /**
     * 定义shiroFilter过滤器并注入securityManager
     * @param manager
     * @return
     */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("--------------*shiroFilter*--------------");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		shiroFilterFactoryBean.setSuccessUrl("/home.html");
		//shiroFilterFactoryBean.setUnauthorizedUrl("/error.html");
		//自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/styles/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/start/login*", "anon");
		filterChainDefinitionMap.put("/start/validateCode", "anon");
		filterChainDefinitionMap.put("/Jump/**", "anon");
		filterChainDefinitionMap.put("/Personal/**", "anon");
		filterChainDefinitionMap.put("/proscenium/**", "anon");
		filterChainDefinitionMap.put("/City/**", "anon");
		filterChainDefinitionMap.put("/Item/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/kickout", "anon");
		List<Power> powersList = powerService.zkSelPower();
		for (Power power : powersList) {
			if (!StringUtil.isNullOrEmpty(power.getUrl())) {
				String permission = "perms["+power.getUrl()+"]";
				filterChainDefinitionMap.put(power.getUrl(), permission);//表示需要某个或某些权限才能通过
			}
		}
		filterChainDefinitionMap.put("/**", "authc,kickout");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
		
	 /**
     * 定义安全管理器securityManager,注入自定义的realm
     * @param authRealm
     * @return
     */
	@Bean
	public SecurityManager securityManager() {
		System.out.println("--------------*securityManager*--------------");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		securityManager.setCacheManager(cacheManager());
		securityManager.setSessionManager(sessionManager());
		securityManager.setRememberMeManager(getCookieRememberMeManager());
		//securityManager.setSessionManager(new DefaultWebSessionManager());
		return securityManager;
	}
	
	@Bean
	public MyShiroRealm myShiroRealm() {
		System.out.println("--------------*myShiroRealm*--------------");
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		/*//userRealm.cachingEnabled：启用缓存，默认false；
		myShiroRealm.setCachingEnabled(true);
		//userRealm.authenticationCachingEnabled：启用身份验证缓存，即缓存AuthenticationInfo信息，默认false；
		myShiroRealm.setAuthenticationCachingEnabled(false);
		//userRealm.authenticationCacheName：缓存AuthenticationInfo信息的缓存名称；
		myShiroRealm.setAuthenticationCacheName("authenticationCache");
		//userRealm. authorizationCachingEnabled：启用授权缓存，即缓存AuthorizationInfo信息，默认false；
		myShiroRealm.setAuthorizationCachingEnabled(false);
		//userRealm. authorizationCacheName：缓存AuthorizationInfo信息的缓存名称；
		myShiroRealm.setAuthorizationCacheName("authorizationCache");*/
		return myShiroRealm;
	}
	
	 /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		System.out.println("--------------*hashedCredentialsMatcher*--------------");
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
        //指定加密方式为MD5
        //加密次数
		hashedCredentialsMatcher.setHashIterations(1024);
	/*	hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);*/
        return hashedCredentialsMatcher;
	}
	
	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 *   配置shiro跟spring的关联
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
    	System.out.println("--------------*authorizationAttributeSourceAdvisor*--------------");
    	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    
    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
	
    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
	public RedisCacheManager cacheManager() {
		System.out.println("--------------*cacheManager*--------------");
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	
	 /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
	public RedisManager redisManager() {
		System.out.println("--------------*redisManager*--------------");
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(timeout);
		return redisManager;
	}
	
	/**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		System.out.println("--------------*sessionManager*--------------");
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	 /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		System.out.println("--------------*redisSessionDAO*--------------");
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}
	
	 /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        kickoutSessionControlFilter.setCacheManager(cacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
    }
    
    @Bean(name = "rememberMeCookie")
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(60);//30天
        return simpleCookie;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager getCookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager =
                new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(
                org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCookie(getRememberMeCookie());
        return cookieRememberMeManager;
    }

}
