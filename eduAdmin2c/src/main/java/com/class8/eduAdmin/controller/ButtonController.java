package com.class8.eduAdmin.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.model.Button;
import com.class8.eduAdmin.service.IButtonService;
/*
 * 操作按钮
 */
@Controller
@RequestMapping("/system/button")
public class ButtonController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ButtonController.class);
	
	private static final String LIST = "/system/button/list";
	private static final String CREATE = "/system/button/add";
	private static final String UPDATE = "/system/button/edit";
	
	@Autowired
	private IButtonService buttonService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody String list(){
		int total = buttonService.countOfButton();
		List<Button> buttons = buttonService.findAllButtons();
		return success(buttons,total);
	}
	
	
}
