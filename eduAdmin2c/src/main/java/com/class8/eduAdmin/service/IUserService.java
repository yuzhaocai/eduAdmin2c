package com.class8.eduAdmin.service;

import java.util.List;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.exception.BusinessException;
import com.class8.eduAdmin.exception.ExistedException;
import com.class8.eduAdmin.model.User;

public interface IUserService {
	
	public void saveUser(User user) throws ExistedException;
	
	public User getUser(Integer userId);
	
	public User findUserByUsername(String username);
	
	public void deleteUser(Integer id);

	public void deleteUsers(List<Integer> userIds);

	public int countOfUser();
	
	public List<User> findAllUsers();

	public List<User> findUsersPage(Page page);

	public void updateUser(User user) throws BusinessException;


}
