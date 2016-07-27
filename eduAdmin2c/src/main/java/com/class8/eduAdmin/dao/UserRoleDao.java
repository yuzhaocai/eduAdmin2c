package com.class8.eduAdmin.dao;

import java.util.List;

import com.class8.eduAdmin.model.UserRole;

public interface UserRoleDao {
	
	public List<Integer> findUserIdByRoleId(Integer roleId);
	
	public List<Integer> findRoleIdByUserId(Integer userId);

	public void saveUserRoles(List<UserRole> userRoles);

	public void deleteUserRolesByUserId(Integer userId);

	public void deleteUserRolesByUserIds(List<Integer> userIds);
}
