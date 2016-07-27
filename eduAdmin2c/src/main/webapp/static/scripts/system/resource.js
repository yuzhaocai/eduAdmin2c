var treegrid;

$(function(){
	//初始化按钮列表
	grid.databind();
	
	//初始化按钮事件
	$("#reload_btn").click(grid.reload);
	$('#add_btn').click(ops.add);
	$('#edit_btn').click(ops.edit);
	$('#del_btn').click(ops.del);
	
});

//数据列表
var grid = {
	databind:function(){
		treegrid = $("#treegrid").treegrid({
			url : basePath + '/system/resource',
			title:'资源列表',
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
			              { field:'id',hidden:true},
			              { field:'parentId',hidden:true},
			              { width : fixWidth(15), title : '资源名称', field : 'name'},
			              { width : fixWidth(15), title: '图标', field: 'iconCls'},
			              { width : fixWidth(15), title: '权限代码', field : 'permission'},
			              { width : fixWidth(10), title : '资源类型', field : 'type',align:'center',
			            	  formatter:function(value,row,index){
			            		  if(value == 0){
			            			  return "<span style='color:green;'>菜单</span>";
			            		  }
			            		  return "<span style='color:red;'>按钮</span>";
			            	  }
			              }, 
			              { width : fixWidth(20), title : '资源路径', field : 'url'}, 
			              { width : fixWidth(5), title : '状态', field : 'status',align:'center',
			            	  formatter:function(value,row,index){
			            		  if(value == 0){
			            			  return "正常";
			            		  }
			            		  return "<span style='color:red;'>冻结</span>";
			            	  }
			              },
			              { width : fixWidth(5), title : '排序', field : 'priority',align:'center'},
			              { width : fixWidth(15), title : '描述', field : 'description'}
			           ] ],
			pagination:false
		});
	},
	reload: function() {
		$("#treegrid").treegrid('clearSelections').treegrid('reload');
    },
    selectRow: function() {
        return $("#treegrid").treegrid('getSelected');
    }
}

//基本操作
var ops = {
	bindCtrl: function (resourceId) {
        var treeNodes = treegrid.treegrid('getData');
        var menuNodes = getMenuNodes(deepClone(treeNodes));
        var menuData = JSON.stringify(menuNodes).replace(/name/g, 'text');
        menuData = '[{"id":0,"selected":true,"text":"请选择父级菜单"}' + (menuData.length>2?',':'') + menuData.substr(1, menuData.length - 1);
       
        top.$('#res_parent_id').combotree({
            data: JSON.parse(menuData),
            lines: true,
            filter: function(q, node){
        		return false;
        	},
            onSelect: function (item) {
                var nodeId = top.$('#res_parent_id').combotree('getValue');
                if (item.id == resourceId) {
                    top.$('#res_parent_id').combotree('setValue',nodeId);
                    alert('上级菜单不能与当前菜单相同!');
                }
            }
        }).combotree('setValue', 0);
        showIcon(); //选取图标
    },
	add:function(){
		var dialog = top.$.modalDialog({
			href : basePath + '/system/resource/create',
			title : '添加资源',
			iconCls:'icon-user_add',
			width:600,
			height:436, 
			onLoad:function(){
				ops.bindCtrl();
				var row = grid.selectRow();
                if (row){
                    top.$('#res_parent_id').combotree('setValue', row.id);
                }
			},
			closed:false,
			buttons : [ {
				text : '确定', 	
				iconCls : 'icon-ok',
				handler : function() {
					if(top.$('#resource_form').form('enableValidation').form('validate')){
						var data = top.$.serializeObject(top.$('#resource_form'));
						var url = basePath + '/system/resource/create';
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
				href : basePath + '/system/resource/update',
				title : '编辑资源',
				iconCls:'icon-pencil',
				width:600,
				height:436,  
				onLoad:function(){
					ops.bindCtrl(row.id);
					top.$('#resource_form').form('load',row);
					top.$('#res_small_icon').attr('class', "icon "+row.iconCls);;
				},
				closed:false,
				buttons : [ {
					text : '确定', 	
					iconCls : 'icon-ok',
					handler : function() {
						if(top.$('#resource_form').form('enableValidation').form('validate')){
							var data = top.$.serializeObject(top.$('#resource_form'));
							var url = basePath + '/system/resource/update';
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
			top.$.messager.alert('提示', "请选择要修改的资源.", 'error');
		}
	},
	del:function(){
		var row = grid.selectRow();
		if(row){
			top.$.messager.confirm('确认','删除父资源时会同时删除其子资源，您确定要删除吗?',function(r){
				if(r){
					var url = basePath + '/system/resource/delete';
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
			top.$.messager.alert('提示','请选择要删除的资源','error');
		}
	},
	importExcel:function(){
		
	},
	exportExcel:function(){
		
	}
}

//显示图标
var showIcon = function(){
	top.$("#res_selecticon").click(function(){
		var iconDialog = top.$.modalDialog({
            iconCls: 'icon-application_view_icons',
            href: basePath + '/static/styles/iconlist.htm?v=' + Math.random(),
            title: '选取图标', 
            width: 800, 
            height: 600,
            onLoad: function () {
                top.$('#iconlist li').attr('style', 'float:left;border:1px solid #fff;margin:2px;width:16px;cursor:pointer').click(function () {
                    var iconCls = top.$(this).find('span').attr('class').replace('icon ', '');
                    top.$('#res_icon_cls').textbox('setValue',iconCls);
                    top.$('#res_small_icon').attr('class', "icon "+iconCls);

                    iconDialog.dialog('close');
                }).hover(function () {
                    top.$(this).css({ 'border': '1px solid red' });
                }, function () {
                    top.$(this).css({ 'border': '1px solid #fff' });
                });
            }
        });
	});
}

//获得菜单节点
var getMenuNodes = function(nodes){
	var menuNodes = new Array();
	if(nodes != null && nodes.length>0){
		for(var i = 0;i<nodes.length;i++){
			var node = nodes[i];
			if(node.status == 0){
				if(node.type == 0){
					if(node.children && node.children.length>0){
						var children = node.children.slice(0); 
						node.children = getMenuNodes(children);
					}
					menuNodes.push(node);
				}
			}
		}
	}
	return menuNodes;
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
