package com.class8.eduAdmin.service;

import java.util.List;

import com.class8.eduAdmin.model.UserRole;

public interface IUserRoleService {

	public void saveUserRoles(List<UserRole> userRoles);

	public void deleteUserRolesByUserId(Integer id);

	public void deleteUserRolesByUserIds(List<Integer> userIds);

	public List<Integer> findRoleIdsByUserId(Integer userId);

}
