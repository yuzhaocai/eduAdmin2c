<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<script type="text/javascript">
		var user_datagrid;
		var user_add_dialog;
		$(function(){
			user_datagrid = $.modalDialog.openner = $("#datagrid").datagrid({
				url : basePath + '/static/scripts/datagrid_data1.json',
				method : 'get',
				border : false,
				fit: true,
				striped : true,
				rownumbers : true,
				singleSelect : false,
				idField : 'itemid',
				fitColumns:true,
				pageSize : 10,
				pageList : [ 10, 20, 30, 40, 50],
				emptyMsg: '无查询记录！',
				view:datagridView,
				columns : [ [ 
				              { field:'ck',checkbox:true},
				              { width : fixWidth(15), title : '项目ID', field : 'itemid'},
				              { width : fixWidth(15), title : '产品ID', field : 'productid'}, 
				              { width : fixWidth(20), title : '产品价格', field : 'listprice'},
				              { width : fixWidth(20), title : '单位联系人', field : 'unitcost'},
				              { width : fixWidth(15), title : '属性', field : 'attr1'},
				              { width : fixWidth(15), title : '状态', field : 'status'},
				           ] ],
				toolbar:"#user_toolbar",
				pagination:true
			});
		});
		
		function searchUser(){
			user_datagrid.datagrid('reload',$.serializeObject($("#user_search_form")));
		}
		
		function addUser(){
			user_add_dialog = $.modalDialog({
				title : '添加课程',
				width:700,
				height:440, 
				href : basePath + '/views/user/add.jsp',
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						$.ajaxSubmit({
							url:basePath + "/user/create",
							dialog:user_add_dialog,
							datagrid:user_datagrid,
							formId:'user_add_form'
						});
					}
				},{
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						user_add_dialog.dialog('destroy');
					}
				}]
			});
		}
		
		function editUser(){
			user_edit_dialog = $.modalDialog({
				title : '编辑用户',
				width:700,
				height:440, 
				href : basePath + '/views/user/edit.jsp',
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						$.ajaxSubmit({
							url:basePath + "/views/index.jsp",
							dialog:user_add_dialog,
							datagrid:user_datagrid,
							formId:'user_edit_form'
						});
					}
				},{
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						user_add_dialog.dialog('destroy');
					}
				}]
			});
		}
		
		function deleteUser(){
			$.ajaxDelete({
				url:basePath + '/user/delete',
				idField:'itemid',
				datagrid:user_datagrid
			});
		}
	</script>
	
	<!-- toolbar start -->
	<div id="user_toolbar">
		<div class="button-wrap">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="javascript:addUser();">新增</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="javascript:editUser();">编辑</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="javascript:deleteUser();">删除</a>
		</div>
		<div class="form-wrap">
			<form id="user_search_form">
				<label>用户名：</label>
				<input class="easyui-textbox" data-options="height:26"/>
				<label>学号：</label>
				<input class="easyui-textbox" data-options="height:26"/>
				<label>上课时间：</label>
				<input class="easyui-datebox" data-options="height:26"/><i style="margin-right:5px;">~</i><input class="easyui-datebox" data-options="height:26"/>
				<div class="br"></div>
				<label>用户名：</label>
				<input class="easyui-textbox" data-options="height:26"/>
				<label>学号：</label>
				<input class="easyui-textbox" data-options="height:26"/>
				<label>上课时间：</label>
				<input class="easyui-datebox" data-options="height:26"/><i style="margin-right:5px;">~</i><input class="easyui-datebox" data-options="height:26"/>
				<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo',height:26">重置</a>
				<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',height:26" onclick="javascript:searchUser();">查询</a>
			</form> 
		</div>
	</div>
	<!-- toolbar end -->
	
	<!-- datagrid start -->
	<div id="datagrid"></div>
	<!-- datagrid end -->
</body>
</html>