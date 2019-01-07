package com.expressage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expressage.pojo.Power;
import com.expressage.pojo.Role;
import com.expressage.service.EmployeeRoleService;
import com.expressage.service.PowerService;
import com.expressage.service.RolePowerService;
import com.expressage.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@Autowired
	PowerService powerService;
	
	@Autowired
	RolePowerService rolePowerService;
	
	@Autowired
	EmployeeRoleService employeeRoleService;
	
	
	/**
	 * 查询角色
	 * @return
	 */
	@RequestMapping(value="zkSelRole")
	@ResponseBody
	public Map<String, Object> zkSelRole() {
		List<Role> roles = roleService.zkSelRole();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles", roles);
		return map;
	}
	/**
	 * 添加角色
	 * @param rname
	 */
	@RequestMapping(value="zkAddRole")
	@ResponseBody
	public void zkAddRole(@RequestParam("rname")String rname) {
		roleService.zkAddRole(rname);
	}
	
	/**
	 * 删除角色
	 * @param rid
	 * @return
	 */
	@RequestMapping(value="zkDelRole")
	@ResponseBody
	public int zkDelRole(@RequestParam("rid")Integer rid) {
		int num = rolePowerService.zkPowerCountByRid(rid);
		if(num>0) {
			num = -1;
		}else {
			num = employeeRoleService.zkEmplCountByRid(rid);
			if(num>0) {
				num = -2;
			}else {
				num = roleService.zkDelRole(rid);
			}
		}
		return num;
	}
	
	/**
	 * 查找权限
	 * @param rid
	 * @return
	 */
	@RequestMapping(value="zkSelAssignPower")
	@ResponseBody
	public Map<String, Object> zkSelAssignPower(@RequestParam("rid")Integer rid) {
		List<Power> power = powerService.zkSelPowerByRid(rid);
		List<Power> powers = powerService.zkSelPower();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("power", power);
		map.put("powers", powers);
		return map;
	}
	
	/**
	 * 添加权限
	 * @param rid
	 * @param powers
	 */
	@RequestMapping(value="zkAssignPower")
	@ResponseBody
	public void zkAssignPower(@RequestParam("rid")Integer rid,@RequestParam(value="powers",required=false)Integer[] powers) {
		rolePowerService.zkDelPowerByRid(rid);
		if(powers!=null) {
			for (int i = 0; i < powers.length; i++) {
				rolePowerService.zkAddPowerByRid(rid, powers[i]);
			}
		}
	}
	
	@RequestMapping("zkSelRoleByRid")
	@ResponseBody
	public Role zkSelRoleByRid(@RequestParam("rid")Integer rid) {
		return roleService.zkSelRoleByRid(rid);
	}
	
	@RequestMapping("zkUpdRole")
	@ResponseBody
	public void zkUpdRole(@RequestParam("rid")Integer rid,@RequestParam("rname")String rname) {
		roleService.zkUpdRole(rid,rname);
	}
	
	@RequestMapping("zkSelRoleCountByRname")
	@ResponseBody
	public int zkSelRoleCountByRname(@RequestParam("rname")String rname) {
		return roleService.zkSelRoleCountByRname(rname);
	}
	
}
