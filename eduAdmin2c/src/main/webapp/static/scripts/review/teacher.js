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
			url : basePath + '/review/teacher',
			title:'认证老师列表',
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
			              { width : fixWidth(10), title : '老师ID', field : 'uid'},
			              { width : fixWidth(20), title : '老师名称', field : 'realName'},
			              { width : fixWidth(20), title : '最后申请时间', field : 'applyLastDate',
			            	  formatter:function(value,row,index){
			            		  if(value){
			            			  return new Date(parseInt(value)).format("yyyy-MM-dd HH:mm");
			            		  }
			            		  return "";
			            	  }},
			              { width : fixWidth(10), title : '申请次数', field : 'applyTimes'}, 
			              { width : fixWidth(20), title : '状态', field : 'status',
			            	  formatter: function(value,row,index){
			            		  var status = "";
			            		  switch(value){
			            		  	case 0:
			            		  		status = "<span>待审核</span>";
			            		  		break;
			            		  	case 1:
			            		  		status = "<span>待审核</span>";
			            		  		break;
			            		  	case 20:
			            		  		status = "<span>审核通过</span>";
			            		  		break;
			            		  	case 21:
			            		  		status = "<span>审核通过</span>";
			            		  		break;
			            		  	case 30:
			            		  		status = "<span>审核未通过</span>";
			            		  		break;
			            		  }
			            		  return status;
			            	  }},
			              { width : fixWidth(20), title : '操作', field : 'action',
			            	  formatter: function(value,row,index){
		            			  return '<a href="javascript:ops.detail('+ row.uid +');">详情</a>';
			            	  }
			              }
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
	detail:function(uid){
		alert("xx");
	},
	importExcel:function(){
		
	},
	exportExcel:function(){
		
	}
}