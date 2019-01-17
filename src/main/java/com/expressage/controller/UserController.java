package com.expressage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expressage.pojo.Employee;
import com.expressage.service.EmployeeService;
import com.expressage.util.ShiroMd5Util;

@Controller
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/getUser")
	public String getUser(Model model) {
		return "backstage/main";
	}

	@RequestMapping(value = "zkSelEmplByAccount")
	public String zkSelEmplByAccount(Model model) {
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("employee", employee);
		return "/user/user_info";
	}

	@RequestMapping("zkUpdUserPassword")
	public String zkUpdUserPassword(@RequestParam("oldPassword")String oldPassword,@RequestParam("password")String password, HttpServletRequest request) {
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		if (employee.getEid() == null) {
			request.setAttribute("msg", "不存在该用户");
			return "/user/user_modi_pwd";
		} 
		String pageReturnOldPassword = ShiroMd5Util.SysMd5(oldPassword, employee.getAccount());// 这个方法和上面的SysMd5一样，就是换了个马甲

		if (!employee.getPassword().equals(pageReturnOldPassword)) {
			request.setAttribute("msg", "旧密码不正确");
			return "/user/user_modi_pwd";
		}
		int num = employeeService.zkUpdPassword(ShiroMd5Util.SysMd5(password, employee.getAccount()), employee.getEid());
		if(num>0) {
			request.setAttribute("msg", "修改成功");
			return "/user/user_modi_pwd";
		}else {
			request.setAttribute("msg", "修改失败");
			return "/user/user_modi_pwd";
		}
	}
}
