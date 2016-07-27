<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class='table-wrap'>
	<div style="height: 50px; overflow: hidden;padding-left: 40px; line-height:20px; background: url('static/styles/icon/32/group_key.png') no-repeat left center;" border="false">
	        <b>角色名称：<label id="role_name"></label></b><br/>
	         <span style="color: #4B4B4B">勾选资源列表为角色分配资源.</span>
	    </div>
	<div class="easyui-panel" title="资源列表" data-options="height:360,iconCls:'icon-list'" style="height:350px;overflow: auto;padding:2px;">
	    <ul id="resource_tree"></ul>
	</div>
</div>

