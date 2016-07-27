package com.class8.eduAdmin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.service.ITeacherReviewService;
import com.class8.user.bean.AuthTeacherInfo;
import com.class8.user.webservice.intf.IEduUserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/review/teacher")
public class TeacherReviewController extends BaseController {
	
	private static final Logger LOGGER = Logger.getLogger(TeacherReviewController.class);
	
	private static final String LIST = "/review/teacher/list";
	private static final String DETAIL = "/";
	
	@Autowired
	private ITeacherReviewService teacherReviewService;
	
	
	@Autowired 
	private IEduUserService eduUserService;
	
	/**
	 * 跳转老师审核首页
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	/**
	 * 获取申请认证的老师
	 * @param page
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public String getAuthTeachers(Page page){
		PageInfo<AuthTeacherInfo> pageInfo = teacherReviewService.getAuthTeacherInfos(page);
		List<AuthTeacherInfo> authTeacherInfos = pageInfo.getList();
		long total = pageInfo.getTotal();
		return success(authTeacherInfos, total);
	}
	
	/**
	 * 审核认证老师
	 * @return
	 */
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public String checkAuthTeacher(){
		return null;
	}
	
	
}
