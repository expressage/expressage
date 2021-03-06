package com.expressage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expressage.pojo.Order;
import com.expressage.service.OrderService;

@Controller
@RequestMapping("/billController")
public class BillController {
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
		Order pmlSelectByPrimaryOid = orderService.pmlSelectByPrimaryOid(oid);
		model.addAttribute("pmlSelectByPrimaryOid",pmlSelectByPrimaryOid);
		model.addAttribute("oid",oid);
		return "/order/Bill_item";
	}   
	
	@RequestMapping("/pmlSelectStatus")
	public String pmlSelectStatus(Model model ,
			@RequestParam(value="oid",required=false)Integer oid) {
		Order pmlSelectByPrimaryOid = orderService.pmlSelectByPrimaryOid(oid);
		model.addAttribute("order",pmlSelectByPrimaryOid);
		model.addAttribute("oid",oid);
		return "/order/Bill_Update_status";
	}   
	
	
	@RequestMapping("/pmlUpdateStatus")
	public String pmlUpdateStatus(Model model ,
			@RequestParam(value="oid",required=false)Integer oid,
			@RequestParam(value="status",required=false)String status,
			Order order) {
		/*order.setStatus(status);
		int pmlUpdateStatus = orderService.pmlUpdateStatus(status, oid);
		if(pmlUpdateStatus > 0) {
			return "redirect:/billController/pmlQuery";
		}*/
		return null;
	}
	
	
	
	
	
	

}
