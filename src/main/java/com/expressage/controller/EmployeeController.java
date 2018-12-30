package com.expressage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expressage.pojo.Employee;
import com.expressage.pojo.Role;
import com.expressage.service.EmployeeRoleService;
import com.expressage.service.EmployeeService;
import com.expressage.service.RoleService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	EmployeeRoleService employeeRoleService;
	
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
	
	@RequestMapping(value="/zkSelEmployee")
	public String zkSelEmployee(
			@RequestParam(value="eid",required=false)Integer eid,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="enable",required=false)String enable,
			@RequestParam(value="num",defaultValue="1",required=false)Integer num,
			@RequestParam(value="size",defaultValue="3",required=false)Integer size,
			Model model) {
		Integer tid = 1;
		List<Employee> employeeList = employeeService.zkSelAll(eid,name,enable,tid, ((num-1)*size), size);
		int count = employeeService.zkCount(eid,name,enable,tid);
		int page = count%size==0?count/size:count/size+1;
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("eid", eid);
		model.addAttribute("name", name);
		model.addAttribute("enable",enable);
		model.addAttribute("num", num);
		return "role/system";
	}
	
	@RequestMapping(value="zkAddEmployee")
	public String zkAddEmployee(@RequestParam("employee")Employee employee) {
		employeeService.zkInsert(employee);
		return "redirect:zkSelEmployee";
	}
	
	@RequestMapping(value="zkSelectByKey")
	public String zkSelectByKey(@RequestParam("eid")Integer eid,Model model) {
		model.addAttribute("employee", employeeService.zkSelectByKey(eid));
		return "zkUpdEmployee";
	}
	
	@RequestMapping(value="zkUpdByKey")
	public String zkUpdByKey(@RequestParam("employee")Employee employee) {
		employeeService.zkUpdByKey(employee);
		return "redirect:zkSelEmployee";
	}
	
	/**
	 **查询分配角色
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="zkSelAssignRoles")
	public String zkSelAssignRoles(@RequestParam("employee")Employee employee,Model model) {
		List<Role> role = roleService.zkSelRoleByEid(employee.getEid());
		List<Role> roles = roleService.zkSelRole();
		model.addAttribute("role", role);
		model.addAttribute("roles", roles);
		model.addAttribute("eid", employee.getEid());
		return "redirect:assignRoles";
	}
	
	/**
	 **分配角色
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="zkAssignRoles")
	public String zkAssignRoles(@RequestParam("eid")Integer eid,@RequestParam("rids")Integer[] rids) {
		for (int i = 0; i < rids.length; i++) {
			employeeRoleService.zkAddRoleByEid(eid, rids[i]);
		}
		return "redirect:assignRoles";
	}
	
}
