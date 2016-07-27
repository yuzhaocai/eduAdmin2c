package com.class8.eduAdmin.service;

import com.class8.eduAdmin.bean.Page;
import com.class8.user.bean.AuthTeacherInfo;
import com.github.pagehelper.PageInfo;

public interface ITeacherReviewService {
	
	public PageInfo<AuthTeacherInfo> getAuthTeacherInfos(Page page);
	
	public void checkAuthTeacher(AuthTeacherInfo authTeacherInfo);
	
}
