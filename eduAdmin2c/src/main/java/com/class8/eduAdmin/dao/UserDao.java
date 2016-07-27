package com.class8.eduAdmin.dao;

import java.util.List;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.model.User;

public interface UserDao {

	public void saveUser(User user);

	public User findUserByUsername(String username);

	public int countOfUser();

	public List<User> findUsersPage(Page page);

	public User getUser(Integer userId);

	public void updateUser(User user);

	public List<User> findAllUsers();

	public void deleteUser(Integer userId);
	
	public void deleteUsers(List<Integer> userIds);

	public List<User> findUsersByOrganizationId(Integer organizationId);

}
