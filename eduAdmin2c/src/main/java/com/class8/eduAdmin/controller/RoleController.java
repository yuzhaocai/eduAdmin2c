package com.class8.eduAdmin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.bean.TreeNode;
import com.class8.eduAdmin.exception.ServiceException;
import com.class8.eduAdmin.model.Resource;
import com.class8.eduAdmin.model.Role;
import com.class8.eduAdmin.service.IResourceService;
import com.class8.eduAdmin.service.IRoleResourceService;
import com.class8.eduAdmin.service.IRoleService;
import com.class8.eduAdmin.util.TreeNodeUtil;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	private static final String LIST = "/system/role/list";
	private static final String CREATE = "/system/role/add";
	private static final String UPDATE = "/system/role/edit";
	
	private static final String ASSIGN_RESOURCE = "/system/role/assignResource";
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private IRoleResourceService roleResourceService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public @ResponseBody String list(Page page){
		int total = roleService.countOfRole();
		List<Role> roles = roleService.findRole(page);
		return success(roles, total);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(){
		return CREATE;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody String create(Role role){
		role.setCreator(1);
		role.setCreateTime(new Date());
		roleService.createRole(role);
		return success("角色添加成功！");
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateForm(){
		return UPDATE;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody String update(@ModelAttribute("role") Role role){
		roleService.updateRole(role);
		return success("角色修改成功！");
	}
	
	@RequestMapping(value="/delete")
	public @ResponseBody String delete(@RequestParam("id")Integer roleId){
		try {
			roleService.deleteRole(roleId);
		} catch (ServiceException e) {
			return failure(e.getMessage());
		}
		return success("角色删除成功！");
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public String listRole(){
		List<Role> roles = roleService.findAllRoles();
		return success(roles);
	}
	
	@RequestMapping(value="/assign/resource",method=RequestMethod.GET)
	public String assignResourceForm(){
		return ASSIGN_RESOURCE;
	}
	
	@RequestMapping(value="/{id}/resources/tree")
	@ResponseBody
	public String getRoleTreeNodes(@PathVariable("id") Integer roleId){
		//所有资源节点
		List<Resource> activeResources = resourceService.findActiveResources();
		List<TreeNode> activeTreeNodes = new ArrayList<TreeNode>();
		if(activeResources != null){
			for (Resource resource : activeResources) {
				activeTreeNodes.add(resource.toTreeNode());
			}
		}
		//构建树结构
		List<TreeNode> treeNodes = TreeNodeUtil.buildTree(activeTreeNodes);
		//角色拥有的资源id
		List<Integer> resourceIds = roleResourceService.findResourceIdsByRoleId(roleId);
		if(activeTreeNodes != null && resourceIds != null){
			for (TreeNode treeNode : activeTreeNodes) {
				if(resourceIds.contains(treeNode.getId()) && treeNode.getChildren().size() < 1){
					treeNode.setChecked(true);
				}
			}
		}
		return success(treeNodes);
	}
	
	@RequestMapping(value="/assign/resource",method=RequestMethod.POST)
	@ResponseBody
	public String assignResource(@RequestParam(value="id") Integer roleId,@RequestParam List<Integer> resourceIds){
		roleResourceService.assignResource(roleId,resourceIds);
		return success("角色授权成功！");
	}
	
	@ModelAttribute
	public void getRole(@RequestParam(value="roleId",defaultValue="-1")Integer roleId,Model model){
		if(roleId != -1){
			model.addAttribute("role", roleService.getRole(roleId));
		}
	}
	
}
