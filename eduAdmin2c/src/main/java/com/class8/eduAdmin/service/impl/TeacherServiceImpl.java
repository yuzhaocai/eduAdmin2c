package com.class8.eduAdmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.class8.eduAdmin.dao.TeacherDao;
import com.class8.eduAdmin.model.Teacher;
import com.class8.eduAdmin.service.ITeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {
	
	@Autowired
	private TeacherDao teacherDao;
	
	@Transactional(readOnly=true)
	public Teacher getTeacher(Integer teacherId) {
		return teacherDao.getTeacher(teacherId);
	}

	public void saveTeacher(Teacher teacher) {
		teacherDao.saveTeacher(teacher);
		
	}

}
