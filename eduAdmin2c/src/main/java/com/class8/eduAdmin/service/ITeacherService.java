package com.class8.eduAdmin.service;

import com.class8.eduAdmin.model.Teacher;

public interface ITeacherService {
	
	public Teacher getTeacher(Integer teacherId);

	public void saveTeacher(Teacher teacher);
	
}	
