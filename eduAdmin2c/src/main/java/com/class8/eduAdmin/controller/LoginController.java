package com.class8.eduAdmin.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * shiro中session中设置的内容是可以在jsp页面的session中获取的
 *
 */
@Controller
public class LoginController extends BaseController {
	
	private static final String LOGIN_PAGE = "login";
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return LOGIN_PAGE;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String loginFail(HttpServletRequest request,Model model,@RequestParam("username")String username){
		String message = this.parseException(request);
		model.addAttribute("message", message);
		model.addAttribute("username", username);
		
		return LOGIN_PAGE;
	}
	
	private String parseException(HttpServletRequest request){
		String message = "其他错误";
		String exceptinName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if ("org.apache.shiro.authc.UnknownAccountException".equals(exceptinName))
			message = "未知帐号错误！";
		else if ("org.apache.shiro.authc.IncorrectCredentialsException"
				.equals(exceptinName))
			message = "密码错误！";
		else if ("com.class8.eduAdmin.shiro.IncorrectCaptchaException"
				.equals(exceptinName))
			message = "验证码错误！";
		else if ("org.apache.shiro.authc.AuthenticationException"
				.equals(exceptinName))
			message = "认证失败！";
		else if ("org.apache.shiro.authc.DisabledAccountException"
				.equals(exceptinName))
			message = "账号被冻结！";
		return message;
	}
}
