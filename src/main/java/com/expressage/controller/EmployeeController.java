package com.expressage.controller;

import javax.servlet.http.HttpServletRequest;

/*import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressage.pojo.Employee;

@Controller
public class EmployeeController {
	/*@RequestMapping(value="/login",method= RequestMethod.GET)
	public String login() {
		return "login.html";
	}
	
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public String login(HttpServletRequest request,Employee employee,Model model) {
		  if (StringUtils.isEmpty(employee.getName()) || StringUtils.isEmpty(employee.getPassword())) {
	            request.setAttribute("msg", "用户名或密码不能为空！");
	            return "login.html";
	        }
	        Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token=new UsernamePasswordToken(employee.getName(),employee.getPassword());
	        try {
	            subject.login(token);
	            return "redirect:home.html";
	        }catch (LockedAccountException lae) {
	            token.clear();
	            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
	            return "login.html";
	        } catch (AuthenticationException e) {
	            token.clear();
	            request.setAttribute("msg", "用户或密码不正确！");
	            return "login.html";
	        }
	}*/
}
