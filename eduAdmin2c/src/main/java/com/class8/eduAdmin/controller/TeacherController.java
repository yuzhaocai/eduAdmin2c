package com.class8.eduAdmin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.class8.eduAdmin.model.Teacher;
import com.class8.eduAdmin.service.ITeacherService;
import com.class8.eduAdmin.util.DateUtil;

@Controller
@RequestMapping("/system/teacher")
public class TeacherController extends BaseController {
	
	@Autowired
	private ITeacherService teacherService;
	
	@RequestMapping("/{teacherId}")
	@ResponseBody
	public String getTeacher(@PathVariable Integer teacherId){
		Teacher teacher = teacherService.getTeacher(teacherId);
		return success(teacher);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public String createTeacher(){
		Teacher teacher = new Teacher();
		teacher.setId(1);
		//teacher.setCreateTime(new Date());
		teacherService.saveTeacher(teacher);
		return success("创建成功");
	}
}
