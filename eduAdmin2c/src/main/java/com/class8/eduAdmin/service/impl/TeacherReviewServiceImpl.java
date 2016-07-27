package com.class8.eduAdmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.service.ITeacherReviewService;
import com.class8.user.bean.AuthTeacherInfo;
import com.class8.user.webservice.intf.IEduUserService;
import com.github.pagehelper.PageInfo;

@Service
public class TeacherReviewServiceImpl implements ITeacherReviewService {
	
	@Autowired
	private IEduUserService eduUserService;
	
	public PageInfo<AuthTeacherInfo> getAuthTeacherInfos(Page page) {
		return eduUserService.listAuthTeacherInfo(page.getPage(), page.getRows());
	}

	public void checkAuthTeacher(AuthTeacherInfo authTeacherInfo) {
		
		
	}

}
