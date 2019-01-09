package com.expressage.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expressage.pojo.Employee;
import com.expressage.util.ValidateCode;

@Controller
@RequestMapping("/start")
public class EmployeeLoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Employee employee, Model model, HttpSession session) {
		String code = (String) session.getAttribute("validateCode");
		String submitCode = WebUtils.getCleanParam(request, "validateCode");
		
		if (StringUtils.isEmpty(employee.getAccount()) || StringUtils.isEmpty(employee.getPassword())) {
			request.setAttribute("msg", "用户名或密码不能为空！");
			return "login";
		}else if(!code.equals(submitCode.toLowerCase())){
			request.setAttribute("msg", "验证码不匹配！");
			return "login";
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(employee.getAccount(), employee.getPassword());
//		token.setRememberMe(true);
		try {
			subject.login(token);
			return "/home";
		} catch (LockedAccountException lae) {
			token.clear();
			request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
			return "login";
		} catch (AuthenticationException e) {
			token.clear();
			System.out.println(e);
			request.setAttribute("msg", "用户或密码不正确！");
			return "login";
		}
	}
	
	/*@RequestMapping(value="/home")
	public String redIndex() {
		return "redirect:/home.html";
	}*/

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
		System.out.println("validateCode:"+ verifyCode);
		System.out.println("bim:"+ bim);
	}
}
