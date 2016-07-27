var treegrid;
$(function(){
	//初始化机构列表
	grid.databind();
	
	//初始化按钮事件
	$('#reload_btn').click(grid.reload);
	$('#add_btn').click(ops.add);
	$('#edit_btn').click(ops.edit);
	$('#del_btn').click(ops.del);
});


//数据列表
var grid = {
	databind:function(){
		treegrid = $("#treegrid").treegrid({
			url : basePath + '/system/organization',
			title:'机构列表',
			iconCls:'icon-list',
			method : 'post',
			border : true,
			fit: true,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			idField : 'id',
			treeField:'name',
			parentField:'parentId',
			view:treegridView,
			fitColumns:true,
			emptyMsg: '无相关查询的记录！',
			view:treegridView,
			columns : [ [ 
			              { field:'ck',checkbox:true},
			              { width : fixWidth(15), title : '机构ID', field : 'id',hidden:true},
			              { width : fixWidth(15), title : '父机构ID', field : 'parentId',hidden:true},
			              { width : fixWidth(15), title : '机构名称', field : 'name'}, 
			              { width : fixWidth(20), title : '排序', field : 'priority'},
			              { width : fixWidth(15), title : '描述', field : 'description'},
			           ] ],
			pagination:false
		});
	},
	reload: function() {
		treegrid.treegrid('clearSelections').treegrid('reload');
    },
    selectRow: function() {
        return treegrid.treegrid('getSelected');
    }
}

//基本操作
var ops = {
	bindCtrl: function (organizationId) {
        var treeNodes = treegrid.treegrid('getData');
        var treeData = JSON.stringify(treeNodes).replace(/name/g, 'text');
        treeData = '[{"id":0,"selected":true,"text":"请选择父级机构"}' + (treeData.length>2?',':'') + treeData.substr(1, treeData.length - 1);
       
        top.$('#org_parent_id').combotree({
            data: JSON.parse(treeData),
            lines: true,
            filter: function(q, node){
        		return false;
        	},
            onSelect: function (item) {
                var nodeId = top.$('#org_parent_id').combotree('getValue');
                if (item.id == organizationId) {
                    top.$('#org_parent_id').combotree('setValue',nodeId);
                    alert('父级机构不能与当前机构相同!');
                }
            }
        }).combotree('setValue', 0);
    },
	add:function(){
		var dialog = top.$.modalDialog({
			href : basePath + '/system/organization/create',
			title : '添加机构',
			iconCls:'icon-chart_organisation_add',
			width:520,
			height:327, 
			onLoad:function(){
				ops.bindCtrl();
				var row = grid.selectRow();
                if (row){
                    top.$('#org_parent_id').combotree('setValue', row.id);
                }
			},
			closed:false,
			buttons : [ {
				text : '确定', 	
				iconCls : 'icon-ok',
				handler : function() {
					if(top.$('#organization_form').form('enableValidation').form('validate')){
						var data = top.$.serializeObject(top.$('#organization_form'));
						var url = basePath + '/system/organization/create';
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
				href : basePath + '/system/organization/update',
				title : '编辑机构',
				iconCls:'icon-pencil',
				width:520,
				height:327, 
				onLoad:function(){
					ops.bindCtrl(row.id);
					top.$('#organization_form').form('load',row);
				},
				closed:false,
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						if(top.$('#organization_form').form('enableValidation').form('validate')){
							var data = top.$.serializeObject(top.$('#organization_form'));
							var url = basePath + '/system/organization/update';
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
			top.$.messager.alert('提示', "请选择要修改的机构.", 'error');
		}
	},
	del:function(){
		var row = grid.selectRow();
		if(row){
			top.$.messager.confirm('确认','删除父机构时会同时删除其子机构，您确定要删除吗?',function(r){
				if(r){
					var url = basePath + '/system/organization/delete';
					var data = {"ids":getChildrenIds(row.id).join(",")};
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
			top.$.messager.alert('提示','请选择要删除的机构','error');
		}
	},
	importExcel:function(){
		
	},
	exportExcel:function(){
		
	}
}

//获得选中节点的子节点(包括自己)
var getChildrenIds = function(pid){
	var chilrenIds = new Array();
	var childrens = treegrid.treegrid('getChildren',pid);
	for(var i = 0;i<childrens.length;i++){
		chilrenIds.push(childrens[i].id);
	}
	chilrenIds.push(pid);
	return chilrenIds;
}