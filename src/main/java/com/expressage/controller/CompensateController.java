
package com.expressage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * 彭美玲 pml
 * 赔偿表
 * 支出
 */
@Controller
@RequestMapping("/compensateController")
public class CompensateController {
	
	/*@Autowired
	private CompensateService compensateService;
	*/
	@RequestMapping("/pmlSelectWage")
	public String pmlSelectWage(Model model) {
		return "wage/Wage_list";
	}
	
	

}
