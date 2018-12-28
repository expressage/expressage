package com.expressage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/getUser")
	public String getUser(Model model) {
		return "backstage/main";
	}
}
