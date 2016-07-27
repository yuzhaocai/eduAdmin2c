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
<title>教师审核</title>
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
<script type="text/javascript" src="<%=basePath%>/static/scripts/review/teacher.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:70px;">
		<div id="toolbar" class="toolbar">
			<a id="reload_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" title="刷新">刷新</a>
			<div class="datagrid-btn-separator"></div>
	        <a id="add_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" title="新增">新增</a>
			<a id="edit_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-pencil',plain:true" title="编辑">编辑</a>
			<a id="del_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-delete3',plain:true" title="删除">删除</a>
			<div class="datagrid-btn-separator"></div>
			<a id="del_btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-delete3',plain:true" title="设置角色">设置角色</a>
	    </div>
		<div class="form-wrap">
			<form id="search_form">
				<label>老师名称：</label>
				<input name="username" class="easyui-textbox" data-options="height:26"/>
				<label>审核状态：</label>
				<select class="easyui-combobox" data-options="height:26">
					<option value="0">待审核</option>
					<option value="20">审核通过</option>
					<option value="30">审核未通过</option>
				</select>
				<label>审核时间：</label>
				<input class="easyui-datebox" data-options="height:26"/><i style="margin-right:5px;">~</i><input class="easyui-datebox" data-options="height:26"/>
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