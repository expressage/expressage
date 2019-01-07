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
import com.expressage.service.PowerService;

@Controller
@RequestMapping("power")
public class PowerController {
	
	@Autowired
	PowerService powerService;
	
	@RequestMapping("zkSelPower")
	@ResponseBody
	public Map<String, Object> zkSelPower() {
		List<Power> power = powerService.zkSelPower();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("powerList", power);
		return map;
	}
	
	@RequestMapping("zkAddPower")
	@ResponseBody
	public void zkAddPower(@RequestParam("pname")String pname,@RequestParam("url")String url) {
		powerService.zkAddPower(pname,url);
	}
	
	@RequestMapping("zkDelPower")
	@ResponseBody
	public int zkDelPower(@RequestParam("pid")Integer pid) {
		int num = powerService.zkDelPower(pid);
		return num;
	}
	
	@RequestMapping("selPowerByPid")
	@ResponseBody
	public Power zkSelPowerByPid(@RequestParam("pid")Integer pid) {
		return powerService.zkSelPowerByPid(pid);
	}
	
	@RequestMapping("updPower")
	@ResponseBody
	public void zkUpdPower(@RequestParam("pid")Integer pid,@RequestParam("pname")String pname,@RequestParam("url")String url) {
		powerService.zkUpdPowerByPid(pid,pname,url);
	}
	
	@RequestMapping("selPowerCountByPname")
	@ResponseBody
	public int zkSelPowerCountByPname(@RequestParam("pname")String pname) {
		return powerService.zkSelPowerCountByPname(pname);
	}
	
}
