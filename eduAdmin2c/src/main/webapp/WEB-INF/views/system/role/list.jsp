<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();     
	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<script type="text/javascript">
	var basePath = '<%=basePath%>';
</script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/themes/default/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/base.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/icon.css"/>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/jquery-easyui.ext.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/function.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/system/role.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:70px;">
		<div class="toolbar">
			<a id="reload_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" title="刷新">刷新</a>
			<div class="datagrid-btn-separator"></div>
	        <a id="add_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" title="新增">新增</a>
			<a id="edit_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-pencil',plain:true" title="编辑">编辑</a>
			<a id="del_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-delete3',plain:true" title="删除">删除</a>
			<div class="datagrid-btn-separator"></div>
			<a id="assign_resource_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-key',plain:true" title="分配资源">分配资源</a>
	    </div>
		<div class="form-wrap">
			<form id="search_form">
				<label>角色名称：</label>
				<input name="name" class="easyui-textbox" data-options="height:26"/>
				<a id="reset_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-arrow_rotate_clockwise',height:26" title="重置">重置</a>
				<a id="query_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',height:26" title="查询">查询</a>
			</form> 
		</div>
	</div>
	
	<div data-options="region:'center',border:false">
		<div id="datagrid"></div>
	</div>
</body>
</html>