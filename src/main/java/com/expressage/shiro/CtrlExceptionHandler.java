package com.expressage.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class CtrlExceptionHandler {

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(Exception.class)
	public String exception(Exception e) {
		System.out.println(e.getMessage());
		return "error";
	}

	// 拦截未授权页面
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(UnauthorizedException.class)
	public String handleException(UnauthorizedException e) {
		System.out.println(e.getMessage());
		return "403";
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthorizationException.class)
	public String handleException2(AuthorizationException e) {
		System.out.println(e.getMessage());
		return "403";
	}
}
