package com.expressage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expressage.pojo.Order;
import com.expressage.service.BillService;
import com.expressage.service.OrderService;

@Controller
@RequestMapping("/billController")
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/pmlQuery")
	public String pmlQuery(Model model,
			@RequestParam(value="orderno",required = false)String orderno) {
		List<Order> pmlSelectAll = orderService.pmlSelectAll(orderno);
		model.addAttribute("pmlSelectAll",pmlSelectAll);
		model.addAttribute("orderno",orderno);
		return "/order/Bill_order";
	}

	
	@RequestMapping("/pmlSelectOid")
	public String pmlSelectOid(Model model ,
			@RequestParam(value="oid",required=false)Integer oid) {
		Order selectByPrimaryKey = orderService.selectByPrimaryKey(oid);
		model.addAttribute("selectByPrimaryKey",selectByPrimaryKey);
		return "/order/Bill_item";
	}   
	
	
	
	
	

}
