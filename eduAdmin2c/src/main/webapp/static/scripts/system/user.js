$(function(){
	//初始化用户列表
	grid.databind();
	
	//初始化按钮事件
	$('#reload_btn').click(search.query);
	$('#add_btn').click(ops.add);
	$('#edit_btn').click(ops.edit);
	$('#del_btn').click(ops.del);
	
	//初始化查询表单
	search.initdata();
	$('#query_btn').click(search.query);
	$('#reset_btn').click(search.reset);
});

//查询表单
var search = {
	initdata:function(){
		$('#search_form').form();
	},
	query:function(){
		var param = $.serializeObject($('#search_form'));
		grid.reload(param);
	},
	reset:function(){
		$('#search_form').form('reset');
	}
}

//数据列表
var grid = {
	databind:function(){
		$("#datagrid").datagrid({
			url : basePath + '/system/user',
			title:'用户列表',
			iconCls:'icon-list',
			method : 'post',
			border : true,
			fit: true,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			idField : 'id',
			fitColumns:true,
			remoteSort:true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			emptyMsg: '无相关查询的记录！',
			view:datagridView,
			columns : [ [ 
			              { field:'ck',checkbox:true},
			              { width : fixWidth(15), title : '用户ID', field : 'id',hidden:true},
			              { width : fixWidth(15), title : '角色IDS', field : 'roleIds',hidden:true},
			              { width : fixWidth(15), title : '部门ID', field : 'organizationId',hidden:true},
			              { width : fixWidth(15), title : '用户名', field : 'username'}, 
			              { width : fixWidth(20), title : '真实姓名', field : 'realname'},
			              { width : fixWidth(20), title : '部门', field : 'organizationName'},
			              { width : fixWidth(15), title : '拥有角色', field : 'roleNames'},
			              { width : fixWidth(20), title : '邮箱', field : 'email'},
			              { width : fixWidth(15), title : '电话', field : 'phone'},
			              { width : fixWidth(15), title : '状态', field : 'status',
			            	  formatter: function(value,row,index){
			            		  if(value == 0){
			            			  return "正常";
			            		  } else if(value == 1){
			            			  return "<span style='color:red'>锁定</span>";
			            		  }
			            	  }},
			              { width : fixWidth(15), title : '创建时间', field : 'createTime',sortable:true,
			            	  formatter: function(value,row,index){
				  				if (value){
									return new Date(parseInt(value)).format('yyyy-MM-dd HH:mm');
								}
				  				return "";
							}}
			           ] ],
			pagination:true,
			onDblClickRow:function(index,row){
				$("#datagrid").datagrid('clearSelections').datagrid('clearChecked').datagrid('checkRow', index);
				ops.edit();
			}
		});
	},
	reload: function(param) {
		var _param = param || {};
		$("#datagrid").datagrid('clearSelections').datagrid('reload',_param);
    },
    selectRow: function() {
        return $("#datagrid").datagrid('getSelected');
    }
}

//基本操作
var ops = {
	initData:function(){
		//角色下拉列表
		$.getJSON(basePath + '/system/role/list',function(data){
			top.$('#role_ids').combobox('loadData',data);
		});
		//部门下拉树
		$.getJSON(basePath + '/system/organization/tree',function(data){
			top.$('#organization_id').combotree('loadData',data);
		});
	},
	add:function(){
		var dialog = top.$.modalDialog({
			href : basePath + '/system/user/create',
			title : '添加用户',
			iconCls:'icon-user_add',
			width:600,
			height:360, 
			onLoad:function(){
				ops.initData();
			},
			closed:false,
			buttons : [ {
				text : '确定', 	
				iconCls : 'icon-ok',
				handler : function() {
					if(top.$('#user_form').form('enableValidation').form('validate')){
						var data = top.$.serializeObject(top.$('#user_form'));
						var url = basePath + '/system/user/create';
						$.ajaxSubmit(url,data,function(result){
							if(result.success){
								top.$.messager.alert('提示', result.message, 'info');
								grid.reload();
								dialog.dialog('close');
							} else {
								top.$.messager.alert('提示', result.message, 'error');
							}
						});
					}
				}
			},{
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					dialog.dialog('destroy');
				}
			}]
		});
	},
	edit:function(){
		var row = grid.selectRow();
		if(row){
			var dialog = top.$.modalDialog({
				href : basePath + '/system/user/update',
				title : '编辑用户',
				iconCls:'icon-pencil',
				width:520,
				height:327, 
				onLoad:function(){
					ops.initData();
					top.$('#user_form').form('load',row);
					top.$('#user_form').find('#username').text(row.username);
				},
				closed:false,
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						if(top.$('#user_form').form('enableValidation').form('validate')){
							var data = top.$.serializeObject(top.$('#user_form'));
							var url = basePath + '/system/user/update';
							$.ajaxSubmit(url,data,function(result){
								if(result.success){
									top.$.messager.alert('提示', result.message, 'info');
									grid.reload();
									dialog.dialog('close');
								} else {
									top.$.messager.alert('提示', result.message, 'error');
								}
							});
						}
					}
				},{
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						dialog.dialog('destroy');
					}
				}]
			});
		} else {
			top.$.messager.alert('提示', "请选择要修改的用户.", 'error');
		}
	},
	del:function(){
		var row = grid.selectRow();
		if(row){
			top.$.messager.confirm('确认','您确认要删除吗？',function(r){
				if(r){
					var arr = new Array();
					arr.push(row.id);
					var url = basePath + '/system/user/delete';
					var data = {"ids":arr.join(",")};
					$.ajaxSubmit(url,data,function(result){
						if(result.success){
							top.$.messager.alert('提示', result.message, 'info');
							grid.reload();
						} else {
							top.$.messager.alert('提示',result.message,'error');
						}
					});
				}
			});
		} else {
			top.$.messager.alert('提示', '请选择要删除的用户.', 'error');
		}
	},
	importExcel:function(){
		
	},
	exportExcel:function(){
		
	}
}