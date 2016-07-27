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

import com.class8.eduAdmin.bean.TreeNode;
import com.class8.eduAdmin.model.Resource;
import com.class8.eduAdmin.service.IResourceService;
import com.class8.eduAdmin.util.TreeNodeUtil;

@Controller
@RequestMapping("/system/resource")
public class ResourceController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ResourceController.class);
	
	private static final String LIST = "/system/resource/list";
	private static final String CREATE = "/system/resource/add";
	private static final String UPDATE = "/system/resource/edit";
	
	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return LIST;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	@ResponseBody
	public String list(){
		List<Resource> resources = resourceService.findAllResources();
		return success(resources);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createForm(){
		return CREATE;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public String create(Resource resource){
		resource.setCreator(1);
		resource.setCreateTime(new Date());
		resourceService.savaResource(resource);
		return success("资源添加成功！");
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateForm(){
		return UPDATE;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute("resource") Resource resource){
		resourceService.updateResource(resource);
		return success("资源修改成功！");
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("ids") List<Integer> resourceIds){
		resourceService.deleteResources(resourceIds);
		return success("资源删除成功！");
	}
	
	@ModelAttribute
	public void getResource(@RequestParam(value="resourceId",defaultValue="-1") Integer resourceId ,Model model){
		if(resourceId != -1){
			model.addAttribute("resource", resourceService.getResource(resourceId));
		}
	}
	
	@RequestMapping(value="/tree")
	@ResponseBody
	public String getTreeNodes(){
		List<Resource> resources = resourceService.findActiveResources();
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		if(resources != null){
			for (Resource resource : resources) {
				treeNodes.add(resource.toTreeNode());
			}
		}
		treeNodes = TreeNodeUtil.buildTree(treeNodes);
		return success(treeNodes);
	}
	
	
}
