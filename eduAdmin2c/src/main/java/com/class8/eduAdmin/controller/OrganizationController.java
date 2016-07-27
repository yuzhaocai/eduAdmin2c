package com.class8.eduAdmin.controller;

import java.util.ArrayList;
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

import com.class8.eduAdmin.bean.TreeNode;
import com.class8.eduAdmin.exception.ServiceException;
import com.class8.eduAdmin.model.Organization;
import com.class8.eduAdmin.service.IOrganizationService;
import com.class8.eduAdmin.util.TreeNodeUtil;

@Controller
@RequestMapping("/system/organization")
public class OrganizationController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(OrganizationController.class);
	
	private static final String LIST = "/system/organization/list";
	private static final String CREATE = "/system/organization/add";
	private static final String UPDATE = "/system/organization/edit";
	
	@Autowired
	private IOrganizationService organizationService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public String list(){
		List<Organization> organizations = organizationService.findAllOrganization();
		return success(organizations);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(){
		return CREATE;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public String create(Organization organization){
		organizationService.saveOrganization(organization);
		return success("组织机构添加成功！");
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateForm(){
		return UPDATE;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute("organization") Organization organization){
		organizationService.updateOrganization(organization);
		return success("组织机构修改成功！");
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public String delete(@RequestParam(value="ids") List<Integer> organizationIds){
		try {
			organizationService.deleteOrganizations(organizationIds);
		} catch (ServiceException e) {
			return failure(e.getMessage());
		}
		return success("组织机构删除成功！");
	}
	
	@RequestMapping(value="/tree")
	@ResponseBody
	public String tree(){
		List<Organization> organizations = organizationService.findAllOrganization();
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		if(organizations != null){
			for (Organization organization : organizations) {
				treeNodes.add(organization.toTreeNode());
			}
		}
		treeNodes = TreeNodeUtil.buildTree(treeNodes);
		return success(treeNodes);
	}
	
	@ModelAttribute
	public void getOrganization(@RequestParam(value="organizationId",defaultValue="-1")Integer organizationId,Model model){
		if(organizationId != -1){
			model.addAttribute("organization",organizationService.getOrganization(organizationId));
		}
	}
	
}
