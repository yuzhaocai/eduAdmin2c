$(function(){
	//初始化列表
	grid.databind();
	
	//初始化按钮事件
	$('#reload_btn').click(search.query);
	$('#add_btn').click(ops.add);
	$('#edit_btn').click(ops.edit);
	$('#del_btn').click(ops.del);
	$('#assign_resource_btn').click(ops.assignResource);
	
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
			url : basePath + '/system/role',
			title:'角色列表',
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
			sortName:'createTime',
			sortOrder:'asc',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			emptyMsg: '无相关查询的记录！',
			view:datagridView,
			columns : [ [ 
			              { field:'ck',checkbox:true},
			              { width : fixWidth(15), title : '角色ID', field : 'id',hidden:true},
			              { width : fixWidth(30), title : '角色名称', field : 'name'}, 
			              { width : fixWidth(70), title : '角色描述', field : 'description'},
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
	initdata:function(){
		
	},
	add:function(){
		var dialog = top.$.modalDialog({
			href : basePath + '/system/role/create',
			title : '添加角色',
			iconCls:'icon-add',
			width:520,
			height:327, 
			onLoad:function(){
				
			},
			closed:false,
			buttons : [ {
				text : '确定', 	
				iconCls : 'icon-ok',
				handler : function() {
					if(top.$('#role_form').form('enableValidation').form('validate')){
						var data = top.$.serializeObject(top.$('#role_form'));
						var url = basePath + '/system/role/create';
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
				href : basePath + '/system/role/update',
				title : '编辑角色',
				iconCls:'icon-pencil',
				width:520,
				height:327, 
				onLoad:function(){
					top.$('#role_form').form('load',row);
				},
				closed:false,
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						if(top.$('#role_form').form('enableValidation').form('validate')){
							var data = top.$.serializeObject(top.$('#role_form'));
							var url = basePath + '/system/role/update';
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
			top.$.messager.alert('提示', "请选择要修改的角色.", 'error');
		}
	},
	del:function(){
		var row = grid.selectRow();
		if(row){
			top.$.messager.confirm('确认','您确认要删除吗？',function(r){
				if(r){
					var url = basePath + '/system/role/delete';
					var data = {"id":row.id};
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
			top.$.messager.alert('提示', '请选择要删除的角色.', 'error');
		}
	},
	assignResource:function(){
		var tree;
		var row = grid.selectRow();
		if(row){
			var dialog = top.$.modalDialog({
				href : basePath + '/system/role/assign/resource',
				title : '分配资源',
				iconCls:'icon-key',
				width:450,
				height:480, 
				onLoad:function(){
					top.$('#role_name').text(row.name);
					tree = top.$('#resource_tree').tree({
						url:basePath + '/system/role/' + row.id +'/resources/tree',
						checkbox:true
					});
				},
				closed:false,
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						var resourceIds = new Array();
						var nodes = tree.tree("getChecked");
						if(nodes != null && nodes.length>0){
							for(var i = 0;i<nodes.length;i++){
								var n = nodes[i];
								if($.inArray(n.id,resourceIds) == -1){
									resourceIds.push(n.id);
								}
								do {
									n = tree.tree("getParent",n.target);
									if(n != null && $.inArray(n.id,resourceIds) == -1){
										resourceIds.push(n.id);
									}
								} while(n != null && tree.tree("getParent",n.target) != null)
							}
						}
						var url = basePath + '/system/role/assign/resource';
						var data = {'id':row.id,'resourceIds':resourceIds.join(',')};
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
				},{
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						dialog.dialog('destroy');
					}
				}]
			});
		
		} else {
			top.$.messager.alert('提示','请选择要分配资源的角色.','error');
		}
	},
	importExcel:function(){
		
	},
	exportExcel:function(){
		
	}
}
