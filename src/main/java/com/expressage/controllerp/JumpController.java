package com.expressage.controllerp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Jump")
public class JumpController {
	// 查件
	@RequestMapping("/zmJumpIndex")
	public String zmJumpIndex() {
		return "proscenium/index";
	}

	// 寄件
	@RequestMapping("/zmJumpMailing")
	public String zmJumpMailing() {
		return "proscenium/mailing";
	}

	// 登录
	@RequestMapping("/zmJumpLogin")
	public String zmJumpLogin() {
		return "proscenium/login";
	}

	// 注册
	@RequestMapping("/zmJumpRegister")
	public String zmJumpRegister() {
		return "proscenium/register";
	}

	// 添加地址
	@RequestMapping("/zmJumpAdd")
	public String zmJumpAdd() {
		return "proscenium/addAddress";
	}
}
