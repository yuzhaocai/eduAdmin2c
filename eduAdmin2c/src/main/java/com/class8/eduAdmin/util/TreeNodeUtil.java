package com.class8.eduAdmin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.class8.eduAdmin.bean.TreeNode;

public class TreeNodeUtil {
	
	public static List<TreeNode> buildTree(List<TreeNode> treeNodes){
		List<TreeNode> rootTreeNodes = new ArrayList<TreeNode>();
		if(treeNodes == null){
			return rootTreeNodes;
		}
		Map<Integer,TreeNode> treeNodeMap = new HashMap<Integer,TreeNode>();
		for (TreeNode treeNode : treeNodes) {
			treeNodeMap.put(treeNode.getId(), treeNode);
		}
		for (Map.Entry<Integer, TreeNode> entry : treeNodeMap.entrySet()) {
			TreeNode treeNode = entry.getValue();
			Integer pid = treeNode.getPid();
			if(pid == null || pid.intValue() == 0){
				rootTreeNodes.add(treeNode);
			} else {
				treeNodeMap.get(pid).getChildren().add(treeNode);
			}
		}
		return rootTreeNodes;
	}
}
