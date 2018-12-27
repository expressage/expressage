package com.expressage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expressage.mapper.BillMapper;

@Controller
@RequestMapping("/billController")
public class BillController {
	
	@Autowired
	private BillMapper billMapper;
	
	@RequestMapping("/query")
	public String query(Model model) {
		return "backstage/bill";
	}

}
