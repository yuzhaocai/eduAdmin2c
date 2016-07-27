package com.class8.eduAdmin.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.class8.eduAdmin.bean.Page;

@Controller
@RequestMapping("/review/school")
public class SchoolReviewController extends BaseController {
	
	private static final Logger LOGGER = Logger.getLogger(SchoolReviewController.class);
	
	private static final String LIST = "/review/school/list";
	private static final String DETAIL = "/review/school/detail";
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String getAuthSchooles(Page page){
		return null;
	}
	
}
