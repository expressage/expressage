package com.expressage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expressage.pojo.Distribute;
import com.expressage.service.DistributeService;

/*
 * 彭美玲
 * 分发表
 */

@Controller
@RequestMapping("/distributeController")
public class DistributeController {
	
	@Autowired
	private DistributeService distributeService;
	
	@RequestMapping("/pmlSelectDistribute")
	public String pmlSelectDistribute(Model model) {
		List<Distribute> pmlSelectDistribute = distributeService.pmlSelectDistribute();
		model.addAttribute("pmlSelectDistribute",pmlSelectDistribute);
		return "/distribute/distribute_list";
	}
	
	
	

}
