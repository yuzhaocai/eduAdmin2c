package com.class8.eduAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.class8.eduAdmin.model.User;

/**
 * 系统首页控制器
 *
 */
@Controller
public class IndexController extends BaseController {
	
	private static final String INDEX = "/index";
	private static final String UPDATE_PASSWORD = "/index/updatePassword";
	private static final String UPDATE_BASICINFO = "/index/updateBasicInfo";
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model){
		return INDEX;
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.GET)
	public String updatePasswordPage(){
		return UPDATE_PASSWORD;
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	@ResponseBody
	public String updatePassword(){
		return success("密码更新成功！");
	}
	
	@RequestMapping(value="/updateBasicInfo",method=RequestMethod.GET)
	public String updateBasicInfoPage(Model model){
		return UPDATE_BASICINFO;
	}
	
	@RequestMapping(value="/updateBasicInfo",method=RequestMethod.POST)
	@ResponseBody
	public String updateBasicInfo(User user){
		return success("基本信息保存成功！");
	}

}
