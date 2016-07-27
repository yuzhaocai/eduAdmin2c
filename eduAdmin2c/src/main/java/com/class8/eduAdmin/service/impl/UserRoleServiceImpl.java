package com.class8.eduAdmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.class8.eduAdmin.dao.UserRoleDao;
import com.class8.eduAdmin.model.UserRole;
import com.class8.eduAdmin.service.IUserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements IUserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public void saveUserRoles(List<UserRole> userRoles) {
		userRoleDao.saveUserRoles(userRoles);
	}

	public void deleteUserRolesByUserId(Integer userId) {
		userRoleDao.deleteUserRolesByUserId(userId);
	}

	public void deleteUserRolesByUserIds(List<Integer> userIds) {
		userRoleDao.deleteUserRolesByUserIds(userIds);
	}

	public List<Integer> findRoleIdsByUserId(Integer userId) {
		return userRoleDao.findRoleIdByUserId(userId);
	}

}
