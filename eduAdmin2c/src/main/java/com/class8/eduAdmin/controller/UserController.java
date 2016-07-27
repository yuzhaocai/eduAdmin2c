package com.class8.eduAdmin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.dto.UserDto;
import com.class8.eduAdmin.exception.BusinessException;
import com.class8.eduAdmin.exception.ExistedException;
import com.class8.eduAdmin.model.Organization;
import com.class8.eduAdmin.model.Role;
import com.class8.eduAdmin.model.User;
import com.class8.eduAdmin.model.UserRole;
import com.class8.eduAdmin.service.IOrganizationService;
import com.class8.eduAdmin.service.IRoleService;
import com.class8.eduAdmin.service.IUserRoleService;
import com.class8.eduAdmin.service.IUserService;
import com.class8.eduAdmin.util.BeanUtil;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	private static final String LIST = "/system/user/list";
	private static final String CREATE = "/system/user/add";
	private static final String UPDATE = "/system/user/edit";
	
	private static final String ASSIGN_ROLE = "/system/user/assign_role";
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IOrganizationService organizationService;
	
	/**
	 * 跳转用户管理首页
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	/**
	 * 查询用户
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody String getUsers(Page page){
		int total = userService.countOfUser();
		List<User> users = userService.findUsersPage(page);
		List<UserDto> userDtos = BeanUtil.mapCollection(users, UserDto.class);
		if(userDtos != null){
			for (UserDto userDto : userDtos) {
				List<Integer> roleIds = userRoleService.findRoleIdsByUserId(userDto.getId());
				if(roleIds != null && roleIds.size()>0){
					List<Role> roles = roleService.findRoles(roleIds);
					if(roles != null){
						for (Role role : roles) {
							userDto.getRoleIds().add(role.getId());
							userDto.getRoleNames().add(role.getName());
						}
					}
				}
				
				Organization organization = organizationService.getOrganization(userDto.getOrganizationId());
				if(organization != null){
					userDto.setOrganizationName(organization.getName());
				}
			}
		}
		return success(userDtos,total);
	}
	
	/**
	 * 跳转添加用户页面
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String showCreateForm(){
		return CREATE;
	}
	
	/**
	 * 添加用户
	 * @param user 
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody String createUser(User user,@RequestParam("roleIds") List<Integer> roleIds){
		user.setCreateTime(new Date());
		user.setCreator(1);
		try {
			userService.saveUser(user);
			if(roleIds != null && roleIds.size()>0){
				List<UserRole> userRoles = new ArrayList<UserRole>();
				for (Integer roleId : roleIds) {
					UserRole userRole = new UserRole();
					userRole.setUserId(user.getId());
					userRole.setRoleId(roleId);
					userRoles.add(userRole);
				}
				userRoleService.saveUserRoles(userRoles);
			}
		} catch (ExistedException e) {
			return failure(e.getMessage());
		}
		return success("用户添加成功！");
	}
	
	/**
	 * 跳转更新用户页面
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String showUpdateForm(){
		return UPDATE;
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody String updateUser(@ModelAttribute("user") User user,@RequestParam("roleIds") List<Integer> roleIds){
		try {
			userService.updateUser(user);
			userRoleService.deleteUserRolesByUserId(user.getId());
			if(roleIds != null && roleIds.size()>0){
				List<UserRole> userRoles = new ArrayList<UserRole>();
				for (Integer roleId : roleIds) {
					UserRole userRole = new UserRole();
					userRole.setUserId(user.getId());
					userRole.setRoleId(roleId);
					userRoles.add(userRole);
				}
				userRoleService.saveUserRoles(userRoles);
			}
		} catch (BusinessException e) {
			return failure(e.getMessage());
		}
		return success("用户修改成功！");
	}
	
	/**
	 * 删除用户
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value="/delete")
	public @ResponseBody String deleteUsers(@RequestParam("ids") List<Integer> userIds){
		userService.deleteUsers(userIds);
		return success("用户删除成功！");
	}
	
	/**
	 * 跳转角色设置页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/assignRole",method=RequestMethod.GET)
	public String showAssignRoleForm(@RequestParam(value="id") Integer userId){
		return ASSIGN_ROLE;
	}
	
	/**
	 * 设置角色
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="/assignRole",method=RequestMethod.POST)
	public String assignRole(@RequestParam(value="id")Integer userId,@RequestParam List<Integer> roleIds){
		return success("角色设置成功！");
	}
	
	@ModelAttribute
	public void getUser(@RequestParam(value="id",defaultValue="-1")Integer userId,Model model){
		if(userId != -1){
			model.addAttribute("user", userService.getUser(userId));
		}
	}
	
}
