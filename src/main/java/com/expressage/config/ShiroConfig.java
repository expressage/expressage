/**
 * 
 */
package com.expressage.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
     * lifecycleBeanPostProcessor是负责生命周期的 , 初始化和销毁的类
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
		shiroFilterFactoryBean.setUnauthorizedUrl("/error.html");
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
		filterChainDefinitionMap.put("/logout", "logout");
		List<Power> powersList = powerService.zkSelPower();
		for (Power power : powersList) {
			if (!StringUtil.isNullOrEmpty(power.getUrl())) {
				String permission = "perms["+power.getUrl()+"]";
				filterChainDefinitionMap.put(power.getUrl(), permission);
			}
		}
		filterChainDefinitionMap.put("/**", "authc");
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
		//securityManager.setCacheManager(cacheManager());
		//securityManager.setSessionManager(sessionManager());
		securityManager.setSessionManager(new DefaultWebSessionManager());
		return securityManager;
	}
	
	@Bean
	public MyShiroRealm myShiroRealm() {
		System.out.println("--------------*myShiroRealm*--------------");
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
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
	
	
	public RedisCacheManager cacheManager() {
		System.out.println("--------------*cacheManager*--------------");
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	
	public RedisManager redisManager() {
		System.out.println("--------------*redisManager*--------------");
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800);
		redisManager.setTimeout(timeout);
		return redisManager;
	}
	
	@Bean
	public DefaultWebSessionManager sessionManager() {
		System.out.println("--------------*sessionManager*--------------");
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		System.out.println("--------------*redisSessionDAO*--------------");
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

}
