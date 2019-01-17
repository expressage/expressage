package com.expressage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expressage.pojo.Employee;
import com.expressage.pojo.Role;
import com.expressage.service.EmployeeRoleService;
import com.expressage.service.EmployeeService;
import com.expressage.service.RoleService;
import com.expressage.service.TransferService;
import com.expressage.util.ShiroMd5Util;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	RoleService roleService;

	@Autowired
	EmployeeRoleService employeeRoleService;

	@Autowired
	TransferService transferService;

	/**
	 * 查询所有
	 * 
	 * @param eid
	 * @param name
	 * @param enable
	 * @param num
	 * @param size
	 * @param model
	 * @return
	 */
	//@RequiresRoles(value={"管理人员","超级管理员"},logical = Logical.OR)
	@RequestMapping(value = "/zkSelEmployee")
	public String zkSelEmployee(@RequestParam(value = "eid", required = false) Integer eid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "enable", required = false) String enable,
			@RequestParam(value = "num", defaultValue = "1", required = false) Integer num,
			@RequestParam(value = "size", defaultValue = "3", required = false) Integer size, Model model) {
		Integer tid = 1;
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		tid = employee.getTid();
		List<Employee> employeeList = employeeService.zkSelAll(eid, name, enable, tid, ((num - 1) * size), size);
		int count = employeeService.zkCount(eid, name, enable, tid);
		int page = count % size == 0 ? count / size : count / size + 1;
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("eid", eid);
		model.addAttribute("name", name);
		model.addAttribute("enable", enable);
		model.addAttribute("num", num);
		return "role/system";
	}

	/**
	 * 增加员工
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "zkAddEmployee")
	public String zkAddEmployee(Employee employee) {
		employee.setFounderid(1);
		employee.setPassword(ShiroMd5Util.SysMd5(employee));
		employeeService.zkInsert(employee);
		return "redirect:zkSelEmployee";
	}

	@RequestMapping(value = "zkSelEmployeeByAccount")
	@ResponseBody
	public int zkSelEmployeeByAccount(String account) {
		int num = employeeService.zkSelEmployeeByAccount(account);
		return num;
	}

	/**
	 * 根据eid查询
	 * 
	 * @param eid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "zkSelectByKey")
	public String zkSelectByKey(@RequestParam("eid") Integer eid, Model model) {
		model.addAttribute("employee", employeeService.zkSelectByKey(eid));
		model.addAttribute("transferList", transferService.zkSelTransfer());
		return "/role/employee_upd.html";
	}

	/**
	 * 修改员工
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "zkUpdByKey")
	public String zkUpdByKey(Employee employee) {
		employeeService.zkUpdByKey(employee);
		return "redirect:zkSelEmployee";
	}

	/**
	 * 禁用员工
	 * 
	 * @param eid
	 * @return
	 */
	@RequestMapping(value = "zkProhibitEmpl")
	public String zkProhibitEmpl(@RequestParam("eid") Integer eid, @RequestParam("num") Integer num) {
		employeeRoleService.zkDelRoleByEid(eid);
		employeeService.zkProhibitEmpl(eid);
		return "redirect:zkSelEmployee?num=" + num;
	}

	/**
	 ** 查询分配角色
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "zkSelAssignRoles")
	@ResponseBody
	public Map<String, Object> zkSelAssignRoles(@RequestParam("eid") Integer eid) {
		List<Role> role = roleService.zkSelRoleByEid(eid);
		List<Role> roles = roleService.zkSelRole();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("roles", roles);
		return map;
	}

	/**
	 ** 分配角色
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "zkAssignRoles")
	@ResponseBody
	public void zkAssignRoles(@RequestParam("eid") Integer eid,
			@RequestParam(value = "rids", required = false) Integer[] rids) {
		employeeRoleService.zkDelRoleByEid(eid);
		if (rids !=null) {
			for (int i = 0; i < rids.length; i++) {
				employeeRoleService.zkAddRoleByEid(eid, rids[i]);
			}
		}
	}

	/**
	 ** 查询中转站
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "zkSelTransfer")
	public String zkSelTransfer(Model model) {
		model.addAttribute("transferList", transferService.zkSelTransfer());
		return "/role/employee_add.html";
	}
	

}
