package com.class8.eduAdmin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.class8.course.webservice.intf.IEduCourseService;
import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.constant.SecurityConst;
import com.class8.eduAdmin.dao.UserDao;
import com.class8.eduAdmin.dao.UserRoleDao;
import com.class8.eduAdmin.exception.BusinessException;
import com.class8.eduAdmin.exception.ExistedException;
import com.class8.eduAdmin.model.User;
import com.class8.eduAdmin.service.IUserService;
import com.class8.eduAdmin.shiro.HashPassword;
import com.class8.eduAdmin.shiro.ShiroDbRealm;
import com.class8.user.webservice.intf.IEduUserService;

@Service 
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IEduUserService eduUserService;
	
	@Autowired
	private IEduCourseService eduCourseService;
	
	@Autowired
	private ShiroDbRealm jdbcRealm;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void saveUser(User user) throws ExistedException {
		if(userDao.findUserByUsername(user.getUsername()) != null){
			throw new ExistedException("用户添加失败,用户名："+user.getUsername()+"已存在.");
		}
		
		HashPassword hashPassword = jdbcRealm.encrypt(user.getPassword());
		user.setSalt(hashPassword.getSalt());
		user.setPassword(hashPassword.getPassword());
		userDao.saveUser(user);
	}
	
	@Transactional(readOnly=true)
	public User getUser(Integer userId) {
		return userDao.getUser(userId);
	}
	
	@Transactional(readOnly=true)
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}
	
	public void deleteUser(Integer userId) {
		userDao.deleteUser(userId);
		userRoleDao.deleteUserRolesByUserId(userId);
	}
	
	public void deleteUsers(List<Integer> userIds) {
		userDao.deleteUsers(userIds);
		userRoleDao.deleteUserRolesByUserIds(userIds);
	}
	
	public int countOfUser() {
		return userDao.countOfUser();
	}
	
	public List<User> findAllUsers(){
		return userDao.findAllUsers();
	}
	
	public List<User> findUsersPage(Page page) {
		return userDao.findUsersPage(page);
	}
	
	public void updateUser(User user) throws BusinessException {
		if(SecurityConst.ADMINISTRATOR.equals(user.getUsername())){
			throw new BusinessException("不能编辑超级管理员用户.");
		}
		userDao.updateUser(user);
	}
	
	

}
