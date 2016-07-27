package com.class8.eduAdmin.dao;

import com.class8.eduAdmin.model.Teacher;

public interface TeacherDao {
	
	public Teacher getTeacher(Integer teacherId);

	public void saveTeacher(Teacher teacher);
	
}
